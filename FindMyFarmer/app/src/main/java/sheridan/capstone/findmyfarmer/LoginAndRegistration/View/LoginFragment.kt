package sheridan.capstone.findmyfarmer.LoginAndRegistration.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_modified_login.*
import kotlinx.android.synthetic.main.fragment_modified_login.view.*
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationInterface
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.ViewBehaviorInterface
import sheridan.capstone.findmyfarmer.R


class LoginFragment : Fragment() {

<<<<<<< HEAD
    private lateinit var login_interface: LoginRegistrationInterface
=======
    private lateinit var loginInterface: LoginRegistrationInterface
>>>>>>> Sohaib
    private lateinit var viewBehaviorInterface: ViewBehaviorInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_modified_login, container, false)
<<<<<<< HEAD
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
=======
        loginInterface = activity as LoginRegistrationInterface

        view.loginBtn.setOnClickListener{
            loginInterface.OnLoginButtonClickListener(view.inputEmail,view.inputPassword)
        }

        view.GSignIn.setOnClickListener{
            loginInterface.OnGoogleButtonClickListener()
        }


//        view.fb2.setOnClickListener{
//            view.FBSignIn.performClick()
//        }
        view.FBSignIn.setOnClickListener{
            loginInterface.OnFBLogInButtonClickListener()
        }

        view.registerAccount.setOnClickListener{
            loginInterface.OnRegisterLinkClickListener()
        }
        view.forgotPswrdLink.setOnClickListener{
            loginInterface.OnResetPasswordButtonClickListener()
>>>>>>> Sohaib
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBehaviorInterface = activity as ViewBehaviorInterface
        viewBehaviorInterface.viewBehavior(constraintLayoutLoginFragment)
    }

}