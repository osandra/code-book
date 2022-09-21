package study.head_first_design_pattern.chap1_composition

class FlyWithWingAndQuackDuck: Duck() {
    init {
        this.flyBehavior = FlyWithWings()
        this.quackBehavior = QuackQuack()
    }

    override fun display() {
        println("display")
    }
}

class SampleDuck(): Duck() {
    constructor(flyBehaviorValue: FlyBehavior, quackBehaviorValue: QuackBehavior) : this() {
        this.flyBehavior = flyBehaviorValue
        this.quackBehavior = quackBehaviorValue
    }

    override fun display() {
        println("display2")
    }
}

fun main() {
    val flyWithWingAndQuackDuck = FlyWithWingAndQuackDuck()
    flyWithWingAndQuackDuck.performFly() // 날고 이쒀
    flyWithWingAndQuackDuck.performQuack() // 꽥꽥
    flyWithWingAndQuackDuck.performQuack(BeepQuack()) // 삑삑

    val secondDuck = SampleDuck(flyBehaviorValue = FlyNoWay(), quackBehaviorValue = MuteQuack())
    secondDuck.display() // display2
    secondDuck.performQuack() // 조용
    secondDuck.performFly() // 못 날고 이쒀
    secondDuck.updateQuackBehavior(BeepQuack())
    secondDuck.performQuack() // 삑삑
}

