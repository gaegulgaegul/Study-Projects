### Spring Data JPA 활용

- Repository
    - spring data common
        - PagingAndSortingRepository > CrudRepository > Repository
        - Repository 인터페이스를 빈으로 등록하지 못하도록 `@NoRepositoryBean`을 사용한다.
    - spring data jpa
        - JpaRepository
            - PagingAndSortingRepository(spring data common)를 상속받는다.
    - [Test](jpa-spring-data/src/test/java/me/whiteship/jpaspringdata/PostRepositoryTest.java)
        - spring data 테스트를 작성할 때 Repository에서 @Transactional이 있어서 기본적으로 롤백된다. 따라서 데이터를 변경하는 쿼리가 생성되지 않는 경우가 생긴다.
        - 하지만 jpa(또는 hibernate)에서 자동적으로 필요한 정보만 판단하여 DB에 쿼리를 전달하기 때문에 테스트는 성공한다.
    - [Custom Query Method](jpa-spring-data/src/main/java/me/whiteship/jpaspringdata/PostRepository.java)
        - [Spring Data Jpa - Query Method Docs](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods)
- Repository interface 정의하기
    - `@RepositoryDefinition`
        - 특정 Repository당 정의
        - 제공하고 싶은 기능들만 정의할 수 있다는 장점, 제공하는 메소드가 한눈에 보인다.
        - 구현한 메소드는 모두 테스트 케이스 작성
        ```java
          @RepositoryDefinition(domainClass = Comment.class, idClass = Long.class)
          public interface CommentRepository {
          
              Comment save(Comment comment);
          
              List<Comment> findAll();
          
          }
        ```
    - `@NoRepositoryBean`
        - 공통 인터페이스로 정의
        ```java
          @NoRepositoryBean
          public interface MyRepository<T, Id extends Serializable> extends Repository<T, Id> {
          
              <E extends T> E save(E entity);
          
              List<T> findAll();
          
          }
        ```
- Null 처리
    - 단일 값은 null이 나올 수 있다.
    - List는 null이 나오지 않는다.(빈 Collection이 나온다.)
        - JPA에서 제공하는 기능
    - null 관련 annotation
        - 런타임 체크 지원
        - `@NonNull`: null이면 안될 경우 사용
        - `@Nullable`: null일 수도 있다.
        - `@NonNullApi, @NonNullField`: 패키지 단위에 붙여 null 체크한다.
- 쿼리 만들기
    - CREATE(Spring data JPA)
        - 메서드 이름을 분석해서 쿼리를 만들어준다.
    - USE_DECLARED_QUERY
        - 미리 정의해 둔 쿼리 찾아 사용
        - 메서드에 붙어있는 부가적인 정보를 바탕으로 찾아서 실행한다.
            - @Query -> (기본값: JPQL)
    - CREATE_IF_NOT_FOUND(기본값)
        - 미리 정의한 쿼리 찾아보고 없으면 만들기
    - `ContainsIgnoreCase`: 대소문자 구분 없이 비교
    - `Stream`으로 리턴 받을 떄, `try-with-resource`로 받아 사용해야 한다.
        - Stream을 사용하고 close해야 한다.
- Custom Repository
    - 쿼리 메소드(쿼리 생성 과 쿼리 찾기)로 해결되지 않는 경우 직접 코딩으로 구현
        - 인터페이스에 기능 추가
        - 기본 기능 덮어쓰기 가능
        - 구현 방법
            - 커스텀 리포지토리 인터페이스 정의
            - 인터페이스 구현 클래스 만들기(기본 접미어는 Impl)
                - 접미어 설정
                    - `@EnableJpaRepositories`에 설정
                        - `repositoryImplementationPostfix`
            - 엔티티 리포지토리에 커스텀 리포지토리 인터페이스 추가
    - 모든 리포지토리에 공통적으로 추가하고 싶은 기능이 있거나 덮어쓰고 싶은 기본 기능이 있을 때
        - `JpaRepository`를 상속받는 인터페이스 정의
            - `@NoRepositoryBean` 반드시 추가
        - 기본 구현체를 상속받는 커스텀 구현체 구현
        - `@EnableJpaRepositories`에 설정
            - `repositoryBaseClass`
- Domain Event Publisher
    - 도메인(Entity)의 변화를 이벤트로 발생, 이때 리스너를 실행 할 수 있도록 한다.
    - [EventPublisher](./jpa-second-application/src/main/java/me/whiteship/jpasecondapplication/post/PostPublishedEvent.java)
    - [EventListener](./jpa-second-application/src/main/java/me/whiteship/jpasecondapplication/post/PostListener.java)
    - ApplicationContext를 통해 Event 등록
        ```java
          Post post = new Post();
          post.setTitle("event");
          PostPublishedEvent event = new PostPublishedEvent(post);
          applicationContext.publishEvent(event);
        ```
    - AbstractAggregateRoot를 도메인 클래스에서 상속받아 사용 [코드](./jpa-second-application/src/main/java/me/whiteship/jpasecondapplication/post/Post.java)
        - 현재는 save()할 때만 발생
        ```java
          Post post = new Post();
          post.setTitle("spring");
          postRepository.save(post.publish());
        ```
