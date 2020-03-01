package com.credit.card.challenge.creditcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * Interface to create connection to the H2 repository
 * @author Abhinav Mehrotra
 *
 */
public interface CreditCardDao extends JpaRepository<CreditCardInfo, Long>{


}
