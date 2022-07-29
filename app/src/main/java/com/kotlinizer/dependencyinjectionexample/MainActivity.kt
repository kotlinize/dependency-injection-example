package com.kotlinizer.dependencyinjectionexample

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinizer.dependencyInjection.IDependencyInjector
import com.kotlinizer.dependencyInjection.Injector

class MainActivity : AppCompatActivity() {
	private val injector: IDependencyInjector by lazy { Injector.instance }
	private val textValue = mutableStateOf("")

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// Retrieve the data from the Dependency Injector.
		val firstName: String? = injector.resolve(type = String::class.java, identifier = "FIRST_NAME")
		val lastName: String? = injector.resolve(type = String::class.java, identifier = "LAST_NAME")

		// No identifier is passed, as we didn't use one when being registered.
		val phoneNumber: Long? = injector.resolve(type = Long::class.java)

		setContent {
			MaterialTheme {
				Surface {
					MainActivityCompose(firstName = firstName!!, lastName = lastName!!, phoneNumber = phoneNumber!!)
				}
			}
		}
	}

	@Composable
	fun MainActivityCompose(firstName: String, lastName: String, phoneNumber: Long) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(10.dp)
		) {
			Text(
				text = textValue.value,
				modifier = Modifier
					.padding(10.dp)
					.align(Alignment.CenterHorizontally)
			)
			Row(modifier = Modifier.fillMaxWidth()) {
				TextButton(
					onClick = { textValue.value = firstName },
					modifier = Modifier.weight(1f)
				) {
					Text(text = "First Name")
				}
				TextButton(
					onClick = { textValue.value = lastName },
					modifier = Modifier.weight(1f)
				) {
					Text(text = "Last Name")
				}
				TextButton(
					onClick = { textValue.value = phoneNumber.toString() },
					modifier = Modifier.weight(1f)
				) {
					Text(text = "Phone")
				}
			}
		}
	}

	@Preview
	@Composable
	private fun Test() {
		MainActivityCompose(
			firstName = "Kotlin",
			lastName = "Dependency Injection",
			phoneNumber = 5555555555L
		)
	}
}