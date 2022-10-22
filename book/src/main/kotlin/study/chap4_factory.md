### 4장 팩토리 패턴

> 팩토리 메서드 패턴

팩토리 메서드 패턴은 슈퍼클래스에서(상위 클래스) 객체를 만들기 위한 인터페이스를 제공하지만
하위 클래스가 만들 객체의 구체적인 타입을 결정할 수 있도록 하는 패턴입니다.

슈퍼 클래스의 경우, 하위 클래스에서 팩토리 메서드 구현하는 것을 강제하려면 abstract 메서드로 정의하고 기본 객체를 반환하려면 기본 메서드로 구현할 수 있습니다.

구상 생산자(구체적인 서브) 클래스가 하나밖에 없더라도, 제품을 생산하는 부분과 사용하는 부분을 분리한다는 관점에서 팩토리 메서드 패턴은 유용합니다. 
팩토리 메서드 패턴을 사용하고 있다면 추후 새로운 타입의 제품이 생길 때 새 서브 클래스를 정의하고 생성하는 부분 코드만 작성하기만 하면 됩니다.

- 구조
![https://refactoring.guru/images/patterns/diagrams/factory-method/structure-2x.png?id=9ea3aa8a47f8be22e12e523c15b448fd](https://refactoring.guru/images/patterns/diagrams/factory-method/structure-2x.png?id=9ea3aa8a47f8be22e12e523c15b448fd)
<p style="text-align:center"><a href="https://refactoring.guru/design-patterns/factory-method" 
target="_blank">이미지 출처</a></p>

팩토리 메서드 패턴 예로 아래 PizzaStore 클래스는 실제 피자를 생성하는 부분을 하위 클래스에서 결정하도록 강제하고 있습니다.
즉 추상 메서드(createPizza)를 통해 Pizza 타입을 반환하도록 정의했습니다.
이를 통해 하위 클래스에서는 추상 메서드(createPizza)를 구현하여, Pizza 타입의 원하는 피자를 반환해야 합니다.
```kotlin
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

// PizzaStore 의 하위클래스인 NYPizzaStore
class NYPizzaStore : PizzaStore() {
    override fun createPizza(pizzaType: PizzaType): Pizza {
        return when (pizzaType) {
            CHEESE -> NYStyleChessPizza()
            PEPPERONI -> NYStylePepperoniPizza()
            VEGGIE -> NYStyleVeggiePizza()
        }
    }
}
```

```kotlin
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
```

> 추상 팩토리 패턴

추상 팩토리 메서드 패턴은 연관된 객체들로 이뤄진 제품군을 구체적인 클래스에 의존하지 않고도
생산할 수 있도록 하는 패턴입니다.
- 구조
![https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure-2x.png](https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure-2x.png)
<p style="text-align:center"><a href="https://refactoring.guru/design-patterns/abstract-factory" 
target="_blank">이미지 출처</a></p>

- 추상 팩토리 패턴과 팩토리 메서드 패턴
    - 공통점: 애플리케이션을 특정 구현으로부터 분리 (ex 객체 생성을 캡슐화)
    - 차이점
        - 추상 팩토리 패턴: 구성(composite)로 객체 생성
            - 클라이언트와 서로 연관된(의존적인) 일련의 제품군을 만들어야 할 때 사용
            - 꽤 많은 제품이 들어있는 제품군 생성 가능
            - 많은 새로운 인터페이스와 클래스가 도입되기 코드가 필요 이상으로 더 복잡해질 수 있음
        - 팩토리 메서드 패턴: 상속으로 객체 생성
            - 한 제품을 생산하는 데 필요한 추상 인터페이스를 클래스에 선언
            - 클래스 확장하고 각 서브 클래스에서 팩토리 메서드를 오버라이딩
            
- 구상(구체) 클래스 의존성을 줄이면 좋다
    - 한 객체에서 수많은 다른 객체에 의존하게 된다면, 다른 객체에 변경사항이 생길 때 함께 수정해야 할 수 있기 때문이다.
      → 관련 디자인 원칙: Dependency Inversion Principle (추상화된 것에 의존하게 만들고, 구상 클래스에 의존하지 않게 만든다)
      - 고수준 구성요소가 저수준 구성요소에 의존하면 안 되며, 항상 추상화에 의존하게 만들어야 한다.
      - 고수준 구성요소는 다른 저수준 구성요소에 의해 정의되는 행동이 들어있는 구성 요소를 뜻한다.

---
- **Reference**
    - 헤드 퍼스트 디자인 패턴 도서
    - https://refactoring.guru
