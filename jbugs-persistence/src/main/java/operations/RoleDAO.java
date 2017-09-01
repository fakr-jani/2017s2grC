package operations;

import java.util.List;

import javax.ejb.Remote;

import entities.Role;

@Remote
public interface RoleDAO {
	public void persistRole(Role role);

	public void deleteRole(Role role);

	public void updateRole(Role role);

	public Role findRole(int idRole);

	public List<Role> listAllRoles();
}
