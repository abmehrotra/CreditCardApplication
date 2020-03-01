package com.credit.card.challenge.creditcard;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

/**
 * 
 * @author Abhinav Mehrotra
 *
 */
@Data
@Entity
@IdClass(CreditCardCompositKey.class)
public class CreditCardInfo {

	private @Id String name;
	private @Id BigInteger cardNumber;
	private int balance;
	private int cardLimit;
	
	public CreditCardInfo() { }
	
	public CreditCardInfo(String name, BigInteger cardNumber, int balance, int cardLimit) {
		this.name = name;
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.cardLimit = cardLimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(BigInteger cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(int cardLimit) {
		this.cardLimit = cardLimit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balance;
		result = prime * result + cardLimit;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCardInfo other = (CreditCardInfo) obj;
		if (balance != other.balance)
			return false;
		if (cardLimit != other.cardLimit)
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
