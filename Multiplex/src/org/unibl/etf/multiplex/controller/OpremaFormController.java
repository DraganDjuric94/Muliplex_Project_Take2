package org.unibl.etf.multiplex.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.unibl.etf.model.adapter.OpremaAdapter;
import org.unibl.etf.model.domain.oo.OpremaOO;

/**
 * FXML Controller class
 *
 * @author Miloš
 */
public class OpremaFormController implements Initializable {

    @FXML
    private AnchorPane opremaForm;
    @FXML
    private TextField opremaFormBrojInventaraTXT;
    @FXML
    private TextField opremaFormNazivTXT;
    @FXML
    private Button opremaFormOkBTN;
    @FXML
    private Button opremaFormCancelBTN;
    @FXML
    private CheckBox opremaFormIspravnaCHK;

    /**
     * Initializes the controller class.
     */
    private OpremaOO old;
    private Boolean isUpdating = false;
    private String brojInventara;
    private String naziv;
    private Boolean ispravnost;
    
    public OpremaFormController(OpremaOO it, Boolean updateFlag) {
        
        if(updateFlag) {
            
            isUpdating = updateFlag;
            old = it;
            brojInventara = old.getBrojInventara();
            naziv = old.getNaziv();
            ispravnost = old.getIspravnost();
        }
    }
    
    public void updateOprema() {
        
        OpremaOO it = new OpremaOO(
                                old.getOpremaId(), 
                                opremaFormBrojInventaraTXT.getText(), 
                                opremaFormNazivTXT.getText(), 
                                opremaFormIspravnaCHK.isSelected()
                            );
        OpremaAdapter.izmijeni(it);
    }
    
    public void addOprema() {
    
        OpremaOO it = new OpremaOO(
                                null, 
                                opremaFormBrojInventaraTXT.getText(), 
                                opremaFormNazivTXT.getText(), 
                                opremaFormIspravnaCHK.isSelected()
                            );
        OpremaAdapter.unesi(it);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(isUpdating){
         
            opremaFormBrojInventaraTXT.setText(brojInventara);
            opremaFormNazivTXT.setText(naziv);
            opremaFormIspravnaCHK.setSelected(ispravnost);   
        }
        
        opremaFormOkBTN.setOnAction((event) -> {
            //TODO Provjeriti da li su unesena polja i sl
            if(isUpdating) {
                
                updateOprema();
                
            } else {
                
                addOprema();
            } 
            Stage stage = (Stage) opremaFormOkBTN.getScene().getWindow();
            stage.close();
        });
        
        opremaFormCancelBTN.setOnAction((event) -> {
        
            Stage stage = (Stage) opremaFormCancelBTN.getScene().getWindow();
            stage.close();
        
        });
    }    
    
}
