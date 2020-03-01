package com.credit.card.challenge;

import java.math.BigInteger;

/**
 * Class to add utilities for the application
 * @author Abhinav Mehrotra
 *
 */
public class CreditCardUtility {

	/**
	 * Function to check if card number pass Luhn mod 10 A
	 * @param cardNumber
	 * @return
	 */
	public boolean isValidLuhn(BigInteger cardNumber) {
		
		boolean isValidLength = isValidLength(cardNumber);
		
		if(isValidLength) {
			int digitsInCardNumer = String.valueOf(cardNumber).length();
			
			int sumOfDigitsOfCardNumber = 0;
			boolean isSecondDigit = false;
			
			for(int i=digitsInCardNumer - 1; i >= 0; i--) {
				int digit = String.valueOf(cardNumber).charAt(i) - '0';
				
				if(isSecondDigit == true) {
					digit = digit * 2;
				}
				
				sumOfDigitsOfCardNumber += digit/10;
				sumOfDigitsOfCardNumber += digit%10;
				
				isSecondDigit = !isSecondDigit;
			}
			return (sumOfDigitsOfCardNumber%10 == 0);
		}
		
		return isValidLength;
	}

	/**
	 * Function to check if the length of Card is more than 0 & less than 20 
	 * @param cardNumber
	 * @return
	 */
	public boolean isValidLength(BigInteger cardNumber) {
		
		int digitsInCardNumer = String.valueOf(cardNumber).length();
		
		if(digitsInCardNumer > 0 && digitsInCardNumer < 20) {
			return true;
		}
		
		return false;
	}
}
