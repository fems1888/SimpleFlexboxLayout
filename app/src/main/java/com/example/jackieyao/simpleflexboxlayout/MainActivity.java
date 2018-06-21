package com.example.jackieyao.simpleflexboxlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flexlib.FlexboxLayout;
import com.example.flexlib.JustifyContent;

public class MainActivity extends AppCompatActivity {
    private FlexboxLayout flexLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flexLayout = findViewById(R.id.flexLayout);
//        findViewById(R.id.text1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,((TextView)view).getText().toString(),Toast.LENGTH_LONG).show();
//            }
//        });
//        findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,((TextView)view).getText().toString(),Toast.LENGTH_LONG).show();
//            }
//        });
//        TextView viewById = findViewById(R.id.text3);
//        viewById.setSelected(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.start:

                flexLayout.setJustifyContent(5);
                break;
            case R.id.end:
                flexLayout.setJustifyContent(JustifyContent.FLEX_END);
                break;
            case R.id.center:
                flexLayout.setJustifyContent(JustifyContent.CENTER);
                break;
                default:

                    break;
        }
        return super.onOptionsItemSelected(item);
    }
}
