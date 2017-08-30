package operations;

import java.util.List;

import javax.ejb.Remote;

import entities.Role;

@Remote
public interface RoleDAO {
	void persistRole(Role Role);

	void deleteRole(Role Role);

	void update(Role Role);

	Role findRole(int idRole);

	List<Role> listAllRoles();
}
