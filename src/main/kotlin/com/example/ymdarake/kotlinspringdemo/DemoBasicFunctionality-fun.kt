
class Demo {

	/**
	 * * inline function
	 * TODO: research more
	 * - noinline
	 * - crossinline
 	 */
	inline fun log(debug: Boolean = true, message: () -> String) {
		if (debug) {
			println(message())
		}
	}

	/**
	 * * non-local return, labeled return
	 * Lambdas returning non-locally must be inline-extracted.
	 */
	inline fun forEach(str: String, f: (Char) -> Unit) {
		for (c in str) {
			(f(c))
		}
	}
	fun containsDigit(str: String): Boolean {
		forEach(str) {
			if (it.isDigit())
				return true// non-local return
		}
		return false
	}
	fun containsDigitLabeled(str: String): Boolean {
		var result = false
		forEach(str) {
			if (it.isDigit()) {
				result = true
				return@forEach//specify the name from which you want to return
			}
		}
		return result
	}

}

fun main(args: Array<String>) {

	/**
	 * object expression
	 */
	val bucket = object {
		val capacity: Int = 5
		var quantity: Int = 0
		fun fill() {
			quantity = capacity
		}
		fun drainAway() {
			quantity = 0
		}
		fun printQuantity() {
			println(quantity)
		}
	}
	bucket.printQuantity()
	bucket.fill()
	bucket.printQuantity()
	bucket.drainAway()
	bucket.printQuantity()

}
