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
