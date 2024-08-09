import java.sql.SQLException;

/**
 * 관계 설정 책임이 추가된 UserDao 클라이언트인 main 메소드
 */
public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // UserDao가 사용할 ConnectionMaker 구현 클래스 DConnectionMaker
        ConnectionMaker connectionMaker = new DConnectionMaker();

        // DConnectionMaker와 UserDao "오브젝트 사이의" 의존관계 설정
        UserDao dao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("hannkim");
        user.setName("김한나");
        user.setPassword("genius");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());

    }
}
