import java.nio.charset.StandardCharsets
import java.text.BreakIterator

import scala.annotation.tailrec


def printEachForward(boundary: BreakIterator, source: String): Seq[String] = {
	@tailrec
	def doPrintEachForward(accu: Seq[String], start: Int, end: Int): Seq[String] = {
		if(end == BreakIterator.DONE)
			accu
		else
			doPrintEachForward(accu :+ source.substring(start, end), end, boundary.next())
	}
	doPrintEachForward(Seq.empty, boundary.first(), boundary.next())
}

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

val brkIter = BreakIterator.getCharacterInstance
brkIter.setText(example)
printEachForward(brkIter, example).map { charString =>
	val utf16Bytes = charString.getBytes(StandardCharsets.UTF_16BE)
	val codePoint = charString.codePointAt(0)
	val codePointBytes = intToByteArray(codePoint)
	s"$charString | ${toHexString(codePointBytes)} | ${toBinString(codePointBytes)} | ${toHexString(utf16Bytes)} | ${toBinString(utf16Bytes)}"
}.foreach(println)