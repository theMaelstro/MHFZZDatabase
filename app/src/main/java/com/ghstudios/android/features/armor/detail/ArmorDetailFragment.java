package com.ghstudios.android.features.armor.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghstudios.android.ClickListeners.ArmorClickListener;
import com.ghstudios.android.ClickListeners.ItemClickListener;
import com.ghstudios.android.ClickListeners.SkillClickListener;
import com.ghstudios.android.components.ColumnLabelTextCell;
import com.ghstudios.android.components.ItemRecipeCell;
import com.ghstudios.android.components.LabelTextRowCell;
import com.ghstudios.android.components.SlotsView;
import com.ghstudios.android.components.TitleBarCell;
import com.ghstudios.android.data.classes.Armor;
import com.ghstudios.android.data.classes.Component;
import com.ghstudios.android.data.classes.Item;
import com.ghstudios.android.data.classes.ItemToSkillTree;
import com.ghstudios.android.features.wishlist.external.WishlistDataAddDialogFragment;
import com.ghstudios.android.mhgendatabase.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArmorDetailFragment extends Fragment {
    private static final String ARG_ARMOR_ID = "ARMOR_ID";

    private static final String DIALOG_WISHLIST_ADD = "wishlist_add";

    private ArmorDetailViewModel viewModel;
    private Armor armor; // set using the viewmodel

    @BindView(R.id.titlebar)
    TitleBarCell titleBar;

    @BindView(R.id.slots) SlotsView slotsReqView;
    @BindView(R.id.defense) ColumnLabelTextCell defenseView;
    @BindView(R.id.part) ColumnLabelTextCell partView;

    @BindView(R.id.skill_section) ViewGroup skillSection;
    @BindView(R.id.skill_list) LinearLayout skillListView;

    @BindView(R.id.recipe_header) View recipeHeader;
    @BindView(R.id.recipe) ItemRecipeCell recipeView;

    @BindView(R.id.improve_header) View improveHeader;
    @BindView(R.id.improve) ItemRecipeCell improveView;

    @BindView(R.id.improve2_header) View improve2Header;
    @BindView(R.id.improve2) ItemRecipeCell improve2View;

    @BindView(R.id.improve3_header) View improve3Header;
    @BindView(R.id.improve3) ItemRecipeCell improve3View;

    @BindView(R.id.improve4_header) View improve4Header;
    @BindView(R.id.improve4) ItemRecipeCell improve4View;

    @BindView(R.id.improve5_header) View improve5Header;
    @BindView(R.id.improve5) ItemRecipeCell improve5View;

    @BindView(R.id.improve6_header) View improve6Header;
    @BindView(R.id.improve6) ItemRecipeCell improve6View;

    @BindView(R.id.improve7_header) View improve7Header;
    @BindView(R.id.improve7) ItemRecipeCell improve7View;

    @BindView(R.id.improve8_header) View improve8Header;
    @BindView(R.id.improve8) ItemRecipeCell improve8View;

    @BindView(R.id.upgrade_to_header) View upgradeToHeader;
    @BindView(R.id.upgrade_to) ItemRecipeCell upgradeTo;

    @BindView(R.id.upgrade_from_header) View upgradeFromHeader;
    @BindView(R.id.upgrade_from) ItemRecipeCell upgradeFrom;
    
    private TextView fireResTextView;
    private TextView waterResTextView;
    private TextView iceResTextView;
    private TextView thunderResTextView;
    private TextView dragonResTextView;

    public static ArmorDetailFragment newInstance(long armorId) {
        Bundle args = new Bundle();
        args.putLong(ARG_ARMOR_ID, armorId);
        ArmorDetailFragment f = new ArmorDetailFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        viewModel = ViewModelProviders.of(this).get(ArmorDetailViewModel.class);

        // Check for a Item ID as an argument, and find the item
        Bundle args = getArguments();
        if (args == null) {
            return;
        }

        long armorId = args.getLong(ARG_ARMOR_ID, -1);
        viewModel.loadArmor(armorId);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_armor_detail,
                container, false);

        ButterKnife.bind(this, view);

        fireResTextView = view.findViewById(R.id.fire_res);
        waterResTextView = view.findViewById(R.id.water_res);
        iceResTextView = view.findViewById(R.id.ice_res);
        thunderResTextView = view.findViewById(R.id.thunder_res);
        dragonResTextView = view.findViewById(R.id.dragon_res);
        
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // bind view model data to view
        viewModel.getArmorData().observe(this, this::populateArmor);
        viewModel.getSkillData().observe(this, this::populateSkills);
        //viewModel.getComponentData().observe(this, this::populateRecipe);
        viewModel.getCreateComponentData().observe(this, this::populateRecipe);
        viewModel.getImproveComponentData().observe(this, this::populateImprove);
        viewModel.getImprove2ComponentData().observe(this, this::populateImprove2);
        viewModel.getImprove3ComponentData().observe(this, this::populateImprove3);
        viewModel.getImprove4ComponentData().observe(this, this::populateImprove4);
        viewModel.getImprove5ComponentData().observe(this, this::populateImprove5);
        viewModel.getImprove6ComponentData().observe(this, this::populateImprove6);
        viewModel.getImprove7ComponentData().observe(this, this::populateImprove7);
        viewModel.getImprove8ComponentData().observe(this, this::populateImprove8);
        viewModel.getUpgradeToData().observe(this, this::populateUpgradeTo);
        viewModel.getUpgradeFromData().observe(this, this::populateUpgradeFrom);
    }

    private void populateArmor(Armor armor) {
        if (armor == null) return;

        this.armor = armor;

        titleBar.setTitleText(armor.getName());
        titleBar.setIcon(armor);
        titleBar.setAltTitleText(getString(R.string.value_rare, armor.getRarityString()));

        String cellPart = "" + armor.getSlot();
        String cellDefense = "" + armor.getDefense() + "~" + armor.getMaxDefense();

        slotsReqView.setSlots(armor.getNumSlots(), 0);
        partView.setValueText(cellPart);
        defenseView.setValueText(cellDefense);

        fireResTextView.setText(String.valueOf(armor.getFireRes()));
        waterResTextView.setText(String.valueOf(armor.getWaterRes()));
        iceResTextView.setText(String.valueOf(armor.getIceRes()));
        thunderResTextView.setText(String.valueOf(armor.getThunderRes()));
        dragonResTextView.setText(String.valueOf(armor.getDragonRes()));
    }

    private void populateSkills(List<ItemToSkillTree> skills) {
        skillListView.removeAllViews();
        if (skills.size() == 0)  {
            skillSection.setVisibility(View.GONE);
            return;
        }

        skillSection.setVisibility(View.VISIBLE);
        for (ItemToSkillTree skill : skills) {
            LabelTextRowCell skillItem = new LabelTextRowCell(getContext());
            skillItem.setLabelText(skill.getSkillTree().getName());
            skillItem.setValueText(String.valueOf(skill.getPoints()));

            skillItem.setOnClickListener(
                    new SkillClickListener(getContext(), skill.getSkillTree().getId())
            );

            skillListView.addView(skillItem);
        }
    }

    private void populateRecipe(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            recipeHeader.setVisibility(View.GONE);
            recipeView.setVisibility(View.GONE);
            return;
        }

        recipeHeader.setVisibility(View.VISIBLE);
        recipeView.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = recipeView.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improveHeader.setVisibility(View.GONE);
            improveView.setVisibility(View.GONE);
            return;
        }

        improveHeader.setVisibility(View.VISIBLE);
        improveView.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improveView.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove2(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve2Header.setVisibility(View.GONE);
            improve2View.setVisibility(View.GONE);
            return;
        }

        improve2Header.setVisibility(View.VISIBLE);
        improve2View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve2View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove3(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve3Header.setVisibility(View.GONE);
            improve3View.setVisibility(View.GONE);
            return;
        }

        improve3Header.setVisibility(View.VISIBLE);
        improve3View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve3View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove4(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve4Header.setVisibility(View.GONE);
            improve4View.setVisibility(View.GONE);
            return;
        }

        improve4Header.setVisibility(View.VISIBLE);
        improve4View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve4View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove5(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve5Header.setVisibility(View.GONE);
            improve5View.setVisibility(View.GONE);
            return;
        }

        improve5Header.setVisibility(View.VISIBLE);
        improve5View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve5View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove6(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve6Header.setVisibility(View.GONE);
            improve6View.setVisibility(View.GONE);
            return;
        }

        improve6Header.setVisibility(View.VISIBLE);
        improve6View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve6View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove7(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve7Header.setVisibility(View.GONE);
            improve7View.setVisibility(View.GONE);
            return;
        }

        improve7Header.setVisibility(View.VISIBLE);
        improve7View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve7View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateImprove8(List<Component> recipe) {
        if (recipe == null || recipe.isEmpty()) {
            improve8Header.setVisibility(View.GONE);
            improve8View.setVisibility(View.GONE);
            return;
        }

        improve8Header.setVisibility(View.VISIBLE);
        improve8View.setVisibility(View.VISIBLE);

        for (Component component : recipe) {
            Item item = component.getComponent();
            View itemCell = improve8View.addItem(item, item.getName(), component.getQuantity(),component.isKey());
            itemCell.setOnClickListener(new ItemClickListener(getContext(), item));
        }
    }

    private void populateUpgradeTo(List<Item> armors) {
        if (armors == null) {
            Log.d("NO UPGRADES AVAILABLE","CHECK");
        }
        else {
            for (int i = 0; i < armors.size(); i++) {
                Item item = armors.get(i);
                Log.d("Upgrades to: " + item.getName(), "CHECK");
            }
        }

        if (armors == null || armors.isEmpty()) {
            upgradeToHeader.setVisibility(View.GONE);
            upgradeTo.setVisibility(View.GONE);
            return;
        }

        upgradeToHeader.setVisibility(View.VISIBLE);
        upgradeTo.setVisibility(View.VISIBLE);

        for (Item item : armors) {
            View itemCell = upgradeTo.addItem(item, item.getName(), 0,false);
            itemCell.setOnClickListener(new ArmorClickListener(getContext(), item.getId(), false));
        }
    }

    private void populateUpgradeFrom(List<Item> armors) {
        if (armors == null) {
            Log.d("NOT UPGRADED FROM","CHECK");
        }
        else {
            for (int i = 0; i < armors.size(); i++) {
                Item item = armors.get(i);
                Log.d("Upgrades from: " + item.getName(), "CHECK");
            }
        }

        if (armors == null || armors.isEmpty()) {
            upgradeFromHeader.setVisibility(View.GONE);
            upgradeFrom.setVisibility(View.GONE);
            return;
        }

        upgradeFromHeader.setVisibility(View.VISIBLE);
        upgradeFrom.setVisibility(View.VISIBLE);

        for (Item item : armors) {
            View itemCell = upgradeFrom.addItem(item, item.getName(), 0,false);
            itemCell.setOnClickListener(new ArmorClickListener(getContext(), item.getId(), false));

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
                if (armor != null) {
                    FragmentManager fm = this.getFragmentManager();
                    WishlistDataAddDialogFragment dialogCopy = WishlistDataAddDialogFragment
                            .newInstance(armor.getId(), armor.getName());
                    dialogCopy.show(fm, DIALOG_WISHLIST_ADD);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
