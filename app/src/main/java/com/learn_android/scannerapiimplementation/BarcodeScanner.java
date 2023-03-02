package com.learn_android.scannerapiimplementation;

import static android.app.Activity.RESULT_OK;

import android.content.Context;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;

import com.google.mlkit.vision.codescanner.GmsBarcodeScanner;
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning;

public class BarcodeScanner implements ActivityResultCallback<ActivityResult>  {
    //    private final ActivityResultLauncher<Intent> launcher;
    private final Context context;
    private OnBarcodeScanCompleteListener callback;
    GmsBarcodeScanner scanner;

    public BarcodeScanner(Context context) {
        this.context = context;
        scanner = GmsBarcodeScanning.getClient(context);
//        launcher = ((AppCompatActivity) context).registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this);
    }

    public void scan(OnBarcodeScanCompleteListener listener) {
        scanner.startScan().addOnSuccessListener(
                        barcode -> {
                            listener.onBarcodeScanCompleted(true,  barcode.getDisplayValue());
                            // Task completed successfully
                        })
                .addOnCanceledListener(
                        () -> {
                            // Task canceled
                        })
                .addOnFailureListener(
                        e -> {
                            // Task failed with an exception
                            listener.onBarcodeScanCompleted(false,  null);
                        });

//        Activity currentActivity = (Activity) context;
//        Intent intent = new Intent(currentActivity, BarcodeScannerActivity.class);
//        launcher.launch(intent);
//        this.callback = listener;
    }

    @Override
    public void onActivityResult(ActivityResult result) {
        if(callback != null) {
            if(result.getData() != null) {
                callback.onBarcodeScanCompleted(result.getResultCode() == RESULT_OK, result.getData().getExtras().getString("content"));
            } else {
                callback.onBarcodeScanCompleted(false, null);
            }
        }
    }

    public interface OnBarcodeScanCompleteListener {
        void onBarcodeScanCompleted(boolean isSuccess,  String content);
    }
}
