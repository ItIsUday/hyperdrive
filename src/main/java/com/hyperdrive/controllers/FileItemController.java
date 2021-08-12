/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hyperdrive.controllers;

import com.google.api.services.drive.model.File;
import com.hyperdrive.drive.DriveController;
import com.hyperdrive.utils.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileItemController implements Initializable {

    @FXML
    private Label labelName;

    @FXML
    private ImageView imageThumbnail;

    @FXML
    private Button download;

    @FXML
    private Button delete;

    private File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<ActionEvent> downloadEvent = this::downloadFile;
        download.setOnAction(downloadEvent);

        EventHandler<ActionEvent> deleteEvent = this::deleteFile;
        delete.setOnAction(deleteEvent);
    }

    public void setFile(File f) {
        this.file = f;
        labelName.setText(this.file.getName());
        String type = this.file.getMimeType();
        System.out.println(type);
        switch (type) {
            case Constants.DOCX_TYPE, Constants.DOC_TYPE -> imageThumbnail.setImage(new Image(getClass().getResourceAsStream(Constants.WORD_ICON)));
            case Constants.XLSX_TYPE -> imageThumbnail.setImage(new Image(getClass().getResourceAsStream(Constants.EXCEL_ICON)));
            case Constants.PDF_TYPE -> imageThumbnail.setImage(new Image(getClass().getResourceAsStream(Constants.PDF_ICON)));
            case Constants.PPT_TYPE -> imageThumbnail.setImage(new Image(getClass().getResourceAsStream(Constants.PPT_ICON)));
            case Constants.JAR_TYPE -> imageThumbnail.setImage(new Image(getClass().getResourceAsStream(Constants.JAVA_ICON)));
        }
    }

    @FXML
    private void downloadFile(ActionEvent event) {
        System.out.println("Downloading... " + labelName.getText());
        try {
            DriveController.downloadFile(this.file.getId(), Constants.DOWNLOAD_PATH + this.file.getName());
            System.out.println("Downloaded successfully");
        } catch (IOException | GeneralSecurityException ex) {
            Logger.getLogger(FileItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteFile(ActionEvent actionEvent) {
        System.out.println("Deleting... " + labelName.getText());
        try {
            DriveController.deleteFile(this.file.getId());
            System.out.println("Deleted successfully");
        } catch (GeneralSecurityException | IOException ex) {
            Logger.getLogger(FileItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
