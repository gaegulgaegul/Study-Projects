### JPA Programming

- 프로젝트 세팅
    - spring-boot-starter-data-jpa 추가
        - JPA v2.X
        - Hibernate V5.X
    - HibernateJpaAutoConfiguration(자동설정)
        - 컨테이너가 관리하는 EntityManager(프록시) 빈 설정
            - JPA에 핵심이 되는 클래스
            - DB에 영속화 할 수 있다.(DB에 데이터를 저장할 수 있다.)
        - PlatformTransactionManager 빈 설정
    - application.properties
        - spring.jpa.hibernate.ddl-auto
            - create: 애플리케이션 구동 시 마다 기존 테이블을 삭제하고 DDL 생성(개발할 때만 유용)
            - validate: 객체에 있는 정보와 테이블의 정보가 일치하는지 검증한다.(운영 시 유용)
            - update: 애플리케이션 구동 시 마다 Entity의 변경사항만 테이블에 추가한다.
        - spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
            - PostgreSQL Driver 추가 시 `createClob()`을 구현하지 않아 생기는 에러
            - 상위 버전에는 해결 됨.
    - [JPA EntityManager 사용](./src/main/java/me/whiteship/jpaspringboot/JpaRunner.java)
    - [Hibernate Session 사용](./src/main/java/me/whiteship/jpaspringboot/HibernateRunner.java)
- Entity Mapping
    - @Temporal
        - 날짜를 맵핑해준다.
        - 자바8 이전에 생성되어 JPA 2.2이상부터 자바8에 추가된 날짜 클래스에 맵핑 할 수 있다.
        - TemporalType(TIMESTAMP, DATE, TIME)
    - @Transient
        - 테이블에 컬럼으로 추가하고 싶지 않고 Entity에서만 사용할 객체를 정의한다.