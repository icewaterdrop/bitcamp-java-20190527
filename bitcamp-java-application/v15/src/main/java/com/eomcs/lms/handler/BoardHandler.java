package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private Board[] boards = new Board[100];
  private int boardsSize = 0;
  
  public Input input;
  
  // BoardHandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // =>Input 객체 처럼 어떤 작업을 실행하기 위해 반드시 있어야하는 객체를
  //   "의존 객체(dependency)" 라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  //    다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.
  public BoardHandler(Input input) {
    this.input = input;
  }
  
  public void addBoard() {
    Board board = new Board();
    board.setNo(input.getIntValue("번호? "));
    board.setContents(input.getStringValue("내용? "));
    board.setCreatedDate(new Date(System.currentTimeMillis()));

    boards[boardsSize++] = board;
    System.out.println("저장하였습니다.");

  }

  public void listBoard() {
    for (int i = 0; i < boardsSize; i++) {
      Board board = boards[i];
      System.out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(), board.getCreatedDate(),
          board.getViewCount());
    }
  }




}
