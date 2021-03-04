import java.time.LocalDate;
import java.util.*;

import business.InsufficientBalanceException;
import business.Item;
import business.PurchaseInvoice;
import business.SellInvoice;
import business.Supplier;
import business.UndefinedItemException;
import business.UndefinedSupplierException;
import business.User;
import business.UserRole;
import dataaccess.DataAccess;
import dataaccess.DataBasePOS;

public class Main {

	public static void main(String[] args) {

//		var invoices = DataBasePOS.getPurchaseInvoices();

		System.out.println(DataBasePOS.getDataAccess().contains(SellInvoice.class, 11));

//		User us1 = new User("101", "123", UserRole.Admin);
//		User us2 = new User("102", "456", UserRole.Cashier);
//		User us3 = new User("103", "789", UserRole.Both);
//		
//		DataBasePOS.getDataAccess().saveRecord(us1);
//		DataBasePOS.getDataAccess().saveRecord(us2);
//		DataBasePOS.getDataAccess().saveRecord(us3);

//		da.saveRecord(new Supplier(1,"Uulen"));
//		da.saveRecord(new Supplier(2,"Serag"));
//		da.saveRecord(new Supplier(3,"Rami"));
//		da.saveRecord(new Supplier(4,"Yousef"));
//		da.saveRecord(new Supplier(5,"Bahrawy"));

		// System.out.println(da.getData(Supplier.class));

//		try {
//			da.saveRecord(new Item(1, "Item1", "DS1", "sss", .1));
//		} catch (UndefinedSupplierException e) {
//			e.printStackTrace();
//		}

//		try {
//			var i1 = new PurchaseInvoice(1, LocalDate.of(2021, 1, 3));
//			var i2 = new PurchaseInvoice(2, LocalDate.of(2021, 1, 13));
//			var i3 = new PurchaseInvoice(3, LocalDate.of(2021, 2, 3));
//			i1.addItem("DS1", 200.0, 10.0);
//			i2.addItem("DS1", 100.0, 20.0);
//			i3.addItem("DS1", 400.0, 5.0);
//			da.saveRecord(i1);
//			da.saveRecord(i2);
//			da.saveRecord(i3);
//		} catch (UndefinedSupplierException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UndefinedItemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InsufficientBalanceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int maxSell = DataBasePOS.getNextKey(SellInvoice.class);
//		var i1 = new SellInvoice(maxSell++, LocalDate.of(2021, 1, 3));
//		var i2 = new SellInvoice(maxSell++, LocalDate.of(2021, 1, 13));
//		var i3 = new SellInvoice(maxSell++, LocalDate.of(2021, 2, 3));
//		try {
//			i1.addItem("DS1", 50.0, 10.0);
//			i2.addItem("DS1", 40.0, 20.0);
//			i3.addItem("DS1", 30.0, 5.0);
//		} catch (UndefinedItemException | InsufficientBalanceException e) {
//
//			e.printStackTrace();
//		}
//
//		da.saveRecord(i1);
//		da.saveRecord(i2);
//		da.saveRecord(i3);

		// System.out.println(DataBasePOS.getNextKey(PurchaseInvoice.class));
	}
}
