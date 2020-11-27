package com.example.test_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.test_01.Day01.Day01;
import com.example.test_01.Day02.Day02;
import com.example.test_01.Day03.Day03;
import com.example.test_01.Day04.Day04;
import com.example.test_01.Day05.Day05;
import com.example.test_01.Day06.Day06;
import com.example.test_01.Day07.Day07;
import com.example.test_01.Day08.Day08;
import com.example.test_01.Day09.Day09;

public class MainActivity extends AppCompatActivity {

//    리스트뷰 타이틀 배열로 지정해서 만들기...
    static final String[] LIST_MENU={
        "Day 01 (20.11.16) : 두 개 뽑아서 더하기",
        "Day 02 (20.11.17) : 모의고사",
        "Day 03 (20.11.18) : K번째 수",
        "Day 04 (20.11.19) : 가운데 글자 가져오기",
        "Day 05 (20.11.20) : 시저 암호",
        "Day 06 (20.11.23) : 소수 찾기",
        "Day 07 (20.11.24) : 가장 큰 수",
        "Day 08 (20.11.25) : 위장",
        "Day 09 (20.11.26) : 소수 찾기2",
    };
    ListView listView;

//    정수배열의 모든 인덱스에 있는 두개의 수를 더해서 만들수 있는 모든 수를 배열에 오름차순으로 담아서 리턴

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1, LIST_MENU);

        listView=findViewById(R.id.p_list);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0: intent=new Intent(MainActivity.this, Day01.class);startActivity(intent);break;
                    case 1: intent=new Intent(MainActivity.this, Day02.class);startActivity(intent);break;
                    case 2:intent=new Intent(MainActivity.this, Day03.class);startActivity(intent);break;
                    case 3:intent=new Intent(MainActivity.this, Day04.class);startActivity(intent);break;
                    case 4:intent=new Intent(MainActivity.this, Day05.class);startActivity(intent);break;
                    case 5:intent=new Intent(MainActivity.this, Day06.class);startActivity(intent);break;
                    case 6:intent=new Intent(MainActivity.this, Day07.class);startActivity(intent);break;
                    case 7:intent=new Intent(MainActivity.this, Day08.class);startActivity(intent);break;
                    case 8:intent=new Intent(MainActivity.this, Day09.class);startActivity(intent);break;
                }




            }
        });

        listView.setBackgroundColor(Color.parseColor("#CCCBCB"));
        listView.setAdapter(adapter);
    }

}