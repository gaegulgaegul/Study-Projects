### RDB와 Java

- [jdbc를 통해 연결](./src/main/java/me/whiteship/Application.java)
    - docker(postgresql)
        - `docker run -p 5432:5432 -e POSTGRES_PASSWORD=pass -e POSTGRES_USER=keesun -e POSTGRES_DB=springdata --name postgres_boot -d postgres:9.6.2`
    - DataSource / DriverManager
    - Connection
    - PreparedStatement