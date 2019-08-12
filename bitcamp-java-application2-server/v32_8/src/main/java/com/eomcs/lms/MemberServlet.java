package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Member;



public class MemberServlet implements Servlet {


  ArrayList<Member> memberList = new ArrayList<>();



  ObjectInputStream in;
  ObjectOutputStream out;


  public MemberServlet(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  private void updateMember() throws Exception {

    Member member = (Member)in.readObject();

    int index = indexOfMember(member.getNo());
    if (index != -1) {
      fail("해당번호의 회원이 없습니다.");
      return;
    }
    memberList.set(index, member);
    out.writeUTF("ok");


  }



  private void detailMember() throws Exception{
    int no = in.readInt();
    int index = indexOfMember(no);
    if (index != -1) {
      fail("해당번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(memberList.get(index));

  }

  private void deleteMember() throws Exception {
    int no = in.readInt();

    int index = indexOfMember(no);
    if (index != -1) {
      fail("해당번호의 회원이 없습니다.");
      return;
    }
    memberList.remove(index);
    out.writeUTF("ok");

  }

  private void addMember() throws Exception {
    // 클라이언트가 보낸 객체를 읽는다.
    Member member = (Member)in.readObject();
    memberList.add(member);
    out.writeUTF("ok");
  }

  private void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존의 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberList);
  }

  private int indexOfMember(int no) {
    int i = 0;
    for (Member m : memberList) {
      if (m.getNo() == no) {
        return i ;
      }
      i++;
    }
    return -1;
  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/member/add":
        addMember();
        break;
      case "/member/list":
        listMember();
        break;

      case "/member/delete" :
        deleteMember();
        break; 

      case "/member/detail":
        detailMember();
        break;  

      case "/member/update":
        updateMember();
        break;  

      case "quit":
        out.writeUTF("ok");
        break;

      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }

}
