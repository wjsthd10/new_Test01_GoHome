package com.example.test_01.Day10;

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
import java.util.HashMap;
import java.util.Random;

public class Day10 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day10);
        textView=findViewById(R.id.answer);
        setTitle("10번 베스트 앨범");

    }

    public void clickbtn(View view) {
        Random random=new Random();
        String[] genres={"classic", "pop", "ballade", "jazz", "CCM", "hip hop", "trot"};
        int[] plays=new int[genres.length];

        HashMap<String, Integer> answer=new HashMap<>();

        for (int i=0;i<genres.length;i++){
            int ranNum=random.nextInt(genres.length-1);
            switch (ranNum){
                case 0:answer.put("classic", random.nextInt(9999)+1);break;
                case 1:answer.put("pop", random.nextInt(9999)+1);break;
                case 2:answer.put("ballade", random.nextInt(9999)+1);break;
                case 3:answer.put("jazz", random.nextInt(9999)+1);break;
                case 4:answer.put("CCM", random.nextInt(9999)+1);break;
                case 5:answer.put("hip hop", random.nextInt(9999)+1);break;
                case 6:answer.put("trot", random.nextInt(9999)+1);break;
            }
        }



        Log.i("test", answer.keySet().toString());
        Log.i("test", answer.values().toString());
        Log.i("test", answer.get("classic")+"");
        Log.i("test", answer.size()+"");


        int[] numsSize=new int[7];
        
        if (answer.get("classic")!=null){
            numsSize[0]=answer.get("classic");
        }
        if (answer.get("pop")!=null){
            numsSize[1]=answer.get("pop");
        }
        if (answer.get("ballade")!=null){
            numsSize[2]=answer.get("ballade");
        }
        if (answer.get("jazz")!=null){
            numsSize[3]=answer.get("jazz");
        }
        if (answer.get("CCM")!=null){
            numsSize[4]=answer.get("CCM");
        }
        if (answer.get("hip hop")!=null){
            numsSize[5]=answer.get("hip hop");
        }
        if (answer.get("trot")!=null){
            numsSize[6]=answer.get("trot");
        }

        ArrayList<Integer> arrayList=new ArrayList<>();
        for (int i=0; i<numsSize.length;i++){
            if (numsSize[i]!=0){
                arrayList.add(numsSize[i]);
            }

        }
        Arrays.sort(new ArrayList[]{arrayList}, Collections.reverseOrder());
        Log.i("test", Arrays.toString(numsSize));
        Log.i("test", Arrays.toString(arrayList.toArray()));

        Integer[] arr=new Integer[numsSize.length];
        for (int i=0;i<numsSize.length;i++){
            arr[i]=numsSize[i];
        }

        Arrays.sort(arr,Collections.reverseOrder());

        Log.i("arr", Arrays.toString(arr));


        for (int i=0; i<numsSize.length;i++){

        }



    }
}