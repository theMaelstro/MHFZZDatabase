package com.ghstudios.android.features.cuffs.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ghstudios.android.ClickListeners.ItemClickListener;
import com.ghstudios.android.ClickListeners.SkillClickListener;
import com.ghstudios.android.components.ColumnLabelTextCell;
import com.ghstudios.android.components.ItemRecipeCell;
import com.ghstudios.android.components.LabelTextRowCell;
import com.ghstudios.android.components.SlotsView;
import com.ghstudios.android.components.TitleBarCell;
import com.ghstudios.android.data.classes.Component;
import com.ghstudios.android.data.classes.Cuff;
import com.ghstudios.android.data.classes.Item;
import com.ghstudios.android.features.wishlist.external.WishlistDataAddDialogFragment;
import com.ghstudios.android.mhgendatabase.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CuffDetailFragment extends Fragment {
    private static final String ARG_CUFF_ID = "CUFF_ID";

    private static final String DIALOG_WISHLIST_ADD = "wishlist_add";

    @BindView(R.id.titlebar) TitleBarCell titleView;
    @BindView(R.id.rare) ColumnLabelTextCell rareView;
    @BindView(R.id.buy) ColumnLabelTextCell buyView;
    @BindView(R.id.sell) ColumnLabelTextCell sellView;
    @BindView(R.id.slots) SlotsView slotsReqView;
    @BindView(R.id.skill_list) LinearLayout skillListView;
    @BindView(R.id.recipe_list) LinearLayout recipeListView;

    // stored to allow add to wishlist to work
    long cuffId;
    String cuffName;

    public static CuffDetailFragment newInstance(long cuffId) {
        Bundle args = new Bundle();
        args.putLong(ARG_CUFF_ID, cuffId);
        CuffDetailFragment f = new CuffDetailFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        // Check for a Item ID as an argument, and find the item
        Bundle args = getArguments();
        if (args == null) {
            return;
        }

        cuffId = args.getLong(ARG_CUFF_ID, -1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cuff_detail,
                container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (cuffId == -1) {
            return;
        }

        CuffDetailViewModel viewModel = ViewModelProviders.of(this).get(CuffDetailViewModel.class);
        viewModel.setCuff(cuffId);

        viewModel.getCuffData().observe(this, this::populateCuff);
        viewModel.getCuffSkillData().observe(this, this::populateSkills);
        viewModel.getComponentData().observe(this, this::populateRecipes);
    }

    /**
     * Updates the UI to set the cuff data.
     * @param cuff
     */
    private void populateCuff(Cuff cuff) {
        if (cuff == null) return;

        cuffId = cuff.getId();
        cuffName = cuff.getName();

        getActivity().setTitle(cuffName);

        String cellRare = cuff.getRarityString();
        String cellBuy = "" + cuff.getBuy() + "z";
        String cellSell = "" + cuff.getSell() + "z";

        if (cellBuy.equals("0z")) {
            cellBuy = "-";
        }
        if (cellSell.equals("0z")) {
            cellSell = "-";
        }

        titleView.setIcon(cuff);
        titleView.setTitleText(cuffName);

        rareView.setValueText(cellRare);
        buyView.setValueText(cellBuy);
        sellView.setValueText(cellSell);
        slotsReqView.setHideExtras(true);
        slotsReqView.setSlots(cuff.getNumSlots(), cuff.getNumSlots());
    }

    private void populateSkills(List<SkillPoints> skills) {
        skillListView.removeAllViews();

        for (SkillPoints skill : skills) {
            LabelTextRowCell skillItem = new LabelTextRowCell(getContext());
            skillItem.setLabelText(skill.getSkillName());
            skillItem.setValueText(String.valueOf(skill.getPoints()));
            skillItem.setOnClickListener(new SkillClickListener(getContext(), skill.getSkillId()));

            skillListView.addView(skillItem);
        }
    }

    private void populateRecipes(Map<String, List<Component>> recipes) {
        recipeListView.removeAllViews();

        for (List<Component> recipe : recipes.values()) {
            ItemRecipeCell cell = new ItemRecipeCell(getContext());

            // only show recipe title if there's more than one
            if (recipes.size() > 1) {
                cell.setTitleText(recipe.get(0).getType());
            }

            for (Component component : recipe) {
                Item item = component.getComponent();
                View itemCell = cell.addItem(item, item.getName(), component.getQuantity(), component.isKey());
                itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
            }

            recipeListView.addView(cell);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add_to_wishlist, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_to_wishlist:
                FragmentManager fm = this.getFragmentManager();
                WishlistDataAddDialogFragment dialogCopy = WishlistDataAddDialogFragment
                        .newInstance(cuffId, cuffName);
                dialogCopy.show(fm, DIALOG_WISHLIST_ADD);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
