package com.ghstudios.android.features.combining.mocha

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ghstudios.android.AssetLoader
import com.ghstudios.android.ClickListeners.BasicItemClickListener
import com.ghstudios.android.adapter.common.SimpleRecyclerViewAdapter
import com.ghstudios.android.adapter.common.SimpleViewHolder
import com.ghstudios.android.data.classes.Mocha
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.setImageAsset
import kotlinx.android.synthetic.main.fragment_combining_mocha_listitem.*

class MochaListAdapter : SimpleRecyclerViewAdapter<Mocha>() {

    var resultItemNavigationEnabled = true

    //override fun isForViewType(obj: Any) = obj is Mocha


    override fun onCreateView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.fragment_combining_mocha_listitem,
            parent, false)
    }

    override fun bindView(viewHolder: SimpleViewHolder, mocha: Mocha) {
        val context = viewHolder.context
        viewHolder.mochapot_icon.setImageAsset(mocha.pot)
        viewHolder.mochapot_name.text = mocha.pot?.name ?: "Mocha Pot"

        viewHolder.input_icon.setImageAsset(mocha.input)
        viewHolder.input_name.text = mocha.input?.name ?: "Mocha Input"

        viewHolder.item1_name.text = mocha.item1?.name ?: "Result 1"
        viewHolder.item1_icon.setImageAsset(mocha.item1)

        viewHolder.item2_name.text = mocha.item2?.name ?: "Result 2"
        viewHolder.item2_icon.setImageAsset(mocha.item2)

        viewHolder.item3_name.text = mocha.item3?.name ?: "Result 3"
        viewHolder.item3_icon.setImageAsset(mocha.item3)

        viewHolder.item4_name.text = mocha.item4?.name ?: "Result 4"
        viewHolder.item4_icon.setImageAsset(mocha.item4)

        viewHolder.item5_name.text = mocha.item5?.name ?: "Result 5"
        viewHolder.item5_icon.setImageAsset(mocha.item5)

        viewHolder.item6_name.text = mocha.item6?.name ?: "Result 6"
        viewHolder.item6_icon.setImageAsset(mocha.item6)

        // Click listeners
        val listenerPot = BasicItemClickListener(viewHolder.context, mocha.pot?.id)
        viewHolder.mochapot.setOnClickListener(listenerPot)

        val listenerItem1 = BasicItemClickListener(viewHolder.context, mocha.item1?.id)
        viewHolder.item1.setOnClickListener(listenerItem1)

        val listenerItem3 = BasicItemClickListener(viewHolder.context, mocha.item3?.id)
        viewHolder.item3.setOnClickListener(listenerItem3)

        val listenerItem5 = BasicItemClickListener(viewHolder.context, mocha.item5?.id)
        viewHolder.item5.setOnClickListener(listenerItem5)

        val listenerInput = BasicItemClickListener(viewHolder.context, mocha.input?.id)
        viewHolder.input.setOnClickListener(listenerInput)

        val listenerItem2 = BasicItemClickListener(viewHolder.context, mocha.item2?.id)
        viewHolder.item2.setOnClickListener(listenerItem2)

        val listenerItem4 = BasicItemClickListener(viewHolder.context, mocha.item4?.id)
        viewHolder.item4.setOnClickListener(listenerItem4)

        val listenerItem6 = BasicItemClickListener(viewHolder.context, mocha.item6?.id)
        viewHolder.item6.setOnClickListener(listenerItem6)
    }
}