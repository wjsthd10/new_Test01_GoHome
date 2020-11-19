package com.example.test_01.Day04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.test_01.R;

import java.util.Random;

public class Day04 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day04);
        textView=findViewById(R.id.answer);
        setTitle("04번 가운데 글자 가져오기");
    }

//    홀수 반 나눈 번째 인덱스 값
//    짝수 반 나눈 번째 인덱스 값, -1번째 인덱스 값

    public void clickbtn(View view) {

        Random random=new Random();
        int ran_num=random.nextInt(100)+1;
        String ran_str="";
        for (int i=0; i<ran_num; i++){
            ran_str+=String.valueOf((char) ((int)random.nextInt(26)+97));
        }

        Log.d("ran_str", ran_str+" : "+ran_num+"개의 char" );
        String answer_str=solution(ran_str, ran_num);

        Log.d("answer_str", ran_str+" : "+ran_num/2+"번째" );
        Log.d("answer_str", answer_str);

        String set_TextView="Random으로 받은 문자열 : "+ran_str+"\n\n"+"가운데 : "+((ran_num/2)+1)+"번째\n\n"+"Return한 값 : "+answer_str;
        textView.setText(set_TextView);

    }
    public String solution(String str, int ran_num){
        String answer_str="";
        if (ran_num%2==0){// 짝수
            int index_num1=ran_num/2;
            int index_num2=(ran_num/2)-1;

            answer_str+=str.charAt(index_num2);
            answer_str+=str.charAt(index_num1);


        }else {// 홀수
            int index_num1=ran_num/2;
            answer_str+=str.charAt(index_num1);
        }


        return answer_str;
    }
}