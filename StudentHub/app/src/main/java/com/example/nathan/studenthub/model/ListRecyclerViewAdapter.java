package com.example.nathan.studenthub.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nathan.studenthub.R;

import java.util.List;

/**
 * Created by Nathan on 16/04/2017.
 */

public class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.AdvertViewHolder>
{
    private static final String TAG = "ListRecyclerViewAdapter";

    private List<Object> adverts;

    // context is needed for using external library to handle image downloading
    private Context context;

    public ListRecyclerViewAdapter(Context context, List<Object> adverts)
    {
        this.context = context;
        this.adverts = adverts;
    }

    // Methods being over-ridden from RecyclerView Class.


    // Called by layout manager when a view is needed
    // This method inflates a view and returns it
    @Override
    public AdvertViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Inflate the view using 3 params. The layout resource, the parent object which gives
        // access to styles etc and set false so it doesn't attach it to the root. Let the Recycler view handle that itself
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.live_adverts, parent, false);
        return new AdvertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdvertViewHolder holder, int position)
    {
        // called by layout manager when requesting new data in existing views
        Advert advert = (Advert) adverts.get(position);
//        Picasso.with(context).load(advert.getImage())
//                .error(R.drawable.placeholder)
//                .placeholder(R.drawable.placeholder)
//                .into(holder.thumbnail);

        holder.title.setText(advert.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return ((adverts != null) && (adverts.size() != 0) ? adverts.size() : 0);
    }

    // Method that loads in new data when needed, and will notify the recycler to handle refresh
    public void loadData(List<Object> adverts)
    {
        this.adverts = adverts;
        notifyDataSetChanged();
    }

    public Advert getAdvert(int position)
    {
        return (Advert)((adverts != null) && (adverts.size() != 0) ? adverts.get(position) : null);
    }

    // View holder class, customized for the object being dealt with - Adverts
    // Used to populate the list of live adverts
    static class AdvertViewHolder extends RecyclerView.ViewHolder
    {
        ImageView thumbnail = null;
        TextView title = null;

        public AdvertViewHolder(View itemView)
        {
            super(itemView);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
