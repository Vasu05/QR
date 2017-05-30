package darksection.qr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        if(id == R.id.m1){      //help
//            Intent intent = new Intent(this, Help.class);
//            startActivity(intent);
//        }
//        if(id == R.id.m2){      //contact
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.setData(Uri.parse("emailto:"));
//            String[] to = {"freeRajeev@hotmail.com","info@darksection.in"};
//            intent.putExtra(Intent.EXTRA_EMAIL,to);
//            intent.putExtra(Intent.EXTRA_SUBJECT,"Mail from QR Code Generator & Scanner");
//            intent.putExtra(Intent.EXTRA_TEXT," ");
//            intent.setType("message/rfc822");
//            Intent chooser = Intent.createChooser(intent,"Launch Email");
//            startActivity(chooser);
//        }
//        if(id == R.id.m3){      //about
//            Intent intent = new Intent(this, AboutUs.class);
//            startActivity(intent);
//        }
//        if(id == R.id.m4){      //Rate
//            Toast.makeText(MainActivity.this, "Opening PlayStore", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
//            intent.setData(Uri.parse("market://details?id=dolphin.developers.com"));
//            Intent chooser = Intent.createChooser(intent, "Launch App");
//            startActivity(chooser);
//        }
//        return super.onOptionsItemSelected(item);
//    }
    public void gen(View view){
        Intent intent = new Intent(this, Gen.class);
        startActivity(intent);
    }
    public void scan(View view){
        Intent intent = new Intent(this, Scan.class);
        startActivity(intent);
    }
}
