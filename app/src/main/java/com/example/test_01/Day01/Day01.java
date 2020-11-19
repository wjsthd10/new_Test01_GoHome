package com.example.test_01.Day01;

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
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Day01 extends AppCompatActivity {

//    11.16일 알고리즘 문제

    TextView answer;
    Stack<SumList> sumLists=new Stack<>();
    ArrayList<Test_solution_str01> solutionList=new ArrayList<>();
    ArrayList<Test_solution_str01> solutionList2=new ArrayList<>();
    ArrayList<Test_solution_str01> solutionTest01=new ArrayList<>();
    ArrayList<Test_solution_str01> solutionTest02=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day01);
        setTitle("01번 두 개 뽑아서 더하기");
        answer=findViewById(R.id.answer);
    }

    public void clickbtn(View view) {

        Random random = new Random();
        int randomNum = random.nextInt(100) + 2;
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < randomNum; i++) {
            numbers.add(random.nextInt(100));
        }
        Arrays.sort(numbers.toArray());
        Log.d("sortNumbers", Arrays.toString(numbers.toArray()));
        int[] numbers_ran=new int[numbers.size()];
        for (int i=0;i<numbers.size();i++){
            numbers_ran[i]=numbers.get(i);
        }
        Arrays.sort(numbers_ran);
        Log.d("sortNumbers", Arrays.toString(numbers_ran));         // 중복 지우지 않고 정렬한 random숫자배열

        int[] re_answer= solution(numbers_ran);// 중복 제거해서 합한 배열
        String[] re_answer_str=solution_str(numbers_ran);

        Log.d("re_answer", Arrays.toString(re_answer));
        Log.d("re_answer_str", Arrays.toString(re_answer_str));

//        \##############################################################################################################################################################

        String setSolutionList="";

        for (int i=0;i<solutionList.size();i++){
            Log.d("solutionList", solutionList.get(i).sumNum+" : "+solutionList.get(i).andSum+" : "+solutionList.get(i).normalSum);

            //조립
//            if (!solutionList.contains(solutionList2.get(i).normalSum)){
//                setSolutionList+=solutionList.get(i).sumNum+"="+solutionList.get(i).normalSum+" 입니다.\n";
//                Log.i("ghkrdls", "ghksad0101");
//            }else if (solutionList.contains(solutionList2.get(i).andSum)){
//                setSolutionList+=solutionList.get(i).sumNum+"="+solutionList.get(i).normalSum+solutionList2.get(i).andSum+" 입니다.\n";
//                Log.i("ghkrdls", "ghksad");
//            }
//            setSolutionList+=(solutionList2.get(i).andSum.equals(solutionList.get(i).normalSum)) ? solutionList.get(i).sumNum+"="+solutionList.get(i).normalSum+solutionList2.get(i).andSum+" 입니다.\n" : solutionList.get(i).sumNum+"="+solutionList.get(i).normalSum+" 입니다.\n";
        }
        Log.d("setSolutionList", setSolutionList);

//        \##############################################################################################################################################################

//        \##############################################################################################################################################################

        Set<Integer> nums = new HashSet<>();
        Set<String> nums1 = new HashSet<>();
        Set<String> nums2 = new HashSet<>();
