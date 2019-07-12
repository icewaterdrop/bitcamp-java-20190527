package ch12.d;

public class Patient {
  
  public static final int WOMAN = 1;
  public static final int MAN = 2;
  
  String name;
  int age;
  int height;
  int weight;
  int gender;
  
  public String toString() {
    System.out.println("호출 되지요?");
    return String.format("name=%s, age=%s, height=%s, weight=%s, gender=%s, ",
        this.name, this.age, this.height, this.weight, this.gender);
    
  }

}
