package com.kty.dailynotepractices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView main_notepad;
    private int RESUlT_CODE;
    private TextView button_closedrawer;
    private String today;
    private ImageView button_opendrawer;
    private DrawerLayout drawerLayout;
    private View drawer;
    private TextView textview_date ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        today = sdf.format(c1.getTime());
        textview_date= (TextView)findViewById(R.id.textview_date);
        textview_date.setText(today);


        main_notepad= (TextView)findViewById(R.id.main_notepad);
        main_notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast myToast = Toast.makeText(getApplicationContext(),"토스트메세지이빈다", Toast.LENGTH_SHORT);
                myToast.show();
                Intent intent= new Intent(MainActivity.this,WriteActivity.class);
                intent.putExtra("today",today);
                startActivityForResult(intent,RESUlT_CODE);

            }
        });

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
                main_notepad.setText(content);
                main_notepad.setGravity(Gravity.START);
            }


    }
}
