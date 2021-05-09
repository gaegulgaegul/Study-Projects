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
        - CSRF
            - spring security에서 공격을 막지만 thymeleaf의 form 전송의 경우 자동으로 _csrf 토큰을 생성해 전송하고 해당 토큰 값을 검증한다.
    - 폼 검증
        - [JSR 303 애노테이션 검증](./src/main/java/com/studyolle/account/SignUpForm.java)
            - 값의 길이, 필수값
        - [커스텀 검증](./src/main/java/com/studyolle/account/SignUpFormValidator.java)
            - 중복 이메일, 닉네임 여부 확인
        - 폼 에러 있을 시, 폼 다시 보여주기
    - [회원가입 처리](./src/main/java/com/studyolle/account/AccountService.java)
        - 회원 정보 저장
        - 인증 이메일 발송
        - 처리 후 첫 페이지로 리다이렉트(Post-Redirect-Get 패턴)
    - 패스워드 인코더
        - 해시 알고리즘
            - 개인정보를 암호화 하여 평문으로 보이지 않도록 한다.
        - 솔트(salt)
            - 평문 개인정보에 솔트 값을 더하여 암호화 한다.
            - 솔트 값은 매번 랜덤한 값이 나온다. 인코딩할 때 솔트 값을 추가하게 된다.
            - 해시 알고리즘 특정 상 (평문 개인정보 + 해시된 개인정보)를 다시 해싱하면 원래 해시값이 나오게 된다. 솔트 값이 랜덤해도 상관없다.
        - 스프링 시큐리티 권장 PasswordEncoder
            - `PasswordEncoderFactories.createDelegatingPasswordEncoder()`
            - 여러 해시 알고리즘을 지원하는 패스워드 인코더
            - bcrypt는 다른 해시 알고리즘에 비해 의도적으로 해시되는 속도가 느리다. 해커들이 여러번 시도할 수 없도록 하기 위해
    - 인증 메일 확인
        - `GET /check-email-token token=${token} email=${email} 요청 처리`
          - 이메일이 정확하지 않은 경우에 대한 에러 처리
          - 토큰이 정확하지 않은 경우에 대한 에러 처리
          - 이메일과 토큰이 정확한 경우 가입 완료 처리
              - 가입일시 설정
              - 이메일 인증 여부 true로 설정
    - 자동 로그인
        - 회원가입 후 바로 로그인 되도록 한다.
        - `UsernamePasswordAuthenticationToken`을 [사용](./src/main/java/com/studyolle/account/AccountService.java)한다.
            - AuthenticationManager 내부에서 사용하도록 만든 클래스로 원래는 다른 방식으로 사용해야 한다.
            - 정석대로 사용하는 방법
            ```java
              UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(nickname, password); // 토큰 조회
              Authentication authentication = authenticationManager.authenticate(token); // 역할 부여
              SecurityContextHolder.getContext().setAuthentication(authentication); // authentication 설정
            ```