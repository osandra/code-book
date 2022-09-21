package study.head_first_design_pattern.chap1_composition

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
