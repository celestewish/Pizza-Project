public class Payment {
	private String nameOnCard;
	private String cardNumber;
	private String expirationDate;
	private int securityCode;

	public Payment(String name, String number, String expDate, int cvv){
		nameOnCard = name;
		cardNumber = number;
		expirationDate = expDate;
		securityCode = cvv;
	}

	public String getNameOnCard(){
		return nameOnCard;
	}

	public void setNameOnCard(String name){
		nameOnCard = name;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}
}
