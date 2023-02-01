package com.example.githubrepositoriessearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepositoriessearch.adpter.Event
import com.example.githubrepositoriessearch.http.RetrofitInstance
import com.example.githubrepositoriessearch.model.Repository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var repoLiveData = MutableLiveData<List<Repository>>()

    var selected: MutableLiveData<Repository> = MutableLiveData()

    var openItemEvent: MutableLiveData<Event<Repository>> = MutableLiveData()

    fun getSearchResult() {
        viewModelScope.launch(IO){
            val result = RetrofitInstance.api.getRepositories("MVVM")
            repoLiveData.postValue(result.body()?.items)
        }
    }

    fun observeRepoLiveData() : LiveData<List<Repository>> {
        return repoLiveData
    }

    fun openItem(item: Repository) {
        selected.value = item
        openItemEvent.value = Event(item)
    }
}