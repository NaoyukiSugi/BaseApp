package me.shishamo.baseapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * メイン画面
 *@author shishamo
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static int REQUEST_CAPTURE = 1;
    ImageView mImage;
    File mSavePath;

    Button btn_camera;
    Button btn_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_camera).setOnClickListener(this);
        findViewById(R.id.button_location).setOnClickListener(this);

        mImage = (ImageView) findViewById(R.id.image);
        mSavePath = new File(Environment.getExternalStorageDirectory(), "capture.jpg");
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_camera:
                Intent intent_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent_camera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mSavePath));
                startActivityForResult(intent_camera, REQUEST_CAPTURE);
                break;
            case R.id.button_location:
                Intent intent_location = new Intent(this, LocationActivity.class);
                startActivity(intent_location);
                break;
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CAPTURE && resultCode == Activity.RESULT_OK){
            try{
                FileInputStream fis =new FileInputStream(mSavePath);
                BitmapFactory.Options option = new BitmapFactory.Options();
                option.inSampleSize = 2;
                Bitmap bitmap = BitmapFactory.decodeStream(fis,null,option);
                //画像をファイルに保存
                FileOutputStream fos = new FileOutputStream(mSavePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }catch(Exception e){
                Log.e("Error", e.toString());
            }
        }
    }
}