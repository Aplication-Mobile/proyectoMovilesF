package com.will.bananap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.will.bananap.ui.users.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.will.bananap.ui.users.AddResearchActivity
import com.will.bananap.ui.users.ResearchActivity

const val valorIntentLogin = 1

class MainActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    var email: String? = null
    var contra: String? = null

    private lateinit var btnOption1: MaterialButton
    private lateinit var btnOption2: MaterialButton
    private lateinit var btnOption3: MaterialButton
    private lateinit var btnOption4: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnOption1 = findViewById(R.id.btnOption1)
        btnOption2 = findViewById(R.id.btnOption2)
        btnOption3 = findViewById(R.id.btnOption3)
        btnOption4 = findViewById(R.id.btnOption4)



        // intenta obtener el token del usuario del local storage, sino llama a la ventana de registro
        val prefe = getSharedPreferences("appData", Context.MODE_PRIVATE)
        email = prefe.getString("email","")
        contra = prefe.getString("contra","")

        if(email.toString().trim { it <= ' ' }.length == 0){
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, valorIntentLogin)
        }else {
            val uid: String = auth.uid.toString()
            if (uid == "null"){
                auth.signInWithEmailAndPassword(email.toString(), contra.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this,"Autenticación correcta", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            obtenerDatos()
        }

        btnOption1.setOnClickListener {
            startActivity(Intent(this, ResearchActivity::class.java))
        }

        btnOption2.setOnClickListener {
            startActivity(Intent(this, AddResearchActivity::class.java))
        }

        btnOption3.setOnClickListener {
            startActivity(Intent(this, AddResearchActivity::class.java))
        }

        btnOption4.setOnClickListener {
            startActivity(Intent(this, AddResearchActivity::class.java))
        }


    }




    private fun obtenerDatos() {
        Toast.makeText(this,"Esperando hacer algo importante", Toast.LENGTH_LONG).show()
    }



}