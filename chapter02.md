## 2장 코틀린 기초

함수, 변수, 클래스, enum, 프로퍼티

제어 구조, smart cast, exception handling

### 1. 함수

```kotlin
fun main(args: Array<String>) {
	println("Hello, world!")
}

fun max(a: Int, b: Int): Int {
	return if (a > b) a else b
}

fun max(a: Int, b: Int) = if (a > b) a else b
```

statement vs expression → 값을 만드는지 아닌지

타입을 적지 않아도 type inference 를 통해 정해준다.

### 2. 클래스와 프로퍼티

```kotlin
class Person(val name: String, var isMarried: Boolean)

println(person.name)
person.isMarried = false
```

자바 = 데이터를 field 에 저장하며, access method (getter, setter) 를 통해 필드를 encapsulate 한다.

코틀린 = 필드와 접근자를 합쳐 property 라고 한다. var (private + getter + setter), val (private + getter)

- 디렉터리와 패키지

같은 패키지 = 직접 사용, 다른 패키지 = import

클래스 import 와 함수 import 가 같다

### 3. enum 과 when

enum 은 class 앞에 있을 때만 keyword 로 작용한다. (soft keyword)

class 는 keyword 이므로 변수로 사용할 수 없다.

enum 클래스 안에 프로퍼티, 메소드를 정의할 수 있다. 자바와 마찬가지로 완전한 형태의 클래스이다.

```kotlin
enum class Color (
	val r: Int, val g: Int, val b: Int
) {
	RED(255, 0, 0), ORANGE(255, 165, 0),
	YELLOW(255, 255, 0);
	fun rgb() = (r * 256 + g) * 256 + b
}
```

when 은 자바의 switch 에 해당하며 expression 이다.

```kotlin
fun getMnemonic(color: Color) =
	when (color) {
		Color.RED -> "Richard"
		Color.ORANGE -> "Of"
	}
```

```kotlin
fun mix(c1: Color, c2: Color) =
	when (setOf(c1, c2)) {
		setOf(RED, YELLOW) -> ORANGE
		else -> throw Exception("Dirty color")
}
```

스마트 캐스트 → instanceof - typecasting 을 자동으로 해준다.

```java
if (authentication instanceof SecondFactorAuthentication) {
	SecondFactorAuthentication secondFactor = (SecondFactorAuthentication) authentication;
	secondFactor ...
}
```

```kotlin
if (authentication is SecondFactorAuthentication) {
	authentication ...
}
```

is → instanceof, as → type casting (type)

```kotlin
fun evalWithLogging(e: Expr): Int = 
	when (e) {
		is Num -> {
			e.value
		}
		is Sum -> {
			println(e)
		}
		else -> throw IllegalArgumentException("Unknown")
}
```

### 4. for & while

```kotlin
for (i in 1..100) {
	println(i)
}
```

downTo, step, until

```kotlin
val list = arrayListOf("10", "11", "1001")
for ((index, element) in list.withIndex()) {
	println("$index: $element")
}
```

in 을 통해 element 가 collection 에 있는지 알 수 있다.

```kotlin
fun isLetter(c: Char) = c in 'a'..'z'
```

맵에 대해서도 이터레이션을 할 수 있다.

```kotlin
for ((letter, binary) in binaryReps) {
	println("$letter = $binary")
}
```

### 5. try, catch, finally

이펙티브 자바: 복구할 수 있는 상황에는 체크 예외를, 프로그래밍 오류에는 런타임 예외를 사용하라.

→ retryable 등 복구 로직 작성하는 코드

```kotlin
fun readNumber(reader: BufferedReader): Int? {
	try {
		val line = reader.readLine()
		return Integer.parseInt(line)
	}
	catch (e: NumberFormatException) {
		return null
	}
	finally {
		reader.close()
	}
}
```

코틀린은 체크 예외여도 함수에 선언하지 않아도 된다. 자바와 다르게 체크 예외 처리를 강제하지 않는다. 자바 클라이언트와의 interoperability 를 위해 @Throws 어노테이션을 제공한다.

```kotlin
class ExceptionExample {
    fun ex1() {
        throw IOException()
    }

		@Throws(IOException.class)
		fun ex2() = throw IOException()
}

public void method1() {
        ExceptionExample ex = new ExceptionExample();
        try {
            ex.ex1();
        } catch (IOException e) { // 컴파일 에러 발생

        }
    }
}
```

자바와 다르게 코틀린의 throw 은 expression 이다.

```kotlin
val percentage = throw IllegalStateException("..")
```

try catch finally 는 expression 이다. 예를 들어 null 이 리턴되고 println(”hello”) 가 찍힌다.

```kotlin
fun tryCatch() = try {
        throw Exception()
    } catch (e: Exception) {
        null
    } finally {
        "Hello"
        println("hello")
    }
```
