package com.traviswu.gravitydroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.twilio.sdk.TwilioRestException;


public class main extends Activity {
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPlanet = (Button) findViewById(R.id.buttonPlanet);
        buttonPlanet.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                        //Read QR Code
                        scanQR(v);



                        //Generate QR Code
                        /*
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
                        }*/
                    }
                }
        );
    ImageButton buttonMessage = (ImageButton)findViewById(R.id.twilio);
        buttonMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                //get prompt.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.twilioprompt,null);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompt.xml to alergdialog builder
                alertDialogBuilder.setView(promptsView);
                //set dialog message
               final EditText userInput = (EditText) promptsView.findViewById(R.id.promptnumber);

               alertDialogBuilder
                       .setPositiveButton("send",
                             new DialogInterface.OnClickListener(){
                                 public void onClick(DialogInterface dialog, int id) {
                                     //get user input and set it to results
                                     //edit text
                                     Toast toast = Toast.makeText(getApplicationContext(),
                                             userInput.getText(), Toast.LENGTH_SHORT);
                                     toast.show();
                                     textSender(userInput.getText().toString());
                                 };
                             })
                       .create()
                       .show();


            };
        });
    }


    public void textSender(String phoneNumber){
        try {
            SmsSender.send(phoneNumber);
        } catch (TwilioRestException e) {
            e.printStackTrace();
        }


    }
    public void scanQR(View v){
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            Toast toast = Toast.makeText(getApplicationContext(),
                    scanContent, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
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
