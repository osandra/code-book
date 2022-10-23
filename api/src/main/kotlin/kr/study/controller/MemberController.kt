package kr.study.controller

import kr.study.service.MemberService
import kr.study.service.dto.MemberDto
import kr.study.service.dto.MemberInfoDto
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping("/members")
    fun createMember(@RequestBody memberInfoDto: MemberInfoDto): MemberDto {
        return memberService.create(memberInfoDto)
    }

    @PatchMapping("/members/{id}")
    fun update(@PathVariable id: Int, @RequestBody memberInfoDto: MemberInfoDto) {
        memberService.update(id, memberInfoDto)
    }
}
