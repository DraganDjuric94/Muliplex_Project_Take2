/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unibl.etf.multiplex.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.unibl.etf.model.adapter.FilmAdapter;
import org.unibl.etf.model.adapter.ProjekcijaAdapter;
import org.unibl.etf.model.adapter.SalaAdapter;
import org.unibl.etf.model.domain.Projekcija;
import org.unibl.etf.model.domain.oo.FilmOO;
import org.unibl.etf.model.domain.oo.ProjekcijaOO;
import org.unibl.etf.model.domain.oo.SalaOO;

/**
 * FXML Controller class
 *
 * @author juhu
 */
public class ProjekcijaFormController implements Initializable {

    @FXML
    private ComboBox<String> projekcijaFormFilmCB;
    @FXML
    private ComboBox<String> projekcijaFormSalaCB;
    @FXML
    private DatePicker projekcijaFormDatumDP;
    @FXML
    private TextField projekcijaFormCijenaKarteTXT;
    @FXML
    private Button projekcijaFormPonistiBTN;
    @FXML
    private Button projekcijaFormOkBTN;
    @FXML
    private TextField projekcijaFormVrijemeTXT;
    @FXML
    private Label validnostPodatakaLBL;

    /**
     * Initializes the controller class.
     */
    private boolean azuriranje = false;
    private Boolean validniPodaci = false;
    private ProjekcijaOO stari;
    private String vrijeme, cijenaKarte, datum, film, sala;
    private ObservableList<String> filmovi = FXCollections.observableArrayList();
    private ObservableList<String> sale = FXCollections.observableArrayList();

    public ProjekcijaFormController(ProjekcijaOO projekcija, boolean azuriranje) {
        if (azuriranje) {
            this.azuriranje = azuriranje;
            stari = projekcija;
            LocalDateTime datVrij = LocalDateTime.ofInstant(projekcija.getDatumVrijeme().toInstant(), ZoneId.systemDefault());
            vrijeme = datVrij.toString().split("T")[1];
            datum = datVrij.toString().split("T")[0];
            cijenaKarte = projekcija.getCijenaKarte().toString();
            film = projekcija.getFilm().getFilmId() + "# " + projekcija.getFilm().getNaziv();
            sala = projekcija.getSala().getSalaId().toString();
        }
        preuzmiSale();
        preuzmiFilmove();
    }

    public void preuzmiSale() {
        ArrayList<SalaOO> temPs = SalaAdapter.preuzmiSve();
        sale.clear();
        for (SalaOO s : temPs) {
            if (!sale.contains(s.getSalaId().toString())) {
                sale.add(s.getSalaId().toString());
            }
        }
    }

    public void preuzmiFilmove() {
        ArrayList<FilmOO> temPf = FilmAdapter.preuzmiSve();
        filmovi.clear();
        for (FilmOO f : temPf) {
            if (!filmovi.contains(f.getFilmId().toString() + "# " + f.getNaziv())) {
                filmovi.add(f.getFilmId().toString() + "# " + f.getNaziv());
            }
        }
    }

    public void azuriraj() {
        LocalDateTime datVrij = LocalDateTime.of(LocalDate.parse(projekcijaFormDatumDP.getValue().toString()), LocalTime.parse(projekcijaFormVrijemeTXT.getText()));
        ProjekcijaOO proj = new ProjekcijaOO(stari.getProjekcijaId(), Date.from(datVrij.atZone(ZoneId.systemDefault()).toInstant()),
                null, null,
                Double.parseDouble(projekcijaFormCijenaKarteTXT.getText()),
                null);
        if (projekcijaFormFilmCB
                .getSelectionModel()
                .getSelectedItem()
                .equals(stari.getFilm().getFilmId() + "# " + stari.getFilm().getNaziv())) {
            proj.setFilm(stari.getFilm());
        } else {
            proj.setFilm(FilmAdapter.preuzmiPoId(Integer.parseInt(projekcijaFormFilmCB.getSelectionModel().getSelectedItem().split("# ")[0])));
        }

        if (projekcijaFormSalaCB
                .getSelectionModel()
                .getSelectedItem()
                .equals(stari.getSala().getSalaId().toString())) {
            proj.setSala(stari.getSala());
        } else {
            proj.setSala(SalaAdapter.preuzmiPoId(Integer.parseInt(projekcijaFormSalaCB.getSelectionModel().getSelectedItem())));
        }

        System.out.println(ProjekcijaAdapter.izmijeni(proj, true));
    }

