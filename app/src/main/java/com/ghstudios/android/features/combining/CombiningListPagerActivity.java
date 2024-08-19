package com.ghstudios.android.features.combining;
import com.ghstudios.android.features.combining.mocha.MochaListFragment;
import com.ghstudios.android.mhgendatabase.R;
import com.ghstudios.android.BasePagerActivity;
import com.ghstudios.android.MenuSection;

/**
 * The main activity for the combining list.
 * Contains separate box, mocha tabs
 */
public class CombiningListPagerActivity extends BasePagerActivity {

    @Override
    public void onAddTabs(TabAdder tabs) {
        setTitle(R.string.title_combining);
        super.setAsTopLevel();

        tabs.addTab(R.string.title_items, CombiningListFragment::new);
        tabs.addTab(R.string.title_mocha, MochaListFragment::new);
    }

    @Override
    protected int getSelectedSection() {
        return MenuSection.COMBINING;
    }
}
