package com.example.ymdarake.demo.others

/**
 * * destructuring declaration
 */
fun destructuring() {
	val (name, age) = Pair("taro", 18)

	/**
	 * * declaring `componentN` enables to destruct properties
	 */
	val (a, b, c) = object {
		operator fun component1(): String = "Hello"
		operator fun component2(): Int = 123
		operator fun component3(): List<Char> = listOf('A', 'B')
	}
}

/**
 * * data class
 * ** automatically implementing `equals`, `hashcode` and `toString`
 * ** in addition, `copy`
 * ** also, `componentN`. in other words you can `destruct`
 */
data class User(val id: Long, val name: String)

fun dataClassDest() {
	val ymdarake = User(1, "ymdarake")
	val copied = ymdarake.copy(name="full of dream")
	val (id, name) = User(3, "Saburo")
}

/**
 * * nested class
 */
data class UserOuter(val id: Id, val name: String) {
	data class Id(val value: Long)
}

val id: UserOuter.Id = UserOuter.Id(1)
val u = UserOuter(id=id, name="hoge")


/**
 * * inner class
 * ** able to capture the outer context
 */
data class Actor(val id: Long, val name: String) {
	inner class Action {
		fun show(): String = "$name($id)"
	}
}


/**
 * * object declaration
 * ** Only ONE instance can exist for a class
 * ** use the class name to refer to the only object
 * ** no constructors
 * ** type will be generated
 */
interface Greeter {
	fun greet(name: String)
}
object JapaneseGreeter: Greeter {
	override fun greet(name: String) {
		println("こんにちは、${name}さん")
	}
}
fun objectDeclaration() {
	JapaneseGreeter.greet("じょん")
}

/**
 * * companion object
 * ** define Singleton object inside a class
 */
class CompanionUser(val id: Long, val name: String) {
	/**
	 * with the name not specified, `companion` is used by default
	 */
	companion object Pool {
		val DUMMY = CompanionUser(0, "dummy")
	}
}
val dummy = CompanionUser.DUMMY

