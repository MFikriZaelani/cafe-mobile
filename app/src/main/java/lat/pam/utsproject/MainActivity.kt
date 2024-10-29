package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val buttonLogin = findViewById(R.id.btnLogin) as Button

        buttonLogin.setOnClickListener {
            val username = findViewById(R.id.etUsername) as EditText
            val password = findViewById(R.id.etPassword) as EditText

            if(username.text.toString() == "fikri" && password.text.toString() == "pergerakan"){
                val intent = Intent(this, ListFoodActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(this, "Username dan password salah", Toast.LENGTH_SHORT).show()
            }
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}