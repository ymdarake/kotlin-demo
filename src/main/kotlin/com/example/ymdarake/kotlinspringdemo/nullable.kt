package com.example.ymdarake.demo.nullable


/**
 * * smart cast
 */
fun smartCast() {
	val a: String? = null
	val b: String? = "Hello"

//	compile error
//	a.toUpperCase()

	if (a != null) {
		println(a.toUpperCase())
	}
	if (b != null) {
		println(b.toUpperCase())
	}

	val list: List<Any> = listOf(1, 'a', false)
	for (e in list) {
		val result: Any? = when (e) {
			is Int -> e + 5
			is Char -> e.toUpperCase()
			is Boolean -> e.not()
			else -> null
		}
		println(result)
	}

}

/**
 * * safe call
 */
fun safeCall() {
	val a: Int? = 5
	val aInc: Int? =
			if (a != null) a.inc()
			else null
	val aIncSafeCall: Int? = a?.inc()

	fun square(i: Int): Int = i * i
	val b: Int? = 5
	val bSquared =
			if (b != null) square(b)
			else null
	/**
	 * if b is null then `let` will not be executed.
	 * if b is NOT null then the value of b will be applied to `square`
	 */
	b?.let { square(it) }
	b?.let(::square)

	/**
	 * * force casting
	 * can throw kotlin.KotlinNullPointerException
	 */
	val casted: Int = b!!
	val castedWithRequireNotNull = requireNotNull(b, {"this cannot be null!!!"}) // can throw exception with the message.

	/**
	 * * elvis operator
	 * ** in case of null use default value, otherwise use specified value.
	 */
	val foo: String? = "Hello"
	(foo ?: "default").toUpperCase()

	/**
	 * * safe cast
	 */
	val str: Any = "I am String"
	str as String
	str as Int // throw ClassCastException
	str as? Int // return null
}









