package com.themoneygigs.themoneygigs

import android.app.Application
import com.themoneygigs.themoneygigs.util.BillingDataSource
import com.themoneygigs.themoneygigs.util.TrivialDriveRepository
import kotlinx.coroutines.GlobalScope

/**
 * Created by Brandon Quintanilla on May/13/2022
 **/
class App : Application() {

    lateinit var appContainer: AppContainer

    inner class AppContainer {
        private val applicationScope = GlobalScope

        //        private val gameStateModel = GameStateModel(this@TrivialDriveApplication)
        private val billingDataSource = BillingDataSource.getInstance(
            this@App,
            applicationScope,
//            TrivialDriveRepository.INAPP_SKUS,
            TrivialDriveRepository.SUBSCRIPTION_SKUS
//            TrivialDriveRepository.AUTO_CONSUME_SKUS
        )
        val trivialDriveRepository = TrivialDriveRepository(
            billingDataSource,
//            gameStateModel,
            applicationScope
        )
    }

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}