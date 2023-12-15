package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoHibernateImpl();
    @Override
    public void createUsersTable() {
        userDao.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.printf("User с именем - %s добавлен в базу данных \n", name);
    }
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = userDao.getAllUsers();
        for(User u : list) {
            System.out.println(u);
        }
        return list;
    }
    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
