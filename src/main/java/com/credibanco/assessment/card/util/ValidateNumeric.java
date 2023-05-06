package com.credibanco.assessment.card.util;

public class ValidateNumeric {

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	public static boolean isLong(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double l = Long.parseLong(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	public static boolean isInteger(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double i = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
