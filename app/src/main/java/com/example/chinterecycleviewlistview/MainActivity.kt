package com.example.chinterecycleviewlistview

//AppCenter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {



    private val firebaseFirestore = FirebaseFirestore.getInstance()

    private val collectionReference = firebaseFirestore.collection("users")




    var userAdaptor: userAdaptor? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        //AppCenter
        AppCenter.start(
            application, "cb044baa-fe0f-429f-8538-d2dea91bffaa",
            Analytics::class.java, Crashes::class.java
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setUpRecycleView()

    }


    fun setUpRecycleView(){

        val query: Query = collectionReference

        val firestoreRecyclerOptions: FirestoreRecyclerOptions<userModel> = FirestoreRecyclerOptions.Builder<userModel>()
            .setQuery(query, userModel::class.java)
            .build()

        userAdaptor = userAdaptor(firestoreRecyclerOptions)

        val recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = userAdaptor



    }


    override fun onStart() {
        super.onStart()
        userAdaptor!!.startListening()
    }

    override fun onDestroy() {
        super.onDestroy()
        userAdaptor!!.stopListening()

    }

}