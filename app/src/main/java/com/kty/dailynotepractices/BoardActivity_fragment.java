package com.kty.dailynotepractices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class BoardActivity_fragment extends Fragment {

    private ArrayList<Data> arrayList;
    private BoardAdapter boardAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    public  BoardActivity_fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_activity_board_, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_board);
        //아이템 배치방식을 설정할수있다..
        arrayList= new ArrayList<>();
        Data data= new Data("안드로이드");
        arrayList.add(data);
        data= new Data("싫다로이드");
        arrayList.add(data);
        boardAdapter= new BoardAdapter(getActivity(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        try{
            recyclerView.setAdapter(boardAdapter);
        }catch (Exception e){
            e.printStackTrace();
        }
        boardAdapter.notifyDataSetChanged();
        if(boardAdapter==null){
            Log.d("activity","isnull");
        }

        return view;

    }


}
