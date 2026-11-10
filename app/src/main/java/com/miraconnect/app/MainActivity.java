package com.miraconnect.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.Settings;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private GradientDrawable bg(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }
    private TextView text(String value, int size, int color) {
        TextView v = new TextView(this);
        v.setText(value);
        v.setTextSize(size);
        v.setTextColor(color);
        return v;
    }
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(13, 18, 35));
        getWindow().setNavigationBarColor(Color.rgb(13, 18, 35));
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(28, 75, 28, 28);
        root.setBackgroundColor(Color.rgb(13, 18, 35));
        TextView symbol = text("▣", 70, Color.rgb(87, 181, 255));
        symbol.setGravity(Gravity.CENTER);
        root.addView(symbol);
        TextView heading = text("MiraConnect", 32, Color.WHITE);
        heading.setTypeface(null, Typeface.BOLD);
        heading.setGravity(Gravity.CENTER);
        root.addView(heading);
        TextView subtitle = text("Трансляция экрана на телевизор", 17, Color.rgb(191, 202, 220));
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 16, 0, 30);
        root.addView(subtitle);
        TextView help = text("1. Включите Miracast / Screen Mirroring на телевизоре.\n\n2. Нажмите кнопку ниже.\n\n3. Выберите телевизор в системном меню, если он доступен.", 16, Color.WHITE);
        help.setPadding(22, 27, 22, 27);
        help.setBackground(bg(Color.rgb(29, 41, 63), 22));
        root.addView(help);
        Button connect = new Button(this);
        connect.setText("НАЙТИ ТЕЛЕВИЗОР");
        connect.setAllCaps(false);
        connect.setTextSize(18);
        connect.setTextColor(Color.WHITE);
        connect.setBackground(bg(Color.rgb(33, 132, 243), 20));
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(-1, 70);
        buttonParams.topMargin = 32;
        root.addView(connect, buttonParams);
        TextView note = text("Подключение выполняет система Android. Некоторые телефоны поддерживают только Google Cast, а не Miracast.", 13, Color.rgb(166, 180, 202));
        note.setGravity(Gravity.CENTER);
        note.setPadding(5, 30, 5, 0);
        root.addView(note);
        connect.setOnClickListener(v -> openCastingSettings());
        setContentView(root);
    }
    private void openCastingSettings() {
        try {
            startActivity(new Intent("android.settings.CAST_SETTINGS"));
        } catch (ActivityNotFoundException e) {
            try {
                startActivity(new Intent(Settings.ACTION_SETTINGS));
                Toast.makeText(this, "Найдите 'Трансляция', 'Smart View' или 'Беспроводной дисплей' в настройках", Toast.LENGTH_LONG).show();
            } catch (Exception ignored) {
                Toast.makeText(this, "Настройки подключения недоступны на этом телефоне", Toast.LENGTH_LONG).show();
            }
        }
    }
}
