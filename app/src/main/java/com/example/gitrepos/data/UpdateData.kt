package com.example.gitrepos.data

interface UpdateData {

    fun updateOnStart(value: String) = { }

    fun updateOnFinish(value: String) = { }

    fun updateOnRunning(value: String) = { }
}

inline fun UpdateData.updateOnStart() {


}

inline fun UpdateData.updateOnFinish() {


}

inline fun UpdateData.updateOnRunning() {


}