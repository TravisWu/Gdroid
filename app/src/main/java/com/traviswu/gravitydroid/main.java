package com.traviswu.gravitydroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.traviswu.gravitydroid.IntentResult;
import com.traviswu.gravitydroid.IntentIntegrator;

public class main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlanet = (Button) findViewById(R.id.buttonPlanet);
        buttonPlanet.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        ImageView imageView = (ImageView) findViewById(R.id.qrCode);

                        String qrData = "Data I want to encode in QR code";
                        int qrCodeDimention = 500;

                        QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(qrData, null,
                                Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

                        try {
                            Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                            imageView.setImageBitmap(bitmap);
                        } catch (WriterException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}