package com.example.exercise4_epfcalculator


import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import kotlinx.android.synthetic.main.date_picker.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class DatePickerFragment : Fragment() {

    private var years : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.date_picker,container,false)
        val pickDateBtn: Button = root.findViewById(R.id.pickDateBtn)
        val shDate: TextView = root.findViewById(R.id.shDate)
        val saving: TextView = root.findViewById(R.id.saving)
        val investment: TextView = root.findViewById(R.id.investment)
        val age: TextView = root.findViewById(R.id.age)

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        pickDateBtn.setOnClickListener {
            val dpd =
                activity?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener { view: DatePicker, year:Int, monthOfYear:Int, dayOfMonth:Int ->
                        shDate.text = "Date of Birth: " + dayOfMonth + "/" + (monthOfYear+ 1) + "/" + year
                        years = year
                        age.text =  "Age: " + calculateAge(years).toString()
                        saving.text = "Saving: " + calculateBasicSaving(calculateAge(years)).toString()
                        investment.text = "Transfer Not More Than 30%: " + calculateTransfer().toString()
                    }, year, month, day)
                }
            if (dpd != null) {
                dpd.show()
            }
        }

        return root
    }

    private fun calculateTransfer() : Double {
        return calculateBasicSaving(calculateAge(years)) * 0.3
    }

    private fun calculateAge(year:Int): Int {
        return Calendar.getInstance().get(Calendar.YEAR) - year

    }

    private fun calculateBasicSaving(age: Int): Int {
    var saving = 0

        when(age){
            16,17,18,19,20 -> saving = 5000
            21,22,23,24,25 -> saving = 14000
            26,27,28,29,30 -> saving = 29000
            31,32,33,34,35 -> saving = 50000
            36,37,38,39,40 -> saving = 78000
            41,42,43,44,45 -> saving = 116000
            46,47,48,49,50 -> saving = 165000
            51,52,53,54,55 -> saving = 228000
        }
        return saving
    }

}
