package com.example.ymdarake.demo.generics

/**
 * [Official Doc](https://kotlinlang.org/docs/reference/generics.html)
 */
class DocHolder


/**
 * * generic class
 */
class Container<T>(var value: T)
val intContainer = Container(1)
val intValue = intContainer.value

/**
 * * generic function
 * * generic property
 */
fun <T> box(value: T): Container<T> = Container(value)
val <T> T.string: String
	get() = toString()

/**
 * Upper bounds
 */
interface Hoge
interface Fuga
interface Piyo: Hoge, Fuga
class Foo<T>
class Bar<T : Hoge>
class Baz<T> where T : Hoge, T : Fuga
fun main(args: Array<String>) {
	Foo<Hoge>()
	Foo<Fuga>()

	Bar<Hoge>()
	//Bar<Fuga>() // compile error

	Baz<Piyo>()
}


/**
 * * variance
 */

/**
 * declaration-site variance:
 * we can annotate the type parameter T of Source to make sure that
 * it is only returned (produced) from members of Source<T>, and never consumed.
 * To do this we provide the out modifier.
 */
interface Source<out T> {
	fun nextT(): T
}
fun demo(strs: Source<String>) {
	val objects: Source<Any> = strs // This is OK, since T is an out-parameter
	// ...
}

/**
 * In addition to out, Kotlin provides a complementary variance annotation: in.
 * It makes a type parameter contravariant: it can only be consumed and never produced.
 */
interface Comparable<in T> {
	operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
	x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
	// Thus, we can assign x to a variable of type Comparable<Double>
	val y: Comparable<Double> = x // OK!
}


/**
 * * star projection
 * ** syntax sugar of `in Nothing` and `out Any?`
 *
 * Sometimes you want to say that you know nothing about the type argument,
 * but still want to use it in a safe way.
 * The safe way here is to define such a projection of the generic type,
 * that every concrete instantiation of that generic type would be a subtype of that projection.
 */
val a: Container<*> = Container<Int>(5)


/**
 * reified type
 */
//fun <T> Any.instanceOf(): Boolean = this is T // compile error because of the type erasure
inline fun <reified T> Any.instanceOf(): Boolean = this is T

