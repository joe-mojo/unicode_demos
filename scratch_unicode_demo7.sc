import java.nio.charset.StandardCharsets
import java.text.Normalizer

def toHexString(buf: Array[Byte]): String = buf.map("%02X" format _).mkString(" ")
def toBinString(buf: Array[Byte]): String = buf.map(b => (b & 0xFF).toBinaryString).map(byteStr => byteStr.reverse.padTo(8, '0').reverse).mkString(" ")
def intToByteArray(i: Int): Array[Byte] = {
	Array(
		(i >>> 24).toByte,
		((i >>> 16) & 0xFF).toByte,
		((i >>> 8) & 0xFF).toByte,
		(i & 0xFF).toByte
	)
}

val example: String = "Hé ! Voilà 15€ 😉"

val nzExample = Normalizer.normalize(example, Normalizer.Form.NFD)

println(toHexString(example.getBytes(StandardCharsets.UTF_16)))
println(toHexString(nzExample.getBytes(StandardCharsets.UTF_16)))

println(s"Avec diacritiques: $example")
println(s"Sans diacritiques: ${nzExample.replaceAll("[\u0300-\u036F]", "")}")
