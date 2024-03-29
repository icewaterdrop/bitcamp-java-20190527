package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class LessonHandler {

  static Lesson[] lessons = new Lesson[100];
  static int lessonsSize = 0;
  static Scanner keyScan;
 
  static void addLesson() {
    // 수업 데이터를 저장할 메모리를 Lesson 설계도에 따라만든다.
    Lesson lesson = new Lesson();

    // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.no = getIntValue("번호? ");
    lesson.title = getStringValue("수업명? ");
    lesson.contents = getStringValue("설명? ");
    lesson.startDate = getDateValue("시작일? ");
    lesson.endDate = getDateValue("종료일? ");
    lesson.totalHours = getIntValue("총수업시간? ");
    lesson.dayHours = getIntValue("일수업시간? ");

    // 수업 데이터를 저장하고 있는 인스턴스의 주소를 레퍼런스 배열에 저장한다.
    lessons[lessonsSize++] = lesson;
    System.out.println("저장하였습니다.");

  }

  static void listLesson() {
    for (int i = 0; i < lessonsSize; i++) {
      Lesson lesson = lessons[i];
      System.out.printf("%s, %s, %s ~ %s, %s\n", lesson.no, lesson.title, lesson.startDate,
          lesson.endDate, lesson.totalHours);
    }
  }

  private static int getIntValue(String message) {
    int value = 0;

    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.print("숫자를 입력하세요.");
      }
    }
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.print("2019-07-05 형식으로 입력하세요. ");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.println(message);
    return keyScan.nextLine();
  }

}
