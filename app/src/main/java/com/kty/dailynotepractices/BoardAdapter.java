package com.kty.dailynotepractices;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.CustomViewHolder> {
    private final ArrayList<Data> arrayList;
    Context context;

    public BoardAdapter(Context context,ArrayList<Data> arrayList) {
        this.arrayList = arrayList;
        this.context =context;

    }
    public class CustomViewHolder extends RecyclerView.ViewHolder{

        protected TextView  textview_content;
        protected TextView  textview_date;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textview_content= itemView.findViewById(R.id.textview_content);
            this.textview_date=itemView.findViewById(R.id.textview_date);
        }
    }
    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //1미리 만들어둔 recyclerview를 위한 레이아웃 불러옴 inflater
        Log.d("onCreateViewHolder","여기");

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_board,parent,false);
        CustomViewHolder holder= new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomViewHolder holder, int position) {
        //2.형태가 그려진 holder에 실질적으로 데이터를 입히는 과정
        holder.textview_content.setText(arrayList.get(position).getContent());
        holder.textview_date.setText(arrayList.get(position).getDate());
        Log.d("onBindViewHolder",arrayList.get(position).getContent());
        Log.d("onBindViewHolder",arrayList.get(position).getDate());
        //클릭을 만들어줌 //여기서 itemView는 아까 그려진 레이아웃의 전체(통)을 말한다.
        holder.itemView.setTag(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"position:"+holder.getLayoutPosition(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("getItemCount", "getItemCount: "+arrayList.size());
        if(arrayList==null){
            return 0;
        }
        else{
            return arrayList.size();
        }
    }


    public void remove(int position){
        try{
            arrayList.remove(position);
            notifyItemRemoved(position);

        }catch (IndexOutOfBoundsException e){e.printStackTrace();}

    }

}
