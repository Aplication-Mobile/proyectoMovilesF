package com.will.bananap.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.will.bananap.R

class ResearchDetailActivity : AppCompatActivity() {


    private lateinit var titleTextView: TextView
    private lateinit var areaTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var pdfUrlTextView: TextView

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research_detail)

        val selectedTitle = intent.getStringExtra("selectedTitle")


        titleTextView = findViewById(R.id.titleTextView)
        areaTextView = findViewById(R.id.areaTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        pdfUrlTextView = findViewById(R.id.pdfUrlTextView)

        db.collection("researches")
            .whereEqualTo("title", selectedTitle)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Muestra los detalles en la interfaz de usuario
                    titleTextView.text = document.getString("title")
                    areaTextView.text = document.getString("areaOfInterest")
                    descriptionTextView.text = document.getString("briefDescription")
                    pdfUrlTextView.text = document.getString("pdfURL")
                }
            }
            .addOnFailureListener { exception ->
                // Manejar errores de la consulta
            }

    }
}