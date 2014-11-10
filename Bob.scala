object HumanTextTypeResolver {
	def isYelling(text: String) : Boolean = {
		text.matches("""^\b(?=.*[A-Z])[A-Z0-9\s,]+\b(|!|\?)$""")
	}

	def isAskingQuestion(text: String) : Boolean = {
		text.matches("^(.*)\\?$")
	}
}

trait Human {
	def hey(text: String): String = {
		getResponse(text)
	}

	protected def getResponse(text: String): String
}

class Bob extends Human {
	protected def getResponse(text: String): String = {
		val (cleanText, defaultResponse) = (text.trim, "Whatever.")

		if (HumanTextTypeResolver.isYelling(cleanText)) {
			"Whoa, chill out!"
		} else if (HumanTextTypeResolver.isAskingQuestion(cleanText)) {
			"Sure."
		} else if (cleanText.length == 0) {
			"Fine. Be that way!"
		} else {
			defaultResponse
		}
	}
}