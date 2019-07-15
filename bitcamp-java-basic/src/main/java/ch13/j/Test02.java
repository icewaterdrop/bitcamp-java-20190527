// 추상 클래스: 파라미터로 사용하기
package ch13.j;

public class Test02 {

  public static void main(String[] args) {
    // 지붕을 열 수 있는 자동차와짐을 싣는 덤프트럭이 필요해서 해당 클래스를 정의하였고,
    // 다음과 같이 사용한다.
    m1(new DumpTruck()); //Ok!
    m2(new DumpTruck());
    m2(new Convertible());
  }
  
  static void m1(DumpTruck car ) {
    //파리미터가 DumpTruck이면,
    //이 메서드를 호출할 때 반드시 DumpTruck의 인스턴스나
    // 또는 DumpTruck의 하위 클래스에 인스턴스를 넘기라는 뜻이다.
    
    
   
  }
  
  static void m2(Car car) {
    // 파라미터가 Car이다.
    // 이메서드를 호출할 때 Car의 하위클래스의 인스턴스를 넘기라는 뜻이다.
    // Car는 추상 클래스이기 때문에 인스턴스를 생성할 수 없다.
    
  }

}
