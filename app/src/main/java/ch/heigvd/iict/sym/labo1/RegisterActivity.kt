package ch.heigvd.iict.sym.labo1

import android.content.Intent
import android.os.Bundle

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        setContentView(R.layout.activity_register)
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage
    }

    override fun setValidateOnClickListener() : Boolean {
        if(super.setValidateOnClickListener())
        {
            val emailInput = email.text?.toString()
            val passwordInput = password.text?.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", emailInput)
            intent.putExtra("pswd", passwordInput)


            setResult(RESULT_OK, intent)
            finish()
        }
        return false;
    }

    companion object {
        private const val TAG: String = "RegisterActivity"
    }
}
