package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.domain.User;

public class UserDao {
    /**
     * 싱글톤이 멀티스레드 환경에서 서비스 형태의 오브젝트로 사용되는 경우에는 상태 정보가 없는 무상태(stateless) 방식으로 만들어져야 하는데,
     * ConnectionMaker처럼 읽기 전용 정보이거나, 자신이 사용하는 다른 싱글톤 빈을 저장하려는 용도라면 인스턴스 변수를 사용해도 괜찮다.
     * 스프링이 한 번 초기화해주고 나면 이후에는 수정되지 않기 때문에 멀티스레드 환경에서 사용해도 아무런 문제가 없다.
     */
    private ConnectionMaker connectionMaker;

    public UserDao() {
        // 1. 의존관계 주입(dependency injection) -> UserDao도 Bean이어야 함
        DaoFactory daoFactory = new DaoFactory();
        this.connectionMaker = daoFactory.connectionMaker();

        // 2. 의존관계 검색(dependency lookup) -> 검색하는 오브젝트는 자신이 Bean일 필요 없음
        // ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        // this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();
        PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }
}
