package com.ghstudios.android.features.cuffs.list

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View

import com.ghstudios.android.ClickListeners.CuffClickListener
import com.ghstudios.android.RecyclerViewFragment
import com.ghstudios.android.features.armorsetbuilder.detail.ASBDetailPagerActivity
import com.ghstudios.android.features.cuffs.detail.CuffDetailActivity


class CuffListFragment : RecyclerViewFragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(CuffListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Enable the search filter and dividers
        enableDivider()
        enableFilter {
            viewModel.setFilter(it)
        }

        // Determine if we're arriving from the Armor Set Builder (ASB).
        // If so, we'll also need the number of slots
        val intent = activity!!.intent
        val fromAsb = intent.getBooleanExtra(ASBDetailPagerActivity.EXTRA_FROM_SET_BUILDER, false)
        val maxSlots = when {
            fromAsb -> intent.getIntExtra(ASBDetailPagerActivity.EXTRA_CUFF_MAX_SLOTS, 3)
            else -> Int.MAX_VALUE
        }

        // Create and set the adapter
        val adapter = CuffListAdapter(maxSlots) { cuff, view ->
            // if from asb, clicking should resolve asb, otherwise go to cuff
            if (fromAsb) {
                intent?.putExtra(CuffDetailActivity.EXTRA_CUFF_ID, cuff.id)
                activity?.setResult(Activity.RESULT_OK, intent)
                activity?.finish()
            } else {
                CuffClickListener(context, cuff.id).onClick(view)
            }
        }
        setAdapter(adapter)

        // Listen for cuff data. This updates when the filter updates as well
        viewModel.cuffData.observe(this, Observer {
            if (it == null) return@Observer
            adapter.setItems(it)
        })
    }
}
