// 트랜잭션 다루기 -commit()호출 후
package ch26.i;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class Test02 {

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
    
    
    List<Board> boards = sqlSession.selectList("board.select");
    for (Board b : boards) {
      System.out.println(b);
    }
    // 이 SqlSession 객체로 실행한 모든 insert/update/delete 결과는 
    // 실제 테이블에 적용된다.
    sqlSession.commit();
    
    sqlSession.close();
    
  }

}






