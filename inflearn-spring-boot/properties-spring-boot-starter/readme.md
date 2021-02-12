### 프로퍼티 설정

- application.properties
    - 프로퍼티 클래스로 사용(Type Safe)
        - `@Component` : 빈 등록
        - `@ConfigurationProperties("keesun")` : 여러 프로퍼티를 prefix별로 묶는다.
        - Relexed bind: case(camel, underscore 등) 상관없이 데이터 바인딩      
- profile 별 properties 사용
    - spring.profiles.active 설정
    - `@PropertySource(value = {"classpath:/config-${spring.profiles.active:default}.properties"})` : 프로퍼티 파일 경로 설정(기본값: default)
- `application.yml`도 사용 가능