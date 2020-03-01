package com.credit.card.challenge;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import com.credit.card.challenge.CreditCardUtility;
import com.credit.card.challenge.creditcard.CreditCardInfo;
/**
 * 
 * @author Abhinav Mehrotra
 *
 */
public class CreditCardUtitlityTest {

	private CreditCardUtility creditCardUtility = new CreditCardUtility();


	@Test
	public void checkInvalidCreditCardNumber() {
		BigInteger creditCardNumber = new BigInteger("1111222233334445");
		boolean isValidLuhn = creditCardUtility.isValidLuhn(creditCardNumber);
		assertEquals(false, isValidLuhn);

	}

	@Test
	public void checkValidCreditCardNumber() {
		BigInteger creditCardNumber = new BigInteger("1111222233334444");
		boolean isValidLuhn = creditCardUtility.isValidLuhn(creditCardNumber);
		assertEquals(true, isValidLuhn);

	}

	@Test
	public void checkCreditCardNumberWithMoreThanNineteenDigits() {
		BigInteger creditCardNumber = new BigInteger("11112222333344445555");
		boolean isValidLength = creditCardUtility.isValidLength(creditCardNumber);
		assertEquals(false, isValidLength);

	}

	@Test
	public void checkCreditCardNumberWithLessThanNineteenDigits() {

		// number of digits = 10
		BigInteger creditCardNumber = new BigInteger("1111222233");
		boolean isValidLength = creditCardUtility.isValidLength(creditCardNumber);
		assertEquals(true, isValidLength);

		// number of digits = 19
		creditCardNumber = new BigInteger("1111222233334444555");
		isValidLength = creditCardUtility.isValidLength(creditCardNumber);
		assertEquals(true, isValidLength);

		// number of digits = 4
		creditCardNumber = new BigInteger("8888");
		isValidLength = creditCardUtility.isValidLength(creditCardNumber);
		assertEquals(true, isValidLength);

	}

}
