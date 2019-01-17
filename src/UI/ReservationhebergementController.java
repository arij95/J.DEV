/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entites.Hebergement;
import Entites.ReservationHebergement;
import Entites.Utilisateur;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import Services.UtilisateurServices;
import Services.serviceHebergement;
import Services.serviceReservationHebergement;
import com.itextpdf.text.BadElementException;
import javafx.scene.control.Label;

 import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import net.glxn.qrgen.image.ImageType;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author Y520-I7-1TR-4G
 */
public class ReservationhebergementController implements Initializable {

    @FXML
    private TableView<Hebergement> tab;
    @FXML
    private TableColumn<Hebergement, ImageView> image;
    @FXML
    private TableColumn<Hebergement, String> nomAgence;
    @FXML
    private TableColumn<Hebergement, String> type_Hebergement;
    @FXML
    private TableColumn<Hebergement, String> nom_Hebergement;
    @FXML
    private TableColumn<Hebergement, Integer> nombre_etoile;
   
    @FXML
    private TableColumn<Hebergement, Integer> nombre_chambre;
    @FXML
    private TableColumn<Hebergement, Integer> prix_single;
    @FXML
    private TableColumn<Hebergement, Integer> prix_double;
    @FXML
    private TableColumn<Hebergement, Integer> taux_demi;
    @FXML
    private TableColumn<Hebergement, Integer> taux_complet;
    @FXML
    private TableColumn<Hebergement, Integer> tel;
    @FXML
    private TableColumn<Hebergement, String> description;
    @FXML
    private TextField rechercherdestination;
    @FXML
    private TableColumn<Hebergement, String> Adresse_Heb;
    @FXML
    private Button reserver;
    @FXML
    private TextField nbrs;
    @FXML
    private TextField nbrn;
   
    
    @FXML
    private Label prixx;
    @FXML
    private RadioButton demip;
    @FXML
    private RadioButton completep;
    

    /**
     * Initializes the controller class.
     *
     */
      public static final String ACCOUNT_SID = "AC8e036098d97a5901324606f509b29256";
    public static final String AUTH_TOKEN = "aab3d1636670275481cfc54601ebc47e";
    @FXML
    private RadioButton single;
    @FXML
    private RadioButton doub;
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        rafrechir();
        
        BooleanBinding booleanBinding = 
      (
        nbrs.textProperty().isEqualTo("")).or(
        
        nbrn.textProperty().isEqualTo(""));

    reserver.disableProperty().bind(booleanBinding);
    }    
