package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnFindWasteLocations;
    private Button btnSchedulePickup;
    private Button btnWasteDisposalTips;

    private TextView textViewTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnFindWasteLocations = findViewById(R.id.btnFindWasteLocations2);
        btnSchedulePickup = findViewById(R.id.btnSchedulePickup);
        btnWasteDisposalTips = findViewById(R.id.btnWasteDisposalTips);
        textViewTitle = findViewById(R.id.textViewTitle);

        Intent intent = getIntent();
        String getName = intent.getStringExtra("name");

        textViewTitle.setText("Hello" + " " + getName);

        btnFindWasteLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a map app or a web page with waste disposal locations
                // For example, using Google Maps:
                String mapsApiKey = "YOUR_MAPS_API_KEY";
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=waste disposal locations");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        btnSchedulePickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the activity for waste pickup scheduling
                Intent scheduleIntent = new Intent(MainActivity.this, PickupFormActivity.class);
                startActivity(scheduleIntent);
            }
        });

        btnWasteDisposalTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the activity for waste disposal tips
                Intent tipsIntent = new Intent(MainActivity.this, WasteDisposalTipsActivity.class);
                startActivity(tipsIntent);
            }
        });

        // Add click listeners for other buttons and implement corresponding functionalities.
    }
}
