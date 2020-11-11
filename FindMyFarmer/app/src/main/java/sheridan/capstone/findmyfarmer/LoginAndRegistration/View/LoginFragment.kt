package sheridan.capstone.findmyfarmer.LoginAndRegistration.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationInterface
import sheridan.capstone.findmyfarmer.R


class LoginFragment : Fragment() {

    private lateinit var login_interface: LoginRegistrationInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        login_interface = activity as LoginRegistrationInterface

        view.loginBtn.setOnClickListener{
            Log.d("AUTHENTICATE", "ButtonPressed")
            login_interface.OnLoginButtonClickListener(view.inputEmail,view.inputPassword)
        }

        view.GSignIn.setOnClickListener{
            login_interface.OnGoogleButtonClickListener()
        }

        view.FBSignIn.setOnClickListener{
            login_interface.OnFBLogInButtonClickListener()
        }

        view.registerAccount.setOnClickListener{
            login_interface.OnRegisterLinkClickListener()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        constraintLayoutLoginFragment.setOnTouchListener{ view, m: MotionEvent ->
            closeKeyboard(constraintLayoutLoginFragment)
            constraintLayoutLoginFragment.requestFocus()
            true}
    }
    private fun closeKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
}