package study.head_first_design_pattern.chap3_decorator

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import study.head_first_design_pattern.chap3_decorator.CustomLowercaseInputStream.Companion.END_OF_STREAM
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.util.Locale

internal class CustomLowercaseInputStreamTest: AnnotationSpec() {

    @Test
    fun `대문자로 적힌 단어를 모두 소문자로 컨버팅할 수 있다`() {
        // given
        val givenText = "It is for TEST"
        var result = ""
        val file = File("test").apply {
            this.writeText(givenText)
        }

        // when
        val givenFileStream = FileInputStream(file)
        val givenLowercaseInputStream = CustomLowercaseInputStream(BufferedInputStream(givenFileStream))

        givenLowercaseInputStream.use {
            while (true) {
                when (val nextByte = it.read()) {
                    END_OF_STREAM -> break
                    else -> result += nextByte.toChar()
                }
            }
        }
        file.delete()

        // then
        result shouldBe (givenText.lowercase(Locale.getDefault()))
    }
}
