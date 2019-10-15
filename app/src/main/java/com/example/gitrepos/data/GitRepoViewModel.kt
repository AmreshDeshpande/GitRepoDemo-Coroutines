package com.example.gitrepos.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class GitRepoViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val status: MutableLiveData<Status>? = MutableLiveData()

    //Exposing live data to view and not Mutable live status
    fun getGitRepoData(): LiveData<Status>? = status


    fun getGitRepo() {
        status?.value = Status.Loading
        viewModelScope.launch {
            dataRepository.getGitRepo({ gitRepoList ->
                status?.value = Status.Success(gitRepoList)
            },
                { errorResponse ->
                    status?.value = Status.Error(errorResponse)
                })
        }
    }

}

