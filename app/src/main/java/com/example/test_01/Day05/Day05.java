package com.example.test_01.Day05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.test_01.R;

import java.util.Arrays;
import java.util.Random;

public class Day05 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day05);
        textView=findViewById(R.id.answer);

        setTitle("05번 시저 암호");
    }

    public void clickbtn(View view) {

        Random random=new Random();
        int numSize=random.nextInt(799)+1;
        int pushNum=random.nextInt(24)+1;// 소문자를 z를 밀어도 소문자로 돌아옴

        String[] caesarPW=new String[numSize];// 문자열로 바꾼거 저장
        int[] caesarPW_int=new int[numSize];// 증가시키기 쉽게 인트로 저장
        String[] caesarPW_answer=new String[numSize];

        for (int i=0; i<numSize; i++){
            int ran=random.nextInt(3);
            switch (ran){
                case 0:caesarPW_int[i]=random.nextInt(26)+97; break;
                case 1:caesarPW_int[i]=random.nextInt(26)+65; break;
                case 2:caesarPW_int[i]=32;
            }
            caesarPW[i]= String.valueOf((char)caesarPW_int[i]);
        }
        Log.i("확인01", Arrays.toString(caesarPW));

//        String setPW_caesar=Arrays.toString(caesarPW_int);// 입력된 아스키번호 보기
//        Log.i("pw확인하기", setPW_caesar);// 번호 출력
        String answer_str="";

        int[] answer=solution(caesarPW_int, pushNum);
        for (int i=0; i<answer.length;i++){

            if (answer[i]==32){
                caesarPW_answer[i]=" ";
                answer_str+=" ";
            }else {
                caesarPW_answer[i]=String.valueOf((char)answer[i]);
                answer_str+=String.valueOf((char)answer[i]);
            }

        }

        Log.i("answer저장확인", Arrays.toString(caesarPW_answer));

//        textView.setText(answer_str);
        textView.setText(Arrays.toString(caesarPW)+"\n\n\n밀 횟수 : "+pushNum+"번\n\nn번 이동한 결과 값 : "+answer_str);

    }
    public int[] solution(int[] caesarPW_int, int pushNum){
        String answer="";
        int[] sumNum=new int[caesarPW_int.length];

        for (int i=0;i<caesarPW_int.length;i++){

            if (caesarPW_int[i]==32){
                sumNum[i]=32;
            }else if (caesarPW_int[i]<97){// 대문자 일로들어옴
                if (caesarPW_int[i]+pushNum>90){// 더한값이 90보다 클때
                    sumNum[i]=(caesarPW_int[i]+pushNum)-26;
                }else {// 더한값이 크지 않을때
                    sumNum[i]=(caesarPW_int[i]+pushNum);
                }
                // 대문자 처리
            }else if (caesarPW_int[i]>=97){// 소문자 일로들어옴
                if (caesarPW_int[i]+pushNum>122){// 더한 값이 122보다 클때
                    sumNum[i]=(caesarPW_int[i]+pushNum)-26;
                }else {//더한값이 크지않을때
                    sumNum[i]=(caesarPW_int[i]+pushNum);
                }
            }
                //소문자 처리
        }// 반복문
        return sumNum;
    }//solution
}//class