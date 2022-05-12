package com.example.crypto;

import com.example.crypto.CryptoAlgorithms.RSACrypto;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Asymmetric {

    @FXML public TextArea txt_plainText;
    @FXML public TextArea txt_decryptedText;
    @FXML public TextArea txt_encryptedText;
    @FXML public TextArea txt_publicKey;
    @FXML public TextArea txt_privateKey;

    private final RSACrypto rsaCrypto;

    public Asymmetric() {
        rsaCrypto = new RSACrypto();
    }

    @FXML
    public void initialize() {
        txt_publicKey.setWrapText(true);
        txt_privateKey.setWrapText(true);
        txt_publicKey.setText("e = " + rsaCrypto.getE().toString() + "\nn = " + rsaCrypto.getPrimeProduct().toString());
        txt_privateKey.setText("d = " + rsaCrypto.getD().toString() + "\nn = " + rsaCrypto.getPrimeProduct().toString());
    }

    @FXML
    public void onEncryptButtonClick() {
        txt_encryptedText.setWrapText(true);
        txt_encryptedText.setText(rsaCrypto.encrypt(txt_plainText.getText()));
    }

    @FXML
    public void onDecryptButtonClick() {
        txt_decryptedText.setText(rsaCrypto.decrypt(txt_encryptedText.getText()));
    }
}
