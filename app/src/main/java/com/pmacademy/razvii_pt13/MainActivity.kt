package com.pmacademy.razvii_pt13

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private var itemList: MutableList<SpannableStringBuilder> = mutableListOf()
    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText

    private lateinit var btnAdd: Button

    private lateinit var recyclerView: RecyclerView
    private lateinit var rvAdapter: CustomRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewElements()

    }

    private fun setupViewElements() {
        recyclerView = findViewById(R.id.recyclerView)

        nameEditText = findViewById(R.id.editName)
        surnameEditText = findViewById(R.id.editSurname)

        btnAdd = findViewById(R.id.btnAdd)

        nameEditText.addTextChangedListener(textWatcher)
        surnameEditText.addTextChangedListener(textWatcher)

        recyclerView.layoutManager = LinearLayoutManager(this)
        rvAdapter = CustomRecyclerViewAdapter(itemList)
        recyclerView.adapter = rvAdapter
    }

    fun onAddButtonClicked(view: View) {
        val name: String = nameEditText.text.toString()
        val surname: String = surnameEditText.text.toString()

        val spannableString = SpannableStringBuilder("$name $surname")

        val foregroundSpan = ForegroundColorSpan(Color.RED)

        val startIndexSurname = spannableString.indexOf(surname)
        val endIndexSurname = startIndexSurname + surname.length

        val flag = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        spannableString.setSpan(foregroundSpan, startIndexSurname, endIndexSurname, flag)
        spannableString.setSpan(UnderlineSpan(), startIndexSurname, endIndexSurname, flag)

        itemList.add(spannableString)
        rvAdapter.notifyItemInserted(itemList.size - 1)

        nameEditText.setText("")
        surnameEditText.setText("")

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            checkFieldsForRules()
        }
    }

    private fun checkFieldsForRules() {

        if (nameEditText.length() != 0 && surnameEditText.length() != 0) {
            val name: String = nameEditText.text.toString()
            val surname: String = surnameEditText.text.toString()

            btnAdd.isEnabled =
                name[0].isUpperCase() && surname[0].isUpperCase() && name.length >= 2 && surname.length >= 3
        } else {
            btnAdd.isEnabled = false
        }

    }

}