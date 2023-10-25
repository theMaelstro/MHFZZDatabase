package com.ghstudios.android.features.bentos.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.data.classes.Bento
import com.ghstudios.android.util.loggedThread
import com.ghstudios.android.util.toList

class BentoListViewModel(app: Application) : AndroidViewModel(app) {
    private val dataManager = DataManager.get()

    private var initialized = false
    val bentoData = MutableLiveData<List<Bento>>()

    fun loadBentos() {
        if (initialized) {
            return
        }

        initialized = true

        loggedThread("Bento List Load") {
            bentoData.postValue(dataManager.queryBentos("").toList { it.bento })
        }
    }

    /*
    fun loadBentos() {
        if (initialized) {
            return
        }

        initialized = true

     */
        /*
        loggedThread("Bento List Load") {
            bentoData.postValue(dataManager.queryBentos("").toList { it.bento })
        }


    }
    */
}