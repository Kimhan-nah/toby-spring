package user.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import user.domain.User;

/**
 * user.dao.UserDao 클라이언트인 main 메소드
 */
public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        // generic method 방식 사용하여 두번째 파라미터에 리턴 타입(UserDao.class) 지정
        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("hannkim3");
        user.setName("김한나");
        user.setPassword("genius");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());

    }
}
