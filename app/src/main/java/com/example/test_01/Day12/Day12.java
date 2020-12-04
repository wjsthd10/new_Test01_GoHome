package com.example.test_01.Day12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_01.R;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Day12 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day12);
        setTitle("12번 최고의 집합");
        textView=findViewById(R.id.answer);
    }

    public void clickbtn(View view) {
        Random random=new Random();
        HashMap<Integer, Integer[]> hashMap=new HashMap<>();

        for (int i=0;i<10;i++){
            Integer[] nums=new Integer[2];
            for (int j=0;j<2;j++){
                nums[j]=random.nextInt(99)+1;
            }
            hashMap.put(random.nextInt(99)+1, nums);
        }

//        String str=Arrays.toString(hashMap.get(1));       // null값 나옴
        Iterator<Integer[]> integers=hashMap.values().iterator();

        Log.i("dataShow", Arrays.toString(integers.next())+" : "+hashMap.keySet().toString());

        

    }
}