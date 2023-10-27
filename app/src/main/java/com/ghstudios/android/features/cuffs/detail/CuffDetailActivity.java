package com.ghstudios.android.features.cuffs.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ghstudios.android.GenericActivity;
import com.ghstudios.android.mhgendatabase.R;
import com.ghstudios.android.MenuSection;

public class CuffDetailActivity extends GenericActivity {
    /**
     * A key for passing a cuff ID as a long
     */
    public static final String EXTRA_CUFF_ID =
            "com.daviancorp.android.android.ui.detail.cuff_id";

    private static final int REQUEST_ADD = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.type_cuff);
    }

    @Override
    protected int getSelectedSection() {
        return MenuSection.CUFF;
    }

    @Override
    protected Fragment createFragment() {
        long cuffId = getIntent().getLongExtra(EXTRA_CUFF_ID, -1);
        return CuffDetailFragment.newInstance(cuffId);
    }
}
