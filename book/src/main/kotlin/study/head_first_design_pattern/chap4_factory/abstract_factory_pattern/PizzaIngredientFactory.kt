package study.head_first_design_pattern.chap4_factory.abstract_factory_pattern

interface PizzaIngredientFactory {
    fun createDough(): Dough
    fun createSauce(): Sauce
}

class NYPizzaIngredientFactory : PizzaIngredientFactory {
    override fun createDough(): Dough {
        return ThickDough()
    }

    override fun createSauce(): Sauce {
        return HotSauce()
    }
}

class ChicagoPizzaIngredientFactory : PizzaIngredientFactory {
    override fun createDough(): Dough {
        return ThinDough()
    }

    override fun createSauce(): Sauce {
        return TomatoSauce()
    }
}
