package com.ghstudios.android.features.cuffs.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghstudios.android.adapter.common.SimpleRecyclerViewAdapter
import com.ghstudios.android.adapter.common.SimpleViewHolder
import com.ghstudios.android.data.classes.Cuff
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.setImageAsset
import kotlinx.android.synthetic.main.fragment_cuff_listitem.*
import  android.util.Log

/**
 * A RecyclerView adapter used to display cuffs
 * @param maxSlots The max possible slots. Any slot value below this number will be greyed out.
 */
class CuffListAdapter(
    private val maxSlots: Int = Int.MAX_VALUE,
    private val onSelected: (Cuff, View) -> Unit

) : SimpleRecyclerViewAdapter<Cuff>() {
    override fun onCreateView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.fragment_cuff_listitem,
            parent, false)
    }

    override fun bindView(viewHolder: SimpleViewHolder, cuff: Cuff) {
        // Set up the text view
        val itemImageView = viewHolder.item_image
        val cuffNameTextView = viewHolder.item
        val skill1TextView = viewHolder.skill1
        val skill1amtTextView = viewHolder.skill1_amt
        val skill2TextView = viewHolder.skill2
        val skill2amtTextView = viewHolder.skill2_amt
        val skill3TextView = viewHolder.skill3
        val skill3amtTextView = viewHolder.skill3_amt
        val skill4TextView = viewHolder.skill4
        val skill4amtTextView = viewHolder.skill4_amt

        itemImageView.setImageAsset(cuff)
        cuffNameTextView.text = cuff.name
        skill1TextView.text = cuff.skill1Name
        skill1amtTextView.text = cuff.skill1Point.toString()

        skill2TextView.visibility = View.GONE
        skill2amtTextView.visibility = View.GONE

        skill3TextView.visibility = View.GONE
        skill3amtTextView.visibility = View.GONE

        skill4TextView.visibility = View.GONE
        skill4amtTextView.visibility = View.GONE

        if (cuff.skill2Point != 0) {
            skill2TextView.text = cuff.skill2Name
            skill2amtTextView.text = cuff.skill2Point.toString()
            skill2TextView.visibility = View.VISIBLE
            skill2amtTextView.visibility = View.VISIBLE
        }

        viewHolder.itemView.tag = cuff.id

        val fitsInArmor = cuff.numSlots <= maxSlots

        viewHolder.itemView.isEnabled = fitsInArmor
        itemImageView.alpha = if (fitsInArmor) 1.0f else 0.5f

        if (fitsInArmor) {
            viewHolder.itemView.setOnClickListener {
                onSelected(cuff, viewHolder.itemView)
            }
        } else {
            viewHolder.itemView.setOnClickListener(null)
        }
    }

}