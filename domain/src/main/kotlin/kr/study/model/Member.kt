package kr.study.model

import javax.persistence.AttributeConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Converter
import javax.persistence.Entity

@Entity
class Member(
    name: String,
    email: String,
    status: Status = Status.ACTIVE
) : BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var email: String = email
        protected set

    @Convert(converter = StatusConverter::class)
    @Column(nullable = false)
    var status: Status = status
        protected set

    fun update(name: String, email: String) {
        this.name = name
        this.email = email
    }
}

enum class Status(val value: String) {
    ACTIVE("활성 상태"),
    INACTIVE("비활성 상태")
}

@Converter(autoApply = false)
class StatusConverter : AttributeConverter<Status, String> {
    override fun convertToDatabaseColumn(attribute: Status): String {
        return attribute.name
    }

    override fun convertToEntityAttribute(dbData: String): Status {
        return Status.values().firstOrNull { it.name.equals(dbData) } ?: throw RuntimeException("유효하지 않은 값입니다.")
    }
}
