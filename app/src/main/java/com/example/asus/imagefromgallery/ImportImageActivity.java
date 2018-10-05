package com.example.asus.imagefromgallery;

import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ImportImageActivity extends AppCompatActivity {

    private final static int SELECT_PICTURE = 1;
    Button button;
    ImageView iv;
    String selectedImagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_image);
        iv=(ImageView)findViewById(R.id.imageView13);
        ((Button)findViewById(R.id.button7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select picture"),SELECT_PICTURE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if (requestCode == SELECT_PICTURE){
                Uri selectedImageUri =data.getData();
                //  selectedImagePath = getRealPathFromURI(selectedImageUri);
                // Toast.makeText(getApplicationContext(),selectedImagePath,Toast.LENGTH_LONG).show();

                //System.out.println("image Path : "+selectedImagePath);
                iv.setImageURI(selectedImageUri);
                //Toast.makeText(getApplicationContext(),selectedImageUri.toString(),Toast.LENGTH_LONG).show();


            }
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

        cursor.close();
        return result;
    }

}
