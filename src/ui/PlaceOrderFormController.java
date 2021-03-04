package ui;

import business.*;
import dataaccess.*;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class PlaceOrderFormController {

	@FXML
	private Label lblTotal;

	@FXML
	private Button btnPlaceOder;

	@FXML
	private TableView<InvoiceItem> tblOrderDetails;

	@FXML
	private Button btnAdd;

	@FXML
	private Label lblOrderNo;

	@FXML
	private Label lblDate;

	@FXML
	private AutoCompleteTextField txtItemCode;
	@FXML
	private TextField txtOrderQuantity;

	private SellInvoice invoice;

	// private ObservableList<InvoiceItem> tempItemsDB =
	// FXCollections.observableArrayList();

	public void initialize() {
//
		tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
		tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
		tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("cost"));
		tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

		txtItemCode.getEntries().addAll(DataBasePOS.getItems().keySet());
		invoice = new SellInvoice(DataBasePOS.getNextKey(SellInvoice.class), LocalDate.now());
		lblDate.setText(invoice.getDate().toString());
		lblOrderNo.setText(String.format("%s", invoice.getNo()));

		// btnPlaceOder.setDisable(true);
//        
//
//		tblOrderDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetailTM>() {
//			@Override
//			public void changed(ObservableValue<? extends OrderDetailTM> observable, OrderDetailTM oldValue,
//					OrderDetailTM selectedOrderDetail) {
//
//				if (selectedOrderDetail == null) {
//					// Clear Selection
//					return;
//				}
//
//				txtItemCode.setText(selectedOrderDetail.getCode());
//				txtItemDescription.setText(selectedOrderDetail.getDescription());
//				txtUnitPrice.setText(selectedOrderDetail.getUnitPrice() + "");
//				txtOrderQuantity.setText(selectedOrderDetail.getQty() + "");
//				// txtQuantityInHand.setText(getItemFromTempDB(txtItemCode.getText()).getQtyOnHand()
//				// + "");
//
//				txtItemCode.setEditable(false);
//				// btnRemove.setDisable(false);
//
//			}
//		});
////
//		tblOrderDetails.getItems().addListener(new ListChangeListener<OrderDetailTM>() {
//			@Override
//			public void onChanged(Change<? extends OrderDetailTM> c) {
//				// calculateTotal();
//
//				btnPlaceOder.setDisable(tblOrderDetails.getItems().size() == 0);
//			}
//		});

	}

//
//
	public void ClickBack(ActionEvent actionEvent) throws IOException {

//        Parent parent = FXMLLoader.load(this.getClass().getResource(" "));
//        Scene scene = new Scene(parent);
//
//        Stage primaryStage = (Stage) lblTotal.getScene().getWindow();
//        primaryStage.setScene(scene);
//        primaryStage.show();
	}

