package com.example.wastemanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PickupFormActivity extends AppCompatActivity {

    private EditText editTextName, editTextAddress, editTextContact;
    private Button btnSubmit;
    private PickupData pickupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pickup_form);

        pickupData = new PickupData(this);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextContact = findViewById(R.id.editTextContact);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPickupRequest();
            }
        });
    }

    private void submitPickupRequest() {
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();

        if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        long result = pickupData.insertPickup(name, address, contact);

        if (result != -1) {
            Toast.makeText(this, "Pickup request submitted successfully", Toast.LENGTH_SHORT).show();
            clearForm();
        } else {
            Toast.makeText(this, "Failed to submit pickup request", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearForm() {
        editTextName.setText("");
        editTextAddress.setText("");
        editTextContact.setText("");
    }
}



