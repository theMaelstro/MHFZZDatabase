package com.ghstudios.android.features.bentos.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.ghstudios.android.data.classes.*
import com.ghstudios.android.data.classes.meta.BentoMetadata
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.data.classes.meta.ItemMetadata
import com.ghstudios.android.util.loggedThread
import com.ghstudios.android.util.toList

/**
 * A viewmodel for the entirety of bento detail data.
 * This should be attached to the activty or fragment owning the viewpager.
 */
class BentoDetailViewModel(app : Application) : AndroidViewModel(app) {
    private val dataManager = DataManager.get()

    val bentoData = MutableLiveData<Item>()
    val recipeData = MutableLiveData<List<Bento>>()
    val ingredientData = MutableLiveData<List<Item>>()


    var bentoId = -1L
        private set

    var metadata: ItemMetadata? = null

    /**
     * Sets the viewmodel to represent a bento, and loads the viewmodels
     * Does nothing if the data is already loaded.
     */
    fun setBento(bentoId : Long): ItemMetadata {
        if (this.bentoId == bentoId && this.metadata != null) {
            return metadata!!
        }

        this.bentoId = bentoId
        this.metadata = dataManager.queryItemMetadata(bentoId)


        loggedThread(name = "Bento Loading") {
            // load and post metadata and bento first (high priority)
            bentoData.postValue(dataManager.getItem(bentoId))
            //recipeData.postValue(dataManager.getBentoRecipes(bentoId))

            val recipeResults = dataManager.queryRecipesOnItemID(bentoId).toList {
                it.bento
            }
            recipeData.postValue(recipeResults)




            //bentoData.postValue(dataManager.queryBentos().toList { it.bento })
        }

        return metadata!!
    }
}