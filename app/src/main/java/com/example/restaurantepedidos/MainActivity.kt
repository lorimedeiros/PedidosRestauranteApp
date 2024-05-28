package com.example.restaurantepedidos

import android.content.Intent
import android.os.Bundle
import android.view.View
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

            if(binding.editQtYakissoba.text.toString().toInt() > 0){
                i.putExtra("qt_yakissoba", binding.editQtYakissoba.text.toString())
                i.putExtra("pc_yakissoba", binding.textPrecoYakissoba.text.toString())
            }

            if(binding.editQtTemaki.text.toString().toInt() > 0){
                i.putExtra("qt_temaki", binding.editQtTemaki.text.toString())
                i.putExtra("pc_temaki", binding.textPrecoTemaki.text.toString())
            }

            if(binding.editQtSushi.text.toString().toInt() > 0){
                i.putExtra("qt_sushi", binding.editQtSushi.text.toString())
                i.putExtra("pc_sushi", binding.textPrecoSushi.text.toString())
            }

            startActivity(i)
        }

        binding.checkYakissoba.setOnClickListener{
            if (binding.checkYakissoba.isChecked()){
                binding.editQtYakissoba.setText("1")
                binding.textPrecoYakissoba.visibility = View.VISIBLE
                if (binding.editQtYakissoba.toString().toInt() > 1){
                    val x = binding.editQtYakissoba.toString().toInt() * binding.textPrecoYakissoba.text.toString().toDouble()
                    val final = "R$ $x"
                    binding.textPrecoYakissoba.setText(final)
                    binding.textPrecoYakissoba.visibility = View.VISIBLE
                }
            } else {
                binding.editQtYakissoba.setText("0")
                binding.textPrecoYakissoba.visibility = View.GONE
            }
        }

        binding.checkTemaki.setOnClickListener{
            if (binding.checkTemaki.isChecked()){
                binding.editQtTemaki.setText("1")
                binding.textPrecoTemaki.visibility = View.VISIBLE
                if (binding.editQtTemaki.toString().toInt() > 1){
                    val x = binding.editQtTemaki.toString().toInt() * binding.textPrecoTemaki.text.toString().toDouble()
                    val final = "R$ $x"
                    binding.textPrecoTemaki.setText(final)
                    binding.editQtTemaki.visibility = View.VISIBLE
                }
            } else {
                binding.editQtTemaki.setText("0")
                binding.textPrecoTemaki.visibility = View.GONE
            }
        }

        binding.checkSushi.setOnClickListener{
            if (binding.checkSushi.isChecked()){
                binding.editQtSushi.setText("1")
                binding.textPrecoSushi.visibility = View.VISIBLE
                if (binding.editQtSushi.toString().toInt() > 1){
                    val x = binding.editQtSushi.toString().toInt() * binding.textPrecoSushi.text.toString().toDouble()
                    val final = "R$ $x"
                    binding.textPrecoSushi.setText(final)
                    binding.editQtSushi.visibility = View.VISIBLE
                }
            } else {
                binding.editQtSushi.setText("0")
                binding.textPrecoSushi.visibility = View.GONE
            }
        }
    }
}