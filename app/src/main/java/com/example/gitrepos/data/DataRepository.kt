package com.example.gitrepos.data


import android.util.Log
import com.example.gitrepos.network.GitRepoService
import com.example.gitrepos.network.model.ErrorResponse
import com.example.gitrepos.network.model.GitRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class DataRepository(private val gitRepoService: GitRepoService) {

    private val TAG = "DataRepository"

    suspend fun getGitRepo(success: (List<GitRepo>?) -> (Unit), failure: (ErrorResponse) -> (Unit)) {

        try {
            val response = withContext(Dispatchers.IO) {
                gitRepoService.getGitRepo()
            }

            try {
                if (response.isSuccessful) {
                    success.invoke(response.body())
                } else {
                    failure.invoke(ErrorResponse(Throwable()))
                }
            } catch (throwable: Throwable) {
                failure.invoke(ErrorResponse(throwable))
            } catch (e: HttpException) {
                Log.e(TAG, e.message())
            }

        } catch (exception: Exception) {
            exception.printStackTrace()
            failure.invoke(ErrorResponse(exception.fillInStackTrace()))
        }
    }
}


