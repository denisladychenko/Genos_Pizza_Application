package pizzeria.exception_classes;

public class PriceOutOfRange extends Exception{

	
	private static final long serialVersionUID = 1L;

	public PriceOutOfRange() {	
			super("The price must be in range from 0.00 - 999.00!");
	}
}
