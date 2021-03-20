### Spring Data JPA 활용

- Repository
    - spring data common
        - PagingAndSortRepository > CrudRepository > Repository
    - spring data jpa
        - JpaRepository
    - [Test](./src/test/java/me/whiteship/jpaspringdata/PostRepositoryTest.java)
        - spring data 테스트를 작성할 때 Repository에서 @Transactional이 있어서 기본적으로 롤백된다. 따라서 데이터를 변경하는 쿼리가 생성되지 않는 경우가 생긴다.
        - 하지만 jpa(또는 hibernate)에서 자동적으로 필요한 정보만 판단하여 DB에 쿼리를 전달하기 때문에 테스트는 성공한다.
    - [Custom Query Method](./src/main/java/me/whiteship/jpaspringdata/PostRepository.java)