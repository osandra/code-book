package study.head_first_design_pattern.chap2_observer.push_method

interface Subject {
    val observers: MutableList<Observer>

    fun registerObserver(observer: Observer) {
        observers.add(observer)
    }

    fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers()
}

interface Observer {
    fun update(tmp: Float, humidity: Float, pressure: Float)
}

class WeatherData : Subject {
    override val observers: MutableList<Observer> = mutableListOf()
    private var temperature: Float = 0F
    private var humidity: Float = 0F
    private var pressure: Float = 0F

    override fun notifyObservers() {
        observers.forEach {
            it.update(temperature, humidity, pressure)
        }
    }

    fun measurementsChanged(tmp: Float, humidity: Float, pressure: Float) {
        this.temperature = tmp
        this.humidity = humidity
        this.pressure = pressure
        notifyObservers()
    }
}
