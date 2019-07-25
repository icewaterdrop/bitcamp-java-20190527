package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardUpdateCommand implements Command{

  private List<Board> boardList; 
  private Input input;

  public BoardUpdateCommand (Input input, List<Board> list) {
    this.input = input;
    this.boardList = list;
  }

  @Override
  public void execute() {

    int no = input.getIntValue("번호? ");

    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    if (board == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }

    String str = input.getStringValue("내용(" + board.getContents() + ")");
    if (str.length() > 0) {
      board.setContents(str);
    }

  }


}
