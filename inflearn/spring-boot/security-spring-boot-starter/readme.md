### Spring Boot Security

- 모든 요청이 다 스프링 시큐리티로 인해 인증을 필요로 하게 된다.
- Authentication과 폼 인증이 지원된다.
- login 폼 페이지를 자동 지원한다.
    - username: user
    - password: application 구동 시 로그에 나온다.
- spring boot security 자동 설정
    - SecurityAutoConfiguration
        - EventPublisher를 통해 여러 가지 상황(비밀번호가 없거나 계정이 없는 경우 등)에 대해 이벤트를 발생시킨다.
    - UserDetailsServiceAutoConfiguration
        - In Memory User를 만들어준다.
        - AuthenticationManager, AuthenticationProvider, UserDetailsService 중 하나라도 빈으로 등록하지 않은 경우 자동으로 설정해준다.
