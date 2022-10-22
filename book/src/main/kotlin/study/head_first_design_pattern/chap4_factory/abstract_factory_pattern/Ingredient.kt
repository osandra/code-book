package study.head_first_design_pattern.chap4_factory.abstract_factory_pattern

interface Dough {
    fun createDough(): Dough
}

class ThickDough: Dough {
    override fun createDough(): Dough {
        return ThickDough()
    }

    override fun toString(): String {
        return "뉴욕 두꺼운 도우"
    }
}

class ThinDough: Dough {
    override fun createDough(): Dough {
        return ThinDough()
    }
    override fun toString(): String {
        return "시카고 얇은 도우"
    }
}

interface Sauce {
    fun createSauce(): Sauce
}

class TomatoSauce: Sauce {
    override fun createSauce(): Sauce {
        return TomatoSauce()
    }
    override fun toString(): String {
        return "토마토 소스"
    }
}

class HotSauce: Sauce {
    override fun createSauce(): Sauce {
        return HotSauce()
    }
    override fun toString(): String {
        return "핫 소스"
    }
}
