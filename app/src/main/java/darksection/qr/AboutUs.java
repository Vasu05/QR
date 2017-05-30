package darksection.qr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

    }

    //opening google map on picture click

    public void visitRajeev(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:28.735,77.486"));
        Intent chooser = Intent.createChooser(intent, "Launch App");
        startActivity(chooser);
    }
    public void visitShubham(View view){
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:28.735,77.486"));
        Intent chooser = Intent.createChooser(intent, "Launch App");
        startActivity(chooser);
    }
}
