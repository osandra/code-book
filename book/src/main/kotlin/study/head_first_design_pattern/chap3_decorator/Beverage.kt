package study.head_first_design_pattern.chap3_decorator

import study.head_first_design_pattern.chap3_decorator.MenuInfo.ESPRESSO
import study.head_first_design_pattern.chap3_decorator.MenuInfo.HOUSE_BLEND
import study.head_first_design_pattern.chap3_decorator.MenuInfo.MOCHA
import study.head_first_design_pattern.chap3_decorator.MenuInfo.WHIP

// 추상 구성 요소
abstract class Beverage(val description: String = "description") {
    abstract fun cost(): Double
}

// 추상 데코레이터
abstract class CondimentDecorator(description: String) : Beverage(description) {
    lateinit var beverage: Beverage
}

// 구상 구성 요소 1
class Espresso : Beverage(ESPRESSO.description) {
    override fun cost(): Double {
        return ESPRESSO.cost
    }
}
// 구상 구성 요소 2
class HouseBlend : Beverage(HOUSE_BLEND.description) {
    override fun cost(): Double {
        return HOUSE_BLEND.cost
    }
}

// 데코레이터 1
class Mocha(beverage: Beverage) : CondimentDecorator(beverage.description + ", ${MOCHA.description}") {

    init {
        this.beverage = beverage
    }

    override fun cost(): Double {
        return beverage.cost() + MOCHA.cost
    }
}

// 데코레이터 2
class Whip(beverage: Beverage) : CondimentDecorator(beverage.description + ", ${WHIP.description}") {
    init {
        this.beverage = beverage
    }

    override fun cost(): Double {
        return beverage.cost() + WHIP.cost
    }
}
