package business;

import java.time.LocalDate;
import java.util.*;

import dataaccess.DataBasePOS;
import dataaccess.IDataAccessable;

public abstract class Invoice implements IDataAccessable<Integer> {
	private static final long serialVersionUID = 1L;
	private final int no;
	private final List<InvoiceItem> items;
	private final LocalDate date;

	public Invoice(int no, LocalDate date) {
		this.no = no;
		this.date = date;
		this.items = new ArrayList<>();
	}

	public int getNo() {
		return no;
	}

	public List<InvoiceItem> getItems() {
		return Collections.unmodifiableList(items);
	}

	public InvoiceItem addItem(String itemCode, double quantity, double cost)
			throws UndefinedItemException, InsufficientBalanceException {
		verifyItemExist(itemCode, quantity);
		var invoiceitem = new InvoiceItem(itemCode, quantity, cost);
		this.items.add(invoiceitem);
		return invoiceitem;
	}

	protected void verifyItemExist(String itemCode, double quantity)
			throws UndefinedItemException, InsufficientBalanceException {
		if (!DataBasePOS.getItems().containsKey(itemCode)) {
			String msg = String.format("Item code [%s] is not exist in Items List", itemCode);
			throw new UndefinedItemException(msg);
		}
	}

	public void removeItem(int index) {
		if (index > 0 && index < this.items.size()) {
			this.items.remove(index);
		}
	}

	public boolean containItem(String itemCode) {
		return this.items.stream().anyMatch(i -> i.getItemCode().equals(itemCode));
	}

	public LocalDate getDate() {
		return date;
	}

	public double getTotal() {
		return this.items.stream().mapToDouble(i -> i.getTotal()).sum();
	}

	@Override
	public Integer getPrimaryKeyValue() {
		return this.getNo();
	}

	@Override
	public String toString() {
		return String.format("[Invoice No: %s, Invoice Date: %s]", no, date);
	}
}
