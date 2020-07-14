package payroll.web.exception;

import payroll.strings.StringFile;

public class MessageNotFoundException extends RuntimeException {

	public MessageNotFoundException(Long id) {
		super(StringFile.COULD_NOT_FIND_MESSAGE + id);
	}
}
