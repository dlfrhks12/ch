package com.icia.cheatingday.util.editor;

import java.beans.*;
import java.time.*;
import java.time.format.*;

public class DatePropertyEditor extends PropertyEditorSupport {
	@Override
    public void setAsText(String text) {
		// 2011-11-20
		String[] ar = text.split("-");
		int year = Integer.parseInt(ar[0]);
		int month = Integer.parseInt(ar[1]);
		int day = Integer.parseInt(ar[2]);
		LocalDateTime time
			= LocalDateTime.of(year, month, day, 0, 0);
		setValue(time);
    }
}
