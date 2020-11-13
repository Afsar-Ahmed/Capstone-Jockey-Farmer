package sheridan.capstone.findmyfarmer.LoginAndRegistration.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.LoginRegistrationInterface
import sheridan.capstone.findmyfarmer.LoginAndRegistration.Controller.ViewBehaviorInterface
import sheridan.capstone.findmyfarmer.R


private lateinit var registration_interface: LoginRegistrationInterface
private lateinit var viewBehaviorInterface: ViewBehaviorInterface
class RegistrationFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        registration_interface = activity as LoginRegistrationInterface

        view.signUpButton.setOnClickListener{
            registration_interface.OnSignUpButtonClickListener(view.newUserEmail,view.password,view.RePassword)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBehaviorInterface = activity as ViewBehaviorInterface
        viewBehaviorInterface.viewBehavior(constraint_layout_registration)
    }

}