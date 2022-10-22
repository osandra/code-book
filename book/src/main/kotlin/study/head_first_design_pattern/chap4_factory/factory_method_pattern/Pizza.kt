package study.head_first_design_pattern.chap4_factory.factory_method_pattern

abstract class Pizza(
    private val name: String,
    private val dough: String,
    private val sauce: String,
    private val toppings: List<String>
) {
    fun prepare() {
        println("이름: $name")
        println("도우 $dough 준비 중")
        println("소스 $sauce 준비 중")
        println("토핑 ${toppings.joinToString(" ")} 준비 중")
    }

    fun bake() {
        println("굽는 중")
    }

    fun cut() {
        println("자르는 중")
    }
}

class NYStyleChessPizza(
    name: String = "뉴욕 스타일 치즈 피자",
    dough: String = "씬 크러스트 도우",
    sauce: String = "마리나라 소스",
    toppings: List<String> = listOf("잘게 썬 치즈")
) : Pizza(
    name = name,
    dough = dough,
    sauce = sauce,
    toppings = toppings
)

class NYStylePepperoniPizza(
    name: String = "뉴욕 스타일 페페로니 피자",
    dough: String = "씬 크러스트 도우",
    sauce: String = "핫 소스",
    toppings: List<String> = listOf("잘게 썬 치즈")
) : Pizza(
    name = name,
    dough = dough,
    sauce = sauce,
    toppings = toppings
)

class NYStyleVeggiePizza(
    name: String = "뉴욕 스타일 베지 피자",
    dough: String = "고구마 도우",
    sauce: String = "마요네즈 소스",
    toppings: List<String> = listOf("잘게 썬 치즈")
) : Pizza(
    name = name,
    dough = dough,
    sauce = sauce,
    toppings = toppings
)


class ChicagoStyleChessPizza(
    name: String = "시카고 스타일 치즈 피자",
    dough: String = "씬 크러스트 도우",
    sauce: String = "핫 소스",
    toppings: List<String> = listOf("올리브")
) : Pizza(
    name = name,
    dough = dough,
    sauce = sauce,
    toppings = toppings
)

class ChicagoStylePepperoniPizza(
    name: String = "시카고 스타일 페페로니 피자",
    dough: String = "씬 크러스트 도우",
    sauce: String = "바베큐 소스",
    toppings: List<String> = listOf("올리브")
) : Pizza(
    name = name,
    dough = dough,
    sauce = sauce,
    toppings = toppings
)

class ChicagoStyleVeggiePizza(
    name: String = "시카고 스타일 베지 피자",
    dough: String = "고구마 도우",
    sauce: String = "마요네즈 소스",
    toppings: List<String> = listOf("양파", "토마토")
) : Pizza(
    name = name,
    dough = dough,
    sauce = sauce,
    toppings = toppings
)
