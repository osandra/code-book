## chap 1. 디자인 패턴 소개와 전략 패턴
- 디자인 원칙: 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분과 분리한다. 
즉 **달라지는 부분을 찾아서 나머지 코드에 영향을 주지 않도록 캡슐화**하면 코드를 변경하는
과정에서 의도치 않게 발생하는 일을 줄이면서 시스템의 유연성을 향상시킬 수 있다

ex)
아래 네 종류의 오리가 있다고 가정할 경우
- 날지 않으며 꽦괙 소리를 내는 오리(A)
- 날지 않으며 삑삑 소리를 내는 오리(B)
- 날며 꽦괙 소리를 내는 오리(C)
- 날며 삑삑 소리를 내는 오리(D)

이를 상속을 이용하여 오리를 구현할 경우 아래와 같이 서로 다른 날갯짓과 소리를 내는 4개의 오리 클래스를 생성하는 방법을 생각할 수 있다.
```kotlin
abstract class InheritDuck {
    fun swim() {
        println("swin")
    }
    abstract fun display()

    abstract fun performFly()

    abstract fun performQuack()
}
// 날지 않으며 꽦괙 소리를 내는 오리(A)
class NoFlyAndQuckDuck() : InheritDuck() {
    override fun display() {
        println("display")
    }
    
    override fun performFly() {
        println("날고 이쒀")
    }

    override fun performQuack() {
        println("꽥꽥")
    }
}

// 날지 않으며 삑삑 소리를 내는 오리(B)
class FlyAndBeepDuck: InheritDuck() {
    override fun display() {
        println("display")
    }

    override fun performFly() {
        println("못 날고 이쒀")
    }

    override fun performQuack() {
        println("꽥꽥")
    }
}
```
그러나 위와 같이 진행할 경우, 날지 못하고 새로운 소리('쓱쓱')를 내는 오리 클래스를 정의해야 할 때 `못 날고 있다`는 것을 표현한 performFly 메서드의 내용은 중복되고
각 오리 클래스의 나는 행동 등을 동적으로 실행 시 바끌 수 없다는 단점이 있다. 

이 경우 상속 대신 구성을 사용하여 구현하는 방법을 생각해 볼 수 있다.

- 구성을 이용한 경우 
  - 나는 행동과 관련된 FlyBehavior 인터페이스와 소리를 내는 행동과 관련된 QuackBehavior 인터페이스를 정의한 후,
오리 클래스에서 두 인터페이스를 변수 타입으로 갖고 있으면, 추후 '쓱쓱' 소리를 내는 오리가 나타날 경우 해당 소리를 내는 QuackBehavior를 구현한 클래스를 추가하여 사용할 수 있다.
따라서 하나의 오리 클래스를 사용하여 변수에 선언된 인터페이스 구현체를 변경함으로써 동적으로 실행 시 행동을 바꿀 수 있게 된다.
```kotlin
// 나는 행동과 관련된 인터페이스 정의
interface FlyBehavior {
    fun fly()
}

class FlyNoWay: FlyBehavior {
    override fun fly() {
        println("못 날고 이쒀")
    }
}

class FlyWithWings: FlyBehavior {
    override fun fly() {
        println("날고 이쒀")
    }
}

// 소리 내는 행동과 관련된 인터페이스 정의
interface QuackBehavior {
    fun quack()
}

class QuackQuack : QuackBehavior {
    override fun quack() {
        println("꽥꽥")
    }
}


class BeepQuack : QuackBehavior {
    override fun quack() {
        println("삑삑")
    }
}

class MuteQuack : QuackBehavior {
    override fun quack() {
        println("조용")
    }
}
```

```kotlin
abstract class Duck {
    protected lateinit var flyBehavior: FlyBehavior
    protected lateinit var quackBehavior: QuackBehavior

    abstract fun display()

    fun performFly() {
        flyBehavior.fly()
    }

    fun performQuack() {
        quackBehavior.quack()
    }
    
    fun performQuack(quackBehavior: QuackBehavior) {
        quackBehavior.quack()
    }

    // set 을 통해 구현체 변경 가능
    fun updateQuackBehavior(quackBehavior: QuackBehavior) {
        this.quackBehavior = quackBehavior
    }
}
```

```kotlin
class SampleDuck(): Duck() {
    constructor(flyBehaviorValue: FlyBehavior, quackBehaviorValue: QuackBehavior) : this() {
        this.flyBehavior = flyBehaviorValue
        this.quackBehavior = quackBehaviorValue
    }

    override fun display() {
        println("display")
    }
}

fun main() {
    val secondDuck = SampleDuck(flyBehaviorValue = FlyNoWay(), quackBehaviorValue = MuteQuack())
    secondDuck.display() // display2
    secondDuck.performQuack() // 조용
    secondDuck.performFly() // 못 날고 이쒀

    // 나는 행동 인터페이스 변경
    secondDuck.updateQuackBehavior(BeepQuack())
    secondDuck.performQuack() // 삑삑
}
```
현재 오리 클래스에는 FlyBehavior, QuackBehavior 인터페이스가 있으며 각 인터페이스의 구현체를 통해 나는 행동과 소리를 내는 행동을 위임 받는다.
오리 클래스는 각 인터페이스를 구현한 구체적인 클래스에 대해 살필 필요가 없이, 각 인터페이스를 상속한 객체들을 사용하는 방식으로 구성(composition)을 이용한다.
즉 인터페이스를 사용함으로써 오리 클래스가 특정 행동을 구현한 구체 클래스에 의존하지 않고, 상위 형식에 맞춰 프로그래밍을 하므로 실행 시에 구현체를 동적을 변경할 수 있다.
> 상속보다 구성을 활용하면 구성요소를 사용하는 객체에선 올바른 행동 인터페이스를 구현하기만 하면 실행 시에 행동을 바꿀 수도 있다.