//
	public void ClickAddButton(ActionEvent actionEvent) {

		if (txtItemCode.getText().isBlank() || txtOrderQuantity.getText().isBlank()) {
			return;
		}
		if (!isInt(txtOrderQuantity.getText())) {
			return;
		}

		String itemCode = txtItemCode.getText();

		Integer qty = Integer.parseInt(txtOrderQuantity.getText());

		if (qty == 0) {
			Helper.ShowMessage(AlertType.ERROR, "Quantity can't be zero");
		}

		try {
			tblOrderDetails.getItems().add(invoice.addItem(itemCode, qty, DataBasePOS.getItemPrice(itemCode)));
			calculateTotal();
		} catch (UndefinedItemException | InsufficientBalanceException e) {
			Helper.ShowMessage(AlertType.ERROR, e.getMessage());
		}

//		double currentBalance = DataBasePOS.getItemBalance(txtItemCode.getText());
//		if (currentBalance < qty) {
//			String msg = String.format("The available balance of item [%s] is only: %.0f", itemCode, currentBalance);
//			Helper.ShowMessage(AlertType.ERROR, msg);
//		}

		//
//        String qty = txtOrderQuantity.getText();
//        if (!isInt(qty)) {
//            showInvalidateMsgBox("Qty should be a number");
//            return;
//        } else if (Integer.parseInt(qty) == 0) {
//            showInvalidateMsgBox("Qty can't be zero");
//            return;
//        } else if (Integer.parseInt(qty) > Integer.parseInt(txtQuantityInHand.getText())) {
//            showInvalidateMsgBox("Invalid Qty");
//            return;
//        }
//
//        if (tblOrderDetails.getSelectionModel().isEmpty()) {
//            // New
//
//            OrderDetailTM orderDetailTM = null;
//
//            if ((orderDetailTM = isItemExist(txtItemCode.getText())) == null) {
//
//                OrderDetailTM newOrderDetailTM = new OrderDetailTM(txtItemCode.getText(),
//                        txtItemDescription.getText(),
//                        Integer.parseInt(qty),
//                        Double.parseDouble(txtUnitPrice.getText()),
//                        Integer.parseInt(qty) * Double.parseDouble(txtUnitPrice.getText()));
//
//                tblOrderDetails.getItems().add(newOrderDetailTM);
//
//            } else {
//                orderDetailTM.setQty(orderDetailTM.getQty() + Integer.parseInt(qty));
//            }
//
//
//        } else {
//            // Update
//            OrderDetailTM selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();
//            synchronizeQty(selectedItem.getCode());
//            selectedItem.setQty(Integer.parseInt(qty));
//        }
//
//        setTempQty(txtItemCode.getText(), Integer.parseInt(qty));
//        tblOrderDetails.refresh();
//        reset();

//        calculateTotal();

	}

	private void calculateTotal() {
		lblTotal.setText(String.format("%.0f", invoice.getTotal()));
	}

	private boolean isInt(String number) {
		char[] chars = number.toCharArray();
		for (char aChar : chars) {
			if (!Character.isDigit(aChar)) {
				return false;
			}
		}
		return true;
	}
//	public void ClickRemoveButton(ActionEvent actionEvent) {
//        var selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();
//        tblOrderDetails.getItems().remove(selectedItem);
//        
////
////        synchronizeQty(selectedItem.getCode());
////        reset();
//
////        calculateTotal();
//	}

	public void EnterCustomerIDtxt(ActionEvent actionEvent) {
//        String customerID = txtCustomerID.getText();
//
//        Customer customer = ManageCustomers.findCustomer(customerID);
//
//        if (customer == null) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Customer ID", ButtonType.OK).showAndWait();
//            txtCustomerName.clear();
//            txtCustomerID.requestFocus();
//            txtCustomerID.selectAll();
//        } else {
//            txtCustomerName.setText(customer.getName());
//            txtItemCode.requestFocus();
//        }
	}

	public void EnterItemCodetxt(ActionEvent actionEvent) {
//        Item item = validateItemCode();
//
//        if (item != null) {
//
//            txtItemDescription.setText(item.getDescription());
//            txtQuantityInHand.setText(getItemFromTempDB(item.getCode()).getQtyOnHand() + "");
//            txtUnitPrice.setText(item.getUnitPrice() + "");
//            txtOrderQuantity.requestFocus();
//        }
	}

	public void pressEnterOnOrderquantity(ActionEvent actionEvent) {
	}

