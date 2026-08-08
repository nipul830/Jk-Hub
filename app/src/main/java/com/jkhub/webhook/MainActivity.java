package com.jkhub.webhook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        EditText webhookUrl = findViewById(R.id.webhookUrl);
        EditText signalType = findViewById(R.id.signalType);
        EditText pair = findViewById(R.id.pair);
        EditText entry = findViewById(R.id.entry);
        EditText sl = findViewById(R.id.sl);
        EditText tp1 = findViewById(R.id.tp1);
        EditText tp2 = findViewById(R.id.tp2);

        Button sendButton = findViewById(R.id.sendButton);
        TextView status = findViewById(R.id.status);

        sendButton.setOnClickListener(v -> {

            String url = webhookUrl.getText().toString().trim();
            String type = signalType.getText().toString().trim();
            String symbol = pair.getText().toString().trim();
            String entryPrice = entry.getText().toString().trim();

            if (url.isEmpty() || symbol.isEmpty() || entryPrice.isEmpty()) {
                status.setText("Please fill required fields");
                return;
            }

            status.setText("Signal ready: " + type + " " + symbol);
        });
    }
}
