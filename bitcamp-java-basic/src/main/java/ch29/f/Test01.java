// 팩토리 클래스가 생성한 Ioc 컨테이너 보관하기
package ch29.f;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ch29.SpringUtils;

public class Test01 {
  public static void main(String[] args) {
    ApplicationContext iocContainer = 
        new ClassPathXmlApplicationContext("ch29/f/application-context-01.xml");
    
    SpringUtils.printObjects(iocContainer);
    System.out.println("---------------------------------------");
    
    System.out.println(iocContainer.getBean("c1"));
    
    java.sql.Date obj = (java.sql.Date) iocContainer.getBean("d1");
    System.out.println(obj);
  }
}






