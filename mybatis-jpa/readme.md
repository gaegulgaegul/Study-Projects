### Spring Data JPA & Mybatis Framework 연동

- 회사 솔루션에 적용 중 Transaction에 대한 문제가 발생
    - jpa & mybatis를 적용하는 과정에서 DataSource를 따로 적용한게 문제가 됨
    - DataSource 하나에 jpa & mybatis 모두 연결
    - [DatabaseConfig](./src/main/java/me/gaegul/mybatisjpa/config/DatabaseConfig.java) 설정 확인
    - @Transactional 또는 AOP Transaction 처리 시 `saveAll()` 다음 바로 `flush()`해야 반영된 데이터를 mybatis를 통해 확인 할 수 있다.
        - [transactional & flush 테스트](./src/test/java/me/gaegul/mybatisjpa/user/service/UserServiceTest.java)
        - [AOP transaction 테스트](./src/test/java/me/gaegul/mybatisjpa/user/service/UserDetailServiceTest.java)