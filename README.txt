Application Specification : 

1. Application runs on default host & port : localhost:8080 

2. There are two end points published & consumed :-

	a. /add --> Adds new Credit Card Details
	        * On Success, returns the details of Card saved as JSON
		* On Failure, throws an exception if the card number is not a valid Luhn mod 10 number or number of digits is more than 19.
				
	b. /getAll --> Returns a list of all the Card Details stored in the DB
	
3. Credit Card Details has Composite Key  - name & card number;

4. The in-memory Database used is H2.

5. Validations provided are as follows:-

	a. Card Number are valid Luhn mod 10 numbers.
	b. Length of Card number is upto 19 digits. (Note: This constraint has been explicitly validated but would be better to validate it on the front end side by using minlength & maxlength attributs on the element. There are JQuery libraries that will easily do the same. Here, the validation is done explicitly to show that, it can be acheived on the back end side as well.)
	c. Card numbers can only be numeric. (Note: No explicit validation is done on the back end server side as the proper design would be to validate this constraint on front end side. The validation happens at the DB level.)
	
6. I used POSTMAN to consume the two end points. Though Curl can also be used to do so. Curl commands are as follows - 

	a. /add --> curl -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"name": "Abhinav", "cardNumber": 455219451,"cardLimit": 1500}'
	
	b. /getAll --> curl -v localhost:8080/getAll
	

7. Application.java is the main application class

8. There are two Test classes - CreditCardFunctionalityTest.java & CreditCardUtitlityTest.java to provide Unit Test.
