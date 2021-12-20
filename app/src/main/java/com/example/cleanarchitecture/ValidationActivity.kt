package com.example.cleanarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitecture.databinding.ActivityValidationBinding
import com.example.cleanarchitecture.presentation.extentions.safeFieldCall
import com.example.cleanarchitecture.validation.FieldType

class ValidationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityValidationBinding
    private lateinit var list: List<FieldType>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_validation)
        list = listOf(
            FieldType.BasicField(binding.field1),
            FieldType.PasswordField(binding.field2),
            FieldType.EmailField(binding.field3),
            FieldType.BasicField(binding.field4)
        )

        binding.button.setOnClickListener {
            list.safeFieldCall { list ->
                if (list.size == 4) {
                    list.forEach {
                        Log.e("data", "text = $it")
                    }
                }
            }
        }

    }
}