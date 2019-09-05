package ch29.g;

import java.beans.PropertyEditorSupport;

// 스프링 IoC 컨테이너에서 사용할 프로퍼티 에디터 만들기
// => java.beans.PropertyEditor 인터페이스를 구현하면 된다.
//    문제는 인터페이스에 선언된 메서드가 너무 많아 클래스로 구현하기가 매우 번거롭다.
// => java.beans.PropertyEditorSupport 클래스를 상속 받으면 된다.
//    PropertyEditor 인터페이스를 구현한 클래스이다.
//    이 클래스를 상속 받아 필요한 메서드만 오버라이딩 하는 것이 
//    인터페이스를 직접 구현하는 것 보다 편하다!
// 
public class MyCustomBlackBoxEditor extends PropertyEditorSupport {
  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    String[] values = text.split(",");
    BlackBox blackBox = new BlackBox();
    blackBox.setMaker(values[0]);
    blackBox.setModel(values[1]);
    
    this.setValue(blackBox);
  }
}









