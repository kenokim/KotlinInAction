### Chapter 6.1 Nullability

Nullability 는 NPE 를 피하는 것을 돕기 위한 코틀린의 타입 시스템이다. 

```kotlin
fun strLen(s: String) = s.length
```

이 함수의 경우 null 을 parameter 로 넘길 경우 컴파일 오류가 발생한다.

자바는 완전하게 컴파일 타임에 NPE 를 방지할 방법이 없다.

코틀린의 ?. 연산자는 이러한 문제를 해결해 준다.

?. 연산자는 return if (… ≠ null) { … } else null 과 같은 메소드다.

?: 연산자는 return if (… ≠ null) { … } else … 과 같은 메소드다.

as? 연산자는 foo is Type 일 경우 foo as Type 을, 아닐 경우 null 을 리턴한다.

!! 연산자는 return if (... != null) { ... } else NPE 과 같이 예외를 리턴한다.

let 함수는 null 이 아닌 경우에만 뒤의 람다를 실행한다.

```kotlin
email?.let { sendEmailTo(it) }
```

lateinit 변경자는 나중에 초기화를 할 수 있다. 초기화 전에 접근할 경우 exception 이 발생한다.

코틀린의 nullable 은 자바의 @Nullable 타입과 비슷하다.
