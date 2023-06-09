package com.tanitama.green

import android.app.Application
import android.content.Context
import com.tanitama.green.data.remote.ApiService
import com.tanitama.green.data.remote.RetrofitClient
import com.tanitama.green.data.repository.AuthRepository
import com.tanitama.green.data.repository.DetectionsRepository
import com.tanitama.green.presentation.viewmodel.AuthViewModel
import com.tanitama.green.presentation.viewmodel.DiseaseDetectionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TanitamaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@TanitamaApp)
            modules(vmModule, repositoryModule)
        }
    }

    private val vmModule = module {
        viewModel { AuthViewModel(get()) }
        viewModel { DiseaseDetectionViewModel(get()) }
    }

    private val repositoryModule = module {
        single { RetrofitClient.createService<ApiService>() }
        single { AuthRepository(get()) }
        single { DetectionsRepository(get()) }
    }

    companion object {
        lateinit var context: Context
    }
}