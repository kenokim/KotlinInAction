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
