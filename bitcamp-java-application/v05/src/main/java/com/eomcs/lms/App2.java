package com.eomcs.lms;

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

  public static java.sql.Date getDateValue(String message) {
    while (true) {
      try {
        System.out.println(message);
        return java.sql.Date.valueOf(keyScan.nextLine());

      } catch (IllegalArgumentException e) {
        System.out.println("2019-07-05 형식으로 입력해주세요");
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

    int no = getIntValue("번호? ");
    String name = getStringValue("이름? ");
    String email = getStringValue("이메일? ");
    int password = getIntValue("비밀번호? ");
    String picture = getStringValue("사진? ");
    String phone = getStringValue("전화번호? ");
    java.sql.Date signupDate = getDateValue("가입일 ?");

    System.out.println();

    System.out.println("번호: " + no);
    System.out.println("이름: " + name);
    System.out.println("이메일: " + email);
    System.out.println("암호: " + password);
    System.out.println("사진: " + picture);
    System.out.println("전화: " + phone);
    System.out.println("가입일: " + signupDate);
  }

}
