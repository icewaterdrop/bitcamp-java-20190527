// 인터페이스 구현 
package ch18.g2;

public class FilmPrinter implements Printer {
  // B에 선언된 메서드 구현 
  @Override
  public void print(String text) {
    System.out.println("FilmPrinter>> " + text);
  }
}
