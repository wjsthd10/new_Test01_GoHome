package com.example.test_01.Day01;

public class SumList implements Comparable<SumList>{

    private int sum;
    private String question;
//    생성자
    public SumList(int sum, String question) {
        this.sum = sum;
        this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }
    public int getSum(){
        return this.sum;
    }



    @Override
    public int compareTo(SumList o) {

//        객체 오름차순, 내림차순 설정하는곳

        if (this.sum < o.sum) {
            return -1;
        } else if (this.sum == o.sum) {
            if (this.question == o.question) {
                return 0;
            } else {
                return -1;
            }
        }else return 1;
    }
}