String sinng="";
String ttt="";
    @FXML
    private void reserver(ActionEvent event) throws SQLException, BadElementException, IOException, DocumentException, MessagingException {
         if (tab.getSelectionModel().getSelectedItem() != null) {
              int penn=1;
              int pennn=1;
             if(single.isSelected())
             {pennn=tab.getSelectionModel().getSelectedItem().getTaux_complet();
              sinng="Single";}
             else if (doub.isSelected())
             { pennn=tab.getSelectionModel().getSelectedItem().getTaux_demi();
             sinng="Double";
             }
            if(completep.isSelected())
            { penn=tab.getSelectionModel().getSelectedItem().getTaux_complet();
            ttt="tauxComplet";}
             else if (demip.isSelected())
             {penn=tab.getSelectionModel().getSelectedItem().getTaux_demi();
             ttt="tauxDemi";}
             int idv = tab.getSelectionModel().getSelectedItem().getIdHebergement();
             int ida = tab.getSelectionModel().getSelectedItem().getIdAgence();
             
             int nbrss=Integer.parseInt(nbrs.getText());
            
             int nbrnn=Integer.parseInt(nbrn.getText());
             
             int prs=tab.getSelectionModel().getSelectedItem().getPrix_single();
             int prd=tab.getSelectionModel().getSelectedItem().getPrix_double();
             String typ = tab.getSelectionModel().getSelectedItem().getType_Hebergement();
             String nm = tab.getSelectionModel().getSelectedItem().getNom_Hebergement();
             String py = tab.getSelectionModel().getSelectedItem().getAdresse_Hebergement();
             int p=(((pennn*nbrss)*(penn/100))+(pennn*nbrss))*nbrnn;
             prixx.setText(Double.toString(p));
             int nbr=nbrss;
             int pla=tab.getSelectionModel().getSelectedItem().getNombre_chambre();
             if(nbr>pla)
             {Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Seulement "+pla+" chambres disponibles!");
            alert2.show();}else{
             UtilisateurServices su = new UtilisateurServices();
             Utilisateur u = su.retournerUtilisateur();
            /* int a=u.getIdu();
                 System.out.println(a);
                 System.out.println(ida);*/
             ReservationHebergement v=new ReservationHebergement(idv,u.getIdu(),ida,nbrss,p,sinng,ttt,nbrnn);
             serviceReservationHebergement sa=new serviceReservationHebergement();
             sa.ajoutreservation(v);
             serviceReservationHebergement ee=new serviceReservationHebergement();
             ee.updateplaces(idv,pla,nbr);
             Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                 /*   Message message = Message
                            .creator(new PhoneNumber("+21627130976"), new PhoneNumber("+13143107633"),
                                    "Votre a été prise en compte " )
                            .create();
        rafrechir();*/
        
        String details = " Mr/Mrs " + u.getNomu()+  " " + u.getPrenomu()+ ", your reservation in our " + typ+ " the : " + nm + " ," +py  + " for  " + Integer.parseInt(nbrn.getText()) + " places, is confirmed, it costs you : " + Double.parseDouble(prixx.getText()) + " DT.";
        ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(details).to(ImageType.JPG).stream();
        File f = new File("C:\\Users\\Y520-I7-1TR-4G\\Desktop\\kk\\HolidaysNowPI2.0l\\reservationHebergement.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
        
         Document doc = new Document();
       
            
            PdfWriter.getInstance((com.itextpdf.text.Document) doc, new FileOutputStream("C:\\Users\\Y520-I7-1TR-4G\\Desktop\\kk\\HolidaysNowPI2.0l\\reservationHebergement.pdf"));
            doc.open();
            
            Image im = Image.getInstance("C:\\Users\\Y520-I7-1TR-4G\\Desktop\\kk\\HolidaysNowPI2.0l\\reservationHebergement.jpg");
            doc.add(im);
            doc.add(new Paragraph("Mr,Msr "+u.getNomu() + " " + u.getPrenomu() +", Your reservation in our " + typ +" the "+nm+ " for  " + Integer.parseInt(nbrn.getText()) +" places is confirmed, it costs you : " + Double.parseDouble(prixx.getText()) + " DT. Here is your Ticket" +"."));
            
            doc.close();
             
            String host = "smtp.gmail.com";
            String port = "587";
            String mailFrom = "arijmediouni@gmail.com";
            String password = "azertyazerty123";
            
            // message info
            String mailTo = "arij.mediouni@esprit.tn";
            String subject = "Here is your QR Code for your reservation!!!";
            String mess = "I have some attachments for you.";
            
            // attachments
            String[] attachFiles = new String[2];
            attachFiles[0] = "C:\\Users\\Y520-I7-1TR-4G\\Desktop\\kk\\HolidaysNowPI2.0l\\reservationHebergement.pdf";
            attachFiles[1] = "C:\\Users\\Y520-I7-1TR-4G\\Desktop\\kk\\HolidaysNowPI2.0l\\reservationHebergement.jpg";
            serviceReservationHebergement.sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                    subject, mess, attachFiles);
            System.out.println("Email sent.");
            
         
            System.out.println("okk");
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("reservation hebergement avec succés");
            alert.show();
        
             }} else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un hebergement");
            alert.show();
               
            
        
    }
        
    }
       

    @FXML
    private void rechercher(ActionEvent event) throws SQLException {
        
         
        String ad=rechercherdestination.getText();
       serviceReservationHebergement ser = new serviceReservationHebergement();
        
        /* add column to the tableview and set its items */
        ObservableList<Hebergement> listeHebergements = FXCollections.observableArrayList((Hebergement.generateImageViews(ser.retournerHebergementadres(ad))));
       tab.setItems(listeHebergements);
        
       
        image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Hebergement, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
        
         nomAgence.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomAgence());
            }
        });
        type_Hebergement.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getType_Hebergement());
            }
        });
         TableColumn albumArt = new TableColumn("Album Art");
        albumArt.setCellValueFactory(new PropertyValueFactory("album"));
        albumArt.setPrefWidth(200);
        nom_Hebergement.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom_Hebergement());
            }
        });
        nombre_etoile.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNombre_etoile());
            }
        });
        Adresse_Heb.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getAdresse_Hebergement());
            }
        });
        nombre_chambre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNombre_chambre());
            }
        });
        prix_single.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix_single());
            }
        });
        prix_double.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix_double());
            }
        });
        
        taux_demi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTaux_demi());
            }
        });
        taux_complet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTaux_complet());
            }
        });
        tel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTel());
            }
        });
        description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });
} 
        
    
    public void rafrechir()
    {
        serviceHebergement service = new serviceHebergement();
        /* add column to the tableview and set its items */
        ObservableList<Hebergement> listeHebergements = FXCollections.observableArrayList((Hebergement.generateImageViews(service.afficherHebergement())));
       tab.setItems(listeHebergements);
        
       
        image.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Hebergement, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
        
         nomAgence.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNomAgence());
            }
        });
        type_Hebergement.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getType_Hebergement());
            }
        });
         TableColumn albumArt = new TableColumn("Album Art");
        albumArt.setCellValueFactory(new PropertyValueFactory("album"));
        albumArt.setPrefWidth(200);
        nom_Hebergement.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom_Hebergement());
            }
        });
        nombre_etoile.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNombre_etoile());
            }
        });
        Adresse_Heb.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getAdresse_Hebergement());
            }
        });
        nombre_chambre.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNombre_chambre());
            }
        });
        prix_single.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix_single());
            }
        });
        prix_double.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix_double());
            }
        });
        
        taux_demi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTaux_demi());
            }
        });
        taux_complet.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTaux_complet());
            }
        });
        tel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Hebergement, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getTel());
            }
        });
        description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Hebergement, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Hebergement, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });
}

 
} 


