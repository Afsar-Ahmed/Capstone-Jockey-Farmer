package sheridan.capstone.findmyfarmer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        logOutBtn.setOnClickListener{
            logOut()
        }
    }

    private fun logOut(){
        Firebase.auth.signOut()
        startActivity(Intent(this,Login::class.java))

    }
}