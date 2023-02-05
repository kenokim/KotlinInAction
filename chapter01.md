## 1장 코틀린이란 무엇이며, 왜 필요한가?

코틀린은 간결하고 실용적이며 자바 코드와 interoperable 한 언어

```kotlin
data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {
	val people = listOf(Person("Jessy"), Person("Tom", age = 20))
	val oldest = people.maxBy { it.age ?: 0 }
	println("$oldest")
}
```

코틀린은 statically typed 언어이고 type inference 기능을 가지고 있다.

코틀린의 타입 시스템은 자바의 class, interface, generics 를 비슷하게 가지며, 자바와 다르게 nullable type, function type 을 가지고 있다.

### 함수형 프로그램잉

function as first-class citizen, immutability, no side-effect

함수형 스타일은 간결하고, 강력한 추상화를 할 수 있다.

멀티스레딩 환경에서 safe 하다. 테스트하기 쉽다.

### 코틀린의 철학

1. 코틀린은 다른 언어가 채택한 검증된 해법에 의존한다.
2. 명령형, 함수형 등 스타일을 강제하지 않는다.
3. 간결성이 좋아 읽기가 쉽다.
4. 자바보다 더 높은 안정성을 제공한다. NPE, ClassCastException 을 언어 차원에서 방지해 준다.

코틀린 컴파일러 (kt → class → jar) 로 컴파일 한 코드는 kotlin runtime library 에 의존한다. 

빌드 툴 (maven, gradle) 과 호환된다.
