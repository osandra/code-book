package study.head_first_design_pattern.chap2_observer.pull_mehod

interface DisplayElement {
    fun display()
}

class CurrentConditionsDisplay(private val weatherData: WeatherData) : Observer, DisplayElement {
    private var temperature: Float = 0F
    private var humidity: Float = 0F

    init {
        weatherData.registerObserver(this)
    }

    override fun update() {
        this.temperature = weatherData.temperature
        this.humidity = weatherData.humidity
        display()
    }

    override fun display() {
        println("햔재 상태: 온도 = $temperature F, 습도: $humidity %")
    }
}

class CurrentPressureDisplay(private val weatherData: WeatherData) : Observer, DisplayElement {
    private var pressure: Float = 0F

    init {
        weatherData.registerObserver(this)
    }

    override fun update() {
        this.pressure = weatherData.pressure
        display()
    }

    override fun display() {
        println("햔재 기압: $pressure")
    }
}

fun main() {
    val weatherData = WeatherData()
    val conditionsDisplay = CurrentConditionsDisplay(weatherData)
    val pressureDisplay = CurrentPressureDisplay(weatherData)

    // 기상 정보를 구독하고 있는 2개의 디스플레이에서 정보 변경된 것이 출력된다
    // 햔재 상태: 온도 = 80.0 F, 습도: 65.0 %
    // 햔재 기압: 30.4
    weatherData.measurementsChanged(80f, 65f, 30.4f)

    weatherData.removeObserver(conditionsDisplay)
    // 기상 정보를 구독하고 있는 1개의 디스플레이에서만(pressureDisplay) 정보 변경된 것이 출력된다
    // 햔재 기압: 30.4
    weatherData.measurementsChanged(20f, 65f, 30.4f)
}
