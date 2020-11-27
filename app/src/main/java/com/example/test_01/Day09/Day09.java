package com.example.test_01.Day09;

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
import java.util.TreeSet;

public class Day09 extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day09);
        setTitle("09번 소수 찾기2");
        textView=findViewById(R.id.answer);
    }

    public void clickbtn(View view) {

        Random random=new Random();

        int ranNum=random.nextInt(6)+1;
        String[] numbers=new String[ranNum];
        for (int i=0;i<ranNum;i++){
            numbers[i]=String.valueOf(random.nextInt(9));
        }
        Log.i("chekstr", Arrays.toString(numbers));

        ArrayList<String> chekStr=new ArrayList<>();
        ArrayList<String> chekStr2=new ArrayList<>();
        StringBuffer buffer=new StringBuffer();
        for (int i=0;i<ranNum;i++){
            if (i<ranNum-1 && ranNum>=1){
                chekStr.add(numbers[i]+numbers[i+1]);
                chekStr2.add(numbers[i]+numbers[(ranNum-1)-i]);
            }else {
                chekStr.add(numbers[i]);
            }
        }
        for (int i=0;i<chekStr2.size();i++){
            chekStr.add(chekStr2.get(i));
        }

        for (int i=0; i<numbers.length;i++){
//            Toast.makeText(this, "확인1", Toast.LENGTH_SHORT).show();
            if (chekStr.contains(numbers[i])){
                chekStr.add(numbers[i]);
                Toast.makeText(this, "확인2", Toast.LENGTH_SHORT).show();
            }
        }

        TreeSet<String> arr01 =new TreeSet<>(chekStr);
        chekStr=new ArrayList<>(arr01);

        Arrays.sort(chekStr.toArray());

        int[] nums=new int[chekStr.size()+chekStr.size()];
        for (int i=0; i<chekStr.size();i++){
            nums[i]=Integer.parseInt(chekStr.get(i));
        }// nums에 중복 제거

        for (int i=chekStr.size(); i<nums.length; i++ ){
            int num=0;
            nums[i]=Integer.parseInt(numbers[num]);
            num++;
        }
        Arrays.sort(nums);

//        int[] answerNum=new int[nums.length];
        chekStr2.clear();
        for (int i=0;i<nums.length;i++){

            if (nums[i]%2!=0 && nums[i]%3!=0 && nums[i]%5!=0 &&nums[i]%7!=0){
                chekStr2.add(String.valueOf(nums[i]));
//                Log.i("Data_Num", num_arr[i]+"");
            }else if (nums[i]==2 | nums[i]==3 | nums[i]==5 | nums[i]==7){
                chekStr2.add(String.valueOf(nums[i]));
            }

        }


        Log.i("chekstr", Arrays.toString(chekStr2.toArray()));
        Log.i("chekstr", Arrays.toString(nums));

        textView.setText(Arrays.toString(numbers)+" : 소수는 "+chekStr2.size()+"개");

    }
}