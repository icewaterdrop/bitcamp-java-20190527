package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {

  static ObjectOutputStream out;
  static ObjectInputStream in;

  public static void main(String[] args) throws Exception {
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");

    try (Socket socket = new Socket("192.168.0.53", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음.");


      // 다른 메서드가 입출력 객체를 사용할 수 있도록 스태틱 변수에 저장한다.
      ServerTest.in = in;
      ServerTest.out = out;

      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");

      if (!add(member)) {
        error();
      }
      
      System.out.println("------------------");
     

      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("leem@test.com");
      member.setPhoto("leem.gif");
      member.setTel("1111-2222");

      if (!add(member)) {
        error();
      }
      
      System.out.println("------------------");
      if (!list()) {
        error();
      }
      System.out.println("------------------");

      if (!delete()) {
        error();
      }
      System.out.println("------------------");
      if (!list()) {
        error();
      }
      System.out.println("------------------");

      if (!detail()) {
        error();
      }
      System.out.println("------------------");
      
      member = new Member();
      member.setNo(1);
      member.setName("고길동");
      member.setEmail("hong2@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");
      
      if (!update(member)) {
        error();
      }
      System.out.println("------------------");
      
      if (!list()) {
        error();
      }
      System.out.println("------------------");
      
      if (!quit()) {
        error();
      }

    } catch (RequestException e) {
      // 서버에서 요청 처리에 실패했다면
      // 서버가 보낸 이유를 받는다.
      System.out.printf("오류: %s\n", in.readUTF());

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음.");
  }


  
  
  private static void error() throws Exception {
    System.out.printf("오류 : %s\n", in.readUTF());
  }

  private static boolean quit() throws Exception {
    out.writeUTF("quit");
    out.flush();
    System.out.print("quit 요청함 =>");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;

  }

  private static boolean delete() throws Exception {
    // 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/member/delete");
    out.writeInt(2);
    out.flush();
    System.out.print("delete 요청함 =>");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }
  
  private static boolean detail() throws Exception {
    // 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/member/detail");
    out.writeInt(1);
    out.flush();
    System.out.print("detail 요청함 =>");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    System.out.println(in.readObject());

    return true;
  }

  private static boolean update(Member m) throws Exception {
    out.writeUTF("/member/update");
    out.writeObject(m);
    out.flush();
    System.out.print("update 요청함 =>");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");
    return true;
  }

  private static boolean list() throws Exception {

    out.writeUTF("/member/list");
    out.flush();
    System.out.print("list 요청함 =>");

    if (!in.readUTF().equals("ok"))
      return false;

    System.out.println("처리 완료!");

    @SuppressWarnings("unchecked")
    List<Member> list = (List<Member>) in.readObject();
    System.out.println("------------------");
    for (Member m : list) {
      System.out.println(m);
    }
    return true;
  }
  
  private static boolean add(Member m) throws IOException, RequestException {
    
    out.writeUTF("/member/add");
    out.writeObject(m);
    out.flush();
    System.out.print("add 요청함 =>");
    
    if (!in.readUTF().equals("ok"))
      return false;
    
    System.out.println("처리 완료!");
    return true;
  }
}


