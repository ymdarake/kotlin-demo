
// class definition
class Rational(val numerator: Int, val denominator: Int) {

	// initializer
	init {
		require(denominator != 0, { "denominator must not be null" })
	}

	// override
	// string extraction
	override fun toString(): String = "$numerator/${denominator}"

	// tail recursion
	// private fun
	// if expression
	tailrec private fun gcd(a: Int, b: Int): Int =
			if (b == 0) a
			else gcd(b, a % b)

//	fun plus(that: Rational): Rational =
//			Rational(
//					numerator * that.denominator + that.numerator * denominator,
//				denominator * that.denominator
//			)

	// operator over loading
	operator fun plus(that: Rational): Rational =
			Rational(
					numerator * that.denominator + that.numerator * denominator,
					denominator * that.denominator
			)

}


class DemoIterator {
	operator fun hasNext(): Boolean = Math.random() < 0.5
	operator fun next(): String = "まだまだ続くよ\n"
}
class DemoIterable {
	operator fun iterator(): DemoIterator = DemoIterator()
}

fun main(args: Array<String>) {

	// constructor, property, accessor
	val r = Rational(1, 2)
	println(r.denominator)

	// if expression
	// type inference
	val message = if (true) "OK" else "NG"

	fun demoWhen(x: Any): String =
			when (x) {
				is String -> "String"
				in 2..10 	-> "between 2 and 10"
				else			-> "something else"
			}


	for (x in 1..10) {
		println(x)
	}

	for (value in DemoIterable()) {
		println(value)
	}

	labeledLoop@ for (x in 1..10) {
		for (y in 100 downTo 90) {
			if (y == 95)
				break@labeledLoop
		}
	}

	fun succ(i: Int): Int = i + 1
	fun sum(ints: Array<Int>): Int /* type must be explicitly specified */ {
		return ints.sum()
	}
	sum(arrayOf(3, 4, 5))

	// named parameter
	fun greet(message: String = "Hello", name: String = "World") = message + "," + name
	greet(name = "Taro")

	// inner method
	// tail recursion
	fun sumTailRec(numbers: List<Long>): Long {
		tailrec fun iterate(numbers: List<Long>, accumulator: Long): Long {
			return if (numbers.isEmpty()) accumulator
			else iterate(numbers.drop(1), accumulator + numbers.first())
		}
		return iterate(numbers, 0)
	}


	// function as an Object
	fun square(i: Int): Int = i * i
	val squareObject: (Int) -> Int = ::square
	println(squareObject(10))

	// lambda expression (functional literal)
	val lambdaSquare1 = { i:Int ->
		i * i
	}
	val lambdaSquare2: (Int) -> Int = { i ->
		i * i
	}
	val lambdaWithIt: (Int) -> Int = {
		it * it
	}

	fun firstIndexOf(str: String, predicate: (Char) -> Boolean): Int {
		tailrec fun iterate(str: String, index: Int): Int =
			when {
				str.isEmpty() -> -1
				predicate(str.first()) -> index
				else -> iterate(str.drop(1), index + 1)
			}
		return iterate(str, 0)
	}

	fun firstIndexOfWhitespace(str: String) =
			firstIndexOf(str) {
				it.isWhitespace()
			}
}

///////////////////////////////////////

// Unit
class Counter {
	private var count = 0
	fun countUp(): Unit {
		count++
//		return Unit
	}
	fun getcount(): Int = count
}
