package com.example.restaurantepedidos

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.substring
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

        var totalConta = 0.0 //somar com os resultados dos metodos
        totalConta += processarItemPorIntentQT(i, binding, "qt_yakissoba")
        totalConta += processarItemPorIntentQT(i, binding, "qt_temaki")
        totalConta += processarItemPorIntentQT(i, binding, "qt_sushi")

        binding.textPrecoTotal.text = totalConta.toString()
    }

    fun processarItemPorIntentQT(intent: Intent, binding: ActivityDadosPedidoBinding, stringIntentQt : String): Double {
        var resultadoMetodo = 0.0
        if(intent.hasExtra(stringIntentQt)){
            val qtItem = intent.getStringExtra(stringIntentQt)
            val t1 = "pc_" + stringIntentQt.substring(3)
            val pcItem = intent.getStringExtra(t1)

            if (binding.textQt.text.toString().length > 0) {
                binding.textQt.text = "$qtItem\n"
            } else {
                binding.textQt.text.toString().plus(qtItem+"\n")
            }

            if (binding.textDesc.text.toString().length > 0){
                binding.textDesc.text = stringIntentQt.substring(3).toUpperCase() +"\n"
            } else {
                binding.textDesc.text.toString().plus(stringIntentQt.substring(3).toUpperCase()+"\n")
            }

            if (binding.textPrecoPorPedido.text.toString().length > 0){
                binding.textPrecoPorPedido.text = pcItem.toString().substring(3) +"\n"
                resultadoMetodo += pcItem.toString().substring(3).toDouble()
            } else {
                binding.textPrecoPorPedido.text.toString().plus(pcItem+"\n")
                resultadoMetodo += pcItem.toString().substring(3).toDouble()
            }
        }

        return resultadoMetodo
    }
}