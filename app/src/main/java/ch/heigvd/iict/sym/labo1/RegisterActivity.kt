package ch.heigvd.iict.sym.labo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        setContentView(R.layout.activity_register)
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
    }

    override fun setOnClickListener() {
        super.setOnClickListener()
        val emailInput = email.text?.toString()
        val passwordInput = password.text?.toString()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("email", emailInput)
        intent.putExtra("pswd", passwordInput)


        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        private const val TAG: String = "RegisterActivity"
    }
}
