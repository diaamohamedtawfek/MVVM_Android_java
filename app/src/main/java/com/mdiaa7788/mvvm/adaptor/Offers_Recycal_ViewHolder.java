package com.mdiaa7788.mvvm.adaptor;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mdiaa7788.mvvm.OnItemClick;
import com.mdiaa7788.mvvm.model.DataPostFinal;
import com.mdiaa7788.mvvm.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Offers_Recycal_ViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int previousPosition = 0;

    private OnItemClick mCallback;
    List<DataPostFinal> List_Item;
    private Context context;
    String deirection;
    Activity activity;



    public Offers_Recycal_ViewHolder(List<DataPostFinal> list_Item, Activity activity,
                                     Context context, String deirection,OnItemClick listener) {
        List_Item = list_Item;
        this.context = context;
        this.deirection=deirection;
        this.activity=activity;
        this.mCallback=listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View menu1 = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_offers, viewGroup, false);
        return new MenuItemViewHolder(menu1);
    }

    public void filterList(ArrayList<DataPostFinal> filteredList) {
        List_Item = filteredList;
        //recyclerView_dAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final MenuItemViewHolder menuItemHolder = (MenuItemViewHolder) holder;


//            menuItemHolder.imageView.setImageResource(R.mipmap.sharpest_logo);

        menuItemHolder.dateexpire.setText(List_Item.get(position).getTitle());
        menuItemHolder.name_corsess.setText(List_Item.get(position).getTitle());
        menuItemHolder.oferDecound.setText((position+1)+"");

        Glide.with(context)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/1200px-Image_created_with_a_mobile_phone.png")
                        .into(menuItemHolder.imageView);
        menuItemHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("",List_Item.get(position).getTitle());
                mCallback.onClick(List_Item.get(position));
            }
        });

        previousPosition = position;


    }
    @Override
    public int getItemCount() {
        return (null != List_Item ? List_Item.size() : 0);
    }

    protected class MenuItemViewHolder extends RecyclerView.ViewHolder {
        //        LinearLayout cardView;
        ImageView imageView;
        TextView name_corsess, oferDecound, dateexpire;
        CardView cardView;
        LinearLayout liner_finsh_offers;

        public MenuItemViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_courzses);

            imageView=(ImageView) view.findViewById(R.id.image_coursses);
            name_corsess = (TextView) view.findViewById(R.id.text_name_coursses);
            dateexpire = (TextView) view.findViewById(R.id.text_date_coursses);
            oferDecound = (TextView) view.findViewById(R.id.oferDecound);
            liner_finsh_offers = (LinearLayout) view.findViewById(R.id.licenser);

        }
    }




}
