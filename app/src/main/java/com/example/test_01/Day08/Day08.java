package com.example.test_01.Day08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Day08 extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day08);
        setTitle("08번 문제 위장");
        textView=findViewById(R.id.answer);

    }

    public void clickbtn(View view) {

        Random random=new Random();
        String[][] clothesList={
                {"노란모자", "파란모자", "빨간모자"},
                {"동그란 안경", "검정 선글라스"},
                {"파란색 티셔츠", "검은색 티셔츠", "흰색 티셔츠", "분홍색 티셔츠"},
                {"청바지", "슬랙스", "반바지", "정장바지"},
                {"긴 코트", "야상", "패딩"}
        };


        int answer=solution(clothesList);
        Log.i("answer", "");
        String answer_str="";
        for (int i=0;i<5;i++){
            switch (clothesList[i][0]){
                case "노란모자": for (int c=0;c<clothesList[i].length;c++){
                    answer_str+="["+clothesList[i][c]+"], ";
                }break;
                case "동그란 안경":for (int c=0;c<clothesList[i].length;c++){
                    answer_str+="["+clothesList[i][c]+"], ";
                }break;
                case "파란색 티셔츠":for (int c=0;c<clothesList[i].length;c++){
                    answer_str+="["+clothesList[i][c]+"], ";
                }break;
                case "청바지":for (int c=0;c<clothesList[i].length;c++){
                    answer_str+="["+clothesList[i][c]+"], ";
                }break;
                case "긴 코트":for (int c=0;c<clothesList[i].length;c++){
                    answer_str+="["+clothesList[i][c]+"], ";
                }break;

            }
        }

        Log.i("showData", answer_str);
        textView.setText(answer_str+"  "+answer+"개의 조합");


    }

    public int solution(String[][] clothes){
        int answer=1;
        HashMap<String, Integer> map=new HashMap<>();

        for (int i=0; i<clothes.length;i++){
            String key=clothes[i][1];
            if (!map.containsKey(key)){
                map.put(key,1);
            }else {
                map.put(key,map.get(key)+1);
            }
        }
        Iterator<Integer> it=map.values().iterator();
        while (it.hasNext()){
            answer*= it.next().intValue()+1;
        }

        return answer-1;
    }


}
