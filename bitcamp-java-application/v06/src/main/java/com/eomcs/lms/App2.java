package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  static Scanner keyScan;

  private static int getIntValue(String message) {

    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력해주세요");
      }
    }
  }

  public static Date getDateValue(String message) {
    while (true) {

      try {
        System.out.println(message);
        return Date.valueOf(keyScan.nextLine());

      } catch (IllegalArgumentException e) {
        System.out.print("2019-07-05 형식으로 입력해주세요");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    int[] password = new int[100];
    String[] picture = new String[100];
    String[] phone = new String[100];
    Date[] signupDate = new Date[100];

    int i = 0;

    for (; i < no.length; i++) {
      no[i] = getIntValue("번호? ");
      name[i] = getStringValue("이름? ");
      email[i] = getStringValue("이메일? ");
      password[i] = getIntValue("비밀번호? ");
      picture[i] = getStringValue("사진? ");
      phone[i] = getStringValue("전화번호? ");
      signupDate[i] = getDateValue("가입일 ?");

      System.out.print("계속 입력하시겠습니까?(Y/n)");
      String response = keyScan.nextLine();

      if (response.equals("n"))
        break;
    }

    System.out.println();

    for(int i2 = 0 ; i2 <=i ; i2++) {
      System.out.printf("%s, %s, %s, %s\n" , name[i2], email[i2],
          phone[i2], signupDate[i2]);
    }
    
  }

}
