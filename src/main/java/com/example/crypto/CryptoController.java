package com.example.crypto;

import com.example.crypto.CryptoAlgorithms.Affine;
import com.example.crypto.CryptoAlgorithms.Caesar;
import com.example.crypto.CryptoAlgorithms.Substitution;
import com.example.crypto.CryptoAlgorithms.Vigenere;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CryptoController {

    @FXML public ComboBox<String> cmb_algorithm;
    @FXML public TextField txt_key;
    @FXML public TextField txt_plainText;
    @FXML public TextField txt_cipher;
    @FXML public TextField txt_cipherToDecrypt;
    @FXML public TextField txt_decryptedText;

    private final Affine affine;
    private final Caesar caesar;
    private final Substitution substitution;
    private final Vigenere vigenere;

    public CryptoController() {
        affine = new Affine();
        caesar = new Caesar();
        substitution = new Substitution();
        vigenere = new Vigenere();
    }

    void setResultFromEncryption(String cipher) {
        txt_cipher.setText(cipher);
        txt_cipherToDecrypt.setText(cipher);
    }

    @FXML
    public void initialize() {
        cmb_algorithm.setItems(FXCollections.observableArrayList(
                "Caesar",
                "Substitution",
                "Affine",
                "Vigen-ere"
        ));
        cmb_algorithm.valueProperty().addListener((observableValue, s, t1) -> {
            switch (t1) {
                case "Caesar" -> txt_key.setText("3");
                case "Substitution" -> txt_key.setText("yhkqgvxfoluapwmtzecjdbsnri");
                case "Affine" -> txt_key.setText("5,6");
                case "Vigen-ere" -> txt_key.setText("CIPHER");
            }
        });
    }

    @FXML
    public void onEncryptButtonClick() {
        String algorithmName = cmb_algorithm.getValue();
        String plainText = txt_plainText.getText();
        String key = txt_key.getText();
        switch (algorithmName) {
            case "Caesar" -> {
                String cipher = caesar.encrypt(plainText, Integer.parseInt(key));
                setResultFromEncryption(cipher);
            }
            case "Substitution" -> {
                String cipher = substitution.encrypt(plainText, key);
                setResultFromEncryption(cipher);
            }
            case "Affine" -> {
                String[] keys = key.split(",");
                String cipher = affine.encrypt(plainText, Integer.parseInt(keys[0]), Integer.parseInt(keys[1]));
                setResultFromEncryption(cipher);
            }
            case "Vigen-ere" -> {
                String cipher = vigenere.encrypt(plainText, key);
                setResultFromEncryption(cipher);
            }
        }
    }

    @FXML
    public void onDecryptButtonClick() {
        String algorithmName = cmb_algorithm.getValue();
        String cipher = txt_cipherToDecrypt.getText();
        String key = txt_key.getText();
        switch (algorithmName) {
            case "Caesar" -> {
                String decrypted = caesar.decrypt(cipher, Integer.parseInt(key));
                txt_decryptedText.setText(decrypted);
            }
            case "Substitution" -> {
                String decrypted = substitution.decrypt(cipher, key);
                txt_decryptedText.setText(decrypted);
            }
            case "Affine" -> {
                String[] keys = key.split(",");
                String decrypted = affine.decrypt(cipher, Integer.parseInt(keys[0]), Integer.parseInt(keys[1]));
                txt_decryptedText.setText(decrypted);
            }
            case "Vigen-ere" -> {
                String decrypted = vigenere.decrypt(cipher, key);
                txt_decryptedText.setText(decrypted);
            }
        }
    }

    public void onAsymmetricButtonClick() {
        try {
            AnchorPane root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/crypto/asymmetric.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Asymmetric");
            stage.setScene(new Scene(root, root.getPrefWidth(), root.getPrefHeight()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}