package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberListCommand implements Command {
  private List<Member> list;
  private Input input;

  public MemberListCommand(Input input, List<Member> member) {
    this.input = input;
    this.list = member;
  }

  @Override
  public void execute() {
    Member[] members = list.toArray(new Member[] {});
    for (Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
          member.getTel(), member.getRegisteredDate());
    }
  }

}
