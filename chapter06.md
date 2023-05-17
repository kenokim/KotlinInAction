### Chapter 6.1 Nullability

Nullability 는 NPE 를 피하는 것을 돕기 위한 코틀린의 타입 시스템이다. 

```kotlin
fun strLen(s: String) = s.length
```

이 함수의 경우 null 을 parameter 로 넘길 경우 컴파일 오류가 발생한다.

자바는 완전하게 컴파일 타임에 NPE 를 방지할 방법이 없다.

코틀린의 ?. 연산자는 이러한 문제를 해결해 준다.
