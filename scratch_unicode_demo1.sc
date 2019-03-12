import java.nio.charset.{Charset, StandardCharsets}

def toHexString(buf: Array[Byte]): String = buf.map("%02X " format _).mkString.trim
def charToHexString(c: Char): String = "%02X" format(c.toInt & 0xFFFF)


val example: String = "Voilà 15€ 😉" //U+1F609 utf-8 = F0 9F 98 89
val exampleDecompo: Seq[String] = Seq("V", "o", "i", "l", "à", " ", "1", "5", "€", " ", "😉")


example.mkString("|")
example.map(charToHexString)

println("String | code point| chars | UTF-32 bytes | UTF-16 bytes | UTF-8 bytes")
exampleDecompo.map { str =>
	s"$str | ${str.codePointAt(0).toHexString} | ${str.mkString("")} | ${toHexString(str.getBytes(Charset.forName("UTF-32")))} | ${toHexString(str.getBytes(StandardCharsets.UTF_16BE))} | ${toHexString(str.getBytes(StandardCharsets.UTF_8))}"
}.foreach(println)

for( i <- 0 to example.length) {
	println(s"${example.charAt(i)} | ${example.codePointAt(i).toHexString}")
}
