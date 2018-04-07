
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

/**
 * * interface
 */
interface Bucket {
	fun fill()
	fun drainAway()
	fun pourTo(that: Bucket)

	/**
	 * property
	 */
	val capacity: Int
	var quantity: Int
}

fun main(args: Array<String>) {

	/**
	 * * object expression
	 */
	fun createBucket(capacity: Int): Bucket = object : Bucket {

		override fun fill() {
			quantity = capacity
		}

		override fun drainAway() {
			quantity = 0
		}

		override fun pourTo(that: Bucket) {
			val thatVacuity = that.capacity - that.quantity
			if (quantity <= thatVacuity) {
				that.quantity += quantity
				drainAway()
			} else {
				that.fill()
				quantity -= thatVacuity
			}
		}

	}

}
