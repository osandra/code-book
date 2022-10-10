package study.head_first_design_pattern.chap3_decorator

enum class MenuInfo(val cost: Double, val description: String) {
    ESPRESSO(1.99, "에스프레소"),
    HOUSE_BLEND(0.89, "하우스 블랜드"),
    MOCHA(0.20, "모카"),
    WHIP(0.40, "휘핑"),
}
