package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1.ml.UINT8;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.schema.Model;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Image_loader extends AppCompatActivity {

    private static final int pic_id = 123;
    Button camera_open_id,detect;
    ImageView click_image_id;
    Bitmap bitmap;
    UINT8 model;
    TextView result;
    String lables;
    List<String> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_loader);

        camera_open_id = findViewById(R.id.camera_button);
        click_image_id = findViewById(R.id.click_image);
        detect = findViewById(R.id.Detect);
        result = findViewById(R.id.Result);
        list = new ArrayList<>();

        Intent intent = getIntent();
        bitmap = intent.getParcelableExtra("image");
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        click_image_id.setImageBitmap(bitmap);

        try {


            InputStream inputStream = getApplication().getAssets().open("labels.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String st;
            while ((st = br.readLine()) != null){

                list.add(st);

            }
        }catch (Exception e){
            Log.d("check", "onCreate: "+e);
        }


        model = null;
        detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap resized = Bitmap.createScaledBitmap(bitmap, 224, 224, true);

                try {
                    model = UINT8.newInstance(Image_loader.this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Creates inputs for reference.
                TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.UINT8);

                TensorImage btbuffer = TensorImage.fromBitmap(resized);
                ByteBuffer  bytebuffure =  btbuffer.getBuffer();

                inputFeature0.loadBuffer(bytebuffure);

                // Runs model inference and gets result.
                UINT8.Outputs outputs = model.process(inputFeature0);
                TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

                int max = max(outputFeature0.getFloatArray());
                result.setText(list.get(max));


                // Releases model resources if no longer used.
                model.close();

            }

        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.the_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.setp:
                Intent i = new Intent(this,Settings.class);
                startActivity(i);
                break;

            case R.id.fun:
                Image_loader.this.finish();
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    int max(float[] arr){

        int ind = 0;
        float min = 0.0f;

        for(int i = 0 ; i<= 1;i++){

            if(arr[i] >min){

                ind = i;
                min = arr[i];
            }

        }

        return ind;
    }

}