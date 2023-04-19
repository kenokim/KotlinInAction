## 5. 람다로 프로그래밍

람다는 다른 함수에 넘길 수 있는 코드 조각이다.


```kotlin
fun findByLambda() {
  val people = listOf(Person("Alice", 29), Person("Bob", 31))
  println(people.maxBy { it.age })
}

people.maxBy(Person::age)

people.maxBy { p: Person -> p.age }
```

람다는 수학에서 함수를 편리하게 표현하기 위한 람다 표현식에서 가져왔다.


람다 식은 파라미터 -> 본문 으로 구성되어 있다.

파라미터가 한 개에고 타입 추론이 가능할 경우 it 으로 대체할 수 있다.

람다는 컬렉션 라이브러리 제공에 필요하다.

### Chapter 5.2 컬렉션 함수형 API

함수형 프로그래밍 스타일을 사용하면 컬렉션을 다룰 때 편리하다.

코틀린 표준 컬렉션 라이브러리를 통해 이를 살펴보자.

1. filter, map

filter 는 Predicate 를, map 은 Function 을 파라미터로 갖는 함수이다.

2. all, any, count, find
