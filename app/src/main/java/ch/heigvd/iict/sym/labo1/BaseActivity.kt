package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

abstract class BaseActivity : AppCompatActivity() {
    // le modifieur lateinit permet de définir des variables avec un type non-null
    // sans pour autant les initialiser immédiatement
    protected lateinit var email: EditText
    protected lateinit var password: EditText
    protected lateinit var cancelButton: Button
    protected lateinit var validateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        // l'appel à la méthode onCreate de la super classe est obligatoire
        super.onCreate(savedInstanceState)
        // on définit le layout à utiliser pour l'affichage

        // on va maintenant lier le code avec les éléments graphiques (champs texts, boutons, etc.)
        // présents dans le layout (nous allons utiliser l'id défini dans le layout, le cast est
        // réalisé automatiquement)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        cancelButton = findViewById(R.id.cancel)
        validateButton = findViewById(R.id.validate)
        // Kotlin, au travers des Android Kotlin Extensions permet d'automatiser encore plus cette
        // étape en créant automatiquement les variables pour tous les éléments graphiques présents
        // dans le layout et disposant d'un id
        // cf. https://medium.com/@temidjoy/findviewbyid-vs-android-kotlin-extensions-7db3c6cc1d0a

        //mise en place des événements
        cancelButton.setOnClickListener {
            //on va vider les champs de la page de login lors du clique sur le bouton Cancel
            email.text?.clear()
            password.text?.clear()
            // on annule les éventuels messages d'erreur présents sur les champs de saisie
            email.error = null
            password.error = null
        }

        validateButton.setOnClickListener {
            this.setOnClickListener();
        }
    }

    open fun setOnClickListener() {
        //on réinitialise les messages d'erreur
        email.error = null
        password.error = null

        //on récupère le contenu de deux champs dans des variables de type String
        val emailInput = email.text?.toString()
        val passwordInput = password.text?.toString()

        if (emailInput.isNullOrEmpty() or passwordInput.isNullOrEmpty()) {
            // on affiche un message dans les logs de l'application
            Log.d(TAG, "Au moins un des deux champs est vide")
            // on affiche un message d'erreur sur les champs qui n'ont pas été renseignés
            // la méthode getString permet de charger un String depuis les ressources de
            // l'application à partir de son id
            if (emailInput.isNullOrEmpty()) {
                email.error = getString(R.string.main_mandatory_field)
            }

            if (passwordInput.isNullOrEmpty())
                password.error = getString(R.string.main_mandatory_field)

            // Pour les fonctions lambda, on doit préciser à quelle fonction l'appel à return
            // doit être appliqué
            return@setOnClickListener
        }
    }

    // En Kotlin, les variables static ne sont pas tout à fait comme en Java
    // pour des raison de lisibilité du code, les variables et méthodes static
    // d'une classe doivent être regroupées dans un bloc à part: le companion object
    // cela permet d'avoir un aperçu plus rapide de tous les éléments static d'une classe
    // sans devoir trouver le modifieur dans la définition de ceux-ci, qui peuvent être mélangé
    // avec les autres éléments non-static de la classe
    companion object {
        private const val TAG: String = "MainActivity"
    }
}