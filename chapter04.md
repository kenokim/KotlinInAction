### 1. 클래스 계층 정의

(1). interface

```kotlin
interface Clickable {
	fun click()
	fun showOff() = println("I am clickable")
}

class Button : Clickable {
	override fun click() = println("I was clicked")
}
```

(2). open, final, abstract modifier

```kotlin
open class RichButton : Clickable {
	fun disable() {}
	open fun animate() {}
	override fun click() {} // override 는 기본적으로 open
	final override fun click2() {}
}

abstract class Animated {
	abstract fun animate()
	open fun stopAnimating() {}
	fun animateTwice() {}
}
```

(3). public, internal, protected, private - visibility modifier

```kotlin
internal open class TalkativeButton : Focusable {
	private fun yell() = println("Hey")
	protected fun whisper() = println("Lets talk")
}

fun TalkativeButton.giveSpeech() { // error
	yell() // error
	whisper() // error
}
```

(4). inner class & nested class

```kotlin
interface State : Serializable
interface View {
	fun getCurrentState(): State
	fun restoreState(state: State) {}
}

class Button : View {
	override fun getCurrentState() : State = ButtonState()
	override fun restoreState(state : State) {}
	class ButtonState : State {}
}
```

(5). sealed class

```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
	when (e) {
		is Num -> e.value
		is Sum -> eval (e.right) + eval (e.left)
		else ->
			throw IllegalArgumentException("Unknown expression")
	}

sealed class Expr {
	class Num(val value: Int) : Expr()
	class Sum(val left: Expr, val right: Expr): Expr()
}

fun eval(e: Expr): Int =
	when (e) {
		is Expr.Num -> e.value
		is Expr.Sum -> eval(e.right) + eval(e.left)
	}
```

default 를 when 에서 처리하지 않아도 되는 기능이다.

### 1. 클래스 계층 정의

(1). interface

```kotlin
interface Clickable {
	fun click()
	fun showOff() = println("I am clickable")
}

class Button : Clickable {
	override fun click() = println("I was clicked")
}
```

(2), (3). Access modifiers 접근 제한자

- The defaults are different



(2). open, final, abstract modifier

```kotlin
open class RichButton : Clickable {
	fun disable() {}
	open fun animate() {}
	override fun click() {} // override 는 기본적으로 open
	final override fun click2() {}
}

abstract class Animated {
	abstract fun animate()
	open fun stopAnimating() {}
	fun animateTwice() {}
}
```

(3). public, internal, protected, private - visibility modifier

```kotlin
internal open class TalkativeButton : Focusable {
	private fun yell() = println("Hey")
	protected fun whisper() = println("Lets talk")
}

fun TalkativeButton.giveSpeech() { // error
	yell() // error
	whisper() // error
}
```

internal 은 같은 모듈 안에서 허용된다.

자바는 package-private (default), private, public, protected 인데 internal 에 해당하는 키워드가 없다. 

(4). inner class & nested class

클래스 안에 다른 클래스를 선언하면 도우미 클래스를 encapsulate 하거나 코드 정의를 그 코드를 사용하는 곳 가까이에 두고 싶을 때 유용하다.

```kotlin
interface State : Serializable
interface View {
	fun getCurrentState(): State
	fun restoreState(state: State) {}
}

class Button : View {
	override fun getCurrentState() : State = ButtonState()
	override fun restoreState(state : State) {}
	class ButtonState : State {}
}
```

(5). sealed class

```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
	when (e) {
		is Num -> e.value
		is Sum -> eval (e.right) + eval (e.left)
		else ->
			throw IllegalArgumentException("Unknown expression")
	}

sealed class Expr {
	class Num(val value: Int) : Expr()
	class Sum(val left: Expr, val right: Expr): Expr()
}

fun eval(e: Expr): Int =
	when (e) {
		is Expr.Num -> e.value
		is Expr.Sum -> eval(e.right) + eval(e.left)
	}
```

### 2. 뻔하지 않은 생성자와 프로퍼티를 갖는 클래스 선언

코틀린은 primary constructor, secondary constructor, initializer block 이 있어 자바와 다르다.

(1) Class initialization : Primary constructor and initializer block

- Primary constructor

```kotlin
class User(val nickname: String)
```

- Initializer block

```kotlin
class User constructor(_nickname: String) {
	val nickname: String
	init {
		nickname = _nickname
	}
}
```

constructor 키워드는 생성자 정의를 할 때, init 키워드는 클래스 객체가 만들어질 때 실행될 초기화 코드를 정의한다.

```kotlin
class User(_nickname: String) {
	val nickname = _nickname
}
```

