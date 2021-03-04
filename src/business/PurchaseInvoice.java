package business;

import java.time.LocalDate;
import java.util.Optional;

import dataaccess.DataBasePOS;

public class PurchaseInvoice extends Invoice {
	private static final long serialVersionUID = -8827050385591086864L;
	private final Integer supplierId;

	public PurchaseInvoice(int no, LocalDate date, Integer supplierId) throws UndefinedSupplierException {
		super(no, date);
		this.supplierId = supplierId;

		if (supplierId <= 0 || !DataBasePOS.getSuppliers().containsKey(supplierId)) {
			String msg = String.format("Supplier [%s] is not exist in suppliers List", supplierId);
			throw new UndefinedSupplierException(msg);
		}
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public Supplier getSupplier() {
		return DataBasePOS.getSuppliers().get(supplierId);
	}

	public double getItemPrice(String itemCode) {
		Optional<InvoiceItem> invoiceItem = this.getItems().stream().filter(i -> i.getItemCode().equals(itemCode))
				.findFirst();
		return invoiceItem.map(i -> i.getCost() * i.getItemInfo().getProfitRatio()).orElse(0.0);
	}

}
