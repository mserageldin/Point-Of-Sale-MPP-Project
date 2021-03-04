package ui;

import java.net.URL;
import java.util.ResourceBundle;

import business.AppManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class MainWindowController implements Initializable {

	@FXML
	private Label roleLabel;

	@FXML
	private Label usrIdLabel;

	@FXML
	private FlowPane btnPane;

	@FXML
	private Button btnManageItems;

	@FXML
	private Button btnManageSuppliers;

	@FXML
	private Button btnPurchaseOrder;

	@FXML
	private Button btnSellOrder;

	@FXML
	private Button btnReporting;

	@FXML
	private Button btnUsers;

	@FXML
	private Button btnChangePassword;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		var currentUser = AppManager.Instance.getCurrentUser();
		this.roleLabel.setText(currentUser.getRole().toString());
		this.usrIdLabel.setText(currentUser.getId());
		btnPane.getChildren().forEach(n -> n.setManaged(AppManager.Instance.hasPermission(getButtonKey(n))));
	}

	public void showWindow(ActionEvent event) {
		Button btn = (Button) event.getSource();
		Helper.showStage(getClass().getResource(String.format("%s.fxml", getButtonKey(btn))), btn.getText());
	}

	private String getButtonKey(Node node) {
		return node.getId().substring(3);
	}

}
