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
import com.ghstudios.android.data.classes.StoreOffer
import com.ghstudios.android.mhgendatabase.R
import kotlinx.android.synthetic.main.fragment_combining_listitem.*
import kotlinx.android.synthetic.main.fragment_items_store_listitem.*

/**
 * An adapter delegate that can be added to any adapter delegate adapter.
 * Renders item combination information
 */
class ItemStoreAdapterDelegate: SimpleListDelegate<StoreOffer>() {
    /**
     * Sets whether the result item performs navigation. Defaults to true.
     * Use before adding items to the adapter
     */
    var resultItemNavigationEnabled = true

    override fun isForViewType(obj: Any) = obj is StoreOffer

    override fun onCreateView(parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(R.layout.fragment_items_store_listitem, parent, false)
    }

    override fun bindView(viewHolder: SimpleViewHolder, data: StoreOffer) {
        val context = viewHolder.context
        AssetLoader.setIcon(viewHolder.item_icon,data.product)
        viewHolder.item_name.text = data.product!!.name
        viewHolder.store_name.text = data.store
        viewHolder.store_notes.text = data.notes
        viewHolder.store_cost.text = data.cost
        viewHolder.store_quantity.text = "x${data.quantity}"



        if (data.cost == "null" || data.cost == "-"){
            viewHolder.store_cost.visibility = View.INVISIBLE
        }
        if (data.quantity == "null"){
            viewHolder.store_quantity.visibility = View.INVISIBLE
        }
        if (data.notes == "null"){
            viewHolder.store_notes.visibility = View.INVISIBLE
        }
    }

}
