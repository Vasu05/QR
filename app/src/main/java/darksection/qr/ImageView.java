package darksection.qr;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import darksection.qr.Image_Model.Model;
import darksection.qr.Image_Model.Model_adapter;
import darksection.qr.Image_Model.RecyclerItemClickListener;

public class ImageView extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 007;
    RecyclerView recyclerView;
    private  Model data;
    private RecyclerView.Adapter adapter;
    ContentResolver contentResolver;
    Context context = ImageView.this;
    public static List<Model> imagelist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {

            Androidpermission();


        }else {
            Log.e("MainActivity","Permission Taken into the account ");
            Loadimages();
            //System.out.println(getAllShownImagesPath(this));
        }

        RecyclerviewListener();
        adapter = new Model_adapter(ImageView.this, imagelist);
        recyclerView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }


    public void  Loadimages()
    {
        Uri uri;
        Cursor cursor;
        int column_index_data,column_index_id,column_width,column_height;


        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME
                ,MediaStore.Images.Media._ID, "width", "height"};

        contentResolver = getContentResolver();
        cursor = contentResolver.query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_height = cursor.getColumnIndexOrThrow("height");
        column_width = cursor.getColumnIndexOrThrow("width");



        column_index_id = cursor.getColumnIndex(
                MediaStore.Images.Media._ID );


        if ( cursor != null && cursor.getCount() > 0 ) {

            String imageId="";
            String imagepath="";
            String height ="";
            String width= "";

            Log.i("DeviceImageManager", " query count=" + cursor.getCount());

            int counter =0;
            while (cursor.moveToNext()) {

                imagepath = cursor.getString(column_index_data);
                imageId = cursor.getString(column_index_id);
                width = cursor.getString(column_width);
                height = cursor.getString(column_height);
                System.out.println("height "+height + " "+ "width " +width);
                if(width!=null || height!=null)
                    data = new Model(imageId,imagepath,Integer.parseInt(height),Integer.parseInt(width),counter);
                counter++;
                imagelist.add(data);
            }
        }

    }


    public void RecyclerviewListener()
    {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        data = imagelist.get(position);
                        Uri mImageUri = Uri.fromFile(new File(data.getImageurl()));
                        Intent mIntent = new Intent();
                        mIntent.setData(mImageUri);
                        setResult(1,mIntent);
                        finish();

                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

    }
    public void Androidpermission()
    {
        if ((ActivityCompat.shouldShowRequestPermissionRationale(ImageView.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(ImageView.this,
                Manifest.permission.READ_EXTERNAL_STORAGE))) {



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Permission is to read your files")
                    .setTitle("LBB -Permission");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    ActivityCompat.requestPermissions(ImageView.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE} , REQUEST_PERMISSIONS);

                }
            });

        }else
        {
            ActivityCompat.requestPermissions(ImageView.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE} , REQUEST_PERMISSIONS);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        //System.out.println(permissions[i] + " " +grantResults[i]);

                    } else {
                        Toast.makeText(ImageView.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
