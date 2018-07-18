package com.example.android.to_do_list_2_0.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.android.to_do_list_2_0.R;
import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private ArrayList<String> todoTask;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button mbutton;

        public ViewHolder(View view) {
            super(view);
            mbutton = view.findViewById(R.id.button_item_number);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public myAdapter(ArrayList<String> todoTask) {
        this.todoTask = todoTask;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mbutton.setText(todoTask.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return todoTask.size();
    }}
