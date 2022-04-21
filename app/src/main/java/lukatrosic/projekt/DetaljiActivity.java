package lukatrosic.projekt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class DetaljiActivity extends AppCompatActivity {


    //Promjena imena varijabli
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji);

        Intent intent = getIntent();
        Crypto crypto = (Crypto) intent.getSerializableExtra("Crypto");

        TextView symbol = findViewById(R.id.symbol);
        symbol.setText(String.valueOf(crypto.getSymbol()));

        TextView highValue = findViewById(R.id.highValue);
        highValue.setText(String.valueOf(crypto.getHighPrice()));

        Button nazad = findViewById(R.id.nazad);
        nazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}