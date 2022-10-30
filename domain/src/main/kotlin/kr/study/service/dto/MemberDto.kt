package kr.study.service.dto

import kr.study.model.Member

class MemberDto(
    val id: Int,
    val email: String,
    val name: String
){
    companion object {
        fun of(member: Member): MemberDto {
            return MemberDto(
                id = member.id,
                email = member.email,
                name = member.name
            )
        }
    }
}

data class MemberInfoDto(
    val email: String,
    val name: String
)
