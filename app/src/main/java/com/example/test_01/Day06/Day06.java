package com.example.test_01.Day06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Day06 extends AppCompatActivity {

    TextView textView;
    EditText editText;

    // reomve commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day06);
        textView=findViewById(R.id.answer);
        editText=findViewById(R.id.edit_query);
        setTitle("06번 소수 찾기");
    }

    public void clickbtn(View view) {

        showSolution();
        hidekeyboard(editText);
    }

    public void showSolution(){
        // push
        if (editText.getText().toString().equals("")){
            Toast.makeText(this, "숫자 입력하지 않음", Toast.LENGTH_SHORT).show();


        }else{
//            Toast.makeText(this, "숫자 입력좀", Toast.LENGTH_SHORT).show();
            String getEdit=editText.getText().toString();
            int editNum=Integer.parseInt(getEdit);
            int[] editNums=new int[editNum];

            for (int i=0;i<editNum;i++){
                editNums[i]=i+1;
            }

            int[] primeNum_edit=new int[editNums.length];
            ArrayList<Integer> nums_edit=new ArrayList<>();
            for (int i=1;i<editNums.length;i++){
                Log.i("Show_Data", "2나누기"+editNums[i]%2+" : "+editNums[i]+" : "+getEdit);
//            Log.i("Show_Data", "3나누기"+num_arr[i]%3+" : "+num_arr[i]+" : "+nNum);
//            Log.i("Show_Data", "5나누기"+num_arr[i]%5+" : "+num_arr[i]+" : "+nNum);

                if (editNums[i]%2!=0 && editNums[i]%3!=0 && editNums[i]%5!=0 &&editNums[i]%7!=0){
                    primeNum_edit[i]=editNums[i];
//                Log.i("Data_Num", num_arr[i]+"");
                }else if (editNums[i]==2 | editNums[i]==3 | editNums[i]==5 | editNums[i]==7){
                    primeNum_edit[i]=editNums[i];
                }
                if (primeNum_edit[i]!=0){
                    nums_edit.add(primeNum_edit[i]);
                }
            }

            textView.setText("1부터 "+editNum+" 사이의 소수는 "+Arrays.toString(nums_edit.toArray())+" "+nums_edit.size()+"개가 존재하므로 "+nums_edit.size()+"를 반환");
        }
        editText.setText("");
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    hidekeyboard(editText);
                    return true;
                }

                return false;
            }
        });
    }

    // 동작 후 키패드 숨김
    public void hidekeyboard(EditText et){
        InputMethodManager imm=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(),0);
    }
}





//        Random random=new Random();
//        int nNum=random.nextInt(1000-2)+2;
//        int[] num_arr=new int[nNum];// randomNums
//
//        int getNum=Integer.parseInt(editText.getText().toString());
//        int[] editNum=new int[getNum];
//
//        // 랜덤수만큼 기입
////        for (int i=0;i<nNum;i++){
////            num_arr[i]=i+1;
////        }
//
//        for (int i=0;i<getNum;i++){
//            editNum[i]=i+1;
//        }
//
//        //소수 배열
//        int[] primeNum=new int[num_arr.length];
//        ArrayList<Integer> nums=new ArrayList<>();
//        for (int i=1;i<num_arr.length;i++){
//            Log.i("Show_Data", "2나누기"+num_arr[i]%2+" : "+num_arr[i]+" : "+nNum);
////            Log.i("Show_Data", "3나누기"+num_arr[i]%3+" : "+num_arr[i]+" : "+nNum);
////            Log.i("Show_Data", "5나누기"+num_arr[i]%5+" : "+num_arr[i]+" : "+nNum);
//
//            if (num_arr[i]%2!=0 && num_arr[i]%3!=0 && num_arr[i]%5!=0 &&num_arr[i]%7!=0){
//                primeNum[i]=num_arr[i];
////                Log.i("Data_Num", num_arr[i]+"");
//            }else if (num_arr[i]==2 | num_arr[i]==3 | num_arr[i]==5 | num_arr[i]==7){
//                primeNum[i]=num_arr[i];
//            }
//            if (primeNum[i]!=0){
//                nums.add(primeNum[i]);
//            }
//        }
//
//        int[] primeNum_edit=new int[num_arr.length];
//        ArrayList<Integer> nums_edit=new ArrayList<>();
//        for (int i=1;i<editNum.length;i++){
//            Log.i("Show_Data", "2나누기"+num_arr[i]%2+" : "+num_arr[i]+" : "+nNum);
////            Log.i("Show_Data", "3나누기"+num_arr[i]%3+" : "+num_arr[i]+" : "+nNum);
////            Log.i("Show_Data", "5나누기"+num_arr[i]%5+" : "+num_arr[i]+" : "+nNum);
//
//            if (editNum[i]%2!=0 && editNum[i]%3!=0 && editNum[i]%5!=0 &&editNum[i]%7!=0){
//                primeNum_edit[i]=editNum[i];
////                Log.i("Data_Num", num_arr[i]+"");
//            }else if (editNum[i]==2 | editNum[i]==3 | editNum[i]==5 | editNum[i]==7){
//                primeNum_edit[i]=editNum[i];
//            }
//            if (primeNum_edit[i]!=0){
//                nums_edit.add(primeNum_edit[i]);
//            }
//        }
//
//        Log.i("Array", Arrays.toString(primeNum));// 0까지 나옴
//        Log.i("Array", Arrays.toString(nums.toArray()));

//        textView.setText("1부터 "+nNum+" 사이의 소수는 "+Arrays.toString(nums.toArray())+" "+nums.size()+"개가 존재하므로 "+nums.size()+"를 반환");
//        textView.setText("1부터 "+getNum+" 사이의 소수는 "+Arrays.toString(nums_edit.toArray())+" "+nums_edit.size()+"개가 존재하므로 "+nums_edit.size()+"를 반환");