- QueryDSL
    - 조건문을 자바로 구현이 가능하고 타입 세이프하다.
    - 쿼리 메소드를 대부분 두 가지 중 하나
        - findOne(Predicate): 다양한 조건으로 무언가 하나를 찾는다.
        - findAll(Predicate): 다양한 조건으로 무언가 여러 개를 찾는다.
    - spring data jpa + Querydsl
        - 인터페이스: QuerydslPredicateExecutor<T>
        - 구현체: QuerydslPredicateExecutor<T>
    - [의존성 추가](./querydsl-application/pom.xml)
    - 커스텀 리포지토리를 사용하면서 Querydsl을 사용하는 경우 구현체가 없어서 에러가 발생한다.(하위 버전)
        - `QuerydslJpaRepository`를 커스텀 리포지토리에 상속받아 사용한다.
- 웹 지원 기능
    - 설정
        - 스프링 부트를 사용하는 경우 자동 설정
        - 스프링 부트를 사용하지 않는 경우 `@Configuration`의 클래스에 `@EnableSpringDataWebSupport` 추가
    - 도메인 클래스 컨버터
        - `@PathVariable, @RequestParam`으로 들어오는 도메인의 Id값을 바로 도메인으로 변환하여 파라미터로 받는다.
    - @RequestHandler, Pageable, Sort 매개변수 사용
    - Page 관련 HATEOAS 기능 제공
        - PagedResourcesAssembler
        - PagedResource
    - Payload 프로젝션
        - 요청으로 들어오는 데이터 중 일부만 바인딩 받아오기
        - `@ProjectPayload, @XBRead, @JsonPath`
    - 요청 쿼리 매개변수를 QueryDSL DML Predicate로 받아오기
        - ?firstname=Mr&lastname=White => Predicate
- DomainClassConverter
    - converter: 하나의 타입을 다른 타입으로 변환하는 인터페이스
    - 자동으로 converter registry에 들어가게 된다.
    - 들어가게 되면 spring mvc에서 어떠한 데이터를 바인딩 받아 컨버팅할 때 참고해서 사용한다.
    - ToEntityConverter <-> ToIdConverter
    - `ToEntityConverter`: repository를 사용해서 `id`로 `findById`를 한다.
- Pageable & Sort
    - Spring MVC HandlerMethodArgumentResolver
        - 스프링 MVC 핸들러 메소드의 매개변수로 받을 수 있는 객체를 확장하고 싶을 때 사용
    - 페이징과 정렬 관련 변수
        - page: 0부터 시작 
        - size: 기본값 20
        - sort: property,(DESC|ASC)
- HATETOS
    - Page를 PageResource로 변환하기
        - HATETOS 의존성 추가
        - 핸들러 매개변수로 PagedResourcesAssembler
- JpaRepository
    - Spring @Repository
        - SQLException 또는 JPA 관련 예외를 spring의 DataAccessException으로 변환해준다.
- Entity save
    - JpaRepository.save()
        - Transient 상태: `EntityManager.persist()`
        - Detached 상태: `EntityManager.merge()`
    - Transient인지 Detached인지 판단하는 기준
        - Entity의 @Id 프로퍼티를 찾는다. 해당 프로퍼티가 null이면 Transient 상태로 판단하고 null이 아니면 Detached 상태로 판단한다.
        - Entity가 Persistable 인터페이스를 구현하고 있다면 `isNew()` 메소드에 위임한다.
        - JpaRepositoryFactory를 상속받는 클래스를 만들고 getEntityInformation()을 오버라이딩해서 자신이 원하는 판단 로직을 구현할 수 있다.
    - EntityManager.persist()
        - persist 메소드에 넘긴 객체를 영속화하고 저장 후 그대로 반환한다.
        - 저장될 객체와 저장된 객체가 같은(주소값이 같은) 객체
    - EntityManager.merge()
        - merge 메소드에 넘긴 객체를 복사하여 복사한 객체를 영속화하고 반환한다.
        - 저장될 객체와 저장된 객체가 다른(주소값이 다른) 객체
    - Entity의 저장 시 persist인지 merge인지 판단하기 힘들다. 반드시 반환되는 객체를 사용해야 한다.
        - 저장할 Entity를 수정하면 dirty checking 등 이슈가 일어날 수 있다.
- Update
    ```java
      @Modifying(clearAutomatically = true, flushAutomatically = true)
      @Query("UPDATE Post p SET p.title = ?1 WHERE p.id = ?2")
      int updateTitle(String title, Long id);
    ```
    - @Modifying
        - update 또는 delete 쿼리라는 것을 알려준다.
        - `clearAutomatically`: 쿼리 실행 후 persistent context를 비워야 find 실행 시 새로 읽어온다.
        - `flushAutomatically`: 쿼리 실행 전에 그 동안 persistent context에 담긴 캐시를 flush 해준다.
- EntityGraph
    - 쿼리 메소드 마다 연관 관계의 Fetch 모드를 설정할 수 있다.
        - @OneToMany : 기본 LAZY
        - @ManyToOne : 기본 EAGER
    - @NamedEntityGraph
        - @Entity에서 재사용할 여러 엔티티의 그룹을 정의한다.
    - @EntityGraph
        - @NamedEntityGraph에 정의되어 있는 엔티티 그룹을 사용한다.
        - 그래프 타입 설정 기능
            - FETCH(기본값) : 설정한 엔티티 애트리뷰트는 EAGER 패치 나머지는 LAZY 패치
            - LOAD: 설정한 엔티티 애트리뷰트는 EAGER 패치 나머지는 기본 패치 전략을 따른다.