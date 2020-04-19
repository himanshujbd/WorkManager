package com.workmanagerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()

        var request = OneTimeWorkRequestBuilder<MyWork>()
                .setConstraints(constraints)
                .build()


        btnClick.setOnClickListener {

            WorkManager.getInstance(this).enqueue(request)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer {

                    val status: String = it.state.name
                    Toast.makeText(this,status, Toast.LENGTH_SHORT).show()
                })

    }
}
