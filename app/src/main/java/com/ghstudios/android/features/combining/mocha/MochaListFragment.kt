package com.ghstudios.android.features.combining.mocha

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.ghstudios.android.RecyclerViewFragment

class MochaListFragment : RecyclerViewFragment() {
    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MochaListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadMocha()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MochaListAdapter()

        setAdapter(adapter)
        enableDivider()

        viewModel.mochaData.observe(this, Observer {
            if (it != null && adapter.itemCount == 0) {
                adapter.setItems(it)
            }
        })
    }
}
