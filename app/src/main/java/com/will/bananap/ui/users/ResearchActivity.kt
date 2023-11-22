package com.will.bananap.ui.users

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.PopupMenu
import com.google.firebase.firestore.FirebaseFirestore
import com.will.bananap.R


class ResearchActivity : AppCompatActivity() {


    private lateinit var researchListView: ListView
    private lateinit var researchList: MutableList<String>
    private lateinit var fabButton: Button

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_research)


        fabButton = findViewById(R.id.fabButton)
        researchListView = findViewById(R.id.researchListView)
        researchList = mutableListOf()

        // Realizar la consulta a Firestore
        db.collection("researches")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Agregar cada título de investigación a la lista
                    researchList.add(document.getString("title") ?: "")
                }

                // Configurar el adaptador para la lista
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, researchList)
                researchListView.adapter = adapter

                // Manejar clics en elementos de la lista
                researchListView.setOnItemClickListener { _, _, position, _ ->
                    val selectedTitle = researchList[position]

                    // Lógica para abrir otra actividad con detalles de la investigación
                    // Puedes pasar 'selectedTitle' u otro identificador único a la siguiente actividad
                    // y luego realizar otra consulta a Firestore para obtener detalles adicionales.
                    val intent = Intent(this, ResearchDetailActivity::class.java)
                    intent.putExtra("selectedTitle", selectedTitle)
                    startActivity(intent)
                }
            }
            .addOnFailureListener { exception ->
                // Manejar errores de la consulta
            }


        // Agrega la lógica que necesitas al hacer clic en el botón
        fabButton.setOnClickListener { view ->
            // Crear instancia de PopupMenu
            val popupMenu = PopupMenu(this, view)

            // Inflar el menú desde el recurso XML
            popupMenu.menuInflater.inflate(R.menu.menu_research_options, popupMenu.menu)

            // Configurar el evento de clic para los elementos del menú
            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.menu_option_1 -> {
                        val intent = Intent(this, AddResearchActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    R.id.menu_option_2 -> {
                        // Lógica para la opción 2
                        true
                    }
                    // Agrega más opciones según sea necesario
                    else -> false
                }
            }

            // Mostrar el menú emergente
            popupMenu.show()
        }



    }


}




