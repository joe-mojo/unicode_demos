import java.text.BreakIterator
import scala.annotation.tailrec

val example: String = "Voilà 15€ 😉"

val brkIter = BreakIterator.getCharacterInstance
brkIter.setText(example)

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

printEachForward(brkIter, example).foreach(println)