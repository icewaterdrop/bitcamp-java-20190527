// Object 클래스 - clone() : shallow copy
package ch15;
 
// clone()은 인스턴스를 복제할 때 호출하는 메서드이다.

public class Test13_2 {
  

//인스턴스 복제 기능을 활성화하려면 Cloneable 인터페이스를 구현해야 한다.
 // => 이 인터페이스에는 메서드가 선언되어 있지 않다.
 // => 따라서 클래스는 따로 메서드를 구현할 필요가 없다.
 // => Cloneable을 구현하는 이유는 
 //    JVM에게 이 클래스의 인스턴스를 복제할 수 있음을 표시하기 위함이다.
 //    이 표시가 안된 클래스는 JVM이 인스턴스를 복제해 주지 않는다. 즉 clone()을 호출할 수 없다.

  static class Engine implements Cloneable {
    int cc;
    int valve;
    
    public Engine(int cc, int valve) {
      this.cc = cc;
      this.valve = valve;
    }
    
    @Override
    public String toString() {
      return "Engine [cc=" + cc + ", valve=" + valve + "]";
    }

    @Override
    public Engine clone() throws CloneNotSupportedException {
      return (Engine) super.clone();
    }
  }
  
  static class Car implements Cloneable {
    String maker;
    String name;
    Engine engine;
    
    public Car(String maker, String name, Engine engine) {
      this.maker = maker;
      this.name = name;
      this.engine = engine;
    }

    @Override
    public String toString() {
      return "Car [maker=" + maker + ", name=" + name + ", engine=" + engine + "]";
    }

    @Override
    public Car clone() throws CloneNotSupportedException {
      // deep copy
      // => 포함하고 있는 하위 객체에 대한 복제를 수행하려면 다음과 같이 
      //    개발자가 직접 하위 객체를 복제하는 코드를 작성해야 한다.
      // 
      Car copy = (Car) super.clone();
      copy.engine = this.engine.clone();
      return copy;
    }
  }
  
  public static void main(String[] args) throws Exception {
    Engine engine = new Engine(3000, 16);
    Car car = new Car("비트자동차", "비트비트", engine);
    
    // 자동차 복제
    Car car2 = car.clone();
    
    System.out.println(car == car2);
    System.out.println(car);
    System.out.println(car2);
    System.out.println(car.engine == car2.engine);
    
  }
}







