package ro.msg.edu.business.common.dto;

import java.io.Serializable;

/**
 * Abstract common class for DTO objects.
 * 
 * @author Andrei Floricel, msg systems ag
 *
 */
public abstract class AbstractDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long lockVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLockVersion() {
		return lockVersion;
	}

	public void setLockVersion(Long lockVersion) {
		this.lockVersion = lockVersion;
	}

}
