package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import org.unibl.etf.model.adapter.TransakcijaAdapter;
import org.unibl.etf.model.domain.oo.TransakcijaOO;

/**
 * FXML Controller class
 *
 * @author Miloš
 */
public class TransakcijaFormController implements Initializable {

    @FXML
    private AnchorPane transakcijaForm;
    @FXML
    private TextField transakcijaFormVrstaTransakcijeTXT;
    @FXML
    private TextField transakcijaFormPrimalacTXT;
    @FXML
    private TextField transakcijaFormPosiljalacTXT;
    @FXML
    private TextField transakcijaFormIznosTXT;
    @FXML
    private Button transakcijaFormOkBTN;
    @FXML
    private DatePicker transakcijaFormDate;
    @FXML
    private Button transakcijaFormCancelBTN;
    @FXML
    private Label validnostPodatakaLBL;

    /**
     * Initializes the controller class.
     */
    private TransakcijaOO old;
    private Boolean isUpdating = false;
    private Boolean validniPodaci = false;
    private String vrstaTransakcije;
    private String primalac;
    private String posiljalac;
    private Double iznos;
    private Date datum;

    public TransakcijaFormController(TransakcijaOO it, Boolean updateFlag) {

        if (updateFlag) {

            isUpdating = updateFlag;
            old = it;
            vrstaTransakcije = old.getVrstaTransakcije();
            primalac = old.getPrimalac();
            posiljalac = old.getPosaljilac();
            iznos = old.getIznos();
            datum = old.getDatumTransakcije();

        }
    }

    public void updateTransakcija() {

        TransakcijaOO it = new TransakcijaOO(
                old.getTransakcijaId(),
                transakcijaFormVrstaTransakcijeTXT.getText(),
                transakcijaFormPrimalacTXT.getText(),
                transakcijaFormPosiljalacTXT.getText(),
                Date.from(transakcijaFormDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), // too much trouble for nothing
                Double.parseDouble(transakcijaFormIznosTXT.getText())
        );

        TransakcijaAdapter.izmijeni(it);
    }

    public void addTransakcija() {

        TransakcijaOO it = new TransakcijaOO(
                null,
                transakcijaFormVrstaTransakcijeTXT.getText(),
                transakcijaFormPrimalacTXT.getText(),
                transakcijaFormPosiljalacTXT.getText(),
                Date.from(transakcijaFormDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), // too much trouble for nothing
                Double.parseDouble(transakcijaFormIznosTXT.getText())
        );
        TransakcijaAdapter.unesi(it);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        validnostPodatakaLBL.setVisible(false);
        transakcijaFormDate.setEditable(false);
        omoguciSamoDecimalneBrojeve(transakcijaFormIznosTXT);
        //unosDatumaFormatter();

        if (isUpdating) {
            transakcijaFormVrstaTransakcijeTXT.setText(vrstaTransakcije);
            transakcijaFormPrimalacTXT.setText(primalac);
            transakcijaFormPosiljalacTXT.setText(posiljalac);
            transakcijaFormDate.setValue(LocalDate.now());
            transakcijaFormIznosTXT.setText(iznos.toString());

        }

        transakcijaFormOkBTN.setOnAction((event) -> {
            //TODO Provjeriti da li su unesena polja i sl
            provjeriPodatke();
            if (validniPodaci == true) {
                if (isUpdating) {

                    updateTransakcija();

                } else {

                    addTransakcija();
                }
                validnostPodatakaLBL.setVisible(false);
                Stage stage = (Stage) transakcijaFormOkBTN.getScene().getWindow();
                stage.close();
            } else {
                validnostPodatakaLBL.setVisible(true);
            }
        });

        transakcijaFormCancelBTN.setOnAction((event) -> {

            Stage stage = (Stage) transakcijaFormCancelBTN.getScene().getWindow();
            stage.close();

        });
    }

    private void provjeriPodatke() {
        if ("".equals(transakcijaFormVrstaTransakcijeTXT.getText()) || "".equals(transakcijaFormPrimalacTXT.getText())
                || "".equals(transakcijaFormPosiljalacTXT.getText()) || "".equals(transakcijaFormIznosTXT.getText())
                || transakcijaFormDate.getValue() == null || transakcijaFormDate.getValue().isAfter(LocalDate.now())) {
            validniPodaci = false;

        } else {
            validniPodaci = true;
        }
    }

    private void omoguciSamoDecimalneBrojeve(TextField unos) {
        Pattern validDoubleText = Pattern.compile("[0-9]*[.]?[0-9]*");

        TextFormatter<Double> textFormatter = new TextFormatter<Double>(new DoubleStringConverter(), null,
                change -> {
                    String newText = change.getControlNewText();
                    if (validDoubleText.matcher(newText).matches()) {
                        return change;
                    } else {
                        return null;
                    }
                });

        transakcijaFormIznosTXT.setTextFormatter(textFormatter);

    }

}
