package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonServlet implements Servlet {

  //수업 데이터 관리 DAO를 교체하기 쉽도록 인터페이스의 레퍼런스로 선언한다.
  LessonDao lessonDao;
  
  public LessonServlet(LessonDao lessonDao) {
    // 서블릿이 사용할 DAO를 직접 만들지 않고 외부에서 주입 받아 사용한다.
    // 이렇게 의존하는 객체를 외부에서 주입 받아 사용하는 방법을
    // "의존성 주입(Dependency Injection; DI)"이라 부른다.
    // => 그래야만 의존 객체를 교체하기 쉽다.
    //
    this.lessonDao = lessonDao;
  }
  
  @Override
  public void service(
      String command,
      ObjectInputStream in, 
      ObjectOutputStream out) throws Exception {
    switch (command) {
      case "/lesson/add":
        addLesson(in, out);
        break;
      case "/lesson/list":
        Thread.currentThread().sleep(10000 ); // 스레드 필요성을 알려주기 위해 실행 지연시킴 
        listLesson(in, out);
        break;
      case "/lesson/delete":
        deleteLesson(in, out);
        break;  
      case "/lesson/detail":
        detailLesson(in, out);
        break;
      case "/lesson/update":
        updateLesson(in, out);
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }

  private void updateLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson)in.readObject();
    
    if (lessonDao.update(lesson) == 0) {
      fail("해당 번호의 수업이 없습니다.", out);
      return;
    }
    
    out.writeUTF("ok");
  }

  private void detailLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    
    Lesson lesson = lessonDao.findBy(no);
    if (lesson == null) {
      fail("해당 번호의 수업이 없습니다.", out);
      return;
    }
    out.writeUTF("ok");
    out.writeObject(lesson);
  }

  private void deleteLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();
    
    if (lessonDao.delete(no) == 0) {
      fail("해당 번호의 수업이 없습니다.", out);
      return;
    }
    
    out.writeUTF("ok");
  }

  private void addLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Lesson lesson = (Lesson)in.readObject();
    
    if (lessonDao.insert(lesson) == 0) {
      fail("수업을 입력할 수 없습니다.", out);
      return;
    }
    
    out.writeUTF("ok");
  }
  
  private void listLesson(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(lessonDao.findAll());
  }
  
  private void fail(String cause, ObjectOutputStream out) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }
}
