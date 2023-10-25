package com.ghstudios.android.features.armor.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.ghstudios.android.data.classes.Armor
import com.ghstudios.android.data.classes.Component
import com.ghstudios.android.data.classes.Item
import com.ghstudios.android.data.classes.ItemToSkillTree
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.util.loggedThread
import com.ghstudios.android.util.toList

/**
 * A ViewModel representing information for a single piece of armor.
 */
class ArmorDetailViewModel(app: Application) : AndroidViewModel(app) {
    private val dataManager = DataManager.get()

    val armorData = MutableLiveData<Armor>()
    val skillData = MutableLiveData<List<ItemToSkillTree>>()
    //val componentData = MutableLiveData<List<Component>>()
    val createComponentData = MutableLiveData<List<Component>>()
    val improveComponentData = MutableLiveData<List<Component>>()
    val improve2ComponentData = MutableLiveData<List<Component>>()
    val improve3ComponentData = MutableLiveData<List<Component>>()
    val improve4ComponentData = MutableLiveData<List<Component>>()
    val improve5ComponentData = MutableLiveData<List<Component>>()
    val improve6ComponentData = MutableLiveData<List<Component>>()
    val improve7ComponentData = MutableLiveData<List<Component>>()
    val improve8ComponentData = MutableLiveData<List<Component>>()
    val upgradeToData = MutableLiveData<List<Item>>()
    val upgradeFromData = MutableLiveData<List<Item>>()


    var armorId = -1L
        private set

    /**
     * Sets the armor and begins loading armor data if not already loaded
     */
    fun loadArmor(armorId: Long) {
        if (this.armorId == armorId) {
            return
        }

        this.armorId = armorId

        loggedThread(name = "Armor Loading") {
            armorData.postValue(dataManager.getArmor(armorId))
            upgradeToData.postValue(dataManager.getArmorUpgrades(armorId, "child").toList { it.item })
            upgradeFromData.postValue(dataManager.getArmorUpgrades(armorId, "parent").toList { it.item })

            skillData.postValue(dataManager.queryItemToSkillTreeArrayItem(armorId))

            val components = dataManager.queryComponentCreated(armorId).toList {
                it.component
            }
            //componentData.postValue(components)

            createComponentData.postValue(components.filter { it.type == Component.TYPE_CREATE })
            improveComponentData.postValue(components.filter { it.type == Component.TYPE_IMPROVE })
            improve2ComponentData.postValue(components.filter { it.type == "Improve2" })
            improve3ComponentData.postValue(components.filter { it.type == "Improve3" })
            improve4ComponentData.postValue(components.filter { it.type == "Improve4" })
            improve5ComponentData.postValue(components.filter { it.type == "Improve5" })
            improve6ComponentData.postValue(components.filter { it.type == "Improve6" })
            improve7ComponentData.postValue(components.filter { it.type == "Improve7" })
            improve8ComponentData.postValue(components.filter { it.type == "Improve8" })
        }
    }
}