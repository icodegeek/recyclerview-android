package com.example.jjmacbook.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jjmacbook on 13/3/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<String> names;
    private int layout;
    private OnItemClickListener itemClickListener;

    //Constructor Myadapter
    public MyAdapter(List<String> names, int layout, OnItemClickListener listener){
        this.names = names;
        this.layout = layout;
        this.itemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Se infla la vista
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        //Se le pasa al viewholder la vista inflada
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;

        //Contructor
        public ViewHolder(View v){
            //se le pasa al padre la vista que le ha llegado 'v'
            super(v);
            this.textViewName = (TextView) v.findViewById(R.id.textViewName);
        }

        public void bind(final String name, final OnItemClickListener listener){

            this.textViewName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(name, getAdapterPosition());
                }
            });
        }
    }

    //Se crea una interfaz
    public interface OnItemClickListener {
         void OnItemClick(String name, int position);
    }
}
