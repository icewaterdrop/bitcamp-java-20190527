package com.eomcs.lms.util;

import java.sql.Date;
import java.util.Scanner;

public class Input {

  public static Scanner keyScan;


  public static int getIntValue(String message) {
    int value = 0;

    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.print("숫자를 입력하세요.");
      }
    }
  }

  public static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.print("2019-07-05 형식으로 입력하세요. ");
      }
    }
  }

  public static String getStringValue(String message) {
    System.out.println(message);
    return keyScan.nextLine();
  }

}
