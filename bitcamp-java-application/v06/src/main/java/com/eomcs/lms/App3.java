package com.eomcs.lms;

import java.util.Scanner;

public class App3 {

  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);



    int no = getIntValue("번호? ");

    String contents = keyScan.nextLine();

    System.out.println();

    System.out.println("번호: " + no);
    System.out.println("내용: " + contents);
    System.out.println("작성일: 2019-0101");
    System.out.println("조회수: 0");
  }

  public static int getIntValue(String message) {
    while (true) {
      try {
      System.out.println(message);
      return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요");
        
      }
    }
  }
  
 
}
