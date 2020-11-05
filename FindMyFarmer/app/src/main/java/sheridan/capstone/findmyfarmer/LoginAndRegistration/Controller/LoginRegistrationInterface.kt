package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller

import android.text.Editable
import android.widget.EditText
import org.w3c.dom.Text
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.LoginFragment

interface LoginRegistrationInterface {
    fun OnLoginButtonClickListener(email: EditText, password: EditText)
}