package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberDeleteCommand implements Command {
  private MemberDao memberDao;
  private Input input;
  
  public MemberDeleteCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    try {
      memberDao.delete(no);
      System.out.println("데이터를 삭제하였습니다.");
    } catch (Exception e) {
      System.out.println("회원목록 저장에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }
}
