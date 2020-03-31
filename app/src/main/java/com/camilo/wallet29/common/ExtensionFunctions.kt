package com.camilo.wallet29.common
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.camilo.wallet29.dialogs.DialogAddAccountWallet
import com.camilo.wallet29.interfaces.FullScreenDialog
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

fun TextInputLayout.text(): String = this.editText!!.text.toString()

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, resourceId, duration).show()

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!
//
//fun ImageView.loadByURL(url: String) = Picasso.get().load(url).into(this)
//
//fun ImageView.loadByResource(resource: Int) = Picasso.get().load(resource).fit().into(this)

inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

fun EditText.validate(validation: (String) -> Unit){
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            validation(editable.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

    })
}

fun TextInputLayout.setHintsColor(color: Int) {
    this.defaultHintTextColor = ColorStateList.valueOf(color)
    this.boxStrokeColor = color
}


fun isValidInput(name: String): Boolean = name.isNotEmpty()



fun Activity.isValidEmail(email: String): Boolean
{
    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()
}

fun Activity.isValidPassword(password: String): Boolean
{
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.*])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(passwordPattern)
    return pattern.matcher(password).matches()
}

fun Activity.isValidConfirmPassword(password: String, confirmPassword: String): Boolean = password == confirmPassword