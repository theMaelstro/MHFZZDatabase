package com.ghstudios.android.features.bentos.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.ghstudios.android.RecyclerViewFragment

class BentoListFragment : RecyclerViewFragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(BentoListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadBentos()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = BentoListAdapter()

        setAdapter(adapter)
        enableDivider()

        viewModel.bentoData.observe(this, Observer {
            if (it != null && adapter.itemCount == 0) {
                adapter.setItems(it)
            }
        })
    }
}
