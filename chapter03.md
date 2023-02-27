## Chapter 3. 함수 정의와 호출

3장은 컬렉션, 문자열, 정규식을 통해 함수 정의와 호출을 설명한다.

### 1. Collection

```kotlin
val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to"one", 7 to"seven", 53 to"fifty-three")
```

to 는 infix 함수이다.

`set.*javaClass*`

Property get 처럼 보이는데 java 의 getClass 메소드를 호출하는데, 커스텀 getter 를 구현하였다.

```kotlin
@SinceKotlin("1.1") public actual typealias ArrayList<E> = java.util.ArrayList<E>
@SinceKotlin("1.1") public actual typealias LinkedHashMap<K, V> = java.util.LinkedHashMap<K, V>
@SinceKotlin("1.1") public actual typealias HashMap<K, V> = java.util.HashMap<K, V>
@SinceKotlin("1.1") public actual typealias LinkedHashSet<E> = java.util.LinkedHashSet<E>
@SinceKotlin("1.1") public actual typealias HashSet<E> = java.util.HashSet<E>

```

코틀린 컬렉션은 실제로 자바 라이브러리를 가져온다.

### 2. 함수를 호출하기 쉽게 만들기

Generic 함수, withIndex() 메소드 → 확장 함수, property 를 두 개 선언하면 (a, b) in … 식으로 이터레이션이 가능하다.

```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)

    // withIndex -> IndexedValue, Iterable 의 확장 함수
    // iterable 의 프로퍼티들에 대해 iterate 한다.
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
```

코틀린은 이름 붙인 인자를 통해 가독성을 높일 수 있다.

```kotlin
joinToString(collection, separator = " ", prefix = " ", postfix = ".")
```

(자바: constructor parameter 가 많다면 클라이언트 코드의 가독성이 안좋아서 builder 패턴을 고려해라)

default parameter value → 자바에서는 없는 기능인데 (빌더를 통해 간접적으로) 제공된다. 클라이언트 코드에서 파라미터를 명시하지 않으면 default 값을 컴파일러가 끼워 준다. 그래서, 클라이언트가 자바면 불가능하다.

```kotlin
fun hello(a : String = "hi") {
    println(a)
}

fun sayHello() {
    hello()
}

public static final void hello(@NotNull String a) {
      Intrinsics.checkNotNullParameter(a, "a");
      System.out.println(a);
   }

   // $FF: synthetic method
   public static void hello$default(String var0, int var1, Object var2) {
      if ((var1 & 1) != 0) {
         var0 = "hi";
      }

      hello(var0);
   }
```

@JvmOverloads 를 코틀린 함수에 붙이면 telescoping constructor pattern 으로 만들어 주고 default 값을 사용한다.

Util 클래스를 없애고 함수로 선언할 수 있다. 자바로 변환하면 static method 가 된다.

클래스는 기본적으로 함수가 포함된 파일 이름이고 @JvmName 으로 바꿀 수 있다.

최상위 프로퍼티도 가능하며 public static 변수와 같다.

### 3. 확장 함수와 확장 프로퍼티

`fun String.lastChar(): Char = get(length - 1)`

확장 함수는 클래스 . 메소드 식으로 정의하며, this 를 생략해서 클래스의 메소드를 가져올 수 있다.

private, protected 에 대한 접근이 제한되어 캡슐화를 지킨다.

```kotlin
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}
```

확장 함수는 static method 에 대한 syntatic sugar 이다.

그냥 static method 이고 Collection 을 parameter 로 받는다. 따라서 클래스 안에 있는 method 처럼 override 도 할 수 없다.

커스텀 프로퍼티도 정의할 수 있다.

### 4. 컬렉션 처리 : 가변 길이 인자, 중위 함수 호출, 라이브러리 지원

vararg, infix, destructuring declaration

vararg 는 자바의 … 과 같은 keyword 이다.

infix call 은 parameter 가 하나일 때, 1.to(”one”) 을 1 to “one” 으로 표현하여 가독성을 좋게 한다.

```kotlin
val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

infix fun Any.to(other: Any) = Pair(this, other)

// destructuring declaration
val (number, name) = 1 to "one"

fun <K, V> mapOf(vararg values: Pair<K, V>): Map<K, V>
```

### 5. String & Regular expression

Java 의 split 함수는 regex 를 String type 의 parameter 로 받는다.

Kotlin 은 regex 받으려면 Regex type 를 parameter 로 값는다.

따라서, 기존의 String type 로 regex 를 받아 헷갈리던 것을 개선하였다.

```kotlin
fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
		println("Dir: $directory, name: $fileName, ext: $extension")
}

fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
				println("Dir: $directory, name: $filename, ext: $extension")
    }
}
```

### 6. 로컬 함수

로컬 함수 기능을 통해 코드의 중복을 줄일 수 있다.

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Name")
    }

    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
            "Can't save user ${user.id}: empty Address")
    }
}

fun saveUserLocalFunction(user: User) {
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(
                "Can't save user ${user.id}: empty $fieldName")
        }
    }
    
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
}

