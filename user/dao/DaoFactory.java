package user.dao;

/**
 * UserDao의 생성 책임을 맡은 팩토리 클래스 (의존 관계 설정)
 */
public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
