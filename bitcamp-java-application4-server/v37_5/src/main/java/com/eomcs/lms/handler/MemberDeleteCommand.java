package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberDeleteCommand implements Command {
  private MemberDao memberDao;

  public MemberDeleteCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");
      if(memberDao.delete(no) > 0) {
        out.println("데이터를 삭제하였습니다.");
      } else {
        out.println(" 해당 데이터가 없습니다.");
      }
    } catch (Exception e) {
      out.println("회원목록 저장에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }
}