### Spring Data JPA 활용

- Repository
    - spring data common
        - PagingAndSortingRepository > CrudRepository > Repository
        - Repository 인터페이스를 빈으로 등록하지 못하도록 `@NoRepositoryBean`을 사용한다.
    - spring data jpa
        - JpaRepository
            - PagingAndSortingRepository(spring data common)를 상속받는다.
    - [Test](./src/test/java/me/whiteship/jpaspringdata/PostRepositoryTest.java)
        - spring data 테스트를 작성할 때 Repository에서 @Transactional이 있어서 기본적으로 롤백된다. 따라서 데이터를 변경하는 쿼리가 생성되지 않는 경우가 생긴다.
        - 하지만 jpa(또는 hibernate)에서 자동적으로 필요한 정보만 판단하여 DB에 쿼리를 전달하기 때문에 테스트는 성공한다.
    - [Custom Query Method](./src/main/java/me/whiteship/jpaspringdata/PostRepository.java)
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