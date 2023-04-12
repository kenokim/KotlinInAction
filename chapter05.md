## 5. 람다로 프로그래밍

람다는 다른 함수에 넘길 수 있는 코드 조각이다.


```kotlin
fun findByLambda() {
  val people = listOf(Person("Alice", 29), Person("Bob", 31))
  println(people.maxBy { it.age })
}
```


람다 식은 파라미터 -> 본문 으로 구성되어 있다.
