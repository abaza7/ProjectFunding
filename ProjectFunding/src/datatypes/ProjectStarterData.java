package datatypes;

public class ProjectStarterData {
	private String email;
	private String paymentService;
	
	public ProjectStarterData(String email, String paymentService) {
		this.email = email;
		this.paymentService = paymentService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(String paymentService) {
		this.paymentService = paymentService;
	}
	
	
	
}
