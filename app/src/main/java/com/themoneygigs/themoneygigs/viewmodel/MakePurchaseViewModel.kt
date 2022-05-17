package com.themoneygigs.themoneygigs.viewmodel

/**
 * Created by Brandon Quintanilla on May/14/2022
 **/
import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.themoneygigs.themoneygigs.util.TrivialDriveRepository
import kotlinx.coroutines.launch
import java.util.HashMap

/*
   This is used for any business logic, as well as to echo LiveData from the BillingRepository.
*/
class MakePurchaseViewModel(private val tdr: TrivialDriveRepository) : ViewModel() {

    companion object {
        val TAG = "TrivialDrive:" + MakePurchaseViewModel::class.java.simpleName
        private val skuToResourceIdMap: MutableMap<String, Int> = HashMap()

        init {
/*            skuToResourceIdMap[TrivialDriveRepository.SKU_GAS] = R.drawable.buy_gas
            skuToResourceIdMap[TrivialDriveRepository.SKU_PREMIUM] = R.drawable.upgrade_app
            skuToResourceIdMap[TrivialDriveRepository.SKU_INFINITE_GAS_MONTHLY] = R.drawable.get_infinite_gas*/
//            skuToResourceIdMap[TrivialDriveRepository.SKU_INFINITE_GAS_YEARLY] = R.drawable.get_infinite_gas
        }
    }

    class SkuDetails internal constructor(val sku: String, tdr: TrivialDriveRepository) {
        val title = tdr.getSkuTitle(sku).asLiveData()
        val description = tdr.getSkuDescription(sku).asLiveData()
        val price = tdr.getSkuPrice(sku).asLiveData()
//        val iconDrawableId = skuToResourceIdMap[sku]!!
    }

    fun getSkuDetails(sku: String): SkuDetails {
        return SkuDetails(sku, tdr)
    }

    fun canBuySku(sku: String): LiveData<Boolean> {
        return tdr.canPurchase(sku).asLiveData()
    }

    fun isPurchased(sku: String): LiveData<Boolean> {
        return tdr.isPurchased(sku).asLiveData()
    }

    /**
     * Starts a billing flow for purchasing gas.
     * @param activity
     * @return whether or not we were able to start the flow
     */
    fun buySku(activity: Activity, sku: String) {
        tdr.buySku(activity, sku)
    }

    val billingFlowInProcess: LiveData<Boolean>
        get() = tdr.billingFlowInProcess.asLiveData()

/*    fun sendMessage(message: Int) {
        viewModelScope.launch {
            tdr.sendMessage(message)
        }
    }*/

    class MakePurchaseViewModelFactory(private val trivialDriveRepository: TrivialDriveRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MakePurchaseViewModel::class.java)) {
                return MakePurchaseViewModel(trivialDriveRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
