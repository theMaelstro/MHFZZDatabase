package com.ghstudios.android.features.help
import android.os.Bundle
import android.support.v4.app.Fragment
import com.ghstudios.android.GenericActivity
import com.ghstudios.android.mhgendatabase.R


class UniversalHelpActivity: GenericActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.help)
    }

    override fun getSelectedSection() = -1

    override fun createFragment(): Fragment {
        return UniversalHelpFragment()
    }
}