# Spring JPA 기반 웹 애플리케이션 개발

> 이 프로젝트는 인프런 백기선님의 강의를 듣고 정리하기 위한 프로젝트 입니다.

- 프로젝트 생성
    - [dependency](pom.xml) 추가
- [Account](./src/main/java/com/studyolle/domain/Account.java) 도메인 클래스
    - @EqualsAndHashCode
        - 연관관계가 복잡해질 때 `@EqualsAndHashCode`에서 순환참조 하면서 무한루프가 발생하여 StackOverFlow가 발생할 수 있다.
        - Id만 등록하고 사용한다.
        - 자세한 설명은 Rest API 강의에서 확인
    - @Lob
        - VARCHAR(255)보다 큰 값이 들어오는 경우 설정
    - @Basic(fetch = FetchType.EAGER)
        - 컬럼의 기본 타입을 매핑해준다.
        - 기본 Lazy
- 회원가입
    - GET "/sign-up" 요청을 받아서 account/sign-up.html 페이지를 보여준다.
    - [AccountController](./src/main/java/com/studyolle/account/AccountController.java)
        - [model](./src/main/java/com/studyolle/account/SignUpForm.java) 정보 전달(닉네임, 이메일, 패스워드)
    - [AccountControllerTest](./src/test/java/com/studyolle/account/AccountControllerTest.java)
        - `@AutoConfigureMockMvc`: MockMvc 자동 설정 추가
        - view, model 테스트
    - [Spring Security 설정](./src/main/java/com/studyolle/config/SecurityConfig.java)
        - 허용할 화면 설정
        - static resources 설정