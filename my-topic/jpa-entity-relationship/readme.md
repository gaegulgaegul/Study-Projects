### JPA Entity 연관관계

- [백기선님 JPA 관련 유튜브](https://www.youtube.com/watch?v=brE0tYOV9jQ&list=PLfI752FpVCS-SJkgvMQ_CCgBa9XN4A5kl&index=37)
- 문제점
    - `BookStore <-> Book` : 양방향 관계
    - BookStore에 저장된 id가 Book의 FK에 저장되지 않는다.
        - [코드](./src/main/java/me/gaegul/jpaentityrelationship/BookRunner.java)
- 해결
    - 양방향 관계를 맵핑해주는 과정에서 Book에 BookStore를 setting 하지 않았다.
        - [BookStore.java](./src/main/java/me/gaegul/jpaentityrelationship/model/BookStoreBefore.java)
        ```java
            @OneToMany(mappedBy = "bookStore")
            private Set<Book> books = new HashSet<>();
          
            public void addBook(Book book) {
                this.getBooks().add(book);
            }
        ```
    - Book에 BookStore setting 추가
        - [BookStore.java](./src/main/java/me/gaegul/jpaentityrelationship/model/BookStore.java)
        ```java
            @OneToMany(mappedBy = "bookStore")
            private Set<Book> books = new HashSet<>();
          
            public void addBook(Book book) {
                book.setBookStore(this); // 추가
                this.getBooks().add(book);
            }
        ```