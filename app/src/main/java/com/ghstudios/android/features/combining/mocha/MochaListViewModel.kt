package com.ghstudios.android.features.combining.mocha

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.data.classes.Mocha
import com.ghstudios.android.util.loggedThread
import com.ghstudios.android.util.toList

class MochaListViewModel(app: Application) : AndroidViewModel(app) {
    private val dataManager = DataManager.get()

    private var initialized = false
    val mochaData = MutableLiveData<List<Mocha>>()

    fun loadMocha() {
        if (initialized) {
            return
        }

        initialized = true

        loggedThread("Mocha List Load") {
            mochaData.postValue(dataManager.queryMochas().toList { it.mocha })
        }
    }
}