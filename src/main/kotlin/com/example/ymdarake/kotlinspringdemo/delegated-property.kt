package com.example.ymdarake.demo.delegatedproperty

import kotlin.reflect.KProperty

class DemoClass {

	var _str: String? = null

	/**
	 * * delegated object
	 * ** has `getValue` and `setValue`
	 * *** if you have the class `PropertyLogger` which has methods to be a delegated object,
	 * *** you can use that like
	 * *** var user: User by PropertyLogger()
	 */
	var str: String? by object {
		operator fun getValue(thisRef: DemoClass,
													property: KProperty<*>): String? {
			println("${property.name} was gotten!")
			return _str
		}

		operator fun setValue(thisRef: DemoClass,
													property: KProperty<*>,
													value: String?) {
			println("${value} was set to ${property.name}")
			_str = value
		}
	}

}

