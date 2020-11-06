package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller

import android.text.Editable
import android.view.View
import android.widget.EditText
import org.w3c.dom.Text
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.LoginFragment

interface LoginRegistrationInterface {
    fun OnLoginButtonClickListener(email: EditText, password: EditText)
    fun OnSignUpButtonClickListener(email: EditText, password: EditText, repeatPassword: EditText)
    fun OnRegisterLinkClickListener()
    fun OnGoogleButtonClickListener()
    fun OnFBLogInButtonClickListener()

}