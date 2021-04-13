# Think Data Structures - 자바로 배우는 핵심 자료구조와 알고리즘

## 인터페이스
- 1.1 리스트가 두 종류인 이유
    - 왜 자바는 List 인터페이스에 두 가지 구현을 제공하는가?
    - 둘 중에서 어느 것을 선택해야 하는가?
- 1.2 자바 interface
    - 자바 interface는 메소드 집합을 의미
    - interface를 구현하는 클래스는 interface의 메소드를 제공해야 한다.
    ```java
      public interface Comparable<T>{
          public int compareTo(T o);
      }
    ```
    ```java
      public final class Integer extends Number implements Comparable<Integer> {
          public int compareTo(Integer anotherInteger) {
              int thisValue = this.value;
              int anotherVal = anotherInteger.value;
              return (thisValue<anotherVal > -1 : (thisValue==anotherVal ? 0 : 1));
          }
      }
    ```
    - Integer 클래스는 Number 클래스를 확장하고 Comparable 인터페이스를 구현한다.
    - 따라서 compareTo 메소드를 제공해야 한다.
- 1.3 List Interface
    - JCF(Java Collection Framework)는 List라는 interface를 정의하고 ArrayList, LinkedList 두 구현 클래스를 제공한다.
    - ArrayList, LinkedList는 List 인터페이스의 메소드를 제공하므로 상호교환 할 수 있다.([예제코드](./src/main/java/me/gaegul/thinkdatastructures/ch1/ListClientExample.java))
    - 라이브러리 사용 시 인터페이스만 의존하고 구현 클래스에 의존하면 안된다.
        - 구현 클래스에 의존하면 구현이 변경될 때 코드를 수정해야 한다.
        - 인터페이스에 의존하면 코드는 그대로 사용할 수 있다.
    - 인터페이스가 변경되면 인터페이스를 구현 클래스, 의존하는 코드는 변경되어야 한다.
- 1.4 실습1
    - 인터페이스에 의존하는 코드
    ```java
      List list = new ArrayList();
  
      List list = new LinkedList();
    ```