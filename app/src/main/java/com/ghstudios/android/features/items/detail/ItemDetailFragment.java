package com.ghstudios.android.features.items.detail;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import com.ghstudios.android.AppSettings;
import com.ghstudios.android.AssetLoader;
import com.ghstudios.android.adapter.ItemCombinationAdapterDelegate;
import com.ghstudios.android.adapter.ItemStoreAdapterDelegate;
import com.ghstudios.android.adapter.ItemMochaAdapterDelegate;
import com.ghstudios.android.adapter.common.BasicListDelegationAdapter;
import com.ghstudios.android.components.ColumnLabelTextCell;
import com.ghstudios.android.components.LabelValueComponent;
import com.ghstudios.android.components.TitleBarCell;
import com.ghstudios.android.data.classes.Item;
import com.ghstudios.android.data.classes.StoreOffer;
import com.ghstudios.android.mhgendatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailFragment extends Fragment {
    private static final String ARG_ITEM_ID = "ITEM_ID";

    @BindView(R.id.item_title) TitleBarCell titleCell;

    @BindView(R.id.carry) LabelValueComponent carryCell;
    @BindView(R.id.buy) LabelValueComponent buyCell;
    @BindView(R.id.sell) LabelValueComponent sellCell;

    @BindView(R.id.description) TextView descriptionTextView;

    @BindView(R.id.combination_section) ViewGroup combinationSection;
    @BindView(R.id.craft_combinations) RecyclerView combinationList;

    @BindView(R.id.mocha_section) ViewGroup mochaSection;
    @BindView(R.id.mocha_items) RecyclerView mochaList;

    @BindView(R.id.store_section) ViewGroup storeSection;
    @BindView(R.id.store_items) RecyclerView storeList;

    public static ItemDetailFragment newInstance(long itemId) {
        Bundle args = new Bundle();
        args.putLong(ARG_ITEM_ID, itemId);
        ItemDetailFragment f = new ItemDetailFragment();
        f.setArguments(args);
        return f;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // add divider for the combination list
        //combinationList.addItemDecoration(new RecyclerViewDivider(combinationList));

        // this uses the pager's view Model
        ItemDetailViewModel viewModel = ViewModelProviders.of(getActivity()).get(ItemDetailViewModel.class);
        viewModel.getItemData().observe(this, this::populateItem);

        viewModel.getStoreData().observe(this, (items) -> {
            if (items == null || items.isEmpty()) {
            return;
            }

            storeSection.setVisibility(View.VISIBLE);

            ItemStoreAdapterDelegate delegate = new ItemStoreAdapterDelegate();
            delegate.setResultItemNavigationEnabled(false);
            BasicListDelegationAdapter<Object> adapter = new BasicListDelegationAdapter<>(delegate);
            adapter.setItems(items);

            storeList.setAdapter(adapter);
        });

        viewModel.getCraftData().observe(this, (items) -> {
            if (items == null || items.isEmpty()) {
                return;
            }

            combinationSection.setVisibility(View.VISIBLE);

            // DO NOT PUT ADAPTER AS AN INSTANCE VARIABLE OF THE FRAGMENT (or it'll leak)
            ItemCombinationAdapterDelegate delegate = new ItemCombinationAdapterDelegate();
            delegate.setResultItemNavigationEnabled(false);
            BasicListDelegationAdapter<Object> adapter = new BasicListDelegationAdapter<>(delegate);
            adapter.setItems(items);

            combinationList.setAdapter(adapter);
        });

        viewModel.getMochaData().observe(this, (items) -> {
            if (items == null || items.isEmpty()) {
                return;
            }

            mochaSection.setVisibility(View.VISIBLE);

            // DO NOT PUT ADAPTER AS AN INSTANCE VARIABLE OF THE FRAGMENT (or it'll leak)
            ItemMochaAdapterDelegate delegate = new ItemMochaAdapterDelegate();
            delegate.setResultItemNavigationEnabled(false);
            BasicListDelegationAdapter<Object> adapter = new BasicListDelegationAdapter<>(delegate);
            adapter.setItems(items);

            mochaList.setAdapter(adapter);
        });

    }

    private void populateItem(Item mItem) {
        // Set title icon and image
        titleCell.setIcon(mItem);
        titleCell.setTitleText(mItem.getName());
        titleCell.setAltTitleText(getString(R.string.value_rare, mItem.getRarityString()));
        if (mItem.getHid() != null) {
            titleCell.setItemHidText("#" + mItem.getHid());
        }

        descriptionTextView.setText(mItem.getDescription());

        String cellSell = "" + mItem.getSell() + "z";
        String cellBuy = "" + mItem.getBuy() + "z";
        
        if (cellBuy.equals("0z")) {
            cellBuy = "-";
        }
        if (cellSell.equals("0z")) {
            cellSell = "-";
        }

        carryCell.setValueText(String.valueOf(mItem.getCarryCapacity()));
        buyCell.setValueText(cellBuy);
        sellCell.setValueText(cellSell);
    }




    private void populateStore(List<StoreOffer> offers) {
        for(int i = 0; i < offers.size(); i++){
            StoreOffer offer = offers.get(i);
            Log.d(offer.getProduct().getName()+": "+offer.getCost(), "CHECK");

        }
    }





}
