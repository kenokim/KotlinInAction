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
