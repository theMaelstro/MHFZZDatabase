package com.ghstudios.android.features.quests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.ghstudios.android.data.classes.QuestHub

import com.ghstudios.android.data.DataManager
import com.ghstudios.android.mhgendatabase.R

// collection of all possible stars
private val guildStars = arrayOf("1", "2", "3", "4", "5", "6", "7", "11", "12", "13", "14")

private val village = arrayOf("X", "2", "3", "4", "5", "6", "7", "8", "9", "10")
//private val guild = arrayOf("1", "2", "3", "4", "5", "6", "7", "G1", "G2", "G3", "G4")

private val event = arrayOf("Low Rank", "G Rank")
private val hunterquests = arrayOf("HR1", "HR2", "HR3", "HR4", "HR5", "HR6", "Exotic Quests")
private val ghunterquests = arrayOf("Gathering Quests","G1","G2","G3","G4","G5","G6","G7","Burst Origin Quests","G Exotic Quests")
private val guild = arrayOf("Quest Orders","Training Support Quests")
private val specialquests = arrayOf("Superior Quests","Promotional","Premium Quests","Paw Coins","Luxury Quests","Hiden Stone Quests","HR Ranking Rewards")
private val other = arrayOf("Gear Acquisition Quests","Hunting Technique Quests")
private val gspecialquests = arrayOf("G Superior Quests","G Promotional","G Hiden Stone Quests")
private val conquest = arrayOf("Level 1","Level 200","Level 1000","Level 9999","Shiten","Adv Shiten Quests")



/**
 * Pieced together from: Android samples:
 * com.example.android.apis.view.ExpandableList1
 * http://androidword.blogspot.com/2012/01/how-to-use-expandablelistview.html
 * http://stackoverflow.com/questions/6938560/android-fragments-setcontentview-
 * alternative
 * http://stackoverflow.com/questions/6495898/findviewbyid-in-fragment-android
 */
class QuestExpandableListFragment : Fragment() {
    companion object {
        private val ARG_HUB = "QUEST_HUB"

        @JvmStatic fun newInstance(hub: QuestHub): QuestExpandableListFragment {
            val args = Bundle()
            args.putString(ARG_HUB, hub.toString())
            val f = QuestExpandableListFragment()
            f.arguments = args
            return f
        }
    }

    private lateinit var mHub: QuestHub
    private lateinit var groups: List<QuestGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHub = QuestHub.from(arguments?.getString(ARG_HUB) ?: "Unimplemented")
        populateList(mHub)
    }

    // todo: This logic should be moved to a viewmodel
    private fun populateList(hub: QuestHub) {
        val dataManager = DataManager.get()
        val allQuests = dataManager.queryQuestArrayHub(hub).filter {
            it.stars != "" // no zero stars (todo: filter in data manager?)
        }

        if (hub == QuestHub.PERMIT) {
            // Permit quests group by monster instead
            val monsters = dataManager.questDeviantMonsterNames()
            val groupedQuests = allQuests.groupBy { it.permitMonsterId }

            groups = groupedQuests.values.withIndex().map {
                val idx = it.index
                val quests = it.value
                //QuestGroup(monsters[idx], -1, quests) tostring
                QuestGroup(monsters[idx], "", quests)
            }

        } else {
            // Create a mapping from stars to the displayed value
            // Necessary because quests are sometimes out of order
            val labelMap = when (hub) {
                // -> village.zip(village).toMap() // village maps to self
                QuestHub.VILLAGE -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.HUNTERQUESTS -> hunterquests.zip(hunterquests).toMap()
                QuestHub.GHUNTERQUESTS -> ghunterquests.zip(ghunterquests).toMap()
                QuestHub.SPECIALQUESTS -> specialquests.zip(specialquests).toMap()
                QuestHub.GSPECIALQUESTS -> gspecialquests.zip(gspecialquests).toMap()
                QuestHub.CONQUEST -> conquest.zip(conquest).toMap()
                QuestHub.GUILD -> guild.zip(guild).toMap()
                QuestHub.OTHER -> other.zip(other).toMap()
                QuestHub.EVENT -> event.zip(event).toMap()
                QuestHub.PERMIT -> throw RuntimeException("This stretch of code can't handle Permit, unexpected error")
                QuestHub.ARENA -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.DAILY -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.GDAILY -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.GEVENT -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.GEXPERIENCE -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.GGEAR -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.INA -> throw UnsupportedOperationException("Arena is not supported")
                QuestHub.SRGUIDE -> throw UnsupportedOperationException("Arena is not supported")
            }

            // quests grouped by stars
            val groupedQuests = allQuests.groupBy { it.stars }

            // Transform to label/questlist combo, sorted by label position ascending
            groups = groupedQuests.map {
                val stars = it.key
                val quests = it.value
                //QuestGroup(labelMap[stars] ?: "", stars?.toInt() ?: -1, quests) tostring
                QuestGroup(labelMap[stars] ?: "", stars.toString(), quests)
            }.sortedBy { labelMap.values.indexOf(it.name) }

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generic_expandable_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elv = view.findViewById<ExpandableListView>(R.id.expandableListView)

        val type = when (mHub) {
            //QuestHub.VILLAGE -> QuestAdapterType.VILLAGE
            QuestHub.VILLAGE -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.PERMIT -> QuestAdapterType.PERMIT
            QuestHub.GUILD, QuestHub.HUNTERQUESTS, QuestHub.GHUNTERQUESTS, QuestHub.SPECIALQUESTS, QuestHub.GSPECIALQUESTS, QuestHub.OTHER, QuestHub.CONQUEST, QuestHub.EVENT -> QuestAdapterType.GUILD
            QuestHub.ARENA -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.DAILY -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.GDAILY -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.GEVENT -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.GEXPERIENCE -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.GGEAR -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.INA -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
            QuestHub.SRGUIDE -> throw UnsupportedOperationException("Arena is unsupported for expandable fragments")
        }

        elv.setAdapter(QuestListExpandableAdapter(groups, type))
    }

}