package study.head_first_design_pattern.chap3_decorator

import java.io.FilterInputStream
import java.io.InputStream

// 데코레이터
class CustomLowercaseInputStream(private val inputStream: InputStream): FilterInputStream(inputStream) {

    companion object {
        const val END_OF_STREAM = -1
    }

    override fun read(): Int {
        val nextByte = inputStream.read()
        return if (nextByte == END_OF_STREAM) {
            nextByte
        } else {
            Character.toLowerCase(nextByte)
        }
    }

    override fun read(b: ByteArray, offset: Int, len: Int): Int {
        val result = inputStream.read(b, offset, len)
        var idx = offset
        while (idx < offset + result) {
            b[idx] = Character.toLowerCase(b[idx].toInt().toChar()).code.toByte()
            idx += 1
        }
        return result
    }
}
