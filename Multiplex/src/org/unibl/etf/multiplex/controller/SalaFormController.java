/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.SalaAdapter;
import org.unibl.etf.model.domain.Sjediste;
import org.unibl.etf.model.domain.oo.SalaOO;
import org.unibl.etf.model.domain.oo.SjedisteOO;

/**
 * FXML Controller class
 *
 * @author Aleksandar
 */
public class SalaFormController implements Initializable {

    private static UnaryOperator<Change> prirodniBrojevi() {
        UnaryOperator<Change> naturalNumberFilter = change -> {
            String text = change.getControlNewText();

            if (!change.isContentChange()) {
                return change;
            }

            if (text.matches("[1-9][0-9]*") || text.isEmpty()) {
                return change;
            }

            return null;
        };
        return naturalNumberFilter;
    }

    private boolean azuriranje;
    private SalaOO sala;

    public SalaFormController(SalaOO sala, boolean azuriranje) {
        if (azuriranje) {
            this.sala = sala;
            this.azuriranje = true;
        }
    }

    @FXML
    private TextField salaFormBrojRedovaTXT;
    @FXML
    private TextField salaFormBrojKolonaTXT;
    @FXML
    private Button salaFormPonistiBTN;
    @FXML
    private Button salaFormOkBTN;
    @FXML
    private Label salaFormObavjestenjeOGrescLBL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.salaFormBrojRedovaTXT.setTextFormatter(new TextFormatter<>(prirodniBrojevi()));
        this.salaFormBrojKolonaTXT.setTextFormatter(new TextFormatter<>(prirodniBrojevi()));
        if (this.azuriranje) {
            this.salaFormBrojRedovaTXT.setText(this.sala.getBrojRedova().toString());
            this.salaFormBrojKolonaTXT.setText(this.sala.getBrojKolona().toString());
        }
    }

    @FXML
    private void salaFormPonistiBTNHandler(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void salaFormOkBTNHandler(ActionEvent event) {
        if (provjeriPodatke()) {
            if (this.azuriranje) {
                azuriraj();
            } else {
                dodaj();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            this.salaFormObavjestenjeOGrescLBL.setText("Podaci nisu validni");
            this.salaFormObavjestenjeOGrescLBL.setVisible(true);
        }

    }

    public void dodaj() {
        List<SjedisteOO> sjedista = new ArrayList<>();
        int brojRedova = Integer.parseInt(this.salaFormBrojRedovaTXT.getText());
        int brojKolona = Integer.parseInt(this.salaFormBrojKolonaTXT.getText());

        for (int i = 0; i < brojRedova; i++) {
            for (int j = 0; j < brojKolona; j++) {
                sjedista.add(new SjedisteOO(null, (j + 1), (i + 1)));
            }
        }

        SalaOO novaSala = new SalaOO(
                null,
                brojRedova,
                brojKolona,
                sjedista
        );

        SalaAdapter.unesi(novaSala);

    }

    public void azuriraj() {
        this.sala.setBrojRedova(Integer.parseInt(this.salaFormBrojRedovaTXT.getText()));
        this.sala.setBrojKolona(Integer.parseInt(this.salaFormBrojKolonaTXT.getText()));

        SalaAdapter.izmijeni(sala);

    }

    public boolean provjeriPodatke() {
        return ((null != this.salaFormBrojRedovaTXT.getText())
                && (null != this.salaFormBrojKolonaTXT.getText()));
    }

}
