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
    - [TEST CODE createUser_JSON()](./src/test/java/me/whiteship/webmvcspringbootstarter/user/UserControllerTest.java) 참고
- ViewResolver
    - Accept-Header에 따라 view 응답이 달라진다.
    - [TEST CODE createUser_XML()](./src/test/java/me/whiteship/webmvcspringbootstarter/user/UserControllerTest.java) 참고