package com.example.test_01.Day07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Day07 extends AppCompatActivity {

    TextView textView;
    Random random=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day07);
        textView=findViewById(R.id.answer);
        setTitle("07번 가장 큰 수");

    }

    public void clickbtn(View view) {

        int essence=random.nextInt(9999)+1;
        String answer=solution(essence);

        textView.setText(answer);

    }
    public String solution(int essence){
        String answer="";

        Integer[] arr=new Integer[essence];
        for (int i=0;i<essence;i++){
            arr[i]=random.nextInt(1000);
        }

        Arrays.sort(arr, Collections.reverseOrder());
        for (int i=0; i< arr.length; i++){
            answer+=arr[i];
        }

        String[] answers=answer.split(",");

//        Log.i("arrShow", Arrays.toString(arr));
//        Log.i("String[]", Arrays.toString(answers));

        return Arrays.toString(answers);
    }
}