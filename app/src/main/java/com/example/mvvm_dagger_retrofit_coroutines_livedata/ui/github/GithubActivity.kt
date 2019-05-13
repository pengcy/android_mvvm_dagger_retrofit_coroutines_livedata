package com.example.mvvm_dagger_retrofit_coroutines_livedata.ui.github

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import com.example.mvvm_dagger_retrofit_coroutines_livedata.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class GithubActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var dispatchingAndroidJnjector : DispatchingAndroidInjector<Fragment>

    lateinit var viewModel : GithubActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_github)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GithubActivityViewModel::class.java)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, GithubFragment.newInstance())
                .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidJnjector
    }
}
