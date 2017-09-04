package ro.msg.edu.business.user.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ro.msg.edu.business.common.exception.BusinessException;
import ro.msg.edu.business.user.control.UserCRUDContol;
import ro.msg.edu.business.user.dto.UserDTO;

/**
 * 
 * @author maresb
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UserFacade {

	@EJB
	private UserCRUDContol userSomething;

	public UserDTO createUser(UserDTO user) throws BusinessException {
		return userSomething.createUser(user);
	}

}
