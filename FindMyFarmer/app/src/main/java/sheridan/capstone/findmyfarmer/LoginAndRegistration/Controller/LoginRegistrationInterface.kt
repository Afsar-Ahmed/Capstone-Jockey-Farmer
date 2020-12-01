package sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller

import android.text.Editable
import android.view.View
import android.widget.EditText
import org.w3c.dom.Text
import sheridan.capstone.findmyfarmer.LoginAndRegistration.View.LoginFragment

interface LoginRegistrationInterface {
    fun OnLoginButtonClickListener(email: EditText, password: EditText)
<<<<<<< HEAD
    fun OnSignUpButtonClickListener(email: EditText, password: EditText, repeatPassword: EditText)
=======
    fun OnSignUpButtonClickListener(email: String, name: String, password: String, isFarmer: Boolean)
>>>>>>> Sohaib
    fun OnRegisterLinkClickListener()
    fun OnGoogleButtonClickListener()
    fun OnFBLogInButtonClickListener()
    fun OnResetPasswordButtonClickListener()
    fun OnSendResetButtonClickListener(email: EditText)
<<<<<<< HEAD
=======
    fun Validation(email: EditText, name: EditText, password: EditText, repeatPassword: EditText):Boolean
    fun Navigate(FragmentId: Int)
>>>>>>> Sohaib
}