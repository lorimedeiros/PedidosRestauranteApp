package com.example.restaurantepedidos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurantepedidos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonPedido.setOnClickListener{
            val i = Intent(this, SplashScreenActivity::class.java)

            adicionaPedidoIntent(i, binding.editQtYakissoba, binding.textPrecoYakissoba, "qt_yakissoba", "preco_yakissoba")
            adicionaPedidoIntent(i, binding.editQtTemaki, binding.textPrecoTemaki, "qt_temaki", "preco_temaki")
            adicionaPedidoIntent(i, binding.editQtSushi, binding.textPrecoSushi, "qt_sushi", "preco_sushi")

            startActivity(i)
        }

        marcaCheckBox(binding.checkYakissoba, binding.editQtYakissoba, binding.textPrecoYakissoba)
        marcaCheckBox(binding.checkTemaki, binding.editQtTemaki, binding.textPrecoTemaki)
        marcaCheckBox(binding.checkSushi, binding.editQtSushi, binding.textPrecoSushi)
    }


    private fun marcaCheckBox(checkBox: CheckBox, editText: EditText, textView: TextView){
        if (checkBox.isChecked) {
            editText.setText("1")
            textView.visibility = View.VISIBLE
            val quantity = editText.text.toString().toIntOrNull()
            if (quantity != null && quantity > 1) {
                val price = textView.text.toString().toDoubleOrNull()
                if (price != null) {
                    val total = quantity * price
                    val finalText = "R$ $total" //lembrar de tirar 3 casas iniciais da string para poder tratar como int
                    textView.text = finalText
                    editText.visibility = View.VISIBLE
                }
            }
        } else {
            editText.setText("0")
            textView.visibility = View.GONE
        }
    }

    private fun adicionaPedidoIntent(intent: Intent, quantityEditText: EditText, priceTextView: TextView, quantityKey: String, priceKey: String) {
        val quantity = quantityEditText.text.toString().toIntOrNull()
        if (quantity != null && quantity > 0) {
            intent.putExtra(quantityKey, quantityEditText.text.toString())
            intent.putExtra(priceKey, priceTextView.text.toString())
        }
    }
}