### Chapter 6.1 Nullability

Nullability 는 NPE 를 피하는 것을 돕기 위한 코틀린의 타입 시스템이다. 

```kotlin
fun strLen(s: String) = s.length
```

이 함수의 경우 null 을 parameter 로 넘길 경우 컴파일 오류가 발생한다.

자바는 완전하게 컴파일 타임에 NPE 를 방지할 방법이 없다. Type 의 정의에 따라 String 과 null 은 다른 타입이지만 자바는 같은 객체에 입력할 수 있고 이는 타입 시스템의 오류다.

코틀린의 ?. 연산자는 이러한 문제를 해결해 준다.

?. 연산자는 return if (… ≠ null) { … } else null 과 같은 메소드다.

?: 연산자는 return if (… ≠ null) { … } else … 과 같은 메소드다.

as? 연산자는 foo is Type 일 경우 foo as Type 을, 아닐 경우 null 을 리턴한다.

!! 연산자는 return if (... != null) { ... } else NPE 과 같이 예외를 리턴한다.

let 함수는 null 이 아닌 경우에만 뒤의 람다를 실행한다.

```kotlin
email?.let { sendEmailTo(it) }
```

lateinit 변경자는 나중에 초기화를 할 수 있다. 초기화 전에 접근할 경우 exception 이 발생한다. 이를 클래스의 프로퍼티를 not nullable 로 정의하고 @Before 등의 late init 을 가능하게 한다.

코틀린의 nullable 은 자바의 @Nullable 타입과 비슷하다.


### Chapter 6.2 코틀린의 원시 타입

Int, Boolean: 코틀린은 primitive type 과 wrapper type 을 구분하지 않는다.

자바는 primitive 와 wrapper 를 구분하는데, 이는 성능상의 문제가 있고 이를 해결하기 위해 project valhalla 가 진행되었었다.

Generic class 를 사용할 경우 wrapper type 으로 컴파일되고, 그 외에는 primitive type 으로 컴파일된다. 자바는 wrapper type 에 null 값이 들어갈 수 있는데, 코틀린은 언어적으로 nullable 을 제공하여 이러한 문제가 없다.

Nullable Int 를 사용할 경우 wrapper type 으로 컴파일된다.

https://stackoverflow.com/questions/29375575/java-generics-type-erasure-byte-code

https://openjdk.org/jeps/218 Candidate 상태로 최신 jdk 에서 제공하지 않는 기능

a = long, b = int 로 a → b 로 변환할 때 overflow 를 검사하지 않는다.

Any 타입은 Object 와 대입되는데, wait / notify 등의 함수가 없다. 이러한 기능을 자주 사용하지 않으므로 Any 에서 제거한 게 아닐까

자바의 Void 타입은 코틀린에서 Unit 과 Nothing 으로 나뉜다.

- Unit 타입은 함수형 프로그램잉에서 아무값도 없는 것을 리턴할 때 사용한다.
- Nothing 타입은 fail() 과 같이 정상적으로 리턴되지 않는 경우를 나타낸다.
    
    ```java
    public static <V> V fail() {
       AssertionUtils.fail();
       return null; // appeasing the compiler: this line will never be executed.
    }
    ```


### Chapter 6.3 컬렉션과 배열

Collection, MutableCollection: read 와 write 를 분리하고, write 가 read 를 의존하도록 한다.

적시에 방어적 복사본을 만들라, 클라이언트로 반환하는 구성요소가 mutable 하다면 방어적으로 복사해야 한다.

Collection 이 thread safe 하다는 뜻이 아니다. Collection 은 MutableCollection 의 super class 이므로 thread safe 가 보장되지 않는다.
