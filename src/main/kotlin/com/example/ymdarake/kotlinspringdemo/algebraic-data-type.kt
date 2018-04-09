package com.example.ymdarake.demo.algebraicdatatype

/**
 * [Official doc](https://kotlinlang.org/docs/reference/sealed-classes.html)
 */
class DocHolder

/**
 * * sealed class
 * ** can be extended inside itself
 */
sealed class MyList<out T> {
	object Nil: MyList<Nothing>() {
		override fun toString(): String = "Nil"
	}
	class Cons<T>(val head: T, val tail: MyList<T>): MyList<T>() {
		override fun toString(): String = "$head:$tail"
	}

	fun headString(list: MyList<*>): String =
			when (list) {
				is MyList.Cons<*> -> list.head.toString()
				is MyList.Nil -> "Nil"
			}
}

/**
 * * enum
 * ** use instead of the sealed class whose all children classes are singleton
 */
sealed class DrinkSizeTypeSealed {
	object Small: DrinkSizeTypeSealed()
	object Medium: DrinkSizeTypeSealed()
	object Large: DrinkSizeTypeSealed()
}
// can be written in the simple way
enum class DrinkSizeTypeEnum {
	SMALL,
	MEDUIM,
	LARGE
}

val myFavoriteSize: DrinkSizeTypeEnum = DrinkSizeTypeEnum.LARGE

/**
 * ** enum can have
 * *** constructor and properties
 * *** implementations of each object
 * ** enum automatically implements `values` and `valueOf`
 */
enum class DrinkSizeTypeWithProperty(val milliliter: Int) {
	SMALL(300) {
		override fun message(): String = "too small???"
	},
	MEDIUM(500) {
		override fun message(): String = "so goooood!"
	},
	LARGE(700) {
		override fun message(): String = "X<"
	};
	abstract fun message(): String
}
val myFavoriteSizemilliliter = DrinkSizeTypeWithProperty.LARGE.milliliter

val youCanUseValues: Array<DrinkSizeTypeWithProperty> = DrinkSizeTypeWithProperty.values()
//val typeList = youCanUseValues.toList()
val favoriteTypeValue: DrinkSizeTypeWithProperty = DrinkSizeTypeWithProperty.valueOf("LARGE")

