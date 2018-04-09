package com.example.ymdarake.demo.aboutinterface


/**
 * * interface
 *
 * ** no constructor
 * ** no state (no properties with backing field)
 */
interface Greeter {
	val language: String
	fun sayHello(target: String)
}

class EnglishGreeter : Greeter {
	override val language: String = "English"
	override fun sayHello(target: String) {
		println("Hello, $target!")
	}
}

/**
 * * implementing multiple interfaces
 */
open class SuperClass
interface Foo
interface Bar
class DemoClass: SuperClass(), Foo, Bar


/**
 * * implementing methods
 */

/**
 * * 1. same signature in different interfaces
 */
interface FooDemo1 {
	fun execute()
}
interface BarDemo1 {
	fun execute()
}
class FooBarDemo : FooDemo1, BarDemo1 {
	override fun execute() {
		println("FooBar")
	}
}

/**
 * * 2. same signature in an interface and a class
 */
interface FooDemo2 {
	fun execute()
}
open class SuperClassDemo2 {
	open fun execute() {
		println("SuperClass")
	}
}
class FooSubDemo2: SuperClassDemo2(), FooDemo2

/**
 * * 3. same signature with implementation in different interfaces
 */
interface HogeDemo1 {
	fun execute() {
		println("Hoge")
	}
}
interface FugaDemo1 {
	fun execute() {
		println("Fuga")
	}
}
class HogeFugaDemo1: HogeDemo1, FugaDemo1 {
	override fun execute() {
		super<HogeDemo1>.execute()
	}
}


/**
 * * extending other interfaces
 */
interface FooEx {
	fun car()
	fun cdr()
}
interface BarEx: FooEx {
	override fun car() {
		println("some great implementation")
	}
	fun cadr()
}
class Baz: BarEx {
	override fun cdr() {
		println("excellent implementation")
	}
	override fun cadr() {
		println("simple implementation")
	}
}

/**
 * * Decorator (NOTE: use delegation described in the next section)
 */
interface GreeterDemo {
	fun sayHello(target: String)
	fun sayHello()
}
class JapaneseGreeterDemo: GreeterDemo {
	override fun sayHello() {
		println("こんにちは、匿名さん")
	}
	override fun sayHello(target: String) {
		println("こんにちは、${target}さん")
	}
}
class JapaneseGreeterWithRecording: GreeterDemo {

	private val greeter: GreeterDemo = JapaneseGreeterDemo()
	private val _targets: MutableSet<String> = mutableSetOf()
	val targets: Set<String>
		get() = _targets

	override fun sayHello() {
		greeter.sayHello()
	}

	override fun sayHello(target: String) {
		_targets += target
		greeter.sayHello(target)
	}

}

/**
 * * class delegation
 */
class GreeterWithRecording(private val greeter: Greeter) : GreeterDemo by greeter {
	/**
	 * `Greeter by greeter` means that 'This class implement the Greeter, but members not implemented are delegated to `greeter`'.
	 */
	private val _targets: MutableSet<String> = mutableSetOf()
	val targets: Set<String>
		get() = _targets

	override fun sayHello(target: String) {
		_targets += target
		greeter.sayHello(target)
	}

	/**
	 * no need to implement `sayHello()`
	 * instead, greeter.sayHello() will be called
	 */
}
