package com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.SingleLiveEventWrapper
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.GithubAccount
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.repository.DataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class GithubFragmentViewModel
@Inject constructor(private val dataRepository: DataRepository): BaseViewModel() {
    private val _githubAccount = MutableLiveData<GithubAccount>()
    val githubAccount: LiveData<GithubAccount> get() = _githubAccount

    private val _showProgress = MutableLiveData<SingleLiveEventWrapper<Boolean>>()
    val showProgress: LiveData<SingleLiveEventWrapper<Boolean>> get() = _showProgress

    fun fetchGithubAccountInfo(username: String) {

        launch {
            _showProgress.value = SingleLiveEventWrapper(true)
            val githubAccountResource = dataRepository.fetchGithubAccount(username).await()
            _showProgress.value = SingleLiveEventWrapper(false)


            githubAccountResource.data?.let {
                _githubAccount.value = it
            }

            githubAccountResource.apiError?.error?.let {
                it.printStackTrace()
            }
        }

    }

}
