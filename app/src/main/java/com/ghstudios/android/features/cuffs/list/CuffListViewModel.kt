package com.ghstudios.android.features.cuffs.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.data.util.SearchFilter
import com.ghstudios.android.util.toList

class CuffListViewModel(app: Application) : AndroidViewModel(app) {
    private val dataManager = DataManager.get()

    /**
     * LiveData containing the current value of the filter.
     * The cuff list derives off this to load the query.
     */
    private val filterSource = MutableLiveData<String>()

    // done synchronously as you can't really map multiple transformations simultaneously...
    private val allCuffData = dataManager.queryCuffs().toList { it.cuff }

    /**
     * Contains the list of cuffs, with a filter applied.
     * Returns cuffs with a name or at least one skill match.
     */
    val cuffData = Transformations.map(filterSource) { searchTerm ->
        // note: we filter in memory because
        // A) more performance
        // B) dataManager.queryCuffSearch only filters on name

        val filter = SearchFilter(searchTerm ?: "")
        allCuffData.filter {
            filter.matches(it.name)
                    || filter.matches(it.skill1Name)
                    || filter.matches(it.skill2Name)
        }
    }

    init {
        setFilter("") // sets filter to blank to trigger initial load
    }

    /**
     * Sets the filter value for the cuff list view model,
     */
    fun setFilter(filter: String) {
        filterSource.value = filter
    }
}