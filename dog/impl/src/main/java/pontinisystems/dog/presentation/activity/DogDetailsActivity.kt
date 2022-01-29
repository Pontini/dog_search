package pontinisystems.dog.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pontinisystems.dog.impl.databinding.ActivityDogDetailsBinding

class DogDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}