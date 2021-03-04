package business;

import java.io.Serializable;

import dataaccess.DataBasePOS;

public final class InvoiceItem implements Serializable {

	private static final long serialVersionUID = -4902107141255193841L;
	private final String itemCode;
	private final double quantity;
	private final double cost;

	InvoiceItem(String itemCode, double quantity, double cost) {
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.cost = cost;
	}

	public String getItemCode() {
		return itemCode;
	}

	public double getQuantity() {
		return quantity;
	}

	public double getCost() {
		return cost;
	}

	public double getTotal() {
		return cost * quantity;
	}
	
	public String getDescription() {
		return this.getItemInfo().getDescription();
	}

	public Item getItemInfo() {
		return DataBasePOS.getItems().get(itemCode);
	}

	@Override
	public String toString() {
		return String.format("[Code: %s, Quantity: %.0f, Cost: %.2f]", itemCode, quantity, cost);
	}
}
