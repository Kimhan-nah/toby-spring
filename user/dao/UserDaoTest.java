package user.dao;

import java.sql.SQLException;

import user.domain.User;

/**
 * user.dao.UserDao 클라이언트인 main 메소드
 */
public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("hannkim1");
        user.setName("김한나");
        user.setPassword("genius");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());

    }
}
