package com.ghstudios.android.ClickListeners

import android.content.Context
import android.content.Intent
import android.view.View
import com.ghstudios.android.features.bentos.detail.BentoDetailActivity

class BentoClickListener(private val c: Context, private val id: Long?) :
    View.OnClickListener {
    override fun onClick(v: View) {
        val i = Intent(c, BentoDetailActivity::class.java)
        i.putExtra(BentoDetailActivity.EXTRA_BENTO_ID, id)
        c.startActivity(i)
    }
}
