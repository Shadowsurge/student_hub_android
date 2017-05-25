package com.example.nathan.studenthub.model;

/**
 * Created by Nathan on 12/05/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.nathan.studenthub.R;

import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.MenuViewHolder>
{
    private List<Object> buttons;

    // context is needed for using external library to handle image downloading
    private Context context;

    public MenuRecyclerViewAdapter(Context context, List<Object> buttons)
    {
        this.context = context;
        this.buttons = buttons;
    }

    // Methods being over-ridden from RecyclerView Class.


    // Called by layout manager when a view is needed
    // This method inflates a view and returns it
    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Inflate the view using 3 params. The layout resource, the parent object which gives
        // access to styles etc and set false so it doesn't attach it to the root. Let the Recycler view handle that itself
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position)
    {
        // called by layout manager when requesting new data in existing views
        MenuItem button = (MenuItem) buttons.get(position);

        holder.button.setText(button.getTitle());
        holder.thumbnail.setImageDrawable(context.getResources().getDrawable(button.getImageID()));
    }

    @Override
    public int getItemCount()
    {
        return ((buttons != null) && (buttons.size() != 0) ? buttons.size() : 0);
    }

    // Method that loads in new data when needed, and will notify the recycler to handle refresh
    public void loadData(List<Object> buttons)
    {
        this.buttons = buttons;
        notifyDataSetChanged();
    }

    public MenuItem getMenuItem(int position)
    {
        return (MenuItem) ((buttons != null) && (buttons.size() != 0) ? buttons.get(position) : null);
    }

    // View holder class, customized for the object being dealt with - Adverts
    // Used to populate the list of live adverts
    static class MenuViewHolder extends RecyclerView.ViewHolder
    {
        ImageView thumbnail = null;
        Button button = null;

        public MenuViewHolder(View itemView)
        {
            super(itemView);
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.button = (Button) itemView.findViewById(R.id.menuItemButton);
        }
    }
}

