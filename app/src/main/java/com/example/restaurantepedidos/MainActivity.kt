package com.example.restaurantepedidos

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.restaurantepedidos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val precoYakissoba = 20
    private val precoTemaki = 25
    private val precoSushi = 30

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

        binding.buttonPedido.setOnClickListener {
            val i = Intent(this, SplashScreenActivity::class.java)
            i.putExtra("qt_yakissoba", binding.editQtYakissoba.text.toString())
            i.putExtra("qt_temaki", binding.editQtTemaki.text.toString())
            i.putExtra("qt_sushi", binding.editQtSushi.text.toString())

            val pcFinalYakissoba = binding.editQtYakissoba.text.toString().toDouble() * precoYakissoba
            i.putExtra("pc_yakissoba", pcFinalYakissoba)
            val pcFinalTemaki = binding.editQtTemaki.text.toString().toDouble() * precoTemaki
            i.putExtra("pc_temaki", pcFinalTemaki)
            val pcFinalSushi = binding.editQtSushi.text.toString().toDouble() * precoSushi
            i.putExtra("pc_sushi", pcFinalSushi)

            startActivity(i)
        }

        binding.checkYakissoba.setOnClickListener {
            if (binding.checkYakissoba.isChecked) {
                binding.editQtYakissoba.setText("1")
                binding.textPrecoYakissoba.visibility = View.VISIBLE
            } else {
                binding.editQtYakissoba.setText("0")
                binding.textPrecoYakissoba.visibility = View.GONE
            }
        }

        binding.checkTemaki.setOnClickListener {
            if (binding.checkTemaki.isChecked) {
                binding.editQtTemaki.setText("1")
                binding.textPrecoTemaki.visibility = View.VISIBLE
            } else {
                binding.editQtTemaki.setText("0")
                binding.textPrecoTemaki.visibility = View.GONE
            }
        }

        binding.checkSushi.setOnClickListener {
            if (binding.checkSushi.isChecked) {
                binding.editQtSushi.setText("1")
                binding.textPrecoSushi.visibility = View.VISIBLE
            } else {
                binding.editQtSushi.setText("0")
                binding.textPrecoSushi.visibility = View.GONE
            }
        }

        binding.main.setOnClickListener {
            try {
                if (binding.editQtYakissoba.text.toString().toInt() > 0) {
                    binding.textPrecoYakissoba.text = (binding.editQtYakissoba.text.toString().toInt() * precoYakissoba).toString()
                }
                if (binding.editQtTemaki.text.toString().toInt() > 0) {
                    binding.textPrecoTemaki.text = (binding.editQtTemaki.text.toString().toInt() * precoTemaki).toString()
                }
                if (binding.editQtSushi.text.toString().toInt() > 0) {
                    binding.textPrecoSushi.text = (binding.editQtSushi.text.toString().toInt() * precoSushi).toString()
                }
                hideKeyboard()
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro ao calcular preço: ${e.message}")
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is View) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }
}