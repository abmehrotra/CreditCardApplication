package com.credit.card.challenge.creditcard;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credit.card.challenge.CreditCardUtility;

/**
 * Main Controller class for the application defining service end points
 * @author Abhinav Mehrotra
 *
 */
@RestController
public class CreditCardController {

	private final CreditCardDao creditCardReposiotry;
	private final CreditCardUtility creditCardUtility = new CreditCardUtility();
	
	public CreditCardController(CreditCardDao creditCardRepository) {
		this.creditCardReposiotry = creditCardRepository;
	}
	
	/**
	 * Function to save a new credit card details
	 * @param newCreditCard
	 * @return
	 * @throws InvalidCreditCardNumberException
	 */
	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public CreditCardInfo addCreditCardDetails(@RequestBody CreditCardInfo newCreditCard) throws InvalidCreditCardNumberException {
		BigInteger creditCardNo = newCreditCard.getCardNumber();
		boolean isValidLuhn = creditCardUtility.isValidLuhn(creditCardNo);
		
		CreditCardInfo savedInfo = null;
		
		if(isValidLuhn) {
			savedInfo =  creditCardReposiotry.save(newCreditCard);
		}else {
			throw new InvalidCreditCardNumberException("The Credit card number is Invalid.");
		}
		
		return savedInfo;   
		
	}
	
	/**
	 * Function to return a list of every credit card in the system
	 * @return
	 */
	@GetMapping(path = "/getAll", produces = "application/json")
	List<CreditCardInfo> getAllCreditCard(){
		return creditCardReposiotry.findAll();
	}
}
