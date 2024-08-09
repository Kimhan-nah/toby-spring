package user.dao;

/**
 * UserDao의 생성 책임을 맡은 팩토리 클래스 (의존 관계 설정)
 */
public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    // public AccountDao accountDao() {
    //     return new AccountDao(connectionMaker());
    // }

    // dao가 많아질 경우 중복된 코드를 분리하기 위함이다.
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}