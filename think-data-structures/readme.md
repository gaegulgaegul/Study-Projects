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

## 알고리즘 분석
- ArrayList vs LinkedList 어떤게 더 빠를까?
    - 상황에 따라 다르다.
    - 비교하려면 모두 구현하고 동일한 성능의 컴퓨터와 같은 데이터로 수행해야 한다.
    - 알고리즘 분석을 통해 구현하지 않고 알고리즘을 비교할 수 있다.
- 알고리즘의 범주
    - 상수 시간
        - 실행시간이 입력 크기에 의존하지 않으면 알고리즘은 상수 시간을 따른다.
    - 선형
        - 실행시간이 입력 크기에 비례하면 선형 알고리즘
        - 배열에 있는 요소를 더한다면 n개의 요소에 접근하여 n-1번의 더하기를 한다.
        - 연산의 총 횟수는 2n-1이고 n에 비례한다.
    - 이차
        - 실행시간이 n^2에 비례하면 이차 알고리즘
        - 리스트에 있는 요소가 두번 이상 나타나는지 확인한다면 n개의 요소가 있고 각각 n-1개의 다른 요소와 비교한다.
        - 총 비교 횟수는 n^2-n이 되어 n이 커지면서 n^2에 비례한다.
- 2.1 [선택 정렬](./src/main/java/me/gaegul/thinkdatastructures/ch2/SelectionSort.java)
    - `swapElements`
        - 배열에 있는 두 요소의 값을 바꾼다.
        - 요소를 읽고 쓰는 것은 `상수 시간 연산`이다.
    - `indexLowest`
        - start에서 시작하여 배열에 있는 가장 작은 요소의 인덱스를 찾는다.
        - 반복문을 돌 때마다 배열의 두 요소에 접근하고 한번의 비교 연산을 한다.
        - 일반적으로 비교 횟수는 n-start가 되어 `선형`이다.
    - `selectionSort`
        - indexLowest 메서드가 호출되면 n번 비교 연산을 한다. 두번째는 n-1번 비교 연산을 한다.
        - 총 비교 횟수는 n + n-1 + n-2 + ... + 1 + 0, 수열의 합은 n(n+1)/2이고 n^2에 비례한다.
        - selectionSort 메소드는 `이차`
- 2.2 빅오 표기법
    - 알고리즘을 작성할 때 알고리즘이 어떻게 동작하는지에 관한 법칙을 표현하는 방법
    - 상수 시간 알고리즘에 이어 선형 시간 알고리즘을 수행하면 총 실행시간은 `선형`
    - 두개의 선형 연산을 하면 합은 `선형`
    - 선형 연산을 n번 반복하면 `이차`

## ArrayList 클래스
- 3.1 [MyArrayList](./src/main/java/me/gaegul/thinkdatastructures/ch2/MyArrayList.java) 메서드 분류하기
    - `get`
        - get은 상수 시간.
    - `set`
        - get 메소드를 통해 배열의 범위를 검사한다.
        - set은 상수 시간.
    - `equals`
        - target.equals를 호출하지만 실행시간은 Element의 크기에 의존한다.
    - `indexOf`
        - 반복문 안에 있는 것은 상수 시간.
        - 반복문이 몇번 실행되는지 모르니 평균적으로 요소 개수의 절반을 테스트하기를 기대한다.
        - indexOf는 선형.
    - `remove`
        - 첫번째 요소를 제거하고 나머지 요소에 접근하게 된다.
        - remove는 선형.
- 3.2 add 메소드 분류하기
    - `add(E)`
        - 새로운 인자를 마지막에 넣는다.
        - 최대 크기에서 인자를 저장할 경우 배열을 다시 생성한다.
        - n번 추가하면 n개의 요소를 저장하고 n-2개를 복사한다. 총 연산은 `2n-2`
        - 평균 연산 횟수를 구하려면 합을 n으로 나눈 `2-2/n`. n이 커지면 2/n은 작아진다.
        - n의 가장 큰 지수에만 관심있다는 원칙을 적용하면 add는 상수 시간으로 간주
    - `add(int, E)`
        - add(E)를 호출하여 새로운 인자를 마지막에 넣는다.
        - 다른 요소를 오른쪽으로 이동시킨다.
        - 해당 위치에 새로운 인자를 넣는다.
        - add(int, E)는 선형.
- 3.3 문제 크기
    - 반복문이 한 개라면 알고리즘은 보통 `선형`
    - 반복문이 두 개가 중첩되어 있다면 알고리즘은 보통 `이차`
    - 하지만 반복 횟수가 항상 n에 비례하지 않는다면 고민해봐야 한다.
    - `removeAll`
        - removeAll은 반복문을 돌면서 선형인 remove를 호출한다.
        - 삭제 요소가 n에 비례한다면 removeAll은 이차, 예를 들어 제거할 리스트에 있는 요소들이 1%를 포함하는 경우 반복을 n에 비례하게 한다.
        - 삭제 요소가 상수라면 removeAll은 선형, 예를 들어 삭제할 요소가 항상 100건 이하인 경우 반복은 최대 100번만 하면 된다.
- 3.4 연결 자료구조
    - [노드](./src/main/java/me/gaegul/thinkdatastructures/ch3/LinkedListExample.java)
        - 자료구조가 연결되었다 함은 노드 객체들이 다른 노드객체를 참조하고 있는 상태를 의미
        - 연결 리스트에서 각 노드는 리스트의 다음 노드에 대한 참조를 포함.
        - 연결구조의 예는 트리와 그래프가 있다.