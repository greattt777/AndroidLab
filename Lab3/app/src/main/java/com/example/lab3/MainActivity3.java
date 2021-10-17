package com.example.lab3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private static final int FONT_10 = 0x111;
    private static final int FONT_16 = 0x112;
    private static final int FONT_20 = 0x113;
    private static final int ORDINARY = 0x114;
    private static final int FONT_RED = 0x115;
    private static final int FONT_BLACK = 0x116;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textView = findViewById(R.id.tv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0, FONT_10, 0, "10号字体");
        fontMenu.add(0, FONT_16, 0, "16号字体");
        fontMenu.add(0, FONT_20, 0, "20号字体");

        menu.add(0, ORDINARY, 0, "普通菜单项");

        SubMenu colorMenu = menu.addSubMenu("字体颜色");
        colorMenu.setHeaderTitle("选择字体颜色");
        colorMenu.add(0, FONT_RED, 0, "红色");
        colorMenu.add(0, FONT_BLACK, 0, "黑色");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case FONT_10:
                textView.setTextSize(10 * 2);
                break;
            case FONT_16:
                textView.setTextSize(16 * 2);
                break;
            case FONT_20:
                textView.setTextSize(20 * 2);
                break;
            case ORDINARY:
                Toast.makeText(MainActivity3.this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
                break;
            case FONT_RED:
                textView.setTextColor(Color.RED);
                break;
            case FONT_BLACK:
                textView.setTextColor(Color.BLACK);
                break;
        }
        return true;
    }
}