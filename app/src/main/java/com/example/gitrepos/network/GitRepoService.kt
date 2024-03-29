package com.example.gitrepos.network

import com.example.gitrepos.network.model.GitRepo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

//https://api.github.com/repositories

interface GitRepoService {

    @GET("/repositories")

    suspend fun getGitRepo(): Response<List<GitRepo>?>

}