디폴트 파라미터를 통해 파라미터 없는 생성자를 만들 수 있다.

클래스의 인스턴스를 만드려면 new 키워드 없이 constructor 를 직접 호출하면 된다.

Base class 가 있다면 괄호를 치고 parameter 를 넘긴다.

```kotlin
oepn class User(val nickname: String) {}
class TwitterUser(nickname: String) : User(nickname) {}
```

생성자를 정의하지 않으면 컴파일러가 default constructor 를 만들어준다.

super 키워드를 통해 base 클래스의 생성자를 호출할 수 있다.

클래스를 extends 할 경우 괄호가 필수이고, 인터페이스를 implement 할 경우 괄호가 없다.

싱글턴을 사용하고 싶을 때는 object 키워드를 통해 객체를 선언하면 된다.



(2). Secondary constructor

코틀린은 자바의 overloaded constructor 를 default parameter 값과 named parameter 를 통해 대다수 대체할 수 있다.

```kotlin
open class View {
	constructor(ctx: Context) {}
	constructor(ctx: Context, attr: AttributeSet) {}
}
```

```kotlin
class MyButton : View {
	constructor(ctx: Context) : super(ctx) {}
	constructor(ctx: Context, attr: AttributeSet) : super(ctx, attr) {}
}
```

(3). Implementing properties declared at interface

인터페이스에 추상 프로퍼티를 declare 하고 클래스로 구현할 수 있다.

자바에서 인터페이스에 선언된 변수는 static final 이다.

```kotlin
interface User {
	val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribingUser(val email: String) : User {
	override val nickname: String
	get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int) : User {
	override val nickname = getFacebookName(accountId)
}

interface User {
	val email: String
	val nickname: String
		get() = email.substringBefore('@')
}
```

### 3. 데이터 클래스와 클래스 delegation

코틀린의 == 는 객체의 동등성을 체크한다.

JVM 언어에서는 equals 가 true 를 반환하는 두 객체는 반드시 같은 hashCode 를 반환해야 하는 제약이 있다.

롬복 @Data 는 get/set, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 를, 코틀린 data class 는 toString, equals, hashCode, copy 를 제공한다. 프로퍼티를 생성자에 선언을 해서 get/set, constructor 도 제공된다고 볼 수 있다.

객체지향 시스템에서 상속에 의해 시스템 취약점이 발생한다. Decorator pattern 을 통해 이를 해결할 수 있다. 코틀린에서는 by 키워드를 제공한다.

```kotlin
class DelegatingCollection<T>: Collection<T> {
	private val innerList = arrayListOf<T>()

	override val size: Int get() = innerList.size
}

class DelegatingCollection<T>: Collection<T> {
    private val innerList = arrayListOf<T>()
    override val size: Int = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun iterator(): Iterator<T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
    override fun contains(element: T): Boolean = innerList.contains(element)

}

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>())
    : MutableCollection<T> by innerSet {
        
    }
```


### 4. object 키워드: 클래스 선언과 인스턴스 생성

object 키워드는 클래스를 선언함과 동시에 인스턴스를 생성한다.

싱글톤 생성, companion object, anonymous inner class 등에 쓸 수 있다. 생성자를 정의할 수 없고 property, inheritance 등을 할 수 있다.

```kotlin
object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmployees) {

        }
    }
}



object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(o1: File?, o2: File?): Int {
    }
}

data class Persion(val name: String) {
    object NameComparator : Comparator<Persion> {
        override fun compare(o1: Persion?, o2: Persion?): Int {
        }

    }
}
```

(2). companion object

코틀린은 static keyword 를 지원하지 않는다. 최상위 함수 또는 object 를 통해 이를 대신하고, private 멤버에 접근해야 하는 경우에는 nested companion object 를 통해 해결한다.

예를 들어, static factory method 를 구현하고자 할 때 nested companion object 를 사용할 수 있다.

```kotlin
class Users private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = Users(email)
        }
    }
}
```

companion object 는 둘러싼 클래스의 private member 에 접근할 수 있고 외부에서 메소드 호출 시 둘러싼 클래스를 콜한다.

companion object 도 이름을 붙일 수 있고 기본은 Companion 이다.

class Person(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Person = TODO()
    }
}

fun main() {
    val person = Person.fromJSON("")
}

class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))

        private fun getFacebookName(accountId: Int): String = TODO()
    }
}

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person2(val name: String) {
    companion object : JSONFactory<Person2> {
        override fun fromJSON(jsonText: String): Person2 = TODO("Not yet implemented")
    }
}
