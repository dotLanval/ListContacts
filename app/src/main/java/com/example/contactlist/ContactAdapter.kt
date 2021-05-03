package com.example.contactlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.databinding.ContactListItemBinding

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){
    private val contacts = mutableListOf<Contact>()

    fun setupContacts(contact: List<Contact>){
        this.contacts.addAll(contact)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ContactListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                    parent,
                    false
            )
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bindItem(contact, position)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }


    inner class ContactViewHolder(private val binding: ContactListItemBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bindItem(contact: Contact, position : Int){
            binding.deleteButton.setOnClickListener {
             contacts.removeAt(position)
                notifyDataSetChanged()

            }
            binding.nameTV.text = contact.name
            binding.numberTV.text = contact.number
        }
    }
}