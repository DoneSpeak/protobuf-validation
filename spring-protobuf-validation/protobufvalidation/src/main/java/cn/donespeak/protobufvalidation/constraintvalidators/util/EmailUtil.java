package cn.donespeak.protobufvalidation.constraintvalidators.util;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * the code is taken from:
 * {@link org.hibernate.validator.internal.util.DomainNameUtil}
 * {@link org.hibernate.validator.internal.constraintvalidators.AbstractEmailValidator}
 * 
 * @author guanrong.yang
 *
 */
public class EmailUtil {
	private static final int MAX_DOMAIN_PART_LENGTH = 255;
	private static final int MAX_LOCAL_PART_LENGTH = 64;

	private static final String LOCAL_PART_ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~\u0080-\uFFFF-]";
	private static final String LOCAL_PART_INSIDE_QUOTES_ATOM = "([a-z0-9!#$%&'*.(),<>\\[\\]:;  @+/=?^_`{|}~\u0080-\uFFFF-]|\\\\\\\\|\\\\\\\")";
	/**
	 * Regular expression for the local part of an email address (everything before '@')
	 */
	private static final Pattern LOCAL_PART_PATTERN = Pattern.compile(
			"(" + LOCAL_PART_ATOM + "+|\"" + LOCAL_PART_INSIDE_QUOTES_ATOM + "+\")" +
					"(\\." + "(" + LOCAL_PART_ATOM + "+|\"" + LOCAL_PART_INSIDE_QUOTES_ATOM + "+\")" + ")*", CASE_INSENSITIVE
	);


	private static final String DOMAIN_CHARS_WITHOUT_DASH = "[a-z\u0080-\uFFFF0-9!#$%&'*+/=?^_`{|}~]";
	private static final String DOMAIN_LABEL = "(" + DOMAIN_CHARS_WITHOUT_DASH + "-*)*" + DOMAIN_CHARS_WITHOUT_DASH + "+";
	private static final String DOMAIN = DOMAIN_LABEL + "+(\\." + DOMAIN_LABEL + "+)*";

	private static final String IP_DOMAIN = "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}";
	//IP v6 regex taken from http://stackoverflow.com/questions/53497/regular-expression-that-matches-valid-ipv6-addresses
	private static final String IP_V6_DOMAIN = "(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))";

	private static final Pattern EMAIL_DOMAIN_PATTERN = Pattern.compile(
			DOMAIN + "|\\[" + IP_DOMAIN + "\\]|" + "\\[IPv6:" + IP_V6_DOMAIN + "\\]", CASE_INSENSITIVE
	);
	
	public static boolean isValidEmail(String value) {
		if(value == null || value.trim().isEmpty()) {
			return false;
		}

		String stringValue = value.toString();
		int splitPosition = stringValue.lastIndexOf( '@' );

		if ( splitPosition < 0 ) {
			return false;
		}

		String localPart = stringValue.substring( 0, splitPosition );
		String domainPart = stringValue.substring( splitPosition + 1 );

		return isValidEmailLocalPart(localPart) && isValidEmailDomainAddress(domainPart);
	}

	private static boolean isValidEmailDomainAddress(String domainPart) {
		if(domainPart.endsWith(".")) {
			return false;
		}
		Matcher matcher = EMAIL_DOMAIN_PATTERN.matcher(domainPart);
		if ( !matcher.matches() ) {
			return false;
		}
		String asciiString;
		try {
			asciiString = IDN.toASCII(domainPart);
		}
		catch (IllegalArgumentException e) {
			return false;
		}

		if (asciiString.length() > MAX_DOMAIN_PART_LENGTH) {
			return false;
		}

		return true;
	}

	private static boolean isValidEmailLocalPart(String localPart) {
		if ( localPart.length() > MAX_LOCAL_PART_LENGTH ) {
			return false;
		}
		Matcher matcher = LOCAL_PART_PATTERN.matcher( localPart );
		return matcher.matches();
	}
}
