package ro.msg.edu.client.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

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

		this.selectedBug.getAttachments().add(attachmentDTO);

		FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String removeAttachment(AttachmentDTO a) {
		this.selectedBug.getAttachments().remove(a);
		return editBugs;
	}

	public void onDateSelect(SelectEvent event) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String s = sdf.format(event.getObject());
			Date d = sdf.parse(s);
			this.selectedBug.setTargetDate(d);
		} catch (ParseException e) {
			addMessage(e.getMessage());
		}
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

	public List<BugDTO> getFixedAndRejectedBugs() {
		return bugFacade.findRejectedAndFixedBugs();
	}

	public String closeBug(BugDTO bugDTO) {
		try {
			bugFacade.closeBug(bugDTO);
			addMessage(bugDTO.getTitleBug() + " " + getMessageFromProperty("#{msg['close.bug.status']}"));
		} catch (TechnicalException e) {
			addMessage(e.getMessage());
		}
		return "closeBug";
	}

	public String getMessageFromProperty(String messageProperty) {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getApplication().evaluateExpressionGet(context, messageProperty, String.class);
	}

}