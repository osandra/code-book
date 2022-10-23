package kr.study.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    @UpdateTimestamp
    @Column(nullable = false)
    var updatedAt: ZonedDateTime = ZonedDateTime.now()
        protected set
}
