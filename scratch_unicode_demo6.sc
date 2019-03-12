import java.text.{CollationKey, Collator}

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

val child = "El niño"
val childUpper = "EL NIÑO"
val childLower = "el niño"
val childUS = "El nino"
val childLowerUs = "el nino"

val collator = Collator.getInstance()
collator.setStrength(Collator.PRIMARY) // a vs b
collator.compare(child, childUpper)
collator.compare(child, childLower)
collator.compare(child, childUS)
collator.compare(child, childLowerUs)
collator.compare(childUS, childLowerUs)

collator.setStrength(Collator.SECONDARY) // a vs ä
collator.compare(child, childUpper)
collator.compare(child, childLower)
collator.compare(child, childUS)
collator.compare(child, childLowerUs)
collator.compare(childUS, childLowerUs)

collator.setStrength(Collator.TERTIARY) // a vs A
collator.compare(child, childUpper)
collator.compare(child, childLower)
collator.compare(child, childUS)
collator.compare(child, childLowerUs)
collator.compare(childUS, childLowerUs)

collator.setStrength(Collator.IDENTICAL)
collator.compare(child, childUpper)
collator.compare(child, childLower)
collator.compare(child, childUS)
collator.compare(child, childLowerUs)
collator.compare(childUS, childLowerUs)

val childCK = collator.getCollationKey(child)
val childUpperCK = collator.getCollationKey((childUpper))

childCK.compareTo(childUpperCK)

collator.setStrength(Collator.SECONDARY)
childCK.compareTo(childUpperCK)

val childCK2 = collator.getCollationKey(child)
val childUpperCK2 = collator.getCollationKey((childUpper))
childCK2.compareTo(childUpperCK2)

