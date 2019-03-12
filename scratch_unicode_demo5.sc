import java.nio.charset.StandardCharsets


def toHexString(buf: Array[Byte]): String = buf.dropWhile(_ == 0).map("%02X" format _).mkString(" ")
def toBinString(buf: Array[Byte]): String = buf.dropWhile(_ == 0).map(b => (b & 0xFF).toBinaryString).map(byteStr => byteStr.reverse.padTo(8, '0').reverse).mkString(" ")
def intToByteArray(i: Int): Array[Byte] = {
	Array(
		(i >>> 24).toByte,
		((i >>> 16) & 0xFF).toByte,
		((i >>> 8) & 0xFF).toByte,
		(i & 0xFF).toByte
	)
}

//Run:
val example: String = "Hé ! Voilà 15€ 😉"
val exampleBytes = example.getBytes(StandardCharsets.UTF_8)
val exampleCassé = new String(exampleBytes, StandardCharsets.ISO_8859_1)
println(s"example: $example")
println(s"example après lecture contenu UTF-8 avec encoding ASCII: $exampleCassé")
