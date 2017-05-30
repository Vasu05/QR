package darksection.qr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Gen extends AppCompatActivity {
    EditText text;
    Button btn,btn_import;
    ImageView image;
    String text2Qr;
    private static int GALLERY_KITKAT_INTENT_CALLED = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gen);

        text = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button3);
        btn_import = (Button)findViewById(R.id.btn_import);
        image = (ImageView)findViewById(R.id.imageView);

        btn_import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenImageGallery();
            }
        });
    }

    public void OpenImageGallery() {
        Intent intent = new Intent(Gen.this, darksection.qr.ImageView.class);
        startActivityForResult(intent, GALLERY_KITKAT_INTENT_CALLED);
    }


    public void genQR(View view){
        text2Qr = text.getText().toString().trim();
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);

        }
        catch (WriterException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("updateSelectedImages", "updateSelectedImages  " + requestCode + "   " + resultCode);

        if (requestCode == 1) {
            Uri originalUri = data.getData();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try{
                BitMatrix bitMatrix = multiFormatWriter.encode(originalUri.toString(), BarcodeFormat.QR_CODE,200,200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                image.setImageBitmap(bitmap);

            }
            catch (WriterException e){
                e.printStackTrace();
            }

        }



    }


}
