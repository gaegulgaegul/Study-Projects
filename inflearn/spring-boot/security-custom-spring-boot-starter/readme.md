### Spring Boot Security 커스터마이징

- [WebSecurityConfigurerAdapter](./src/main/java/me/whiteship/securitycustomspringbootstarter/config/SecurityConfig.java) 설정
- [UserDetailsService](./src/main/java/me/whiteship/securitycustomspringbootstarter/account/AccountService.java) 설정
    - User를 관리하는 서비스에서 상속받아 구현하거나 따로 상속받은 클래스를 구현해서 사용한다.
    - 빈으로 등록되어야 더 이상 spring boot에서 user를 만들어주지 않는다.
    - loadUserByUsername
        - username을 입력받아 비밀번호를 확인한다.
            - 일치하면 로그인 처리
            - 다르면 Exception 발생
- PasswordEncoder 설정
    - spring에서 권장하는 방법
        - ```PasswordEncoderFactories.createDelegatingPasswordEncoder()```
    - 사용 부분
        - ```account.setPassword(passwordEncoder.encode(password));```