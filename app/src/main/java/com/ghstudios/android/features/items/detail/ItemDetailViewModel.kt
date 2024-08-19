package com.ghstudios.android.features.items.detail

import android.util.Log

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.ghstudios.android.util.MHUtils
import com.ghstudios.android.data.classes.meta.ItemMetadata
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.data.classes.*
import com.ghstudios.android.util.loggedThread
import com.ghstudios.android.util.toList
import kotlin.math.log

data class ItemUsage(
        val combinations: List<Combining>,
        val mocha: List<Mocha>,
        val crafting: List<Component>
)

class ItemDetailViewModel(app: Application): AndroidViewModel(app) {
    private val dataManager = DataManager.get()

    val itemData = MutableLiveData<Item>()
    val craftData = MutableLiveData<List<Combining>>()
    val mochaData = MutableLiveData<List<Mocha>>()
    val usageData = MutableLiveData<ItemUsage>()
    val storeData = MutableLiveData<List<StoreOffer>>()


    // live data used to create cursors. Every call returns a new cursor. Temporary and here to support incremental refactor
    val monsterRewardsData
        get() = MHUtils.createLiveData { dataManager.queryHuntingRewardItem(itemId) }

    // live data used to create cursors. Every call returns a new cursor. Temporary and here to support incremental refactor
    val questRewardsData
        get() = MHUtils.createLiveData { dataManager.queryQuestRewardItem(itemId) }

    /**
     * Returns a livedata containing a list of item gather data.
     */
    val gatherData = MutableLiveData<List<Gathering>>()

    var itemId = -1L
        private set


    private var metadata: ItemMetadata? = null

    /**
     * If the item id is unique, loads item data.
     */
    fun setItem(itemId: Long): ItemMetadata {
        if (this.itemId == itemId && this.metadata != null) {
            return metadata!!
        }

        this.itemId = itemId
        this.metadata = dataManager.queryItemMetadata(itemId)

        loggedThread(name = "Item Loading") {
            itemData.postValue(dataManager.getItem(itemId))

            // query crafting usage
            val craftUsage = dataManager.queryComponentComponent(itemId).toList {
                it.component
            }

            // query combination usage
            val combiningResults = dataManager.queryCombiningOnItemID(itemId).toList {
                it.combining
            }

            //query mocha usage
            val mochaResults = dataManager.queryMochaOnItemID(itemId).toList {
                it.mocha
            }

            // Combinations that result in this item are added to the craft list
            craftData.postValue(combiningResults.filter { it.createdItem.id == itemId })

            mochaData.postValue(
                mochaResults.filter {
                    it.item1?.id == itemId ||
                    it.item2?.id == itemId ||
                    it.item3?.id == itemId ||
                    it.item4?.id == itemId ||
                    it.item5?.id == itemId ||
                    it.item6?.id == itemId
                }
            )

            usageData.postValue(ItemUsage(
                    combinations = combiningResults.filter { it.createdItem.id != itemId },
                    mocha = mochaResults.filter {
                        it.input?.id == itemId
                    },
                    crafting = craftUsage
            ))
            storeData.postValue(dataManager.queryOffersOnItemID(itemId).toList { it.storeoffer })

            // query and post gathering data
            val gatherDataItems = dataManager.queryGatheringItem(itemId).toList { it.gathering }
            gatherData.postValue(gatherDataItems)
        }

        return metadata!!
    }
}