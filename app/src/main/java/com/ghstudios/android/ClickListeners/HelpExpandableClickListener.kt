package com.ghstudios.android.ClickListeners
import android.view.View

/**
 * Simple ClickListener class to hide and show elements.
 * Takes view element to be toggled as parameter.
 * Universal, can be used outside of Help page.
 */
class HelpExpandableClickListener(private val element: View) :
    View.OnClickListener {
    override fun onClick(v: View) {
        if (element.visibility == View.GONE) {
            element.visibility = View.VISIBLE
        }
        else {
            element.visibility = View.GONE
        }
    }
}



