package operations;

import java.util.List;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface UserDAO {
	void persistUser(User user);

	void deleteUser(User user);

	void update(User user);

	User findUser(int idUser);

	List<User> listAllUsers();
}
