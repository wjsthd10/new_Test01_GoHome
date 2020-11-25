package com.example.test_01.Day08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
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
                {"긴 코트", "야상", "패딩"}};

        int ranNum0=random.nextInt(clothesList[0].length-1);
        int ranNum1=random.nextInt(clothesList[1].length-1);
        int ranNum2=random.nextInt(clothesList[2].length-1);
        int ranNum3=random.nextInt(clothesList[3].length-1);
        int ranNum4=random.nextInt(clothesList[4].length-1);

        String[] head=new String[ranNum0];
        String[] face=new String[ranNum1];
        String[] top=new String[ranNum2];
        String[] bottom=new String[ranNum3];
        String[] clothe=new String[ranNum4];

        int whileNum=0;
        while (whileNum==0){

            for (int c1=0;c1<ranNum0;c1++){
                head[c1]=clothesList[0][c1];
            }
            for (int i=0;i<ranNum1;i++){
                face[i]=clothesList[1][i];
            }
            for (int i=0;i<ranNum2;i++){
                top[i]=clothesList[2][i];
            }
            for (int i=0;i<ranNum3;i++){
                bottom[i]=clothesList[3][i];
            }
            for (int i=0;i<ranNum4;i++){
                clothe[i]=clothesList[4][i];
            }

            if (head.length+face.length+top.length+bottom.length+clothe.length!=0){
                whileNum=1;
            }
        }

        int[] sumList=new int[5];
        ArrayList<Integer> sumNums=new ArrayList<>();

        if (head.length!=0){
            sumList[0]=head.length;
            sumNums.add(head.length);
        }
        if (face.length!=0){
            sumList[1]=face.length;
            sumNums.add(face.length);
        }
        if (top.length!=0){
            sumList[2]=top.length;
            sumNums.add(top.length);
        }
        if (bottom.length!=0){
            sumList[3]=bottom.length;
            sumNums.add(bottom.length);
        }
        if (clothe.length!=0){
            sumList[4]=clothe.length;
            sumNums.add(clothe.length);
        }

        Log.i("sumListTest", Arrays.toString(sumList));
        Log.i("sumListTest", Arrays.toString(sumNums.toArray()));


        int answerNum=0;
        for (int i=0;i<sumNums.size();i++){
            answerNum+=sumNums.get(i);
        }

        for (int i=0;i<sumNums.size();i++){
            answerNum*=sumNums.get(i);
        }

        Log.i("sumListTest", answerNum+"");



    }
}