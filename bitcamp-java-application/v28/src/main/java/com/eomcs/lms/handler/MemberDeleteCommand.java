package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberDeleteCommand implements Command {
  private List<Member> list;
  private Input input;

  public MemberDeleteCommand(Input input, List<Member> member) {
    this.input = input;
    this.list = member;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    // 사용자가 입력한 번호를 가지고 목록에서 그 번호에 해당하는 Member 객체를 찾는다.
    for (int i = 0; i < list.size(); i++) {
      Member temp = list.get(i);
      if (temp.getNo() == no) {
        list.remove(i);
        System.out.println("데이터를 삭제하였습니다.");
        return;
      }
    }

    System.out.println("해당 번호의 데이터가 없습니다!");
  }

}
