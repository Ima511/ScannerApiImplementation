package com.learn_android.scannerapiimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button startScanningButton;
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startScanningButton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
//        editText = (EditText) findViewById(R.id.editTextBarCode);
        startScanningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarcodeScanner scanner = new BarcodeScanner(MainActivity.this);
                scanner.scan(new BarcodeScanner.OnBarcodeScanCompleteListener() {
                    @Override
                    public void onBarcodeScanCompleted(boolean isSuccess, String content) {
                        EditText text = findViewById(R.id.editTextBarCode);
                        if (isSuccess) {
                            text.setText(content);
                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(),
                                    "No scan data received!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });
    }
}