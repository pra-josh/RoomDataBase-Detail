package com.pra.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pra.roomdatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)



    }






}