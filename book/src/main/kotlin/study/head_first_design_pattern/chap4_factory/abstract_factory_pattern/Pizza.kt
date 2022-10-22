package study.head_first_design_pattern.chap4_factory.abstract_factory_pattern

abstract class Pizza(
    private val dough: Dough,
    private val sauce: Sauce,
    private val name: String,
) {
    fun prepare() {
        println("name: $name")
        println("$dough 준비 중")
        println("$sauce 준비 중")
    }
}

class CheesePizza(ingredientFactory: PizzaIngredientFactory, name: String) :
    Pizza(ingredientFactory.createDough(), ingredientFactory.createSauce(), name)

class PepperoniPizza(ingredientFactory: PizzaIngredientFactory, name: String) :
    Pizza(ingredientFactory.createDough(), ingredientFactory.createSauce(), name)

