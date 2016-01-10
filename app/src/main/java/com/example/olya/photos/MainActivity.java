package com.example.olya.photos;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity  extends Activity {
    private static String CLIENT_ID = "7e9dad6d8d074670a56ddd7ac96b821a";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final YandexToken token = new YandexToken();
        token.connect(CLIENT_ID, MainActivity.this, new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, token.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}

