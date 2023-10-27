package com.ghstudios.android.features.cuffs.detail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ghstudios.android.data.classes.Component
import com.ghstudios.android.data.classes.Cuff
import com.ghstudios.android.data.DataManager
import com.ghstudios.android.util.loggedThread
import com.ghstudios.android.util.toList

/**
 * Internal view data class to encapsulate skill information.
 */
data class SkillPoints(val skillId: Long, val skillName: String, val points: Int)

class CuffDetailViewModel(app: Application) : AndroidViewModel(app) {
    private val dataManager = DataManager.get()
    val cuffData = MutableLiveData<Cuff>()
    val componentData = MutableLiveData<Map<String, List<Component>>>()

    val cuffSkillData = Transformations.map(cuffData) { cuff ->
        val results = ArrayList<SkillPoints>()
        if (cuff.skill1Id > 0) {
            results.add(SkillPoints(
                cuff.skill1Id, cuff.skill1Name, cuff.skill1Point))
        }
        if (cuff.skill2Id > 0) {
            results.add(SkillPoints(
                cuff.skill2Id, cuff.skill2Name, cuff.skill2Point))
        }
        results
    }

    private var cuffId : Long = -1

    fun setCuff(cuffId : Long) {
        if (this.cuffId == cuffId) {
            return
        }

        this.cuffId = cuffId

        loggedThread(name = "Cuff Loading") {
            val cuff = dataManager.getCuff(cuffId)
            val components = dataManager.queryComponentCreated(cuffId).toList {
                it.component
            }.groupBy { it.type }

            cuffData.postValue(cuff)
            componentData.postValue(components)
        }
    }
}
