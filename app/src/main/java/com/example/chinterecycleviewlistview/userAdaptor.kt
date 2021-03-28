package com.example.chinterecycleviewlistview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class userAdaptor(options: FirestoreRecyclerOptions<userModel>) :
    FirestoreRecyclerAdapter<userModel, userAdaptor.UserAdaptorViewHolder>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdaptorViewHolder {
        return UserAdaptorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_users,parent,false))
    }

    override fun onBindViewHolder(holder: UserAdaptorViewHolder, position: Int, model: userModel) {

        holder.firstName.text = model.firstName
        holder.lastName.text = model.lastName

        Glide.with(holder.itemView.context)
            .load(model.image)
            .into(holder.imageView)


    }

    class UserAdaptorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var firstName = itemView.findViewById<TextView>(R.id.fName)
        var lastName =  itemView.findViewById<TextView>(R.id.lName)
        var imageView = itemView.findViewById<ImageView>(R.id.img)





    }
}