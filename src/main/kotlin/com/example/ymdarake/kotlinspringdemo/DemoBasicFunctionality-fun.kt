
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

	/**
	 * class instantiation
	 */
	val niceBucket: Bucket = BucketDemoImpl(3)
	val greatBucket: Bucket = BucketDemoImpl(9)
	niceBucket.fill()
	niceBucket.pourTo(greatBucket)
	println(niceBucket.quantity)
	println(greatBucket.quantity)

}

/**
 * * class
 */
class BucketDemoImpl(_capacity: Int) : Bucket {

	override val capacity = _capacity

	override var quantity: Int = 0

	override fun fill() {
		quantity = capacity
	}

	override fun drainAway() {
		quantity = 0
	}

	override fun pourTo(that: Bucket) {
		val thatVacuity = that.capacity - that.quantity
		if (capacity <= thatVacuity) {
			that.quantity += quantity
			drainAway()
		} else {
			that.fill()
			quantity -= thatVacuity
		}
	}

}


/**
 * * class and property
 */
class Person {

	/**
	 * with automatically generated Backing-field
	 */
	var name: String = ""
	var age: Int = 0
	lateinit var atode: String//no need to init. Be careful not to access before initializing

	/**
	 * with Custom-getter
	 * without Backing-field
	 */
	val nameLength: Int
		get(): Int {
			return this.name.length
		}
	// get() = name.length //`this` is redundant.

}

/**
 * * Primary constructor
 */
class RationalwithConstructorKeyword constructor(n: Int, d: Int) {
	val numerator: Int = n
	val denominator: Int = d + 999//oe something else..
}

/**
 * * Primary constructor
 * * using parameters as properties
 */
class RationalWithPropertyconstructor(val numerator: Int, val denominator: Int/* can set a default value */) {

	/**
	 * Secondary constructor
	 * TODO: research more
	 */
	constructor(numerator: Int) : this(numerator, 1)

}