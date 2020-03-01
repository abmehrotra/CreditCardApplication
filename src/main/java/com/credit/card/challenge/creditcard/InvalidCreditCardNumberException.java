package com.credit.card.challenge.creditcard;

/**
 * User Defined Exception
 * @author Abhinav Mehrotra
 *
 */
public class InvalidCreditCardNumberException extends Exception{
	
	public InvalidCreditCardNumberException(String error) {
		super(error);
	}

}
