package com.kty.dailynotepractices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private int RESUlT_CODE;
    private TextView button_closedrawer;
    private String today;
    private ImageView button_opendrawer;
    private DrawerLayout drawerLayout;
    private View drawer;
    private TextView textview_date ;
    private LinearLayout button_showAllBoard;
    private FrameLayout frame ;
    private ArrayAdapter arrayAdapter;
    private Boolean Music_ONOFF;
    private LinearLayout button_dialog_background_music_onoff;
    public void setDialog() {
        Music_ONOFF=false;
        //다이얼로그 생성
        final AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_music_onoff, null);

        aBuilder.setTitle("Background Music");       // 제목 설정

        // 스피너 설정
        final Spinner sp = (Spinner) mView.findViewById(R.id.spinner_select_music_onoff);

        // 스피너 어댑터 설정
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp.setAdapter(arrayAdapter);
//        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(i==1){
//                    Music_ONOFF=true;
//
//                }else{
//                    Music_ONOFF=false;
//
//                }
//            }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                Music_ONOFF=false;
//            }
//        });
        aBuilder.setSingleChoiceItems(arrayAdapter, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==1){
                    Music_ONOFF=true;

                }else{
                    Music_ONOFF=false;

                }

                dialogInterface.dismiss();
            }
        });

        //aBuilder.setView(mView);
            AlertDialog dialog = aBuilder.create();
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //s: 게시글 전체보기(fragment)
        button_showAllBoard= (LinearLayout)findViewById(R.id.button_showAllBoard);
        button_showAllBoard.setClickable(true);
        button_showAllBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout ll= findViewById(R.id.line);
                ll.removeAllViews();
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                Fragment BoardActivity_fragment= new BoardActivity_fragment();
                transaction.replace(R.id.frame,BoardActivity_fragment);

                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

    //e: 게시글 전체보기 (fragment)
        frame = findViewById(R.id.frame);
        frame.setClickable(true);
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                Fragment writeActivity= new WriteActivity();
                transaction.replace(R.id.frame,writeActivity);

                transaction.addToBackStack(null);
                transaction.commit();

            }
        });






        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        today = sdf.format(c1.getTime());
        textview_date= (TextView)findViewById(R.id.textview_date);
        textview_date.setText(today);


//클릭햇을떄 이동하는 intent만들어야한다.//




        //드로우어 끄기버튼
        button_closedrawer =(TextView)findViewById(R.id.button_closedrawer);
        button_closedrawer.setClickable(true);
        button_closedrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawer);
            }
        });
        //드로우어 버튼
        drawerLayout= (DrawerLayout)findViewById(R.id.drawerLayout);
        drawer= (View)findViewById(R.id.drawer);

        //다이얼로그 생성버튼
         button_dialog_background_music_onoff = drawer.findViewById(R.id.button_dialog_background_music_onoff);
         button_dialog_background_music_onoff.setClickable(true);
        button_dialog_background_music_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //s: 다이얼로그 설정
                setDialog();
                //e: 다이얼로그 설정

            }
        });
        button_opendrawer= (ImageView)findViewById(R.id.button_opendrawer);
        button_opendrawer.setClickable(true);
        button_opendrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawer 설정
                drawerLayout.openDrawer(drawer);
            }
        });

        //
        drawerLayout.setDrawerListener(drawerListener);


    }


    DrawerLayout.DrawerListener drawerListener= new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }


    };



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
               String content= data.getStringExtra("content");
               Toast.makeText(getApplicationContext(),content,Toast.LENGTH_SHORT).show();
            }


    }
}
