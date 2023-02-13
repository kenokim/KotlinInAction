### 1. 코틀린에서 컬렉션 만들기

```java
val set = hashSetOf(1, 7, 53)

val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
```

to 는 keyword 가 아닌 함수이다.

### 2. 함수를 호출하기 쉽게 만들기
```java
fun <T> joinToString (
	collection: Collection<T>,
	separator: String,
	prefix: String,
	postfix: String
}: String {
	val result = StringBuilder(prefix)
	for ((index, element) in collection.withIndex()) {
		if (index > 0) result.append(separator)
		result.append(element)
	}
	
	result.append(postfix)
	return result.toString()
}
```

코틀린으로 작성한 함수는 인자를 명시할 수 있다.

```kotlin
joinToString(list, separator = " ", prefix = " ", postfix = ".")
```

자바에서는 오버로딩을 통해 메소드를 다양하게 만드는데, 예를 들어 telescoping constructor pattern, 코틀린에서는 디폴트 값을 지정해 이렇게 안해도 된다.

```kotlin
fun <T> joinToString(
	collection: Collection<T>,
	separator: String = ", ",
	prefix: String = "",
	postfix: String = ""
): String
```
