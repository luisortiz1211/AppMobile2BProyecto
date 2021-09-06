package com.example.proyecto2b;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Juego extends Activity {
    ImageButton
            imb00, imb01, imb02, imb03,
            imb04, imb05, imb06, imb07,
            imb08, imb09, imb10, imb11,
            imb12, imb13, imb14, imb15,
            imb16, imb17, imb18, imb19,
            imb20, imb21, imb22, imb23;

    ImageButton[] tableGame = new ImageButton[24];
    Button BReiniciar, BSalir;
    TextView textPuntuation;

    int points;
    int goals;

    int[] images;
    int back;

    //variables

    ArrayList<Integer> arrayDesordenado;
    ImageButton first;
    int numFirst, numSecond;
    boolean block = false;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        init();
    }

    private void updateTable() {
        imb00 = findViewById(R.id.botton0);
        imb01 = findViewById(R.id.botton1);
        imb02 = findViewById(R.id.botton2);
        imb03 = findViewById(R.id.botton3);
        imb04 = findViewById(R.id.botton4);
        imb05 = findViewById(R.id.botton5);
        imb06 = findViewById(R.id.botton6);
        imb07 = findViewById(R.id.botton7);
        imb08 = findViewById(R.id.botton8);
        imb09 = findViewById(R.id.botton9);
        imb10 = findViewById(R.id.botton10);
        imb11 = findViewById(R.id.botton11);
        imb12 = findViewById(R.id.botton12);
        imb13 = findViewById(R.id.botton13);
        imb14 = findViewById(R.id.botton14);
        imb15 = findViewById(R.id.botton15);
        imb16 = findViewById(R.id.botton16);
        imb17 = findViewById(R.id.botton17);
        imb18 = findViewById(R.id.botton18);
        imb19 = findViewById(R.id.botton19);
        imb20 = findViewById(R.id.botton20);
        imb21 = findViewById(R.id.botton21);
        imb22 = findViewById(R.id.botton22);
        imb23 = findViewById(R.id.botton23);
        tableGame[0] = imb00;
        tableGame[1] = imb01;
        tableGame[2] = imb02;
        tableGame[3] = imb03;
        tableGame[4] = imb04;
        tableGame[5] = imb05;
        tableGame[6] = imb06;
        tableGame[7] = imb07;
        tableGame[8] = imb08;
        tableGame[9] = imb09;
        tableGame[10] = imb10;
        tableGame[11] = imb11;
        tableGame[12] = imb12;
        tableGame[13] = imb13;
        tableGame[14] = imb14;
        tableGame[15] = imb15;
        tableGame[16] = imb16;
        tableGame[17] = imb17;
        tableGame[18] = imb18;
        tableGame[19] = imb19;
        tableGame[20] = imb20;
        tableGame[21] = imb21;
        tableGame[22] = imb22;
        tableGame[23] = imb23;
    }

    private void updateButton() {
        BReiniciar = findViewById(R.id.botonJuegoReiniciar);
        BSalir = findViewById(R.id.botonJuegoSalir);
        BReiniciar.setOnClickListener(view -> init());
        BSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateText() {
        textPuntuation = findViewById(R.id.textPuntuation);
        points = 0;
        goals = 0;
        textPuntuation.setText("Puntuación :" + points);
    }

    private void updateImage() {
        images = new int[]{
                R.drawable.f1,
                R.drawable.f2,
                R.drawable.f3,
                R.drawable.f4,
                R.drawable.f5,
                R.drawable.f6,
                R.drawable.f7,
                R.drawable.f8,
                R.drawable.f9,
                R.drawable.f10,
                R.drawable.f11,
                R.drawable.f12,
        };
        back = R.drawable.fondo2;
    }

    private ArrayList<Integer> charginArray(int log) {
        ArrayList<Integer> rest = new ArrayList<Integer>();
        for (int i = 0; i < log * 2; i++) {
            rest.add(i % log);
        }
        Collections.shuffle(rest);
        return rest;
    }

    private void comprobar(int i, final ImageButton imgb) {
        if (first == null) {
            first = imgb;
            first.setScaleType(ImageView.ScaleType.CENTER_CROP);
            first.setImageResource(images[arrayDesordenado.get(i)]);
            first.setEnabled(false);
            numFirst = arrayDesordenado.get(i);
        } else {
            block = true;
            imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imgb.setImageResource(images[arrayDesordenado.get(i)]);
            imgb.setEnabled(false);
            numSecond = arrayDesordenado.get(i);
            if (numFirst == numSecond) {
                first = null;
                block = false;
                goals++;
                points++;
                textPuntuation.setText("Puntuación: " + points);
                if (goals == images.length) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Has ganado!!", Toast.LENGTH_LONG);
                    toast.show();
                }
            } else {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        first.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        first.setImageResource(back);
                        first.setEnabled(true);
                        imgb.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        imgb.setImageResource(back);
                        imgb.setEnabled(true);
                        block = false;
                        first = null;
                        points--;
                        textPuntuation.setText("Puntuación: " + points);
                    }
                }, 1000);
            }
        }
    }

    private void init() {
        updateTable();
        updateButton();
        updateText();
        updateImage();
        arrayDesordenado = charginArray(images.length);
        for (int i = 0; i < tableGame.length; i++) {
            tableGame[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
            tableGame[i].setImageResource(images[arrayDesordenado.get(i)]);
            //tableGame[i].setImageResource(back);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < tableGame.length; i++) {
                    tableGame[i].setScaleType(ImageView.ScaleType.CENTER_CROP);
                    tableGame[i].setImageResource(back);
                }
            }
        }, 500);

        for (int i = 0; i < tableGame.length; i++) {
            final int j = i;
            tableGame[i].setEnabled(true);
            tableGame[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!block)
                        comprobar(j, tableGame[j]);
                }
            });
        }
    }
}

