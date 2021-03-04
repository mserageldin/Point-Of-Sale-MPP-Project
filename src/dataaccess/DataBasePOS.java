package dataaccess;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

import business.Invoice;
import business.Item;
import business.PurchaseInvoice;
import business.SellInvoice;
import business.Supplier;
import business.User;

public class DataBasePOS {

	private static DataAccess dataAccess = new DataAccess();

	public static DataAccess getDataAccess() {
		return dataAccess;
	}

	public static HashMap<String, User> getUsers() {
		return dataAccess.getData(User.class);
	}

	public static HashMap<String, Item> getItems() {
		return dataAccess.getData(Item.class);
	}

	public static HashMap<Integer, Supplier> getSuppliers() {
		return dataAccess.getData(Supplier.class);
	}

	public static HashMap<Integer, PurchaseInvoice> getPurchaseInvoices() {
		return dataAccess.getData(PurchaseInvoice.class);
	}

	public static HashMap<Integer, SellInvoice> getSellInvoices() {
		return dataAccess.getData(SellInvoice.class);
	}

	public static <T extends IDataAccessable<Integer>> int getNextKey(Class<T> cls) {
		return dataAccess.getNextKey(cls);
	}

	public static double getItemPrice(String itemCode) {
		Optional<PurchaseInvoice> recentInvoice = getPurchaseInvoices().values().stream()
				.filter(i -> i.containItem(itemCode))
				.sorted(Comparator.comparing((i -> i.getDate()), Comparator.reverseOrder())).findFirst();
		return recentInvoice.map(i -> i.getItemPrice(itemCode)).orElse(0.0);
	}

	public static double getItemBalance(String itemCode) {
		double inQty = getPurchaseInvoices().values().stream().flatMap(i -> i.getItems().stream())
				.filter(i -> i.getItemCode().equals(itemCode)).mapToDouble(i -> i.getQuantity()).sum();

		double outQty = getSellInvoices().values().stream().flatMap(i -> i.getItems().stream())
				.filter(i -> i.getItemCode().equals(itemCode)).mapToDouble(i -> i.getQuantity()).sum();

		return inQty - outQty;
	}

	public static double getItemBalance(String itemCode, Invoice currentInvoice) {
		double qtyCurrent = currentInvoice.getItems().stream().filter(i -> i.getItemCode().equals(itemCode))
				.mapToDouble(i -> i.getQuantity()).sum();
		return getItemBalance(itemCode) - qtyCurrent;
	}
}
