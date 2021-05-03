package com.example.contactlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.contactlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = ContactAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpData(binding)
    }

    private fun setUpData(binding: ActivityMainBinding) {
        binding.contactsRv.adapter = adapter
        binding.contactsRv.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.add_contact_dialog, null)
        builder.setView(view)

        val name = view.findViewById<TextView>(R.id.nameEt)
        val no = view.findViewById<TextView>(R.id.numberEt)
        val saveButton = view.findViewById<TextView>(R.id.saveBt)
        no.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                saveButton.isEnabled = s?.length == 11
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        val alertDialog = builder.create()
        saveButton.setOnClickListener{
            val contact = Contact(name.text.toString(), no.text.toString())
            val contacts = mutableListOf(contact)
            adapter.setupContacts(contacts)
            alertDialog.dismiss()
        }
        binding.fab.setOnClickListener {
            alertDialog.show();
        }
    }
}