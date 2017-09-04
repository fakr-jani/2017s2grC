package ro.msg.edu.business.common.dto;

/**
 * 
 * @author maresb
 *
 */
public abstract class AbstractDTO {
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
