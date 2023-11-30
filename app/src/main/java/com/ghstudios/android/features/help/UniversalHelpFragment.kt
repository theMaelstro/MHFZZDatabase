package com.ghstudios.android.features.help

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.ghstudios.android.ClickListeners.HelpExpandableClickListener
import com.ghstudios.android.mhgendatabase.R
import kotlinx.android.synthetic.main.fragment_help.view.*


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        /**
         * Iterate through layout and set listeners for headers.
         */
        var about_layout = view.about_layout
        val childCount: Int = about_layout.childCount
        for (i in 0 until childCount) {
            if (i % 2 == 0) {
                val parent: View = about_layout.getChildAt(i)
                val child: View = about_layout.getChildAt(i+1)
                parent.setOnClickListener(HelpExpandableClickListener(child))

            }
        }
    }

}