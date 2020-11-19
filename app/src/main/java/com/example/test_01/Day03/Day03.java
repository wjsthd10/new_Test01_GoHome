package com.example.test_01.Day03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Day03 extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day03);
        textView=findViewById(R.id.answer);
        setTitle("03번 K번째수");


    }

    public void clickbtn(View view) {

        Random random=new Random();
        int randomNum=random.nextInt(100)+1;// 반복문 길이 + 각 원소지정용
        int commandsNum=random.nextInt(50)+1;// 생성자로 줄때 배열로 3개 줘야함.
        ArrayList<Commands> arrayList=new ArrayList<>();
        int[][] commands_Val=new int[commandsNum][3];

//        int[] array=new int[randomNum];// 선별할 값들을 배열에 배치함

        ArrayList<Integer> array01=new ArrayList<>();

        ArrayList<Commands> commandsList=new ArrayList<>();

        for (int i=0;i<randomNum;i++){
            //array에 넣을 값 지정하기기
//            array[i]=random.nextInt(100)+1;//배치함.
            array01.add(random.nextInt(100)+1);
        }
//        Log.e("arrayVal", Arrays.toString(array));
        // Commands에 있는 int[]에 commandsNum의 수만큼 int[3]을 넣어준다.
        for (int i=0;i<commandsNum;i++){
            int[] ijkVal=new int[3];
            ijkVal[0]=random.nextInt(randomNum)+1;// int[]중에 0번째에 들어갈 번호 랜덤지정                               Log.i("ijkval0", i+":"+ijkVal[0]);            Log.e("randomNumVal", randomNum+"");
            commands_Val[i][0]=ijkVal[0];
            int subtraction1=randomNum-(ijkVal[0]-1);//                                                                 Log.e("subtraction1", subtraction1+"");

            ijkVal[1]=random.nextInt(subtraction1)+ijkVal[0];// 전체숫자보다 큰값이 나옴// 양수여야함                     Log.i("ijkval1", i+":"+ijkVal[1]);
            commands_Val[i][1]=ijkVal[1];

            // [2] 부분은 [0]과[1]의 차이만큼만 숫자로 지정해야함
            int subtraction3=ijkVal[1]-(ijkVal[0]-1);//                                                                 Log.e("subtraction3", subtraction3+"");

            int randomNumIJK=random.nextInt(subtraction3)+1;// int[]중에 2번째에 들어갈 번호 임시 지정
            if (randomNumIJK<ijkVal[1]) {
                ijkVal[2]=randomNumIJK;         //                                                                      Log.i("ijkval2", i+":"+ijkVal[2]);
                commands_Val[i][2]=randomNumIJK;
            }else if (randomNumIJK>ijkVal[1]){
                i--;
                return ;// 돌아가서 다시 함
            }else if (randomNumIJK==ijkVal[1]){
                ijkVal[2]=randomNumIJK;
                commands_Val[i][2]=randomNumIJK;
            }// if else문 끝
            commandsList.add(new Commands(ijkVal));

        }// 랜덤으로 지정될 숫자들 지정 for문끝
        int[] arrays=new int[array01.size()];// int[]에 숫자 렌덤 지정
        for (int i=0;i<array01.size(); i++){
            arrays[i]=array01.get(i);
        }


        int[] answer=solution(arrays, commands_Val);// 랜덤숫자 + 분류기준 숫자 파라미터로 보내고 결과 값 리턴받기   // 원하는 번째의 숫자 갖고있음

        Log.d("answer", Arrays.toString(arrays));
        String arrays_str=Arrays.toString(arrays);

        String commands_val_str="";//
        for (int i=0;i<commands_Val.length;i++){
            Log.d("answer", Arrays.toString(commands_Val[i]));
            commands_val_str+=Arrays.toString(commands_Val[i])+", ";
            // 첫번째 숫자랑 두번째 숫자 따로 빼기
        }

        Log.d("answer", Arrays.toString(answer));
//        todo (*삽질구간*) 2중 배열 인덱스 번호로 쓸때와 그냥 정수로 사용할때 잘 구분해야함.... 인덱스 번호로 사용할때 -1안해주면 바로 에러나옴.........!!!!!!!
        Log.d("인덱스 번호로 사용할때 -1해라.", (commands_Val[1][0]-1)+"");// 인덱스 번호로 사용할때는 -1해줘야함
        Log.d("그냥 값으로 사용할때는 -1안한다.", (commands_Val[1][0])+"");// 인덱스 번호로 사용하지 않고 그냥 정수로 사용할때는 그냥 사용
        String answer_str=Arrays.toString(answer);

//        출력할 문자열 만들기
        String str_answer01="Array : "+arrays_str+"\n\n"+"Commands : "+commands_val_str+"\n\n"+"Return : "+answer_str+"\n\n\n";

        String str_answer02="";
        // 입출력 예에 맞게 출력될 String에 반복문으로 +=으로 문자열 작성.
        for (int i=0;i<commands_Val.length;i++){
            str_answer02+=arrays_str+"를 \n"+commands_Val[i][0]+"번째부터 "+commands_Val[i][1]+"번째까지 자른 후 정렬합니다. \n"+Arrays.toString(commands_Val[i])+"의 "+commands_Val[i][2]+"번째 숫자는 "+answer[i]+"입니다.\n\n";
        }

        // 출력
        textView.setText(str_answer01+str_answer02);




    }// click btn 끝

    // 렌덤한 정수를 갖고 있는 배열을 자를 첫번째 숫자, 마지막 숫자와 자르고 정렬한 배열에서 원하는 위치의 숫자를 찾을 숫자를 가지고 찾은
    // 원하는 숫자를 순서대로 담은 배열을 리턴해준다.
    public int[] solution(int[] array, int[][] commands){// 1번파라미터 int[]로 랜덤으로 배치된 정수의 배열을 받아온다, 2번 파라미터 2중 int배열로 원하는 값을 분류하는 배열을 받아온다.
        int[] answer=new int[commands.length];// 원하는 숫자가 담길 배열
        for (int i=0; i<commands.length;i++){// 분류를 진행할 횟수 만큼의 반복문
            List<Integer> list =new ArrayList<>();// arraylist에 받아온 배열을 start번째부터 end번째 까지 잘라서 보관할 임시 배열 생성

            int start = commands[i][0]-1;//첫번째 자리 인덱스 번호
            int end = commands[i][1]-1;// 마지막자리 인덱스 번호
            int pick = commands[i][2]-1;// 고를 숫자 번째의 인덱스 번호
            // 인덱스 번호로 사용할때는 찾은 정수에 -1 해야한다.

            for (int j=start; j<=end; j++){// start번째 부터 시작하여 end번째 까지 반복하는 반복문
                list.add(array[j]);// 임시 변수 list에 파라미터로 받아온 배열을 잘라서 삽입
            }

            Collections.sort(list);// 정렬
            answer[i]= list.get(pick);// list에 담겨있는 배열에서 원하는 번째(pick)의 정수를 answer의 [i]번째 배열에 순서대로 넣어서 리턴해준다.
        }
        return answer;
    }



}// class 끝