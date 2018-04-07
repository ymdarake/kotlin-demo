
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

	fun getCapacity(): Int
	fun getQuantity(): Int
	fun setQuantity(quantity: Int)
}

fun main(args: Array<String>) {

	/**
	 * * object expression
	 */
	fun createBucket(capacity: Int): Bucket = object : Bucket {

		var _quantity: Int = 0

		override fun getCapacity(): Int = capacity
		override fun getQuantity(): Int = _quantity
		override fun setQuantity(quantity: Int) {
			_quantity = quantity
		}

		override fun fill() {
			setQuantity(getQuantity())
		}

		override fun drainAway() {
			setQuantity(0)
		}

		override fun pourTo(that: Bucket) {
			val thatVacuity = that.getCapacity() - that.getQuantity()
			if (getQuantity() <= thatVacuity) {
				that.setQuantity(that.getQuantity() + getQuantity())
				drainAway()
			} else {
				that.fill()
				setQuantity(getQuantity() - thatVacuity)
			}
		}

	}

}
