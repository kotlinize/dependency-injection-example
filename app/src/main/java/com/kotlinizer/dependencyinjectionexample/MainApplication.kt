package com.kotlinizer.dependencyinjectionexample

import android.app.Application
import com.kotlinizer.injection.Injector

class MainApplication : Application() {

	private val injector: Injector by lazy { Injector.instance }

	override fun onCreate() {
		super.onCreate()

		// Load Dependencies
		val firstName = "Kotlin"
		val lastName = "Dependency-Injection"
		val phoneNumber = 5555555555L

		injector.register(type = String::class.java, provider = firstName, identifier = "FIRST_NAME")
		injector.register(type = String::class.java, provider = lastName, identifier = "LAST_NAME")
		injector.register(type = Long::class.java, provider = phoneNumber)

	}
}