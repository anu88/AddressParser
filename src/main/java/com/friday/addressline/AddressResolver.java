package com.friday.addressline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class AddressResolver {

	private static final Pattern NUM_REGEX = Pattern.compile(
			"(^\\d+\\s?\\p{L}?\\s)|(\\s\\d+\\s?\\p{L}?$)|((^|\\s)(?i)(no){1}\\s+\\d+\\s?\\p{L}?(\\s+|$))",
			Pattern.UNICODE_CHARACTER_CLASS);
	
	private static final String HOUSE_NO_MATCH= "NO MATCH";

	public static Address resolve(String rawAddress) {
		if (rawAddress != null && rawAddress.trim().length() > 0) {
			Address parsedAddress;
			System.out.println("\nRaw address: " + rawAddress);
			String cleansedAddressString = cleanseString(rawAddress);
			Matcher m = NUM_REGEX.matcher(cleansedAddressString);
			if (m.find()) {
				String houseNumber = m.group(0).trim();
				String street = cleansedAddressString.replace(m.group(0), "").trim();
				parsedAddress = new Address(houseNumber, street);
			} else {
				parsedAddress = new Address(HOUSE_NO_MATCH, cleansedAddressString);
			}
			System.out.println(parsedAddress);
			return parsedAddress;
			
		} else {
			throw new IllegalArgumentException("Please enter a valid non empty address");
		}
	}

	private static String cleanseString(String addressString) {
		String s = addressString.replaceAll("[.,]", "");
		s = s.replaceAll("\\s+", " ");
		return s;
	}

}
