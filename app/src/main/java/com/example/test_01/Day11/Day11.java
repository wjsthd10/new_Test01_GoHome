package com.example.test_01.Day11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Day11 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day11);
        setTitle("11번 호텔 방 배정");
        textView=findViewById(R.id.answer);
    }

    public void clickbtn(View view) {
        Random random=new Random();
        int k=random.nextInt(50)+1;// 전체 방의 개수
        int[] room_number=new int[random.nextInt(199)+1];// 손님이 원하는 방번호


        for (int i=0;i<room_number.length;i++){
            room_number[i]=random.nextInt(k-1)+1;
        }
        Log.i("romm_number", k+" : "+Arrays.toString(room_number));
        int maxRoom=0;// 반복문돌릴 숫자
        if (k>room_number.length){
            maxRoom=room_number.length;
        }else if (k<=room_number.length){
            maxRoom=k;
        }


//        int[] set_room=new int[k];
        ArrayList<Integer> set_room=new ArrayList<>();
        for (int i=0;i<=maxRoom;i++){
            int iNum=set_room.indexOf(room_number[i]);
            if (iNum==-1){// 같은게 없을때만 들어가짐
                set_room.add(room_number[i]);
            }else if (set_room.get(iNum)==room_number[i]){// 같은 숫자일때
                for (int l=1;l<room_number[i];l++){// 1부터 배열의 마지막까지 확인
                    if (set_room.indexOf(l)==-1){// 같은게 없으면 l번째 숫자 넣음
                        set_room.add(l);
                        break;
                    }
                }
            }
        }

        if (set_room.size() < maxRoom){// 다안들어갔을때
            Toast.makeText(this, "if문", Toast.LENGTH_SHORT).show();
            for (int i=1; i<maxRoom;i++){
                Toast.makeText(this, i+"", Toast.LENGTH_SHORT).show();
                if (set_room.indexOf(i)==-1){
                    set_room.add(i);
                }
            }
        }

        //k값 room_number값 set_room값
        Log.i("datasShow", maxRoom+" : "+set_room.size()+" : "+Arrays.toString(set_room.toArray()));

        Log.i("set_room", Arrays.toString(set_room.toArray()));
        String answer="k값 : "+(maxRoom-1)+"  \nroom_number : "+Arrays.toString(room_number)+"  \nanswer : "+Arrays.toString(set_room.toArray());

        textView.setText(answer);


    }
}