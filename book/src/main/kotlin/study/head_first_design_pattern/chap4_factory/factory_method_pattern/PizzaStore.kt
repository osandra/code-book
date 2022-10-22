package study.head_first_design_pattern.chap4_factory.factory_method_pattern

import study.head_first_design_pattern.chap4_factory.factory_method_pattern.PizzaType.CHEESE
import study.head_first_design_pattern.chap4_factory.factory_method_pattern.PizzaType.PEPPERONI
import study.head_first_design_pattern.chap4_factory.factory_method_pattern.PizzaType.VEGGIE

enum class PizzaType {
    CHEESE, PEPPERONI, VEGGIE
}

abstract class PizzaStore {
    fun orderPizza(pizzaType: PizzaType): Pizza {
        val pizza = createPizza(pizzaType)
        pizza.prepare()
        pizza.bake()
        pizza.cut()
        return pizza
    }

    // 구체적으로 어떤 클래스의 인스턴스를 만들지는 서브클래스에서 결정한다
    abstract fun createPizza(pizzaType: PizzaType): Pizza
}

class NYPizzaStore : PizzaStore() {
    override fun createPizza(pizzaType: PizzaType): Pizza {
        return when (pizzaType) {
            CHEESE -> NYStyleChessPizza()
            PEPPERONI -> NYStylePepperoniPizza()
            VEGGIE -> NYStyleVeggiePizza()
        }
    }
}

class ChicagoPizzaStore : PizzaStore() {
    override fun createPizza(pizzaType: PizzaType): Pizza {
        return when (pizzaType) {
            CHEESE -> ChicagoStyleChessPizza()
            PEPPERONI -> ChicagoStylePepperoniPizza()
            VEGGIE -> ChicagoStyleVeggiePizza()
        }
    }
}

fun main() {
    /**
     * 이름: 뉴욕 스타일 치즈 피자
     * 도우 씬 크러스트 도우 준비 중
     * 소스 마리나라 소스 준비 중
     * 토핑 잘게 썬 치즈 준비 중
     * 굽는 중
     * 자르는 중
     */
    val newYorkPizzaStore = NYPizzaStore()
    newYorkPizzaStore.orderPizza(CHEESE)

    /**
     * 이름: 시카고 스타일 치즈 피자
     * 도우 씬 크러스트 도우 준비 중
     * 소스 핫 소스 준비 중
     * 토핑 올리브 준비 중
     * 굽는 중
     * 자르는 중
     */
    val chicagoPizzaStore = ChicagoPizzaStore()
    chicagoPizzaStore.orderPizza(CHEESE)
}
