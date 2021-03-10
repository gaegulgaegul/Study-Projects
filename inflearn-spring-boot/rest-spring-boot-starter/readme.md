### 스프링 REST 클라이언트

- RestTemplate
    - Blocking I/O 기반의 Synchronous API
    - spring-web 모듈이 있다면 RestTemplateBuilder를 빈으로 등록
    - [RestTemplate](./src/main/java/me/whiteship/restspringbootstarter/RestRunner.java) 사용
- WebClient
    - Non-Blocking I/O 기반의 Asynchronous API
    - spring-webflux 모듈이 있다면 WebCient.Builder를 빈으로 등록
    - [WebClient](./src/main/java/me/whiteship/restspringbootstarter/WebFluxRunner.java) 사용