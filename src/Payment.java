// Class representing a payment method with card details
public class Payment {
	private String nameOnCard;    // Name on the credit/debit card
	private String cardNumber;    // Card number
	private String expirationDate; // Expiration date of the card
	private int securityCode;     // Security code (CVV) of the card

	// Constructor to initialize payment details
	public Payment(String name, String number, String expDate, int cvv){
		nameOnCard = name;
		cardNumber = number;
		expirationDate = expDate;
		securityCode = cvv;
	}

	// Getter for the name on the card
	public String getNameOnCard(){
		return nameOnCard;
	}

	// Setter for the name on the card
	public void setNameOnCard(String name){
		nameOnCard = name;
	}

	// Getter for the card number
	public String getCardNumber(){
		return cardNumber;
	}

	// Setter for the card number
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	// Getter for the card expiration date
	public String getExpirationDate() {
		return expirationDate;
	}

	// Setter for the card expiration date
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	// Getter for the security code (CVV)
	public int getSecurityCode() {
		return securityCode;
	}

	// Setter for the security code (CVV)
	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
}