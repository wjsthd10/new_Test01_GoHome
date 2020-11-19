package com.example.test_01.Day01;

public class Test_solution_str01 {
    String normalSum;
    String andSum;
    int sumNum;
    int getIndex;

    public Test_solution_str01(String normalSum, String andSum, int sumNum) {
        this.normalSum = normalSum;
        this.andSum = andSum;
        this.sumNum = sumNum;
    }

    public Test_solution_str01() {
    }

    public Test_solution_str01(String andSum, int getIndex) {
        this.andSum = andSum;
        this.getIndex = getIndex;
    }

    public Test_solution_str01(String normalSum, String andSum, int sumNum, int getIndex) {
        this.normalSum = normalSum;
        this.andSum = andSum;
        this.sumNum = sumNum;
        this.getIndex = getIndex;
    }
}
