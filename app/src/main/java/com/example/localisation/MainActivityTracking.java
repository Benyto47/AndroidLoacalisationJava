package com.example.localisation;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityTracking extends AppCompatActivity {

    Button buttonRetour ;
    private EditText etFromLocation ;
    private EditText etToLocation ;
    private Button btnGetDirection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tracked_layout);

        etFromLocation = findViewById(R.id.etFromLocation);
        etToLocation = findViewById(R.id.etToLocation);
        btnGetDirection = findViewById(R.id.btnGetDirection);

        btnGetDirection.setOnClickListener(view->{
            String userLocation = etFromLocation.getText().toString();
            String userDestination = etToLocation.getText().toString();

            if(userLocation.equals("") || userDestination.equals("")){
                Toast.makeText(this,"Veuillez s'il vous plaît entrer votre position",Toast.LENGTH_SHORT).show();
            }else {
                getDirections(userLocation,userDestination);
            }
        });



         buttonRetour = findViewById(R.id.buttonRetour);

         buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité en cours
            }
        });

    }

    private void getDirections(String from, String to) {
        try{
            Uri uri = Uri.parse("https://www.google.com/maps/dir/"+ from + "/" + to) ;
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }catch (ActivityNotFoundException exception){

            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en_US&pli=1" + from + "/"+ to) ;
            Intent intent = new Intent(Intent.ACTION_VIEW,uri) ;
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }
}
