package com.example.restaurantepedidos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.d("SplashScreenActivity", "Splash screen carregada")

        Handler(Looper.getMainLooper()).postDelayed({
            Log.d("SplashScreenActivity", "Timer finalizado, iniciando DadosPedidoActivity")
            val i = intent
            Log.d("SplashScreenActivity", "Intent recebido: $i")
            val j = Intent(this, DadosPedidoActivity::class.java)
            j.putExtras(i)
            Log.d("SplashScreenActivity", "Iniciando DadosPedidoActivity com Intent: $j")
            startActivity(j)
        }, 2000)
    }
}
