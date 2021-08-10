package com.hyperdrive.controllers;

import com.google.api.services.drive.model.File;
import com.hyperdrive.drive.DriveController;
import com.hyperdrive.utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.layout.FlowPane;
import javafx.scene.control.Button;



public class HomeController implements Initializable {

    @FXML
    private Label labelEmail;

    @FXML
    private ScrollPane vb;
    final FlowPane container = new FlowPane();
    private File file=new File();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<File> files = DriveController.connect();
            labelEmail.setText(DriveController.USER_EMAIL);
            Node[] nodes = new Node[files.size()];
            for (int i = 0; i <10; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_FILE_ITEM));
                FileItemController controller = new FileItemController();
                loader.setController(controller);
                nodes[i] = loader.load();
                container.getChildren().add(nodes[i]);
                controller.setFile(files.get(i));
            }
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        vb.setContent(container);
    }

    @FXML
    private void uploadFile(ActionEvent event) {
        System.out.println("Hello");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File to Google Drive");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        java.io.File archivo = fileChooser.showOpenDialog(stage);
        System.out.println(archivo.getName());
        try {
            DriveController.uploadFile(archivo);
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
     private void Reload(ActionEvent event){
    System.out.println("Hello");
    }
}