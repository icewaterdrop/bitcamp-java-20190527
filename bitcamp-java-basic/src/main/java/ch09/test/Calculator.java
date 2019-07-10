package ch09.test;

// 계산 기능과 관련된 메서드를 별도의 블록으로 분리할 때 사용하는 문법이 "클래스"이다.
public class Calculator {
  // 개별적으로 관리 되어야 할 값은 인스턴스 필드에 저장해야 한다.
   int result;
  
  static void plus(Calculator that, int a) {
    that.result += a;
  }
  
  static void minus(Calculator that, int a) {
    that.result -=a;
  }
  
  static void multiple(Calculator that, int a) {
    that.result *=a;
  }
  
  static void divide(Calculator that, int a) {
    that.result /=a;
  }
  
  
  
  // 클래스를 정의하면서 만든 기능을 간단하게 확인하고 싶을 때, 
  // 다음과 같이 해당 클래스에 main() 메서드를 만들어 
  // 테스트 해 볼 수 있다.
  //
  /*
  public static void main(String[] args) {
    System.out.println(abs(100));
    System.out.println(abs(-100));
  }
  */
}









