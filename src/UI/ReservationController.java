/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entites.Agence;
import Entites.ReservationVoyageOrganise;
import Entites.Utilisateur;
import Entites.Vols;
import Entites.VoyageOrganise;
import Services.ServiceReservation;
import Services.ServiceVoyageOrganise;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import Services.UtilisateurServices;
import Services.VolService;
import static UI.GererVoyageOrganiseController.he;
import Utiles.Singleton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.beans.binding.BooleanBinding;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ReservationController implements Initializable {

    @FXML
    private TableView<VoyageOrganise> tablereservation;
    
    @FXML
    private TableColumn<VoyageOrganise, Integer> prix;
    @FXML
    private TableColumn<VoyageOrganise, Date> datedepart;
    @FXML
    private TableColumn<VoyageOrganise, Date> dateretour;
    @FXML
    private TableColumn<VoyageOrganise, String> origine;
    @FXML
    private TableColumn<VoyageOrganise, String> paysdestination;
    @FXML
    private TableColumn<VoyageOrganise, String> villeddestination;
    @FXML
    private TableColumn<VoyageOrganise, Integer> placesdisponibles;
    @FXML
    private TableColumn<VoyageOrganise, String> hotel;
    
    @FXML
    private TableColumn<VoyageOrganise, String> nomagence;
    @FXML
    private TextField nbrenfant;
    @FXML
    private TextField nbradulte;
    @FXML
    private Label prixx;
    @FXML
    private Button reserver;
    @FXML
    private Button imprimer;
    @FXML
    private TableColumn<VoyageOrganise, ImageView> imager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rafrechir();
        BooleanBinding booleanBinding = 
      (
        nbradulte.textProperty().isEqualTo("")).or(
        nbrenfant.textProperty().isEqualTo(""));

    reserver.disableProperty().bind(booleanBinding);
        
    }    
    public void rafrechir()
    {
        ServiceVoyageOrganise service = new ServiceVoyageOrganise();
        ObservableList<VoyageOrganise> listeHebergements = FXCollections.observableArrayList((VoyageOrganise.generateImageViews(service.afficherVoyageOrganise())));
        tablereservation.setItems(listeHebergements);
        
       
        imager.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<VoyageOrganise, ImageView> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getImgview());
            }
        });
        
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<VoyageOrganise, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix_voyage());
            }
        });
         TableColumn albumArt = new TableColumn("Album Art");
        albumArt.setCellValueFactory(new PropertyValueFactory("album"));
        albumArt.setPrefWidth(200);
        datedepart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VoyageOrganise, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_depart());
            }
        });
        dateretour.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<VoyageOrganise, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_retour());
            }
        });
        origine.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VoyageOrganise, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getOrigine());
            }
        });
        paysdestination.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VoyageOrganise, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPays_destination());
            }
        });
        villeddestination.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VoyageOrganise, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille_destination());
            }
        });
        placesdisponibles.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<VoyageOrganise, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getnb_places());
            }
        });
        hotel.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VoyageOrganise, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getHotel());
            }
        });
        
        nomagence.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<VoyageOrganise, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<VoyageOrganise, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom_agence());
            }
        });
        
        
}
int e=1;
    @FXML
    private void reserver(ActionEvent event) throws SQLException{
        
        
        
         if (tablereservation.getSelectionModel().getSelectedItem() != null) {

            
             int idv = tablereservation.getSelectionModel().getSelectedItem().getId();
             int ida = tablereservation.getSelectionModel().getSelectedItem().getId_agence();
             int nbra=Integer.parseInt(nbradulte.getText());
             int nbre=Integer.parseInt(nbrenfant.getText());
             int pr=tablereservation.getSelectionModel().getSelectedItem().getPrix_voyage();
             double p=pr*nbra+(pr*0.8*nbre);
             e=(int) p;
             prixx.setText(Double.toString(p));
             int nbr=nbre+nbra;
             int pla=tablereservation.getSelectionModel().getSelectedItem().getnb_places();
             if(nbr>pla)
             {Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Seulement "+pla+" places disponible!");
            alert2.show();}else{
             UtilisateurServices su = new UtilisateurServices();
             Utilisateur u = su.retournerUtilisateur();
             ReservationVoyageOrganise v=new ReservationVoyageOrganise(idv,u.getIdu(),ida,e,nbr);
             ServiceReservation sa=new ServiceReservation();
             sa.ajoutreservation(v);
             ServiceReservation ee=new ServiceReservation();
             ee.updateplaces(idv,pla,nbr);
             sendMail();
        rafrechir();
        Alert alert55 = new Alert(Alert.AlertType.ERROR);
            alert55.setTitle("information Dialog");
            alert55.setHeaderText(null);
            alert55.setContentText("Reservation avec succes");
            alert55.show();
             }} else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un voyage organise");
            alert.show();
        }
        
        
    }
     public void sendMail() throws SQLException {
          Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
                

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("raniamnissi1995","rania1234");
				}
			});
                

		try {
  UtilisateurServices su = new UtilisateurServices();
                Utilisateur u = su.retournerUtilisateur();
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(u.getEmailu()));
			message.setSubject("Reservation");
			message.setText("votre réservaion a été prise en compte");

			Transport.send(message);

			System.out.println("Done");
            
   

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

    }

   @FXML
    private void imprimer(ActionEvent event) throws FileNotFoundException {
       try {
           VolService service = new VolService();
           Document pdfReport = new Document();
           PdfWriter.getInstance(pdfReport, new FileOutputStream("Reservation.pdf"));
          pdfReport.open();
          pdfReport.add(new Paragraph("les reservations"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
          
          PdfPTable my_report_table = new PdfPTable(7);
          
          /* PdfPCell tableCellColumn = new PdfPCell(new Phrase("debut vol"));
           my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("fin volt"));
           my_report_table.addCell(tableCellColumn);*/
           PdfPCell  tableCellColumn = new PdfPCell(new Phrase("Prix"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("origine"));
          my_report_table.addCell(tableCellColumn);
          tableCellColumn = new PdfPCell(new Phrase("paye destination"));
            my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("ville destination"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("place disponible"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("hotel"));
           my_report_table.addCell(tableCellColumn);
           tableCellColumn = new PdfPCell(new Phrase("nom agence"));
           my_report_table.addCell(tableCellColumn);
           
           
           
            tableCellColumn = new PdfPCell(new Phrase("Quantite"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("Date"));
            my_report_table.addCell(tableCellColumn);
            
            double h= 0;
            tablereservation.getItems().forEach((VoyageOrganise e) -> {
                String Prix = "" + e.getPrix_voyage();
               PdfPCell tableCell = new PdfPCell(new Phrase(Prix));
                my_report_table.addCell(tableCell);
                
                  tableCell = new PdfPCell(new Phrase(e.getOrigine()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getPays_destination()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getPays_destination()));
                my_report_table.addCell(tableCell);
                
                 String quant = " "+e.getnb_places();
                tableCell = new PdfPCell(new Phrase(quant));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getHotel()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getNom_agence()));
                my_report_table.addCell(tableCell);
                
                
                
                
                
                 
            });
            /* Attach report table to PDF */
            pdfReport.add(my_report_table);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
            
            
            
            pdfReport.close();
            
            Alert alertReservation = new Alert(Alert.AlertType.INFORMATION);
            alertReservation.setTitle("Extraction en PDF");
            alertReservation.setHeaderText(null);
            alertReservation.setContentText("PDF report has been created.\nYou'll find "
                    + "the file under: C:\\Users\\RANI\\Desktop\\final\\holdayspi_1copie");
            alertReservation.showAndWait();
        } catch (DocumentException ex) {
            System.out.println(ex);
        }
    }

   
    
    
}

    

