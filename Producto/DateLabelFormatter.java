package vaquitas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

//Toda la clase fue copiada
//MadProgrammer. (2014, November 7). How do I implement JDatePicker. Recuperado de https://stackoverflow.com/questions/26794698/how-do-i-implement-jdatepicker

public class DateLabelFormatter extends AbstractFormatter {

	private String datePattern = "dd-MM-yyyy";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}
	
}
