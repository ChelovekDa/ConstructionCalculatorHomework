package com.msaggik.fifthlessonconstructioncalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class WallbricksActivity extends AppCompatActivity {

    private int bricksLength, bricksWidth, wallLength, wallWidth; // bricks в данном случае - обозначение плитки
    private double cost;

    private EditText bricksLengthEdit, bricksWidthEdit, wallLengthEdit, wallWidthEdit, costEdit;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wallbricks);

        bricksLengthEdit = findViewById(R.id.bricksLength);
        bricksWidthEdit = findViewById(R.id.bricksWidth);
        wallLengthEdit = findViewById(R.id.wallLength);
        wallWidthEdit = findViewById(R.id.wallWidth);
        costEdit = findViewById(R.id.cost);

        button = findViewById(R.id.button);
        button.setOnClickListener(listener);
    }

    private View.OnClickListener listener = view -> {

        try {
            bricksLength = Integer.parseInt(bricksLengthEdit.getText().toString());
            bricksWidth = Integer.parseInt(bricksWidthEdit.getText().toString());
            wallLength = Integer.parseInt(wallLengthEdit.getText().toString());
            wallWidth = Integer.parseInt(wallWidthEdit.getText().toString());
            cost = Double.parseDouble(costEdit.getText().toString());

            int wallSquare = wallLength * wallWidth; //Площадь стены в см2
            int brickSquare = bricksLength * bricksWidth; //Площадь ОДНОЙ плитки в см2
            int bricksCost = (int) Math.round(cost * (Math.round(wallSquare / brickSquare))); //Стоимость плиток

            Intent intent = new Intent(getApplicationContext(), CalculationActivity.class);
            intent.putExtra("wallSquare", (wallLength * wallWidth));
            intent.putExtra("brickSquare", (bricksLength * bricksWidth));
            intent.putExtra("cost", bricksCost);
            intent.putExtra("Data", "bricks");
            startActivity(intent);
        }
        catch (NumberFormatException exception) {
            Toast.makeText(this, "Необходимые значения не были введены!", Toast.LENGTH_SHORT).show();
        }
    };
}