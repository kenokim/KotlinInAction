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

클래스의 인스턴스를 만드려면 new 키워드 없이 constructor 를 직접 호출하면 된다.

Base class 가 있다면 괄호를 치고 parameter 를 넘긴다.

```kotlin
oepn class User(val nickname: String) {}
class TwitterUser(nickname: String) : User(nickname) {}
```

생성자를 정의하지 않으면 컴파일러가 default constructor 를 만들어준다.

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