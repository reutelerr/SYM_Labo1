package ch.heigvd.iict.sym.labo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ch.heigvd.iict.sym.labo1.network.ImageDownloader
import kotlinx.android.synthetic.main.activity_show_your_face.*

class ShowYourFaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_your_face)
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = intent.getStringExtra(labo1_EMAIL)
        }

        ImageDownloader(imageView, "https://thispersondoesnotexist.com/image").show()
    }
}