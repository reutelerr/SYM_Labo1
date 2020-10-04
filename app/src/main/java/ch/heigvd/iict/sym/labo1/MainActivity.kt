package ch.heigvd.iict.sym.labo1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog


const val labo1_EMAIL=""
const val LAUNCH_REGISTER_ACTIVITY = 1;

class MainActivity : BaseActivity() {

    // on définit une liste de couples e-mail / mot de passe
    // ceci est fait juste pour simplifier ce premier laboratoire,
    // mais il est évident que de hardcoder ceux-ci est une pratique à éviter à tout prix...
    private val credentials = mutableListOf<Pair<String, String>>(
        Pair("user1@heig-vd.ch", "1234"),
        Pair("user2@heig-vd.ch", "abcd")
    )

    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        registerButton = findViewById(R.id.new_account)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, LAUNCH_REGISTER_ACTIVITY)
        }
    }

    override fun setValidateOnClickListener(){
        super.setValidateOnClickListener();

        //on récupère le contenu de deux champs dans des variables de type String
        val emailInput = email.text?.toString()
        val passwordInput = password.text?.toString()
        if (!credentials.contains(Pair(emailInput, passwordInput))) {
            var builder = AlertDialog.Builder(this)
            builder.setMessage("Invalid credentials")
            builder.create().show()
        } else {
            val intent = Intent(this, ShowYourFaceActivity::class.java).apply {
                putExtra(labo1_EMAIL, emailInput)
            }
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_REGISTER_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                val email = data?.getStringExtra("email")
                val pswd = data?.getStringExtra("pswd")

                credentials.add(Pair<String, String>(email as String, pswd as String))
            }
        }
    } //onActivityResult

    companion object {
        private const val TAG: String = "MainActivity"
    }
}