//
	public void ClickPlaceOrder(ActionEvent actionEvent) {
//        if (txtCustomerID.getText().trim().isEmpty()) {
//            new Alert(Alert.AlertType.ERROR, "Can't place a order without a customer Id", ButtonType.OK).showAndWait();
//            txtCustomerID.requestFocus();
//            return;
//        }
//
//        ObservableList<OrderDetailTM> items = tblOrderDetails.getItems();
//        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
//
//        for (OrderDetailTM item : items) {
//            orderDetails.add(new OrderDetail(item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice()));
//        }
//        ManageOrders.createOrder(new Order(txtOrderID.getText(), DatePickerBox.getValue(), txtCustomerID.getText(), orderDetails));
//
//        new Alert(Alert.AlertType.CONFIRMATION, "Order has been placed successfully", ButtonType.OK).showAndWait();
//
//        String Oid = txtOrderID.getText();
//        String Cus_id = txtCustomerID.getText();
//        String Cus_Name = txtCustomerName.getText();
//
//        File file = new File("table/place_order.jasper");
//        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(file);
//
//        HashMap<String, Object> parems = new HashMap<>();
//        parems.put("oid", Oid);
//        parems.put("cus_id", Cus_id);
//        parems.put("cus_name", Cus_Name);
//
//        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"item_code", "item_desc", "item_qty", "item_unitPrice", "total"}, 0);
//        ObservableList<OrderDetailTM> itmes = tblOrderDetails.getItems();
//
//        for (OrderDetailTM item : itmes) {
//            Object[] rowDate = {item.getCode(), item.getDescription(), item.getQty(), item.getUnitPrice(), item.getTotal()};
//            dtm.addRow(rowDate);
//        }
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parems, new JRTableModelDataSource(dtm));
//        JasperViewer.viewReport(jasperPrint,false);
//
//        hardReset();
	}

//
	private void hardReset() {
//        reset();
//        tblOrderDetails.getItems().removeAll(tblOrderDetails.getItems());
//        txtCustomerID.clear();
//        txtCustomerName.clear();
//        txtOrderID.setText(ManageOrders.generateOrderId());
//        txtCustomerID.requestFocus();
	}
//
//    public void calculateTotal() {
//        ObservableList<OrderDetailTM> items = tblOrderDetails.getItems();
//
//        double total = 0.0;
//
//        for (OrderDetailTM item : items) {
//            total += item.getTotal();
//        }
//
//        lblTotal.setText("Total : " + total + "");
//    }
//
//    private Item validateItemCode() {
//        String itemCode = txtItemCode.getText();
//
//        Item item = ManageItems.findItem(itemCode);
//
//        if (item == null) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Item Code", ButtonType.OK).showAndWait();
//            txtItemDescription.clear();
//            txtQuantityInHand.clear();
//            txtUnitPrice.clear();
//            txtOrderQuantity.clear();
//            txtItemCode.requestFocus();
//            txtItemCode.selectAll();
//        }
//        return item;
//    }
//
//    public boolean isInt(String number) {
//        char[] chars = number.toCharArray();
//        for (char aChar : chars) {
//            if (!Character.isDigit(aChar)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public Item getItemFromTempDB(String itemCode) {
//        for (Item item : tempItemsDB) {
//            if (item.getCode().equals(itemCode)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    private void showInvalidateMsgBox(String message) {
//        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).showAndWait();
//        txtOrderQuantity.requestFocus();
//        txtOrderQuantity.selectAll();
//    }
//
//    private OrderDetailTM isItemExist(String itemCode) {
//        ObservableList<OrderDetailTM> items = tblOrderDetails.getItems();
//        for (OrderDetailTM item : items) {
//            if (item.getCode().equals(itemCode)) {
//                return item;
//            }
//        }
//        return null;
//    }
//
//    public void reset() {
//        tblOrderDetails.refresh();
//        txtItemCode.clear();
//        txtItemDescription.clear();
//        txtOrderQuantity.clear();
//        txtQuantityInHand.clear();
//        txtUnitPrice.clear();
//        txtItemCode.setEditable(true);
	// btnRemove.setDisable(true);
//        tblOrderDetails.getSelectionModel().clearSelection();
//        txtItemCode.requestFocus();
//    }
//
//    private void setTempQty(String itemCode, int qty) {
//        for (Item item : tempItemsDB) {
//            if (item.getCode().equals(itemCode)) {
//                item.setQtyOnHand(item.getQtyOnHand() - qty);
//                break;
//            }
//        }
//    }
//
//    private void synchronizeQty(String itemCode) {
//        int qtyOnHand = ManageItems.findItem(itemCode).getQtyOnHand();
//        for (Item item : tempItemsDB) {
//            if (item.getCode().equals(itemCode)) {
//                item.setQtyOnHand(qtyOnHand);
//                return;
//            }
//        }
//    }

}
