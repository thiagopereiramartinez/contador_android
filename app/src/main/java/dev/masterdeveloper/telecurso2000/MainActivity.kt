package dev.masterdeveloper.telecurso2000

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dev.masterdeveloper.telecurso2000.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel

        viewModel.contador.observe(this) {
            binding.tvContador.text = it.toString()
        }

        viewModel.status.observe(this) { status ->
            when (status) {
                MainViewModel.Status.NAO_INICIADO -> {
                    with (binding) {
                        btnIniciar.isEnabled = true
                        btnPausar.isEnabled = false
                        btnResetar.isEnabled = false
                    }
                }
                MainViewModel.Status.INICIADO -> {
                    with (binding) {
                        btnIniciar.isEnabled = false
                        btnPausar.isEnabled = true
                        btnResetar.isEnabled = false
                    }
                }
            }
        }

    }

}