package kr.study.model

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Member(
    name: String,
    email: String,
) : BaseEntity() {
    @Column(nullable = false)
    var name: String = name
        protected set

    @Column(nullable = false)
    var email: String = email
        protected set
}
