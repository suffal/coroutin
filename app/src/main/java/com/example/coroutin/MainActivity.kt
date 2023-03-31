package com.example.coroutin

import android.content.ContentValues.TAG
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.coroutin.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.log

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    var count: Int = 0
    val TAG = "coroutin"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)




        binding.btnClick.setOnClickListener(this)

        binding.btnDownlode.setOnClickListener(this)


    }

    override fun onClick(view: View?) {

        when (view?.id) {
            R.id.btn_click -> {

                count++

             binding.tvCount.text="${Thread.currentThread().name}$count"

            }


            R.id.btn_downlode -> {

                downloading()

            }


        }


    }

    private fun downloading() {

      //  use coroutin scop function

        // background thread run program to use IO method
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..3000){
                // to work in diffrent thread to use withcontext coroutin to switch to backand thread to mainthread
               withContext(Dispatchers.Main){
                   binding.tvDownlode.text="${Thread.currentThread().name} main thread\ndownloade$i"
               }

            }
            binding.tvDownlode.text="download completed"
        }
    }
}
