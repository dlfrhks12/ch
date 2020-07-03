package com.icia.cheatingday.util.editor;

import java.beans.*;
import java.util.*;

public class AuthorityPropertyEditor extends PropertyEditorSupport {
	// ROLE_USER		{ROLE_USER}
	// ROLE_MANAGER		{ROLE_MANAGER}
	// ROLE_ADMIN		{ROLE_USER, ROLE_MANAGER, ROLE_ADMIN}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		List<String> list = new ArrayList<>();
		if(text.equals("ROLE_USER")) {
			list.add("ROLE_USER");
		} else if(text.equals("ROLE_MANAGER")) {
			list.add("ROLE_MANAGER");
		} else if(text.equals("ROLE_ADMIN")) {
			list.add("ROLE_ADMIN");
		} else
			new IllegalArgumentException();
		setValue(list);
	}
	
}



