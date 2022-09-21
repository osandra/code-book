package study.head_first_design_pattern.chap1_composition

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
