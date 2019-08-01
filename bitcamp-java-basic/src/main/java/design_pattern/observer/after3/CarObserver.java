package design_pattern.observer.after3;

// 이 인터페이스를 구현하는 클래스를 만들 때 관심있는 메서드만 구현하도록 편의를 제공하는 방법:
// 1) 추상 클래스
// => 이 인터페이스를 구현한 추상 클래스를 만들고 그 추상 클래스에서
// 모든 메서드를 미리 구현해 놓는다.
// 2) default 메서드
// => 추상 메서드를 미리 구현할 때 특별히 추가할 코드가 없다면
// => default 메서드로 선언하는 것도 괜찮다.
// => 원래 default 메서드의 목적은 인터 페이스를 정의한 후에,
// 즉 규칙을 정의한 후에
// 메서드(규칙)를 추가하더라도 기존에 인터페이스를 구현한 클래스에 영향을 주지 않게 하는 것이다.
// => 그런데 이렇게 간단한 경우, 추상 클래스를 대신하는 용도로 사용할 수 있다.
public interface CarObserver {

  // 구현 클래스에서 원하는 메서드만 구현 하도록 default 메서드로 선언한다.
  default void carStarted() {}
  default void carStopped() {}
}
