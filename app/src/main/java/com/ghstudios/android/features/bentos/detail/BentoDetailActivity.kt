package com.ghstudios.android.features.bentos.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import com.ghstudios.android.GenericActivity
import com.ghstudios.android.MenuSection
import com.ghstudios.android.mhgendatabase.R

class BentoDetailActivity : GenericActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.title_bentos)
    }

    override fun getSelectedSection(): Int {
        return MenuSection.BENTOS
    }

    override fun createFragment(): Fragment {
        val bentoId = intent.getLongExtra(EXTRA_BENTO_ID, -1)
        return BentoDetailFragment.newInstance(bentoId)
    }

    companion object {
        /**
         * A key for passing a bento ID as a long
         */
        const val EXTRA_BENTO_ID = "com.daviancorp.android.android.ui.detail.bento_id"
        private const val REQUEST_ADD = 0
    }
}