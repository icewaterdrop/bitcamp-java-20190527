// 스프링 IoC 컨테이너 사용
package ch29.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test01_1 {
  public static void main(String[] args) {
    // IoC(Inversion Of Control) 컨테이너
    // => bean container 라고도 부른다.
    // => 인스턴스의 생성과 관리를 담당한다.
    // => 각 객체가 의존하는 객체(dependency)를 주입한다.
    //    "의존 객체 주입(dependency injection; DI)"이라 부른다.
    //    그래서 "DI 컨테이너"라고도 부른다.
    //
    // Spring IoC 컨테이너
    // => spring.io 사이트에서 제공하는 프레임워크이다.
    // => 프로젝트에 Spring IoC 컨테이너 포함하기
    //    mvnrepository.com 에서 spring-context 로 라이브러리를 검색한다.
    //    build.gradle 에 의존 라이브러리 정보를 추가한다.
    //    '$ gradle eclipse'를 실행하여 라이브러리 파일을 다운로드 받고 이클립스 설정 파일을 갱신한다.
    //    이클립스에서 프로젝트 정보를 갱신한다.
    // 
    // ApplicationContext 인터페이스
    // => 스프링 IoC 컨테이너의 사용 규칙을 정의한 인터페이스이다.
    // => 모든 스프링 IoC 컨테이너는 이 규칙에 따라 IoC 컨테이너가 정의되어 있다.
    //
    // ApplicationContext 구현체(implements, 인터페이스를 구현한 클래스 또는 그 클래스의 인스턴스)의 종류
    // => XML 파일에서 설정 정보를 읽어들이는 IoC 컨테이너
    //    - ClassPathXmlApplicationContext : 설정 파일을 자바 CLASSPATH 경로에서 찾는다.
    //    - FileSystemXmlApplicationContext : 설정 파일을 OS 경로에서 찾는다.
    // => 자바 클래스 파일의 애노테이션에서 설정 정보를 읽어 들이는 IoC 컨테이너
    //    - AnnotationConfigApplicationContext : 설정 정보를 자바 클래스에서 찾는다.
    //
    
    // 1) 자바 CLASSPATH 에서 설정 파일을 찾는 IoC 컨테이너
    // => 자바 CLASSPATH?
    //    예를 들면 /Users/eomjinyoung/git/bitcamp-2018-12/java-basic/bin/main
    //    즉, JVM이 자바 클래스 파일(.class)을 로딩하기 위해 찾는 경로이다.
    //    보통 JVM을 실행할 때 -classpath 옵션이나 -cp 옵션으로 경로를 지정한다.
    //    물론 JVM이 클래스 파일을 찾을 JVM의 기본 경로($JAVA_HOME/lib)를 가장 먼저 뒤진다.
    // => 설정 파일 경로를 지정할 때 자바 패키지 경로를 지정한다.
    //    파일 경로이기 때문에 패키지와 패키지 사이에는 . 대신에 /를 사용해야 한다.
    ApplicationContext iocContainer1 = 
        new ClassPathXmlApplicationContext("ch29/a/application-context.xml");
    
    // 2) 운영체제의 파일 시스템에서 설정 파일을 찾는 IoC 컨테이너
    // => 설정 파일 경로를 지정할 때 직접 파일 경로를 지정해야 한다.
    // => 주의!
    //    설정 파일 경로를 지정할 때 URL 형식으로 지정해야 한다.
    //    URL 형식에서는 파일 시스템을 가리킬 때 다음의 접두어를 붙인다.
    //    file://
    ApplicationContext iocContainer2 = 
        new FileSystemXmlApplicationContext(
            "file:///Users/bit/git/bitcamp-java-20190527/bitcamp-java-basic/src/main/java/ch29/a/application-context.xml");
    
    // 3) 자바 클래스 파일의 애노테이션으로부터 설정 정보를 추출한다. = java config라 부른다.
    // => 생성자에 설정 정보를 갖고 있는 클래스의 타입 정보를 넘긴다. 
    ApplicationContext iocContainer3 = 
        new AnnotationConfigApplicationContext(AppConfig.class);
    
    System.out.println("실행 완료!");
  }
}






