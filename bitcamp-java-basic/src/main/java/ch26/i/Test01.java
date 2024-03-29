// 트랜잭션 다루기 -commit()호출 전
package ch26.i;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Test01 {

  public static void main(String[] args) throws Exception {
    
    InputStream inputStream = Resources.getResourceAsStream(
        "ch26/i/mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory =
      new SqlSessionFactoryBuilder().build(inputStream);
    
    SqlSession sqlSession = sqlSessionFactory.openSession();
 
    // insert 문을 실행할 때는 insert() 메서드를 호출하라.
    // => 그러나 update(), delete() 메서드를 호출해도 된다.
    //    왜? insert, update, delete 모두 JDBC에서 executeUpdate()를 호출하기 때문이다.
    // => 하지만 SQL의 명령과 메서드 이름을 가능한 같게 하라!
    Board board = new Board();
    board.setTitle("a101");
    board.setContents("내용");
    
    // insert(sql id, 파라미터값을 담은 객체)
    // => 리턴 값은 insert 된 row의 개수이다.
    sqlSession.insert("board.insert", board);
    board = new Board();
    board.setTitle("a102");
    board.setContents("내용");
    sqlSession.insert("board.insert", board);
    
    //insert 할 때 사용한 ,SqlSession으로 select를 실행한다면, 
    // 커밋을 하지 않아도,
    // 기본적으로 임시 데이터 베이스에 보관된 입력 데이터를 포함하여 결과를 리턴한다.
    // => 하지만 SqlSession을 닫으면 임시 보관된 대이터는 자동 제거된다.
    List<Board> boards = sqlSession.selectList("board.select");
    for (Board b : boards) {
      System.out.println(b);
    }
    
    sqlSession.close();
    
  }

}






