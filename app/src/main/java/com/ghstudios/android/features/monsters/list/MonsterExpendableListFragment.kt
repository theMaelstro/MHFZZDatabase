package com.ghstudios.android.features.monsters.list
import com.ghstudios.android.features.monsters.list.MonsterListExpandableAdapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import com.ghstudios.android.data.classes.MonsterType

import com.ghstudios.android.data.DataManager
import com.ghstudios.android.mhgendatabase.R

// collection of all possible stars
private val village = arrayOf("X", "2", "3", "4", "5", "6", "7", "8", "9", "10")
private val montype = arrayOf(
    "Bird Wyvern",
    "Brute Wyvern",
    "Carapaceon",
    "Elder Dragon",
    "Fanged Wyvern",
    "Flying Wyvern",
    "Herbivore",
    "Leviathan",
    "Lynian",
    "Neopteron",
    "Pelagus",
    "Piscine Wyvern",
    "Snake Wyvern",
    "Unknown"
)

/**
 * Pieced together from: Android samples:
 * com.example.android.apis.view.ExpandableList1
 * http://androidword.blogspot.com/2012/01/how-to-use-expandablelistview.html
 * http://stackoverflow.com/questions/6938560/android-fragments-setcontentview-
 * alternative
 * http://stackoverflow.com/questions/6495898/findviewbyid-in-fragment-android
 */
class MonsterExpendableListFragment : Fragment() {
    companion object {
        private val ARG_TYPE = "MONSTER_TYPE"

        @JvmStatic fun newInstance(tab: String): MonsterExpendableListFragment {
            val args = Bundle()
            args.putString(ARG_TYPE, tab)
            val f = MonsterExpendableListFragment()
            f.arguments = args
            return f
        }
    }

    private lateinit var mType: MonsterType
    private lateinit var groups: List<MonsterGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //mType = MonsterType.from(arguments?.getString(ARG_TYPE) ?: "Type")
        populateList("Type")
    }

    // todo: This logic should be moved to a viewmodel
    private fun populateList(type: String) {
        val dataManager = DataManager.get()
        //val allMonsters = dataManager.queryMonsterArrayType(type).filter {
        val allMonsters = dataManager.queryMonsterArrayType()
            /*.filter {
            it.monsterClassType != null // no empty class (todo: filter in data manager?)
        }*/
        Log.d(allMonsters.toString(),"Class")

        // Create a mapping from stars to the displayed value
        // Necessary because quests are sometimes out of order
        val labelMap = montype.zip(montype).toMap()

        // quests grouped by stars
        val groupedMonsters = allMonsters.groupBy { it.monsterClassType.toString() }
        // Transform to label/questlist combo, sorted by label position ascending
        groups = groupedMonsters.map {
            val stars = it.key
            val monsters = it.value
            Log.d(stars.toString(), "KEY")
            Log.d(monsters.toString(),"VALUE")
            //MonsterGroup(labelMap[stars] ?: "x", stars.toString(), monsters)
            MonsterGroup(labelMap[stars] ?: "NULL", stars?.toString(), monsters)
        }.sortedBy { labelMap.values.indexOf(it.name) }


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generic_expandable_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val elv = view.findViewById<ExpandableListView>(R.id.expandableListView)

        val type = MonsterAdapterType.TYPE

        elv.setAdapter(MonsterListExpandableAdapter(groups, type))
    }

}