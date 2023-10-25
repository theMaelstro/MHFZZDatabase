package com.ghstudios.android.features.decorations.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ghstudios.android.adapter.common.SimpleRecyclerViewAdapter
import com.ghstudios.android.adapter.common.SimpleViewHolder
import com.ghstudios.android.data.classes.Decoration
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.setImageAsset
import kotlinx.android.synthetic.main.fragment_decoration_listitem.*
import  android.util.Log

/**
 * A RecyclerView adapter used to display decorations
 * @param maxSlots The max possible slots. Any slot value below this number will be greyed out.
 */
class DecorationListAdapter(
        private val maxSlots: Int = Int.MAX_VALUE,
        private val onSelected: (Decoration, View) -> Unit

) : SimpleRecyclerViewAdapter<Decoration>() {
    override fun onCreateView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.fragment_decoration_listitem,
                parent, false)
    }

    override fun bindView(viewHolder: SimpleViewHolder, decoration: Decoration) {
        // Set up the text view
        val itemImageView = viewHolder.item_image
        val decorationNameTextView = viewHolder.item
        val skill1TextView = viewHolder.skill1
        val skill1amtTextView = viewHolder.skill1_amt
        val skill2TextView = viewHolder.skill2
        val skill2amtTextView = viewHolder.skill2_amt
        val skill3TextView = viewHolder.skill3
        val skill3amtTextView = viewHolder.skill3_amt
        val skill4TextView = viewHolder.skill4
        val skill4amtTextView = viewHolder.skill4_amt

        itemImageView.setImageAsset(decoration)
        decorationNameTextView.text = decoration.name
        skill1TextView.text = decoration.skill1Name
        skill1amtTextView.text = decoration.skill1Point.toString()

        skill2TextView.visibility = View.GONE
        skill2amtTextView.visibility = View.GONE

        skill3TextView.visibility = View.GONE
        skill3amtTextView.visibility = View.GONE

        skill4TextView.visibility = View.GONE
        skill4amtTextView.visibility = View.GONE

        if (decoration.skill2Point != 0) {
            skill2TextView.text = decoration.skill2Name
            skill2amtTextView.text = decoration.skill2Point.toString()
            skill2TextView.visibility = View.VISIBLE
            skill2amtTextView.visibility = View.VISIBLE
        }

        if (decoration.skill3Point != 0) {
            skill3TextView.text = decoration.skill3Name
            skill3amtTextView.text = decoration.skill3Point.toString()
            skill3TextView.visibility = View.VISIBLE
            skill3amtTextView.visibility = View.VISIBLE
        }

        if (decoration.skill4Point != 0) {
            skill4TextView.text = decoration.skill4Name
            skill4amtTextView.text = decoration.skill4Point.toString()
            skill4TextView.visibility = View.VISIBLE
            skill4amtTextView.visibility = View.VISIBLE
        }

        viewHolder.itemView.tag = decoration.id

        val fitsInArmor = decoration.numSlots <= maxSlots

        viewHolder.itemView.isEnabled = fitsInArmor
        itemImageView.alpha = if (fitsInArmor) 1.0f else 0.5f

        if (fitsInArmor) {
            viewHolder.itemView.setOnClickListener {
                onSelected(decoration, viewHolder.itemView)
            }
        } else {
            viewHolder.itemView.setOnClickListener(null)
        }
    }

}