// v32_10: 파일 저장기능 추가
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.servlet.BoardServlet;
import com.eomcs.lms.servlet.LessonServlet;
import com.eomcs.lms.servlet.MemberServlet;

public class ServerApp {

  static ArrayList<Member> memberList = new ArrayList<>();



  static ObjectInputStream in;
  static ObjectOutputStream out;




  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");


    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작!");

      try (Socket clientSocket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

        System.out.println("클라이언트와 연결되었음.");

        BoardServlet boardServlet = new BoardServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);
        MemberServlet memberServlet = new MemberServlet(in, out);


        while (true) {
          String command = in.readUTF();
          System.out.println(command + " 요청 처리중...");
          if(command.startsWith("/board/")) {
            boardServlet.service(command);

          } else if(command.startsWith("/lesson/")) {
            lessonServlet.service(command);

          } else if(command.startsWith("/member/")) {
            memberServlet.service(command);
          }   else if (command.equals("quit")) {
            out.writeUTF("ok");
            out.flush();
            break;
          } else {
            out.writeUTF("fail");
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료!");
        } 
        // 클라이언트와 연결을 끊기전에 작업내용을 파일에 저장한다.
        boardServlet.saveData();
        lessonServlet.saveData();
        memberServlet.saveData();
      }
      
      System.out.println("클라이언트와 연결을 끊었음");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료!");
  }












}


