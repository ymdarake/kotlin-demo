package com.example.ymdarake.demo.inheritance
/**
 * * package
 * ** function
 * ** property
 * ** class
 * ** interface
 * ** object
 */

fun demoFun(): String = "hoge"
val demoProperty = 1
class DemoClass
interface DemoInterface
object demo

/**
 * * visibility (top-level declaration)
 */
public fun demoPub() = "from anywhere"
internal fun demoInter() = "from the same compiled unit"
private fun demoPriv() = "from this file"


/**
 * * inheritance
 */
open class Person(open val name: String) {

	/**
	 * * to be overridden
	 */
	open fun introduceMyself() {
		println("I am $name.")
	}

	/**
	 * * visibility (class)
	 */
	public fun pub() = "from anywhere"
	internal fun int() = "from the same compiled unit"
	protected fun prot() = "same class and children"
	private fun priv() = "from me"

}

/**
 * * visibility (constructor)
 */
open class HogeHoge private constructor()
//class FugaFuga: HogeHoge() // compile error!


class StudentDemo1(/* no need to specify `val` */name: String, val id: Long): Person(name)

class StudentDemo2(name: String, private val id: Long): Person(name) {

	override val name: String
		get() = "anonymous;)"

	override fun introduceMyself(){
		println("I as $name(id=$id")
//		super.introduceMyself()
	}

}

/**
 * * Abstract class
 */
abstract class Greeter(val target: String) {
	abstract fun sayHello()
}

class EnglishGreeter(target: String) : Greeter(target) {
	override fun sayHello() {
		println("Hello, $target")
	}
}

class JapaneseGreeter(target: String) : Greeter(target) {
	override fun sayHello() {
		println("こんにちは、$target")
	}
}
