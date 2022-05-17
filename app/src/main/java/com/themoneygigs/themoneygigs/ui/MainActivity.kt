package com.themoneygigs.themoneygigs.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.themoneygigs.themoneygigs.App
import com.themoneygigs.themoneygigs.R
import com.themoneygigs.themoneygigs.util.TrivialDriveRepository
import com.themoneygigs.themoneygigs.viewmodel.MakePurchaseViewModel

class MainActivity : AppCompatActivity() {

    lateinit var makePurchaseViewModel: MakePurchaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val makePurchaseViewModelFactory = MakePurchaseViewModel.MakePurchaseViewModelFactory(
            (application as App).appContainer.trivialDriveRepository
        )
        makePurchaseViewModel =
            ViewModelProvider(this, makePurchaseViewModelFactory)[MakePurchaseViewModel::class.java]

        makePurchaseViewModel.canBuySku(TrivialDriveRepository.SKU_INFINITE_GAS_MONTHLY)
            .observe(this) {
                Log.i("#MainActivity", "canBuySku SKU_INFINITE_GAS_MONTHLY: " + it)
            }
        makePurchaseViewModel.canBuySku(TrivialDriveRepository.SKU_INFINITE_GAS_YEARLY)
            .observe(this) {

                Log.i("#MainActivity", "canBuySku SKU_INFINITE_GAS_YEARLY: " + it)
            }
        /*************************************/
        makePurchaseViewModel.isPurchased(TrivialDriveRepository.SKU_INFINITE_GAS_YEARLY)
            .observe(this) {

                Log.i("#MainActivity", "isPurchased SKU_INFINITE_GAS_YEARLY: " + it)
            }
        makePurchaseViewModel.isPurchased(TrivialDriveRepository.SKU_INFINITE_GAS_YEARLY)
            .observe(this) {

                Log.i("#MainActivity", "isPurchased SKU_INFINITE_GAS_YEARLY: " + it)
            }
        makePurchaseViewModel.isPurchased(TrivialDriveRepository.SKU_TMG_TEST)
            .observe(this) {

                Log.i("#MainActivity", "isPurchased SKU_TMG_TEST: " + it)
            }
    }
}