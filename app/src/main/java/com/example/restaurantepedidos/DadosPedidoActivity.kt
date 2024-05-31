package com.example.restaurantepedidos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurantepedidos.databinding.ActivityDadosPedidoBinding

class DadosPedidoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDadosPedidoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDadosPedidoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val i = intent

        if (intent.hasExtra("qt_temaki")) {
            val qtTemaki = intent.getStringExtra("qt_temaki")
            val pcTemaki = intent.getStringExtra("pc_temaki")

            // Faça algo com qtTemaki e pcTemaki
            // Por exemplo, exibir os valores em TextViews

        } else {
            // O extra "qt_temaki" não foi enviado (o valor era 0)
            // Lidar com essa situação, por exemplo, exibir uma mensagem ou ocultar certos elementos

        }

    }
}