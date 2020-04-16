package com.kty.dailynotepractices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