    public void dodaj() {
        LocalDateTime datVrij = LocalDateTime.of(LocalDate.parse(projekcijaFormDatumDP.getValue().toString()), LocalTime.parse(projekcijaFormVrijemeTXT.getText()));
        ProjekcijaOO proj = new ProjekcijaOO(null, Date.from(datVrij.atZone(ZoneId.systemDefault()).toInstant()),
                null, null,
                Double.parseDouble(projekcijaFormCijenaKarteTXT.getText()),
                null);
        proj.setFilm(FilmAdapter.preuzmiPoId(Integer.parseInt(projekcijaFormFilmCB.getSelectionModel().getSelectedItem().split("# ")[0])));
        proj.setSala(SalaAdapter.preuzmiPoId(Integer.parseInt(projekcijaFormSalaCB.getSelectionModel().getSelectedItem())));

        ProjekcijaAdapter.dodaj(proj);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        projekcijaFormDatumDP.setEditable(false);
        validnostPodatakaLBL.setVisible(false);
        omoguciSamoDecimalneBrojeve(projekcijaFormCijenaKarteTXT);

        projekcijaFormFilmCB.setItems(filmovi);
        projekcijaFormSalaCB.setItems(sale);

        if (azuriranje) {
            projekcijaFormFilmCB.getSelectionModel().select(film);
            projekcijaFormSalaCB.getSelectionModel().select(sala);
            projekcijaFormDatumDP.setValue(LocalDate.parse(datum));
            projekcijaFormVrijemeTXT.setText(vrijeme);
            projekcijaFormCijenaKarteTXT.setText(cijenaKarte);
        } else {
            projekcijaFormFilmCB.getSelectionModel().select(0);
            projekcijaFormSalaCB.getSelectionModel().select(0);
        }

    }

    @FXML
    private void projekcijaFormPonistiBTNHandler(ActionEvent event) {
        ((Stage) projekcijaFormCijenaKarteTXT.getScene().getWindow()).close();
    }

    @FXML
    private void projekcijaFormOkBTNHandler(ActionEvent event) {
        System.out.println(projekcijaFormDatumDP.getValue());
        provjeriPodatke();
        if (validniPodaci == true) {
            if (azuriranje) {
                azuriraj();
            } else {
                dodaj();
            }
            validnostPodatakaLBL.setVisible(false);
            ((Stage) projekcijaFormCijenaKarteTXT.getScene().getWindow()).close();
        } else {
            validnostPodatakaLBL.setVisible(true);

        }

    }

    private void provjeriPodatke() {
        
        
        LocalDateTime  trenutno = LocalDateTime.now();
       
        if ("".equals(projekcijaFormCijenaKarteTXT.getText()) || "".equals(projekcijaFormVrijemeTXT.getText())
                || projekcijaFormDatumDP.getValue() == null) {
            validniPodaci = false;
        } else {
            if(projekcijaFormVrijemeTXT.getText().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
                LocalDateTime datVrij = LocalDateTime.of(LocalDate.parse(projekcijaFormDatumDP.getValue().toString()), LocalTime.parse(projekcijaFormVrijemeTXT.getText()));
                ArrayList<ProjekcijaOO> danasnje = ProjekcijaAdapter.preuzmiSve();
                Iterator<ProjekcijaOO> it = danasnje.iterator();
                FilmOO filmZaPr = FilmAdapter.preuzmiPoId(Integer.parseInt(projekcijaFormFilmCB.getSelectionModel().getSelectedItem().split("#")[0]));
                SalaOO salaZaPr = SalaAdapter.preuzmiPoId(Integer.parseInt(projekcijaFormSalaCB.getSelectionModel().getSelectedItem()));
                boolean dobroVrijeme = true;
                while(it.hasNext()){
                    ProjekcijaOO pr = it.next();
                    LocalDateTime odPr = LocalDateTime.ofInstant(pr.getDatumVrijeme().toInstant(), ZoneId.systemDefault());
                    if(datVrij.equals(odPr) && pr.getSala().equals(salaZaPr)){
                        dobroVrijeme = false;
                        break;
                    }else if(datVrij.isBefore(odPr) && pr.getSala().equals(salaZaPr)){
                        if(!datVrij.plusMinutes(filmZaPr.getTrajanje() + 30).isBefore(odPr)){
                            dobroVrijeme = false;
                            break;
                        }
                    }else if(datVrij.isAfter(odPr) && pr.getSala().equals(salaZaPr)){
                        if(!odPr.plusMinutes(pr.getFilm().getTrajanje() + 30).isBefore(datVrij)){
                            dobroVrijeme = false;
                            break;
                        }
                    }
                }
                if(datVrij.isAfter(trenutno) && dobroVrijeme){
                    validniPodaci = true;
                }else{
                    validniPodaci = false;
                }
            } else {
                validniPodaci = false;
            }
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

        unos.setTextFormatter(textFormatter);
    }

}
