package com.example.colortiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int darkColor;
    int brightColor;
    View[][] tiles = new View[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        darkColor = r.getColor(R.color.dark);
        brightColor = r.getColor(R.color.bright);

        tiles[0][0] = findViewById(R.id.t00);
        tiles[0][1] = findViewById(R.id.t01);
        tiles[0][2] = findViewById(R.id.t02);
        tiles[0][3] = findViewById(R.id.t03);
        tiles[1][0] = findViewById(R.id.t10);
        tiles[1][1] = findViewById(R.id.t11);
        tiles[1][2] = findViewById(R.id.t12);
        tiles[1][3] = findViewById(R.id.t13);
        tiles[2][0] = findViewById(R.id.t20);
        tiles[2][1] = findViewById(R.id.t21);
        tiles[2][2] = findViewById(R.id.t22);
        tiles[2][3] = findViewById(R.id.t23);
        tiles[3][0] = findViewById(R.id.t30);
        tiles[3][1] = findViewById(R.id.t31);
        tiles[3][2] = findViewById(R.id.t32);
        tiles[3][3] = findViewById(R.id.t33);

        Random rnd = new Random();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (rnd.nextBoolean()) {
                    tiles[y][x].setBackgroundColor(darkColor);
                } else {
                    tiles[y][x].setBackgroundColor(brightColor);
                }
            }
        }
    }
    public void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
        } else {
            v.setBackgroundColor(brightColor);
        }
    }
    public void onClick(View v) {
        // получаем тэг на кнопке
        String tag = v.getTag().toString();
        int x = Integer.parseInt(tag.substring(1)), y = Integer.parseInt(tag.substring(0, 1)); //
        // координаты
        // тайла и
        // строки вида
        // "00"

        // изменить цвет на самом тайле и всех тайлах
        // с таким же x и таким же y
        changeColor(v);
        for (int i = 0; i < 4; i++) {
            changeColor(tiles[y][i]);
            changeColor(tiles[i][x]);
        }

        checkWin();
    }
    
    private void checkWin() {
        boolean isAllDark = true;
        boolean isAllBright = true;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                ColorDrawable d = (ColorDrawable) tiles[y][x].getBackground();
                if (d.getColor() == brightColor) isAllDark = false;
                else isAllBright = false;
            }
        }
        
        if (isAllBright || isAllDark) {
            Toast.makeText(this, "Вы выиграли!", Toast.LENGTH_SHORT).show();
        }
    }
}
