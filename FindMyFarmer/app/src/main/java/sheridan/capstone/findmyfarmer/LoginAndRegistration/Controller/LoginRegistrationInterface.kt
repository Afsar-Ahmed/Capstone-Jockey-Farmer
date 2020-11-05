package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller

import android.widget.EditText
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.LoginFragment

interface LoginRegistrationInterface {
    fun OnLoginButtonClickListener(email: EditText, password: EditText)
}