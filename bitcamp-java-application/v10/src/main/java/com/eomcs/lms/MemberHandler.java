package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class MemberHandler {
  static Member[] members = new Member[100];
  static int membersSize = 0;
  static Scanner keyScan;

  
  static void addMember() {
    Member member = new Member();
    member.no = getIntValue("번호? ");
    member.name = getStringValue("이름? ");
    member.email = getStringValue("이메일? ");
    member.password = getStringValue("암호? ");
    member.photo = getStringValue("사진? ");
    member.tel = getStringValue("전화? ");
    member.registeredDate = new Date(System.currentTimeMillis());

    members[membersSize++] = member;
    System.out.println("저장하였습니다.");

  }
  
  static void listMember() {
    for (int i = 0; i < membersSize; i++) {
      Member member = members[i];
      System.out.printf("%s, %s, %s, %s, %s\n", member.no, member.name, member.email, member.tel,
          member.registeredDate);
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

 

  private static String getStringValue(String message) {
    System.out.println(message);
    return keyScan.nextLine();
  }
  
}
