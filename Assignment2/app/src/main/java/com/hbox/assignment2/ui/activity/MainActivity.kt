package com.hbox.assignment2.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.android.material.chip.Chip
import com.hbox.assignment2.R
import com.hbox.assignment2.databinding.ActivityMainBinding
import com.hbox.assignment3.utils.StringUtils

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnUpdate.setOnClickListener(View.OnClickListener {
            if (!StringUtils.isEmpty(binding.etMessage.text.toString().trim())) {
                if (getColorFromChipGroupSelection() != 0) {
                    binding.tvMessage.setTextColor(getColorFromChipGroupSelection())
                    binding.tvMessage.text = binding.etMessage.text
                } else {
                    Toast.makeText(this, getString(R.string.msg_select_color), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.msg_enter_message), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun getColorFromChipGroupSelection(): Int {
        var color: Int = 0
        for (id in binding.cgColor.getCheckedChipIds()) {
            val chip: Chip = binding.cgColor.findViewById(id)
            if (chip.isChecked) {
                when (chip.id) {
                    binding.chipRed.id -> {
                        color = Color.RED
                    }
                    binding.chipGreen.id -> {
                        color = Color.GREEN
                    }
                    binding.chipBlue.id -> {
                        color = Color.BLUE
                    }
                }
            }
        }
        return color
    }
}