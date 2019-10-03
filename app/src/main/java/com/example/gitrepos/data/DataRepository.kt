package com.example.gitrepos.data


import android.util.Log
import com.example.gitrepos.network.GitRepoService
import com.example.gitrepos.network.model.ErrorResponse
import com.example.gitrepos.network.model.GitRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

class DataRepository(private val gitRepoService: GitRepoService) {

    private val TAG = "DataRepository"

    fun getGitRepo(success: (List<GitRepo>?) -> (Unit), failure: (ErrorResponse) -> (Unit)) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = gitRepoService.getGitRepo()

                withContext(Dispatchers.Main) {
                    try {
                        if (response.isSuccessful) {
                            success.invoke(response.body())
                        } else {
                            failure.invoke(ErrorResponse(Throwable()))
                        }
                    } catch (e: HttpException) {
                        Log.e(TAG, e.message())
                    } catch (throwable: Throwable) {
                        failure.invoke(ErrorResponse(throwable))
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                withContext(Dispatchers.Main) {
                    failure.invoke(ErrorResponse(exception.fillInStackTrace()))
                }
            }
        }
    }
}


