package com.ghstudios.android.features.help

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.ghstudios.android.mhgendatabase.R


class UniversalHelpFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_help,
            container, false
        )
        ButterKnife.bind(this, view)
        return view
    }
/*
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

 */
}