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
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.ViewBehaviorInterface
import sheridan.capstone.findmyfarmer.R


class LoginFragment : Fragment() {

    private lateinit var login_interface: LoginRegistrationInterface
    private lateinit var viewBehaviorInterface: ViewBehaviorInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_login, container, false)
        login_interface = activity as LoginRegistrationInterface

        view.loginBtn.setOnClickListener{
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
        view.forgotPswrdLink.setOnClickListener{
            login_interface.OnResetPasswordButtonClickListener()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBehaviorInterface = activity as ViewBehaviorInterface
        viewBehaviorInterface.viewBehavior(constraintLayoutLoginFragment)
    }

}