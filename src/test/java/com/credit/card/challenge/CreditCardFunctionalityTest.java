package com.credit.card.challenge;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.credit.card.challenge.creditcard.CreditCardController;
import com.credit.card.challenge.creditcard.CreditCardDao;
import com.credit.card.challenge.creditcard.CreditCardInfo;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@WebMvcTest(CreditCardController.class)

public class CreditCardFunctionalityTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private CreditCardDao repository;

	@Test
	public void insertNewCreditCardInfo_WithValidCardNumber_ValidLuhnNumber_AndValidLength() throws Exception{
		CreditCardInfo newCreditCard = new CreditCardInfo("Abhinav Mehrotra", new BigInteger("1111222233334444"), 0, 2000);

		mock.perform(MockMvcRequestBuilders
				.post("/add")
				.content(asJsonString(newCreditCard))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
	}



	@Test
	public void insertNewCreditCardInfo_WithInvalidCardNumber_InvalidLuhnNumber_ButValidLength(){
		CreditCardInfo newCreditCard = new CreditCardInfo("Jack Mills", new BigInteger("1111222233334445"), 0, 1000);
		
		try {
			mock.perform(MockMvcRequestBuilders
					.post("/add")
					.content(asJsonString(newCreditCard))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isInternalServerError())
					.andReturn();
		} catch (Exception e) {
			assertEquals("The Credit card number is Invalid.", e.getMessage().split(":")[1].trim());
		}

		
	}

	@Test
	public void insertNewCreditCardInfo_WithInvalidCardNumber_ValidLuhnNumber_ButInvalidLength() throws Exception{
		CreditCardInfo newCreditCard = new CreditCardInfo("Danny Smith", new BigInteger("11112222333344445553"), 0, 1000);

		try {
			mock.perform(MockMvcRequestBuilders
					.post("/add")
					.content(asJsonString(newCreditCard))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isInternalServerError())
					.andReturn();
		} catch (Exception e) {
			assertEquals("The Credit card number is Invalid.", e.getMessage().split(":")[1].trim());
		}

	}
	
	@Test
	public void getEveryCreditCardDetail_WithZeroRecords() throws Exception {
		
		List<CreditCardInfo> savedCreditCards = new ArrayList<CreditCardInfo>();
		
		when(repository.findAll()).thenReturn(savedCreditCards);
		MvcResult result = mock.perform(MockMvcRequestBuilders
				.get("/getAll"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		
		List<Object> retrievedCreditCards = asJavaObjectList(result.getResponse().getContentAsString());
		assertEquals(retrievedCreditCards.size(), savedCreditCards.size());
		
	}
	
	@Test
	public void getEveryCreditCardDetail_WithTwoRecords() throws Exception {
		CreditCardInfo newCreditCard = new CreditCardInfo("Arya Stark", new BigInteger("329694127567"), 0, 1500);
		CreditCardInfo newCreditCard1 = new CreditCardInfo("Jon Snow", new BigInteger("5153987622"), 0, 2500);
		List<CreditCardInfo> savedCreditCards = new ArrayList<CreditCardInfo>();
		savedCreditCards.add(newCreditCard);
		savedCreditCards.add(newCreditCard1);
		
		when(repository.findAll()).thenReturn(savedCreditCards);
		MvcResult result = mock.perform(MockMvcRequestBuilders
				.get("/getAll"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
		
		
		List<Object> retrievedCreditCards = asJavaObjectList(result.getResponse().getContentAsString());
		assertEquals(retrievedCreditCards.size(), savedCreditCards.size());
		
	}

	/**
	 * Function to convert JSON Object with multiple value into a List of Java Objects
	 * @param s
	 * @return
	 */
	public static List<Object> asJavaObjectList(final String s) {
		try {
			CreditCardInfo[] card = new ObjectMapper().readValue(s, CreditCardInfo[].class);
			List<Object> cardsList = Arrays.asList(card);
			return cardsList;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Function to convert a JSON string to a Java Object
	 * @param s
	 * @return
	 */
	public static CreditCardInfo asJavaObject(final String s) {
		try {
			CreditCardInfo card = new ObjectMapper().readValue(s, CreditCardInfo.class);
			return card;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Function to convert an Object to JSON String
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
