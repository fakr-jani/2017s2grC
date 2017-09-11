package ro.msg.edu.persistence.bug.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ro.msg.edu.persistence.common.entity.AbstractEntity;

@Entity
public class Attachment extends AbstractEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAttachment;

	@ManyToOne
	private Bug bugAttachment;

	@Override
	public Long getId() {
		return idAttachment;
	}

}
