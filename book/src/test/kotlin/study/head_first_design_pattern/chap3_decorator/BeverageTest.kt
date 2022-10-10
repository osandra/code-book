package study.head_first_design_pattern.chap3_decorator

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import study.head_first_design_pattern.chap3_decorator.MenuInfo.ESPRESSO
import study.head_first_design_pattern.chap3_decorator.MenuInfo.HOUSE_BLEND
import study.head_first_design_pattern.chap3_decorator.MenuInfo.MOCHA

internal class BeverageTest: AnnotationSpec() {
    @Test
    fun `에스프레스의 이름과 가격을 저장할 수 있다`() {
        val espresso = Espresso()
        espresso.description shouldBe ESPRESSO.description
        espresso.cost() shouldBe ESPRESSO.cost
    }

    @Test
    fun `음료에 모카샷을 한 번 이상 추가할 수 있다`() {
        val (espresso, houseblend) = Espresso() to HouseBlend()
        val (twoMochaWithEspresso, oneMochaWithHouseBlend) = Mocha(Mocha(espresso)) to Mocha(houseblend)

        twoMochaWithEspresso.description shouldBe listOf(ESPRESSO.description, MOCHA.description, MOCHA.description).joinToString(", ")
        twoMochaWithEspresso.cost() shouldBe (ESPRESSO.cost + 2 * MOCHA.cost)

        oneMochaWithHouseBlend.description shouldBe listOf(HOUSE_BLEND.description, MOCHA.description).joinToString(", ")
        oneMochaWithHouseBlend.cost() shouldBe (HOUSE_BLEND.cost + MOCHA.cost)
    }
}
