package sheridan.capstone.findmyfarmer.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.inputEmail
import kotlinx.android.synthetic.main.activity_login.inputPassword
import kotlinx.android.synthetic.main.activity_login.loginBtn
import kotlinx.android.synthetic.main.fragment_login.*
import sheridan.capstone.findmyfarmer.R


class LoginFragment : Fragment() {
    var navController: NavController? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_loginBtn.setOnClickListener{
            login(view)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    fun login(view: View){
        auth.signInWithEmailAndPassword(inputEmail.text.toString(), inputPassword.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("AUTHENTICATION", "login :success")
                    val user = auth.currentUser
                    navController = Navigation.findNavController(view)
                    navController!!.navigate(R.id.action_loginFragment_to_farmerListing)
                    Toast.makeText(context, "Logged in",Toast.LENGTH_SHORT).show()
                    //finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("AUTHENTICATION", "login :failure", task.exception)
                    Toast.makeText(
                        context, "Incorrect email/password!",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)

                }
            }
    }

}