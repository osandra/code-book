package kr.study.service

import kr.study.model.Member
import kr.study.repository.MemberRepository
import kr.study.service.dto.MemberDto
import kr.study.service.dto.MemberInfoDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository
){

    @Transactional
    fun create(memberInfoDto: MemberInfoDto): MemberDto {
        val member = Member(name = memberInfoDto.name, email = memberInfoDto.email)
        memberRepository.save(member)
        return MemberDto.of(member)
    }

    @Transactional
    fun update(id: Int, memberInfoDto: MemberInfoDto) {
        val member = memberRepository.findByIdOrNull(id) ?: throw RuntimeException("해당하는 회원이 없습니다")
        member.update(name = memberInfoDto.name, email = memberInfoDto.email)
    }

    fun getMember(id: Int): MemberDto {
        val member = memberRepository.findByIdOrNull(id) ?: throw RuntimeException("해당하는 회원이 없습니다")
        return MemberDto.of(member)
    }
}
