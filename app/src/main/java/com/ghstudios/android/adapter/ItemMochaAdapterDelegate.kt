package com.ghstudios.android.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.ghstudios.android.AssetLoader
import com.ghstudios.android.ClickListeners.BasicItemClickListener
import com.ghstudios.android.adapter.common.SimpleListDelegate
import com.ghstudios.android.adapter.common.SimpleViewHolder
import com.ghstudios.android.data.classes.Mocha
import com.ghstudios.android.mhgendatabase.R
import kotlinx.android.synthetic.main.fragment_combining_mocha_listitem.*

/**
 * An adapter delegate that can be added to any adapter delegate adapter.
 * Renders item combination information
 */
class ItemMochaAdapterDelegate: SimpleListDelegate<Mocha>() {
    /**
     * Sets whether the result item performs navigation. Defaults to true.
     * Use before adding items to the adapter
     */
    var resultItemNavigationEnabled = true

    override fun isForViewType(obj: Any) = obj is Mocha

    override fun onCreateView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.fragment_combining_mocha_listitem, parent, false)
    }

    override fun bindView(viewHolder: SimpleViewHolder, data: Mocha) {
        val context = viewHolder.context

        AssetLoader.setIcon(viewHolder.mochapot_icon,data.pot)
        AssetLoader.setIcon(viewHolder.input_icon,data.input)
        AssetLoader.setIcon(viewHolder.item1_icon,data.item1)
        AssetLoader.setIcon(viewHolder.item2_icon,data.item2)
        AssetLoader.setIcon(viewHolder.item3_icon, data.item3)
        AssetLoader.setIcon(viewHolder.item4_icon,data.item4)
        AssetLoader.setIcon(viewHolder.item5_icon,data.item5)
        AssetLoader.setIcon(viewHolder.item6_icon, data.item6)

        viewHolder.mochapot_name.text = data.pot?.name
        viewHolder.input_name.text = data.input?.name
        viewHolder.item1_name.text = data.item1?.name
        viewHolder.item2_name.text = data.item2?.name
        viewHolder.item3_name.text = data.item3?.name
        viewHolder.item4_name.text = data.item4?.name
        viewHolder.item5_name.text = data.item5?.name
        viewHolder.item6_name.text = data.item6?.name

        viewHolder.mochapot.setOnClickListener(BasicItemClickListener(context, data.pot?.id))
        viewHolder.input.setOnClickListener(BasicItemClickListener(context, data.input?.id))
        viewHolder.item1.setOnClickListener(BasicItemClickListener(context, data.item1?.id))
        viewHolder.item2.setOnClickListener(BasicItemClickListener(context, data.item2?.id))
        viewHolder.item3.setOnClickListener(BasicItemClickListener(context, data.item3?.id))
        viewHolder.item4.setOnClickListener(BasicItemClickListener(context, data.item4?.id))
        viewHolder.item5.setOnClickListener(BasicItemClickListener(context, data.item5?.id))
        viewHolder.item6.setOnClickListener(BasicItemClickListener(context, data.item6?.id))

        if (resultItemNavigationEnabled) {
            viewHolder.itemView.setOnClickListener(BasicItemClickListener(context, data.pot?.id))
        } else {
            // disable selectable item background
            viewHolder.itemView.setBackgroundResource(0)
        }

    }

}
