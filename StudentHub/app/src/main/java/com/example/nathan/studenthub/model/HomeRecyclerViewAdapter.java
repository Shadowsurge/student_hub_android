package com.example.nathan.studenthub.model;

/**
 * Created by Nathan on 14/05/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nathan.studenthub.R;

import java.util.List;


public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder>
{
    private List<Object> posts;

    // context is needed for using external library to handle image downloading
    private Context context;

    public HomeRecyclerViewAdapter(Context context, List<Object> posts)
    {
        this.context = context;
        this.posts = posts;
    }

    // Methods being over-ridden from RecyclerView Class.
    // Called by layout manager when a view is needed
    // This method inflates a view and returns it
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Inflate the view using 3 params. The layout resource, the parent object which gives
        // access to styles etc and set false so it doesn't attach it to the root. Let the Recycler view handle that itself
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_feed_listing, parent, false);

        view.findViewById(R.id.homeBlogEmailContent).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", ((TextView) v).getText().toString(), null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Mental Health Blog - My Story");
                intent.putExtra(Intent.EXTRA_TEXT, "Type your story here!");
                v.getContext().startActivity(Intent.createChooser(intent, "Send email..."));
                return false;
            }
        });

        view.findViewById(R.id.homeBlogSaveItem).setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Toast.makeText(context, "Saving item", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position)
    {
        final Post item = (Post) posts.get(position);

        switch(item.getCategory())
        {
            case "Blog":
                holder.title.setText(item.getTitle());

                if (item.getContent().length() > 100) {
                    String subText = item.getContent().substring(0, 100) + "...";
                    holder.content.setText(subText);
                    holder.viewMore.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event)
                        {
                            toggleTextContent();
                            return false;
                        }

                        private void toggleTextContent()
                        {
                            if(holder.isTextExpanded)
                            {
                                String subText = item.getContent().substring(0, 100) + "...";
                                holder.content.setText(subText);
                                holder.viewMore.setText("View More");
                                holder.isTextExpanded = false;
                            }
                            else
                            {
                                holder.content.setText(item.getContent());
                                holder.viewMore.setText("View Less");
                                holder.isTextExpanded = true;
                            }
                        }
                    });
                }
                else
                {
                    holder.content.setText(item.getContent());
                }

                Log.d("ITEM " + item.getTitle(), item.getSubmittedBy());
                if(item.getSubmittedBy() == null)
                {
                    holder.submittedBy.setText("Submitted Anonymously");
                }
                else
                {
                    holder.submittedBy.setText("Submitted by: " + item.getSubmittedBy());
                }
                break;

            case "Video":
                holder.title.setVisibility(View.GONE);
                holder.content.setVisibility(View.GONE);
                holder.submittedBy.setVisibility(View.GONE);

                TextView view = new TextView(context);
                view.setText("This is a video");
                RelativeLayout layout = (RelativeLayout) holder.itemView.findViewById(R.id.homeLayout);
                layout.addView(view);
                break;

        }
    }


    @Override
    public int getItemCount()
    {
        return ((posts != null) && (posts.size() != 0) ? posts.size() : 0);
    }

    // Method that loads in new data when needed, and will notify the recycler to handle refresh
    public void loadData(List<Object> posts)
    {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public Post getPostItem(int position)
    {
        return (Post) ((posts != null) && (posts.size() != 0) ? posts.get(position) : null);
    }

    // View holder class, customized for the object being dealt with - Adverts
    // Used to populate the list of live adverts
    static class HomeViewHolder extends RecyclerView.ViewHolder
    {
        ImageView saveItem = null;
        TextView title = null;
        TextView content = null;
        TextView submittedBy = null;
        TextView emailLink = null;
        TextView viewMore = null;

        boolean isTextExpanded = false;

        public HomeViewHolder(final View itemView)
        {
            super(itemView);
            this.saveItem = (ImageView) itemView.findViewById(R.id.homeBlogSaveItem);
            this.title = (TextView) itemView.findViewById(R.id.homeBlogTitle);
            this.content = (TextView) itemView.findViewById(R.id.homeBlogContent);
            this.submittedBy = (TextView) itemView.findViewById(R.id.homeBlogSubmittedBy);
            this.emailLink = (TextView) itemView.findViewById(R.id.homeBlogEmailContent);
            this.viewMore = (TextView) itemView.findViewById(R.id.homeBlogViewMore);
        }
    }
}


