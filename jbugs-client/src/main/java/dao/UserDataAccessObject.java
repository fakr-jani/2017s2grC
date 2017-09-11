/**
 * 
 */
package dao;

import javax.enterprise.context.RequestScoped;

import entities.User;

/**
 * @author Mihaly Fodor
 */
@RequestScoped
public class UserDataAccessObject {

	public User getUser(String userName, String password) {
		return new User("nim", "nim");
	}

}
