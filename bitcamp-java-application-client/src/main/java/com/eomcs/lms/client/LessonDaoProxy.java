package com.eomcs.lms.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoProxy implements LessonDao {

  String host; 
  int port;

  public LessonDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/lesson/add");
      out.writeObject(lesson);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Lesson> findAll() throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/lesson/list");
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return (List<Lesson>)in.readObject();
    }
  }
  @Override
  public Lesson findBy(int no) throws Exception {

    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/lesson/detail");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return (Lesson)in.readObject();
    }
  }
  @Override
  public int update(Lesson lesson) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/lesson/update");
      out.writeObject(lesson);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }
  @Override
  public int delete(int no) throws Exception {
    try(Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/lesson/delete");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());    
      return 1;
    }
  }
  
   public static void main(String[] args) throws Exception {

     LessonDaoProxy daoProxy = new LessonDaoProxy("localhost", 8888);
     
     /*
     //입력 테스트
     
     Lesson lesson = new Lesson();
     lesson.setNo(200);
     lesson.setTitle("바보");
     lesson.setContents("멍청이");
     lesson.setStartDate(Date.valueOf("2019-01-01"));
     lesson.setEndDate(Date.valueOf("2019-02-02"));
     lesson.setTotalHours(300);
     lesson.setDayHours(4);
     daoProxy.insert(lesson);
     System.out.println("입력성공!");
     */
     
     /*
     // 조회테스트
     Lesson lesson = daoProxy.findBy(200);
     System.out.println(lesson);
     */
     
   /*
     //목록 조회 테스트
     List<Lesson> lessons = daoProxy.findAll();
     for(Lesson lesson : lessons) {
       System.out.println(lesson);
     }
     */
     
     //변경 테스트
     
     Lesson lesson = new Lesson();
     lesson.setNo(200);
     lesson.setTitle("바보");
     lesson.setContents("멍청이");
     lesson.setStartDate(Date.valueOf("2019-01-01"));
     lesson.setEndDate(Date.valueOf("2019-02-02"));
     lesson.setTotalHours(300);
     lesson.setDayHours(4);
     
     daoProxy.update(lesson);
     Lesson lesson2 = daoProxy.findBy(200);
     System.out.println(lesson2);
   }
}
