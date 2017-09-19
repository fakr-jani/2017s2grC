package ro.msg.edu.client.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import ro.msg.edu.business.bug.boundary.BugFacade;
import ro.msg.edu.business.bug.dto.AttachmentDTO;
import ro.msg.edu.business.bug.dto.BugDTO;
import ro.msg.edu.business.common.exception.TechnicalException;
import ro.msg.edu.business.user.boundary.UserFacade;
import ro.msg.edu.business.user.dto.UserDTO;
import ro.msg.edu.persistence.bug.entity.enums.BugSeverityType;

@ManagedBean
@SessionScoped
public class BugBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private BugFacade bugFacade;

	@EJB
	private UserFacade userFacade;

	private BugDTO selectedBug = new BugDTO();

	private static final String editBugs = "editBugs";

	public List<BugDTO> getAllBugs() {
		return bugFacade.findAllBugs();
	}

	public List<UserDTO> getAllUsers() {
		return userFacade.findAllUsers();
	}

	public BugDTO getSelectedBug() {
		return selectedBug;
	}

	public void setSelectedBug(BugDTO selectedBug) {
		this.selectedBug = selectedBug;
	}

	public List<BugSeverityType> getAllBugSeverityTypes() {
		return new ArrayList<>(Arrays.asList(BugSeverityType.class.getEnumConstants()));
	}

	public String updateBug() {
		try {
			bugFacade.updateBug(this.selectedBug);
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return editBugs;
	}

	public void addUploadedFile(FileUploadEvent event) {
		AttachmentDTO attachmentDTO = new AttachmentDTO();
		attachmentDTO.setFileBytes(event.getFile().getContents());

		attachmentDTO.setBug(selectedBug);
		attachmentDTO.setFileName(event.getFile().getFileName());
		this.selectedBug.getAttachments().add(attachmentDTO);

		FacesMessage message = new FacesMessage(event.getFile().getFileName() + " is added. Please Upload and Update.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void removeAttachment(AttachmentDTO a) {
		a.setBug(null);
		this.selectedBug.getAttachments().remove(a);

		FacesMessage message = new FacesMessage("Attachment(s) deleted. Please Update.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String enterUpdateMode(BugDTO bug) {
		this.selectedBug = bug;
		return editBugs;
	}

	public String leaveUpdateMode() {
		selectedBug = new BugDTO();
		return editBugs;
	}

	public boolean verifyEditRendered(BugDTO bug) {
		return (selectedBug != null && bug.getId().equals(selectedBug.getId()));
	}

}