package com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mvvm_dagger_retrofit_coroutines_livedata.R
import com.example.mvvm_dagger_retrofit_coroutines_livedata.data.model.GithubAccount
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_github.*
import javax.inject.Inject

class GithubFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel : GithubFragmentViewModel

    companion object {
        fun newInstance() : GithubFragment {
            return GithubFragment()
        }
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fragment_github, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubFragmentViewModel::class.java!!)

        initObserver()

        viewModel.fetchGithubAccountInfo("google")
    }

    private fun initObserver() {

        viewModel.showProgress.observe(this, Observer {
            it?.getContentIfNotHandled()?.let {
                progressbar.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
        })


        val gitHubAccountObserver =
                Observer<GithubAccount> { githubAccount ->
                    tv_account_info.text = githubAccount!!.login + "\n" + githubAccount.createdAt
                }

        viewModel.githubAccount.observe(this, gitHubAccountObserver)
    }
}
