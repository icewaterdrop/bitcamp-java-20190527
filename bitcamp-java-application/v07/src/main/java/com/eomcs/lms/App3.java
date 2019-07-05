package com.eomcs.lms;

import java.util.Scanner;

public class App3 {

  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    Board[] boards = new Board[100];
    
    int i = 0;

    for (; i < boards.length; i++) {
      Board board = new Board();
      board.no = getIntValue("번호? ");
      board.contents = getStringValue("내용? ");
      
      boards[i] = board;
      
      System.out.println("계속 입력하시겠습니까? (Y/n)");
      String response = keyScan.nextLine();

      if (response.equals("n"))
        break;
    }

    System.out.println();

    
    for (int i2 = 0; i2 <= i; i2++) {
      Board board = boards[i2];
      System.out.printf("%s, %s\n", board.no, board.contents);
      System.out.println("작성일: 2019-01-01");
      System.out.println("조회수: 0");
    }
  }


  public static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요");

      }
    }
  }

  public static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

}
