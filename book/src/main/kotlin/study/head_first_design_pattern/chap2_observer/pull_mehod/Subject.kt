package study.head_first_design_pattern.chap2_observer.pull_mehod

interface Subject {
    val observers: MutableList<Observer>

    fun registerObserver(o: Observer) {
        observers.add(o)
    }

    fun removeObserver(o: Observer) {
        observers.remove(o)
    }

    fun notifyObservers() {
        observers.forEach {
            it.update()
        }
    }
}

interface Observer {
    fun update()
}

class WeatherData : Subject {
    override val observers: MutableList<Observer> = mutableListOf()

    var temperature: Float = 0F
    var humidity: Float = 0F
    var pressure: Float = 0F

    fun measurementsChanged(tmp: Float, humidity: Float, pressure: Float) {
        this.temperature = tmp
        this.humidity = humidity
        this.pressure = pressure
        notifyObservers()
    }
}
