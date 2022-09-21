package study.head_first_design_pattern.chap1_composition

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


abstract class InheritDuck {
    fun swim() {
        println("swin")
    }
    abstract fun display()

    abstract fun performFly()

    abstract fun performQuack()
}

class NoFlyAndQuckDuck() : InheritDuck() {
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
