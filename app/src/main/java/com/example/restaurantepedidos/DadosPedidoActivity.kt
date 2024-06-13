package com.example.restaurantepedidos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurantepedidos.databinding.ActivityDadosPedidoBinding

class DadosPedidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDadosPedidoBinding

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

        val qt_yakissoba = i.getStringExtra("qt_yakissoba")?.toIntOrNull() ?: 0
        val qt_temaki = i.getStringExtra("qt_temaki")?.toIntOrNull() ?: 0
        val qt_sushi = i.getStringExtra("qt_sushi")?.toIntOrNull() ?: 0

        val pc_yakissoba = i.getDoubleExtra("pc_yakissoba", 0.0)
        val pc_temaki = i.getDoubleExtra("pc_temaki", 0.0)
        val pc_sushi = i.getDoubleExtra("pc_sushi", 0.0)

        var textoQt = ""
        if (qt_yakissoba > 0) { textoQt += "$qt_yakissoba\n" }
        if (qt_temaki > 0) { textoQt += "$qt_temaki\n" }
        if (qt_sushi > 0) { textoQt += "$qt_sushi\n" }

        var textoDesc = ""
        if (qt_yakissoba > 0) { textoDesc += "Yakissoba\n" }
        if (qt_temaki > 0) { textoDesc += "Temaki\n" }
        if (qt_sushi > 0) { textoDesc += "Sushi\n" }

        var textoValor = ""
        if (qt_yakissoba > 0) { textoValor += "$pc_yakissoba\n" }
        if (qt_temaki > 0) { textoValor += "$pc_temaki\n" }
        if (qt_sushi > 0) { textoValor += "$pc_sushi\n" }

        val total = "${pc_yakissoba + pc_temaki + pc_sushi}"

        binding.textQt.text = textoQt
        binding.textDesc.text = textoDesc
        binding.textPrecoPorPedido.text = textoValor
        binding.textPrecoTotal.text = total
    }
}