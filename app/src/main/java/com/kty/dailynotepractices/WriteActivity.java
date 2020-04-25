package com.kty.dailynotepractices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteActivity extends Fragment {
    private TextView textview_date;
    private Button button_close;
    private EditText edit_content;
    private static final int   REQUEST_IMAGE_CAPTURE= 672;
    private Uri photoUri;
    private String imageFilePath;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_write, container, false);
     //권한체크

        edit_content = (EditText)view.findViewById(R.id.edit_content);
        button_close = (Button)view.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //일기장 작성한것 메인에 보내주기.
                Intent intentToBack=new Intent();
                String content =       edit_content.getText().toString();
                intentToBack.putExtra("content",content);
                getActivity().setResult(Activity.RESULT_OK,intentToBack);
                getActivity().finish();
            }
        });
        textview_date=(TextView)view.findViewById(R.id.textview_date);
        String today= getActivity().getIntent().getStringExtra("today").toString();

        textview_date.setText(today);
        //캡쳐 버튼 클릭리스너만들기
        view.findViewById(R.id.button_camera).setClickable(true);
        view.findViewById(R.id.button_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getActivity().getPackageManager()) != null){
                    File photoFile =null;
                    try{
                        photoFile=createImageFile();
                    }
                    catch(IOException e) {
                        e.printStackTrace();
                    }

                    if(photoFile!=null){

                        photoUri =FileProvider.getUriForFile(getActivity(),getActivity().getPackageName(),photoFile);

                        intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                        startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                    }

                }
            }

        });
        return view;


    }

    private File createImageFile() throws IOException  {
        String timeStamp= new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String  imageFileName= "TEST_"+timeStamp+"_";
        File storageDir =getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image=    File.createTempFile(
                imageFileName,".jap",storageDir
        );

        imageFilePath=image.getAbsolutePath();


        return image;
   }
//    @Override//startActivityForResult를 위한 함수
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==REQUEST_IMAGE_CAPTURE&& resultCode==getActivity().RESULT_OK){
//            Bitmap bitmap= BitmapFactory.decodeFile(imageFilePath);
//            ExifInterface exif= null;
//
//            try{
//                exif= new ExifInterface(imageFilePath);
//
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//            int  exifOrientation;
//            int  exifDegree;
//            if(exif!=null){
//
//                exifOrientation= exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
//                exifDegree=exifOrientationToDegree(exifOrientation);
//            }
//            else
//            {
//                exifDegree=0;
//            }
//            ((ImageView)view.findViewById(R.id.iv_result)).setImageBitmap(rotate(bitmap,exifDegree));
//        }
//    }

    private int exifOrientationToDegree(int exifOrientation){
            if(exifOrientation==ExifInterface.ORIENTATION_ROTATE_90){
                return 90;
            }
            else if(exifOrientation==ExifInterface.ORIENTATION_ROTATE_180){
                return 180;

            }
            else if(exifOrientation==ExifInterface.ORIENTATION_ROTATE_270){
                return 270 ;
            }
            return 0;

    }

    private    Bitmap rotate(Bitmap bitmap ,   float degree){
        Matrix matrix= new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());
    }

}
