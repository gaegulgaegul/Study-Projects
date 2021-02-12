### LOGGING

- 로깅 퍼사드 VS 로거
    - 로깅 퍼사드 : 로그 API를 추상화 한 인터페이스(Commons Logging, SLF4j)
    - 로거 : 로깅 퍼사드 구현체(JUL, Log4J2, Logback)
    - `spring-boot-starter-logging`
        - `jul`, `log4j`는 라이브러리(`jul-to-slf4j`, `log4j-to-slf4j`)를 통해 slf4j로 전달하게 된다.
        - `slf4j`는 Logback(slf4j 구현체)으로 전달한다.
        - 기본 `Logback`을 사용한다.