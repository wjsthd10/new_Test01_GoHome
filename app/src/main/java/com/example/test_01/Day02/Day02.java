package com.example.test_01.Day02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Day02 extends AppCompatActivity {

    List<Integer> supoja01=new ArrayList<>();//12345.12345
    List<Integer> supoja02=new ArrayList<>();//21232425.21232425
    List<Integer> supoja03=new ArrayList<>();//3311224455.3311224455
    TextView answer;

    Random random=new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day02);
        setTitle("02번 모의고사");
        answer=findViewById(R.id.answer);

    }

    public void clickbtn(View view) {

        int question=random.nextInt(10000)+1;
        List<Integer> questionList=new ArrayList<>();

//        int supoja01correctAnswer=0;
//        int supoja02correctAnswer=0;
//        int supoja03correctAnswer=0;

        int[] correctAnswer={0,0,0};



        // 학생이 찍은 정답 호출
        stu01_set(question, questionList);

//        for (int i=0;i<question;i++){
//            // 정답 비교(questionList와 supoja에 저장된 값 비교)
//            if (questionList.get(i)==supoja01.get(i)) supoja01correctAnswer+=1;
//            if (questionList.get(i)==supoja02.get(i)) supoja02correctAnswer+=1;
//            if (questionList.get(i)==supoja03.get(i)) supoja03correctAnswer+=1;
//        }
        for (int i=0;i<question;i++){
            // 정답 비교(questionList와 supoja에 저장된 값 비교)
            if (questionList.get(i)==supoja01.get(i)) correctAnswer[0]+=1;
            if (questionList.get(i)==supoja02.get(i)) correctAnswer[1]+=1;
            if (questionList.get(i)==supoja03.get(i)) correctAnswer[2]+=1;
        }

        String totalText="";

//        answer.setText(supoja01correctAnswer+" : "+supoja02correctAnswer+" : "+supoja03correctAnswer+" // "+questionList.size());// 1번 수포자 : 2번 수포자 : 3번 수포자 // 문제의 수
        //

        if (questionList.size()==correctAnswer[0]){
            totalText+="수포자 1은 모든 문제("+correctAnswer[0]+"개)를 맞혔습니다.\n";
        }else if (correctAnswer[0]==0){
            totalText+="수포자 1은 모든 문제를 틀렸습니다.\n";
        }
        if (questionList.size()==correctAnswer[1]){
            totalText+="수포자 2은 모든 문제("+correctAnswer[1]+"개)를 맞혔습니다.\n";
        }else if (correctAnswer[1]==0){
            totalText+="수포자 2은 모든 문제를 틀렸습니다.\n";
        }
        if (questionList.size()==correctAnswer[2]){
            totalText+="수포자 3은 모든 문제("+correctAnswer[2]+"개)를 맞혔습니다.\n";
        }else if (correctAnswer[2]==0){
            totalText+="수포자 3은 모든 문제를 틀렸습니다.\n";
        }

        // 뒤에 "따라서 가장 문제를 많이 맞힌 사람은 수포자 OO입니다."추가해야함.

        //1등 찾기
//        int no1=( supoja01correctAnswer>supoja02correctAnswer )&&( supoja01correctAnswer>supoja03correctAnswer ) ? 1 : 0;
//        int no2=( supoja02correctAnswer>supoja01correctAnswer )&&( supoja02correctAnswer>supoja03correctAnswer ) ? 1 : 0;
//        int no3=( supoja03correctAnswer>supoja02correctAnswer )&&( supoja03correctAnswer>supoja01correctAnswer ) ? 1 : 0;
//        String equals1=(supoja01correctAnswer==supoja02correctAnswer)?"따라서 가장 많은 문제를 맞힌 사람은 수포자1과 수포자2 입니다.":null;
//        String equals2=(supoja02correctAnswer==supoja03correctAnswer)?"따라서 가장 많은 문제를 맞힌 사람은 수포자2와 수포자3 입니다.":null;
//        String equals3=(supoja01correctAnswer==supoja03correctAnswer)?"따라서 가장 많은 문제를 맞힌 사람은 수포자1과 수포자3 입니다.":null;

        int no1=( correctAnswer[0]>correctAnswer[1] )&&( correctAnswer[0]>correctAnswer[2] ) ? 1 : 0;
        int no2=( correctAnswer[1]>correctAnswer[0] )&&( correctAnswer[1]>correctAnswer[2] ) ? 1 : 0;
        int no3=( correctAnswer[2]>correctAnswer[1] )&&( correctAnswer[2]>correctAnswer[0] ) ? 1 : 0;
        String equals1=(correctAnswer[0]==correctAnswer[1])?"따라서 가장 많은 문제를 맞힌 사람은 수포자1과 수포자2 입니다.":null;
        String equals2=(correctAnswer[1]==correctAnswer[2])?"따라서 가장 많은 문제를 맞힌 사람은 수포자2와 수포자3 입니다.":null;
        String equals3=(correctAnswer[0]==correctAnswer[2])?"따라서 가장 많은 문제를 맞힌 사람은 수포자1과 수포자3 입니다.":null;

        if (no1==1){totalText+="따라서 가장 많은 문제를 맞힌 사람은 수포자1 입니다.";
        }else if (no2==1){totalText+="따라서 가장 많은 문제를 맞힌 사람은 수포자2 입니다.";
        }else if (no3==1){totalText+="따라서 가장 많은 문제를 맞힌 사람은 수포자3 입니다.";
        }else if (equals1!=null){totalText+=equals1;
        }else if (equals2!=null) {totalText+=equals2;
        }else if (equals3!=null){totalText+=equals3;
        }


        // 3항연산자로 모두 같은 개수의 정답을 맞추었는지 확인 후 totalText에 저장
        totalText+=(correctAnswer[0]==correctAnswer[1])&&(correctAnswer[0]==correctAnswer[2]) ? "모든 사람이 "+correctAnswer[0]+"문제씩을 맞췄습니다.\n" : "";

        String questionListTest=questionList.toString();

        answer.setText("answers : "+questionListTest+"\n\n"+"\n\n\n"+"수포자1 : "+correctAnswer[0]+"개\n"+"수포자2 : "+correctAnswer[1]+"개\n"+"수포자3 : "+correctAnswer[2]+"개\n문제수 : "+questionList.size()+"개\n\n"+totalText);// 수포자들의 정답 수 + 문제의 수 + 입출력 예 1,2

        supoja01.clear();
        supoja02.clear();
        supoja03.clear();
    }

    public void stu01_set(int question, List<Integer> questionList){
        for (int i=0;i<question;i++){// 설정된 문제의 수만큼 반복
            questionList.add(random.nextInt(5)+1);// 정답인 숫자 qustionList에 add하기
            for (int s1=1;s1<6;s1++) supoja01.add(s1);
            for (int s2=0;s2<7;s2++){
                switch (s2){
                    case 0: supoja02.add(2);break;
                    case 1: supoja02.add(1);break;
                    case 2: supoja02.add(2);break;
                    case 3: supoja02.add(3);break;
                    case 4: supoja02.add(2);break;
                    case 5: supoja02.add(4);break;
                    case 6: supoja02.add(2);break;
                    case 7: supoja02.add(5);break;
                }
            }
            for (int s3=0;s3<5;s3++){
                switch (s3){
                    case 0: supoja03.add(3);supoja03.add(3);break;
                    case 1: supoja03.add(1);supoja03.add(1);break;
                    case 2: supoja03.add(2);supoja03.add(2);break;
                    case 3: supoja03.add(4);supoja03.add(4);break;
                    case 4: supoja03.add(5);supoja03.add(5);break;
                }
            }
        }// 문제의 정답 랜덤설정.
    }
}