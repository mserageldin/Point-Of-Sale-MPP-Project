package business;

import java.time.LocalDate;

import dataaccess.DataBasePOS;

public class SellInvoice extends Invoice {
	private static final long serialVersionUID = -3221846917372035487L;

	public SellInvoice(int no, LocalDate date) {
		super(no, date);
	}

	@Override
	protected void verifyItemExist(String itemCode, double quantity)
			throws UndefinedItemException, InsufficientBalanceException {
		super.verifyItemExist(itemCode, quantity);
		double currentBalance = DataBasePOS.getItemBalance(itemCode, this);
		if (currentBalance < quantity) {
			String msg = String.format("The available balance of item [%s] is only: %.0f", itemCode, currentBalance);
			throw new InsufficientBalanceException(msg);
		}
	}
}
