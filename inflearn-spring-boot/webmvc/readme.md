### Spring Web MVC

- intro
    - 기능 추가 : @Configuration + WebMvcConfigurer
    - 기능 재정의 : @Configuration + @EnableWebMvc
    - @WebMvcTest를 사용하면 자동으로 MockMvc를 빈으로 등록해준다.
- HttpMessageConverters
    - HTTP 요청 본문을 객체로 변환하거나, 객체를 HTTP 응답 본문으로 변경할 때 사용한다.
    - @ResponseBody
        - 리턴 형식 본문을 설정해준다.
        - @RestController는 생략할 수 있다.
        - @Controller는 기본적으로 해당 view를 찾기 때문에 @ResponseBody 선언
    - @RequestBody
        - 요청 본문의 데이터를 convert 해준다.
        - spring converter format에 맞도록 getter/setter 생성 
    - [TEST CODE createUser_JSON()](./webmvc-spring-boot-starter/src/test/java/me/whiteship/webmvcspringbootstarter/user/UserControllerTest.java) 참고
- ViewResolver
    - Accept-Header에 따라 view 응답이 달라진다.
    - [TEST CODE createUser_XML()](./webmvc-spring-boot-starter/src/test/java/me/whiteship/webmvcspringbootstarter/user/UserControllerTest.java) 참고
- 정적 리소스 지원
    - 정적 리소스 맵핑 /**(root)
    - 기본 리소스 위치
        - classpath:/static
        - classpath:/public
        - classpath:/resources
        - classpath:/META-INF/resources
    - 커스터마이징
        - application.properties 설정
            - spring.mvc.static-path-pattern: 맵핑 설정 변경
            - spring.mvc.static-locations: 리소스 위치 설정 변경
        - WebMvcConfigurer 설정
            - WebMvcConfigurer를 상속받아 addResourceHandlers()를 통해 설정
            - `addResourceLocations()`의 classpath는 마지막에 "/"를 반드시 붙여야 한다.(맵핑이 제대로 안될 수 있다.)
    - Last-Modified를 헤더에서 확인하고 `304` 코드 반환
- webjars
    - 정적 리소스 파일(js, css)를 jar 형식으로 읽어 사용할 수 있다.
    - webjars-locator-core: webjars의 버전관리를 하지 않아도록 도와준다.
- welcome page
    - "/" 기본 페이지
    - 기본 리소스 위치에 index.html 파일생성
- favicon
    - [favicon](https://favicon.io) 생성
    - 기본 리소스 위치에 파일을 넣는다.
- Thymeleaf
    - 탬플릿 엔진
        - 주로 view를 만드는데 사용하고 code generation, email template 등을 사용한다.
    - JSP를 권장하지 않는 이유
        - JAR 패키징 할 떄는 동작하지 않고, WAR 패키징을 해야 함.
        - Undertow는 JSP를 지원하지 않는다.
- HtmlUnit
    - Html 템플릿 뷰를 보다 전문적으로 테스트한다.
    - html을 단위 테스트 하기 위한 툴
    - [HtmlUnit 테스트 참고](./thymeleaf-spring-boot-starter/src/test/me/whiteship/thymeleafspringbootstarter/sample/SampleControllerTest.java)