//        List<String> sumList=new ArrayList<String>();

        for (int i = 0; i < numbers.size(); i++) {
            Log.d("nums01", numbers.toString());
            for (int j = i + 1; j < numbers.size(); j++) {
                nums.add(numbers.get(j) + numbers.get(i));
                nums1.add(numbers.get(i) + "+" + numbers.get(j));
                nums2.add(numbers.get(j) + "+" + numbers.get(i));
                //여기서 sumLists를 add하지 않아보기
                sumLists.add(new SumList(numbers.get(j) + numbers.get(i), numbers.get(j) + "+" + numbers.get(i)));
            }
        }


        List<Integer> numList = new ArrayList<>(nums);
        List<String> numList1 = new ArrayList<>(nums1);
        List<String> numList2 = new ArrayList<>(nums2);

        String str = "";
        int nSumNum = -1;// 처음 수식 찍고 다음 수식과 비교할때 사용하는 기준 int

        Collections.sort(sumLists);

        for (int i = 0; i < sumLists.size(); i++) {
            if (nSumNum != -1) {// 처음에는 -1이기때문에 else문으로 나감
                // todo nSumNum과 sumLists.get(i).getSum()이 같을때의 조건문이 들어가지지 않음....
                if (nSumNum == sumLists.get(i).getSum() ) {// 다음숫자 나왔을때 출력
//                    Toast.makeText(this, nSumNum+" : "+sumLists.get(i).getSum(), Toast.LENGTH_SHORT).show();
//                    str += " = "+sumLists.get(i).getQuestion()+"입니다\n";
//                    str += sumLists.get(i).getQuestion();
                    //같은 값을 가진 다른 수식 입력해서 str에 += 하기
                } else {//

                    str += sumLists.get(i).getSum() + " = " + sumLists.get(i).getQuestion();
//
                    if (nSumNum==sumLists.get(i).getSum()){
                        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
                        str += " = "+sumLists.get(i).getQuestion();
                    }
                    str += " 입니다\n";

                }
            } else {// nSumNum이 -1이기 때문에 첫번째 str출력
                Toast.makeText(this, "1번", Toast.LENGTH_SHORT).show();
                str += sumLists.get(i).getSum() + " = " + sumLists.get(i).getQuestion();
                str += " 입니다\n";
            }
            nSumNum = sumLists.get(i).getSum();// 중복확인
        }// 반복문

//        str += " 입니다\n";// 맨 마지막에 "입니다\n"

//        출력되는 문자열(정수 2개를 더한 값들)
        String num = Arrays.toString(numList.toArray());

//        출력문
        answer.setText(numbers + "\n\n\n" + str + "\n" + "따라서 " + num + " 를 return해야 합니다.");
        sumLists.clear();// 데이터 출력 후 데이터 삭제
        solutionList.clear();

    }

    public int[] solution(int[] numbers){
        int[] answer={};
        ArrayList<Integer> arr=new ArrayList<>();
        for (int i=0; i<numbers.length-1; i++){
            for (int j=i+1; j<numbers.length; j++){
                if (!arr.contains(numbers[i]+numbers[j])){
                    arr.add(numbers[i]+numbers[j]);// contains에서 포함되어있는지 확인해버림
                }
            }
        }
        Collections.sort(arr);
        answer=new int[arr.size()];
        for (int i=0;i<arr.size();i++){
            answer[i]=arr.get(i);
        }

        return answer;
    }

    public String[] solution_str(int[] numbers){
        int[] answer={};
        ArrayList<Integer> arr=new ArrayList<>();

        String[] solution_str={};
        String[] solution_str2={};
        ArrayList<String> arr_str=new ArrayList<>();
        for (int i=0; i<numbers.length-1;i++){
            for (int j=i+1; j<numbers.length;j++){
                if (!arr.contains(numbers[i]+numbers[j])){
                    arr.add(numbers[i]+numbers[j]);
                    arr_str.add(numbers[i]+"+"+numbers[j]);

                    // solutionList를 add하는 곳
                    Log.i("TestAddNums", numbers[i]+"+"+numbers[j]);
                    solutionList.add(new Test_solution_str01(numbers[i]+"+"+numbers[j],"",numbers[i]+numbers[j]));
                    solutionTest01.add(new Test_solution_str01(numbers[i]+"+"+numbers[j], i));
                    Log.i("TestAdd", solutionTest01.get(i).andSum);


                }else if (arr.contains(numbers[i]+numbers[j])){
                    if (!arr_str.contains(numbers[i]+"+"+numbers[j]) && !arr_str.contains("="+numbers[i]+"+"+numbers[j])){
                        arr_str.add("="+numbers[i]+"+"+numbers[j]);
                        // 배열에 넣고 배열의 값을 변경해야한다.
                        Log.i("TestAddNums0202", numbers[i]+"+"+numbers[j]);
                        solutionList2.add(new Test_solution_str01("",numbers[i]+"+"+numbers[j],numbers[i]+numbers[j]));
                        solutionTest02.add(new Test_solution_str01(numbers[i]+"+"+numbers[j], i));
//                        Log.i("TestAdd02", solutionTest02.get(i).andSum);
                    }
                }
            }
        }

        solution_str=new String[arr_str.size()];
        for (int i=0; i<arr_str.size();i++){
            solution_str[i]=arr_str.get(i);
        }
        return solution_str;
    }

}
