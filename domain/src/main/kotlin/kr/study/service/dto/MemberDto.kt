package kr.study.service.dto

import kr.study.model.Member
import kr.study.model.Status

class MemberDto(
    val id: Int,
    val email: String,
    val name: String,
    val status: Status
){
    companion object {
        fun of(member: Member): MemberDto {
            return MemberDto(
                id = member.id,
                email = member.email,
                name = member.name,
                status = member.status
            )
        }
    }
}

data class MemberInfoDto(
    val email: String,
    val name: String
)
