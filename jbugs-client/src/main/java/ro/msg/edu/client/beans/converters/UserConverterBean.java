package ro.msg.edu.client.beans.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ro.msg.edu.business.user.dto.UserDTO;

@ManagedBean
@FacesConverter(value = "userConverter")
public class UserConverterBean implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		String[] strArr = value.split(",");
		if (strArr.length != 9) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setId(Long.parseLong(strArr[0]));
		userDTO.setFirstname(strArr[1]);
		userDTO.setLastname(strArr[2]);
		userDTO.setEmail(strArr[3]);
		userDTO.setUsername(strArr[4]);
		userDTO.setPassword(strArr[5]);
		userDTO.setPhoneNumber(strArr[6]);
		userDTO.setActive(Boolean.valueOf(strArr[7]));
		userDTO.setCounter(Integer.parseInt(strArr[8]));

		return userDTO;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

}
