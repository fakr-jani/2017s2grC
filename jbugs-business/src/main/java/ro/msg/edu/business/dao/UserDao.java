package ro.msg.edu.business.dao;

import javax.ejb.Stateless;

import ro.msg.edu.persistence.user.entity.User;

@Stateless
public class UserDao extends AbstractDao<User> {

	@Override
	public Class<User> getEntityClass() {
		return User.class;
	}
}
