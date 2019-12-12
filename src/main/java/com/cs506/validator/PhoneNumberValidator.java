package com.cs506.validator;

import java.util.regex.Pattern;

import org.apache.wicket.validation.validator.PatternValidator;

/**
 * Validates a phone number string. The number should contain only numbers 0-9, whitespace or the characters ()-+
 */
public class PhoneNumberValidator extends PatternValidator {

    private static final long serialVersionUID = 1L;

    public PhoneNumberValidator() {
    	//"[0-9\\+\\-\\(\\)\\s]*"
        super(Pattern.compile("(^\\(?[0-9]{3}\\)?[\\-\\.\\s]?([0-9]{3})?[\\-\\.\\s]?([0-9]{4})$)|(^\\+(?:\\d[\\-\\.\\s]?){6,14}[0-9]$)"));
    }

}