package com.example.dell.todolist.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.todolist.R;
import com.example.dell.todolist.activities.EditNotesActivity;
import com.example.dell.todolist.utilities.Notes;
import com.example.dell.todolist.utilities.SaveNote;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.CustomViewHolder> {


    private Context mContext;
    private List<Notes> list;
    private int resource;

    public NotesAdapter(Context mContext, List<Notes> list, int resource) {
        this.mContext = mContext;
        this.list = list;
        this.resource = resource;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        return new CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder holder, final int position) {


        holder.Title.setText(list.get(position).getTitle());
        String preview = list.get(position).getContent();

        //CHECK LENGTH OF CONTENT TO BE PREVIEWED ON THE DISPLAY ACTIVITY
        if (preview.length() > 50) {
            holder.Preview.setText(preview.substring(0, 50));
        } else {
            holder.Preview.setText(preview);
        }


        // AFTER VIEW IS CLICKED IT GOES TO THE EDIT VIEW WHERE THE PRESAVED NOTE IS DISPLAYED
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String filename = (list.get(position).getDateTime() + SaveNote.FILE_EXTENSION);

                Intent intent = new Intent(mContext, EditNotesActivity.class);
                intent.putExtra("FILENAME", filename);
                mContext.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView Title;
        private TextView Preview;


        public CustomViewHolder(View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.TitleTextView);
            Preview = itemView.findViewById(R.id.PreviewTextView);


        }
    }
}

