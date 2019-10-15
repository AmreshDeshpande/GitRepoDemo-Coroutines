package com.example.gitrepos.data

import android.util.Log

class NetworkHandler: (Boolean) -> Unit {


    override fun invoke(updatedVal: Boolean) {

        Log.e("NetworkHandler", ""+updatedVal)
    }

}