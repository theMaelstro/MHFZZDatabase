package com.ghstudios.android.features.bentos.list

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ghstudios.android.GenericActivity
import com.ghstudios.android.MenuSection



import com.ghstudios.android.mhgendatabase.R

class BentoListActivity : GenericActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.title_bentos)

        // Tag as top level activity
        super.setAsTopLevel()
    }

    override fun getSelectedSection(): Int {
        return MenuSection.BENTOS
    }

    override fun createFragment(): Fragment {
        return BentoListFragment()
    }
}