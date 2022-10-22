package study.head_first_design_pattern.chap4_factory.abstract_factory_pattern

import study.head_first_design_pattern.chap4_factory.abstract_factory_pattern.PizzaType.CHEESE
import study.head_first_design_pattern.chap4_factory.abstract_factory_pattern.PizzaType.PEPPERONI

enum class PizzaType(name: String) {
    CHEESE("치즈"), PEPPERONI("페페로니")
}

abstract class PizzaStore {
    fun orderPizza(pizzaType: PizzaType): Pizza {
        val ingredientFactory = getIngredientFactory()
        return createPizza(pizzaType, ingredientFactory).also {
            it.prepare()
        }
    }

    private fun createPizza(pizzaType: PizzaType, ingredientFactory: PizzaIngredientFactory): Pizza {
        return when (pizzaType) {
            CHEESE -> CheesePizza(ingredientFactory, pizzaType.name)
            PEPPERONI -> PepperoniPizza(ingredientFactory, pizzaType.name)
        }
    }

    abstract fun getIngredientFactory(): PizzaIngredientFactory
}

class NYPizzaStore : PizzaStore() {
    override fun getIngredientFactory(): PizzaIngredientFactory {
        return NYPizzaIngredientFactory()
    }
}

class ChicagoPizzaStore : PizzaStore() {
    override fun getIngredientFactory(): PizzaIngredientFactory {
        return ChicagoPizzaIngredientFactory()
    }
}

fun main() {
    val newYorkPizzaStore = NYPizzaStore()
    /**
     * name: CHEESE
     * 뉴욕 두꺼운 도우 준비 중
     * 핫 소스 준비 중
     */
    newYorkPizzaStore.orderPizza(CHEESE)
    /**
     * name: PEPPERONI
     * 뉴욕 두꺼운 도우 준비 중
     * 핫 소스 준비 중
     */
    newYorkPizzaStore.orderPizza(PEPPERONI)

    /**
     * name: CHEESE
     * 시카고 얇은 도우 준비 중
     * 토마토 소스 준비 중
     */
    val chicagoPizzaStore = ChicagoPizzaStore()
    chicagoPizzaStore.orderPizza(CHEESE)
}
