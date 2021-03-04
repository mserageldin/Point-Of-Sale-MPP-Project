package business;

import dataaccess.DataBasePOS;
import dataaccess.IDataAccessable;

public final class Item implements IDataAccessable<String> {

	private static final long serialVersionUID = 739297006409334587L;
	private final String name;
	private final String code;
	private final String description;
	private final int supplierId;
	private final double profitRatio;

	public Item(int supplierId, String name, String code, String description, double profitRatio)
			throws UndefinedSupplierException {

		if (supplierId <= 0 || !DataBasePOS.getSuppliers().containsKey(supplierId)) {
			String msg = String.format("Supplier [%s] is not exist in suppliers List", supplierId);
			throw new UndefinedSupplierException(msg);
		}

		this.supplierId = supplierId;
		this.name = name;
		this.code = code;
		this.description = description;
		this.profitRatio = profitRatio;
	}

	@Override
	public String getPrimaryKeyValue() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public double getProfitRatio() {
		return profitRatio;
	}

	public Supplier getSupplier() {
		return DataBasePOS.getSuppliers().get(supplierId);
	}

	@Override
	public String toString() {
		return String.format("[Name: %s, Code: %s, Description: %s, SupplierId: %s, ProfitRatio: %.2f]", name, code,
				description, supplierId, profitRatio);
	}
}
