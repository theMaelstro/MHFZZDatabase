package com.ghstudios.android.features.monsters.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ghstudios.android.AssetLoader
import com.ghstudios.android.ClickListeners.MonsterClickListener
import com.ghstudios.android.adapter.common.SimpleViewHolder
import com.ghstudios.android.data.classes.Monster
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.setImageAsset
import com.ghstudios.android.data.classes.MonsterType

import kotlinx.android.synthetic.main.fragment_list_item_large.*

enum class MonsterAdapterType {
    TYPE
}

class MonsterGroup(
    val name: String,
    val type: String,
    val monsters: List<Monster>
)

class MonsterListExpandableAdapter(
    private val monsterGroups: List<MonsterGroup>,
    private val type: MonsterAdapterType
) : BaseExpandableListAdapter() {

    override fun hasStableIds() = true
    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = true

    override fun getGroupCount() = monsterGroups.size
    override fun getChildrenCount(i: Int) = monsterGroups[i].monsters.size

    override fun getGroupId(i: Int) = i.toLong()
    override fun getChildId(groupPosition: Int, childPosition: Int) = groupPosition * 200L + childPosition

    override fun getGroup(i: Int) = monsterGroups[i]

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return monsterGroups[groupPosition].monsters[childPosition]
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, view: View?,
                              viewGroup: ViewGroup?): View {
        val context = viewGroup?.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(
            R.layout.fragment_monster_expandablelist_group_item,
            viewGroup, false)

        val monsterGroupTextView = v.findViewById<TextView>(R.id.numstars)

        val group = getGroup(groupPosition)

        monsterGroupTextView.text = AssetLoader.localizeMonsterType(MonsterType.from(group.name))


        return v
    }

    override fun getChildView(i: Int, i1: Int, b: Boolean, view: View?,
                              viewGroup: ViewGroup?): View {
        val context = viewGroup?.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(
            R.layout.fragment_list_item_large,
            viewGroup, false)

        /*
        override fun bindView(viewHolder: SimpleViewHolder, monster: Monster) {
            AssetLoader.setIcon(viewHolder.item_image,monster)
            viewHolder.item_label.text = monster.name
            viewHolder.itemView.tag = monster.id
            viewHolder.itemView.setOnClickListener(MonsterClickListener(viewHolder.context, monster.id))
        }
        */

        val iv = v.findViewById<ImageView>(R.id.item_image)
        val monsterChildTextView = v.findViewById<TextView>(R.id.item_label)

        val monster = getChild(i, i1) as Monster
        AssetLoader.setIcon(iv, monster)

        monsterChildTextView.text = monster.name

        val monsterId = monster.id

        v.tag = monsterId
        v.setOnClickListener(MonsterClickListener(context, monsterId))
        return v
    }
}