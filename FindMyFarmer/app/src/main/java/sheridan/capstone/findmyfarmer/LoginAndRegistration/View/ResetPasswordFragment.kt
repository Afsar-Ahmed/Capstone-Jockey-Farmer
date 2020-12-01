package sheridan.capstone.findmyfarmer.LoginAndRegistration.View

import android.os.Bundle
<<<<<<< HEAD
=======
import android.util.Log
>>>>>>> Sohaib
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_reset_password.*
import kotlinx.android.synthetic.main.fragment_reset_password.view.*
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationInterface
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.ViewBehaviorInterface
import sheridan.capstone.findmyfarmer.R

class ResetPasswordFragment : Fragment() {

    private lateinit var viewBehaviorInterface: ViewBehaviorInterface
    private lateinit var loginRegistrationInterface: LoginRegistrationInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_reset_password, container, false)
        loginRegistrationInterface = activity as LoginRegistrationInterface
        view.sendButton.setOnClickListener{
            loginRegistrationInterface.OnSendResetButtonClickListener(editTextTextEmailAddress)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBehaviorInterface = activity as ViewBehaviorInterface
        viewBehaviorInterface.viewBehavior(resetPasswordConstraintLayout)
    }
}