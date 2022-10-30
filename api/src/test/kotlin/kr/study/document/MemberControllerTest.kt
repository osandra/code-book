package kr.study.document

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kr.study.RestDocsSupport
import kr.study.service.MemberService
import kr.study.service.dto.MemberDto
import kr.study.service.dto.MemberInfoDto
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class MemberControllerDocumentTest: RestDocsSupport() {
    @MockBean
    private lateinit var memberService: MemberService

    protected val objectMapper: ObjectMapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())

    @Test
    fun `회원 아이디를 통해 회원 정보를 조회할 수 있다`() {
        // given
        val givenMember = MemberDto(
            id = 1,
            email = "test@gmail.com",
            name = "givenName"
        )

        Mockito.`when`(memberService.getMember(givenMember.id))
            .thenReturn(givenMember)

        // when
        mockMvc.perform(
            RestDocumentationRequestBuilders
                .get("/members/{id}", givenMember.id)
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "members/GetMember",
                    pathParameters(
                        parameterWithName("id").description("멤버 아이디")
                    ), responseFields(
                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                        fieldWithPath("email").type(JsonFieldType.STRING).description("멤버 이메일"),
                        fieldWithPath("name").type(JsonFieldType.STRING).description("멤버 이름")
                    )
                )
            )
    }

    @Test
    fun `회원은 가입하기 위해 이메일과 이름 정보가 필수다`() {
        // given
        val givenMemberInfo = MemberInfoDto(
            email = "email@gmail.com",
            name = "name"
        )

        val givenMember = MemberDto(
            id = 1,
            email = givenMemberInfo.email,
            name = givenMemberInfo.name
        )

        Mockito.`when`(memberService.create(givenMemberInfo))
            .thenReturn(givenMember)

        // when
        mockMvc.perform(
                RestDocumentationRequestBuilders
                    .post("/members")
                    .contentType(APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(givenMemberInfo))
            )
                .andExpect(status().isOk)
                .andDo(
                    document(
                        "members/CreateMember",
                        requestFields(
                            fieldWithPath("email").description("email 값"),
                            fieldWithPath("name").description("name 값")
                        ),
                        responseFields(
                            fieldWithPath("id").type(JsonFieldType.NUMBER).description("멤버 아이디"),
                            fieldWithPath("email").type(JsonFieldType.STRING).description("멤버 이메일"),
                            fieldWithPath("name").type(JsonFieldType.STRING).description("멤버 이름")
                        )
                    )
                )
    }
}
