### Spring Data

- 인메모리 데이터베이스
    - 종류
        - h2
        - HSQL
        - Derby
    - Spring JDBC가 클래스 패스에 있으면 자동 설정이 필요한 빈을 설정해준다.
        - DataSource
        - JdbcTemplate
    - H2
        - datasource 설정이 없다면 자동으로 h2 연결된다.
        - H2-console
            - spring-boot-devtools를 의존성 추가해 자동 설정
            - [application.properties 추가](./h2-spring-boot-starter/src/main/resources/application.properties)
                - 접근방법: 어플리케이션 실행 후 http://localhost:8080/h2-console 접속