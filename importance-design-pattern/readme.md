### 디자인 패턴의 중요성

- 들어가기 전..
    - 회사에서 배운 내용 정리
    - Java JDBC를 사용하면서 디자인 패턴을 적용하는 과정을 보여준다.
    - Spring Data JDBC의 DataSource를 의존받아 코드 구현
    - H2 DB 사용
    - 간단한 구현을 위해 `select`만 구현(테이블 생성 및 데이터 입력은 Spring Data 이용)
    
- [anti-project](./anti-project/src/main/java/me/gaegule/antiproject/account/AccountDao.java)
    - 코드 중복 및 유지보수성 어려움을 나타낸다.
    - 소스를 보면 쿼리와 파라미터 전달 방식만 변경되며 나머지 코드는 중복된다.
    ```java
      public List<Account> selectAll() {
          Connection conn = null;
          PreparedStatement psmt = null;
          ResultSet rs = null;
          List<Account> accounts = new ArrayList<>();
          final String sql = "SELECT ID, USERNAME, AGE FROM ACCOUNT";
    
          try {
              conn = getConnection();
              psmt = conn.prepareStatement(sql);
              rs = psmt.executeQuery();
    
              while (rs.next()) {
                  accounts.add(new Account(rs.getLong("id"), rs.getString("username"), rs.getInt("age")));
              }
          } catch (SQLException e) {
              e.printStackTrace();
              System.out.println("Error");
          } finally {
              if (rs != null) {
                  try {
                      rs.close();
                  } catch (SQLException e) {
                      e.printStackTrace();
                  }
              }
              if (psmt != null) {
                  try {
                      psmt.close();
                  } catch (SQLException throwables) {
                      throwables.printStackTrace();
                  }
              }
              if (conn != null) {
                  try {
                      conn.close();
                  } catch (SQLException throwables) {
                      throwables.printStackTrace();
                  }
              }
          }
    
          return accounts;
      }
    ```

- [메소드 탬플릿 패턴(method template pattern)](./method-template-pattern/src/main/java/me/gaegul/methodtemplatepattern/template/DataSourceTemplate.java)
    - 코드가 중복되는 부분을 따로 abstract class에 메서드로 정의한다.
        - 장점: 공통되는 부분의 기능을 한곳에서 정의하여 코드의 중복을 제거하고, 유지보수도 용이하다.
        - 단점: 기능마다 상속을 통해 새로운 클래스를 만들어야 한다. 그리고 확장 구조가 이미 클래스를 설계하는 시점에서 고정된다. 따라서 관계에 대한 유연성이 떨어진다.
    - 예제
        - 변하지 않는 부분은 슈퍼클래스에 두고 사용하고, 변하는 부분은 추상 메소드로 정의
        ```java
          public <T> T select(String sql, List<String> params) {
              Connection conn = null;
              PreparedStatement psmt = null;
              ResultSet rs = null;
      
              try {
                  conn = getConnection();
                  psmt = conn.prepareStatement(sql);
      
                  this.setParams(psmt, params);
      
                  rs = psmt.executeQuery();
      
                  return this.setResult(rs);
              } catch (SQLException e) {
                  e.printStackTrace();
                  System.out.println("Error");
              } finally {
                  if (rs != null) {
                      try {
                          rs.close();
                      } catch (SQLException e) {
                          e.printStackTrace();
                      }
                  }
                  if (psmt != null) {
                      try {
                          psmt.close();
                      } catch (SQLException throwables) {
                          throwables.printStackTrace();
                      }
                  }
                  if (conn != null) {
                      try {
                          conn.close();
                      } catch (SQLException throwables) {
                          throwables.printStackTrace();
                      }
                  }
              }
              return null;
          }
        ```
        - 추상 메소드로 sql, parameter 설정부분을 재정의하여 사용한다.
            - [selectAll class](./method-template-pattern/src/main/java/me/gaegul/methodtemplatepattern/account/AccountTemplate.java)
                - 내부클래스로 구현
        - [AccountDao](./method-template-pattern/src/main/java/me/gaegul/methodtemplatepattern/account/AccountDao.java)에서 구현된 클래스를 활용한다.
        
- 전략 패턴(strategy pattern)
    - 템플릿 메소드 패턴보다 더 유연하고 확장성이 뛰어나다. 오브젝트를 아예 둘로 분리하고 클래스 레벨에서는 인터페이스를 통해서만 의존하도록 만든다.
    - OCP 관점에서, 확장에 해당하는 부분을 별도의 클래스로 만들어 추상화된 인터페이스를 통해 위임하는 방식이다.
        - 장점: 컨텍스트는 그대로 유지되면서(OCP의 폐쇄 원칙) 필요에 따라 전략을 바꿔 쓸 수 있다. (OCP의 개방 원칙)
    - sql, parameter만 따로 설정할 수 있도록 재정의 하는 인터페이스를 구현한다.
        - [ParamsStrategy](./strategy-parttern/src/main/java/me/gaegul/strategyparttern/template/ParamsStrategy.java)
        - [ResultSetStrategy](./strategy-parttern/src/main/java/me/gaegul/strategyparttern/template/ResultSetStrategy.java)
    - [AccountDao](./strategy-parttern/src/main/java/me/gaegul/strategyparttern/account/AccountDao.java)에서 [abstract class](./strategy-parttern/src/main/java/me/gaegul/strategyparttern/template/DataSourceTemplate.java)를 상속받아 사용한다.
    - 이 떄 functional interface만 재정의하여 사용한다.