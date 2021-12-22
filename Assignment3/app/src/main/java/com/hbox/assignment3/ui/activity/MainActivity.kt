package com.hbox.assignment3.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.hbox.assignment3.databinding.ActivityMainBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import android.app.DatePickerDialog
import android.widget.Toast
import com.hbox.assignment3.R
import com.hbox.assignment3.utils.Constants
import com.hbox.assignment3.utils.StringUtils


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.tvStartDate.setOnClickListener(this)
        binding.tvEndDate.setOnClickListener(this)
        binding.btnCalculateDays.setOnClickListener(this)

    }

    fun getDifferenceDays(startDateString: String, endDateString: String) {
        val myFormat = SimpleDateFormat(Constants.dateFormat)
        try {
            val startDate: Date = myFormat.parse(startDateString)
            val endDate: Date = myFormat.parse(endDateString)
            val diff: Long = endDate.time - startDate.time
            binding.tvCaculatedDays.visibility = View.VISIBLE
            val totalDays: Long = TimeUnit.DAYS.convert(
                diff,
                TimeUnit.MILLISECONDS
            ) + 1
            binding.tvCaculatedDays.text =
                getString(R.string.msg_number_of_days) + " " + totalDays
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.tvStartDate.id -> {
                val calendar = Calendar.getInstance(TimeZone.getDefault())

                val dialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { datepicker, year, month, day ->
                        binding.tvStartDate.setText(day.toString() + " " + (month + 1).toString() + " " + year.toString())
                        if (binding.tvCaculatedDays.visibility == View.VISIBLE) {
                            binding.tvCaculatedDays.visibility = View.GONE
                        }
                    },
                    calendar[Calendar.YEAR], calendar[Calendar.MONTH],
                    calendar[Calendar.DAY_OF_MONTH]
                )
                dialog.show()

            }
            binding.tvEndDate.id -> {
                val calendar = Calendar.getInstance(TimeZone.getDefault())

                val dialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { datepicker, year, month, day ->
                        binding.tvEndDate.setText(day.toString() + " " + (month + 1).toString() + " " + year.toString())
                        if (binding.tvCaculatedDays.visibility == View.VISIBLE) {
                            binding.tvCaculatedDays.visibility = View.GONE
                        }
                    },
                    calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH],
                    calendar[Calendar.DAY_OF_MONTH]
                )
                dialog.show()
            }
            binding.btnCalculateDays.id -> {
                if (!StringUtils.isEmpty(binding.tvStartDate.text.toString().trim())) {
                    Toast.makeText(
                        this,
                        getString(R.string.msg_select_start_date),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    if (!StringUtils.isEmpty(binding.tvEndDate.text.toString().trim())) {
                        Toast.makeText(
                            this,
                            getString(R.string.msg_select_end_date),
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        getDifferenceDays(
                            binding.tvStartDate.text.toString(),
                            binding.tvEndDate.text.toString()
                        )
                    }
                }
            }
        }
    }
}