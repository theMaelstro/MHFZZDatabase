package com.ghstudios.android.ClickListeners;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ghstudios.android.features.armorsetbuilder.detail.ASBDetailPagerActivity;
import com.ghstudios.android.features.cuffs.detail.CuffDetailActivity;

/**
 * Created by Mark on 2/24/2015.
 */
public class CuffClickListener implements View.OnClickListener {
    private Context c;
    private Long id;

    private boolean fromAsb;
    private Activity activity;

    public CuffClickListener(Context context, Long id) {
        super();
        this.id = id;
        this.c = context;
    }

    public CuffClickListener(Context context, Long id, boolean fromAsb, Activity activity) {
        this(context, id);
        this.fromAsb = fromAsb;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(c, CuffDetailActivity.class);
        i.putExtra(CuffDetailActivity.EXTRA_CUFF_ID, id);

        if (fromAsb) {
            i.putExtras(activity.getIntent());
            activity.startActivityForResult(i, ASBDetailPagerActivity.REQUEST_CODE_ADD_CUFF);
        }
        else {
            c.startActivity(i);
        }
    }
}
