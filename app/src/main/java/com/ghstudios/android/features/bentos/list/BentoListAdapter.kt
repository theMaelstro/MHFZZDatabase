package com.ghstudios.android.features.bentos.list

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ghstudios.android.AssetLoader
import com.ghstudios.android.ClickListeners.BasicItemClickListener
import com.ghstudios.android.ClickListeners.BentoClickListener
import com.ghstudios.android.adapter.common.SimpleRecyclerViewAdapter
import com.ghstudios.android.adapter.common.SimpleViewHolder
import com.ghstudios.android.data.classes.Bento
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.setImageAsset
import kotlinx.android.synthetic.main.fragment_item_listitem.icon
import kotlinx.android.synthetic.main.fragment_list_item_large.*
import kotlinx.android.synthetic.main.listitem_bento.*

class BentoListAdapter : SimpleRecyclerViewAdapter<Bento>() {

    var resultItemNavigationEnabled = true

    //override fun isForViewType(obj: Any) = obj is Bento


    override fun onCreateView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.fragment_list_item_large,
            parent, false)
    }

    override fun bindView(viewHolder: SimpleViewHolder, bento: Bento) {
        val context = viewHolder.context

        viewHolder.item_image.setImageAsset(bento)
        viewHolder.item_label.text = bento.name

        //viewHolder.effect_1.text = bento.effect1.toString()
        //viewHolder.effect_2.text = bento.effect2.toString()
        //viewHolder.effect_3.text = bento.effect3.toString()
        //viewHolder.effect_4.text = bento.effect4.toString()

        /*
        if (bento.effect1 == 0L) {
            viewHolder.effect_1.visibility = View.GONE
            viewHolder.effectheader_1.visibility = View.GONE
        }
        else {
            viewHolder.effect_1.visibility = View.VISIBLE
            viewHolder.effectheader_1.visibility = View.VISIBLE
        }

        if (bento.effect2 == 0L) {
            viewHolder.effect_2.visibility = View.GONE
            viewHolder.effectheader_2.visibility = View.GONE
        }
        else {
            viewHolder.effect_2.visibility = View.VISIBLE
            viewHolder.effectheader_2.visibility = View.VISIBLE
        }

        if (bento.effect3 == 0L) {
            viewHolder.effect_3.visibility = View.GONE
            viewHolder.effectheader_3.visibility = View.GONE
        }
        else {
            viewHolder.effect_3.visibility = View.VISIBLE
            viewHolder.effectheader_3.visibility = View.VISIBLE
        }

        if (bento.effect4 == 0L) {
            viewHolder.effect_4.visibility = View.GONE
            viewHolder.effectheader_4.visibility = View.GONE
        }
        else {
            viewHolder.effect_4.visibility = View.VISIBLE
            viewHolder.effectheader_4.visibility = View.VISIBLE
        }

         */


        val listener = BentoClickListener(viewHolder.context, bento.id)
        viewHolder.itemView.setOnClickListener(listener)
    }
}