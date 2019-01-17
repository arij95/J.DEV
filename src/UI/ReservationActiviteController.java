
package UI;

import Entites.Activite;
import Entites.ReservationActivite;
import Entites.Utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import Services.ActiviteService;
import Services.ServiceReservationActivite;
import Services.UtilisateurServices;
import javafx.scene.control.Label;

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
import java.util.Properties;

public class ReservationActiviteController implements Initializable {

    @FXML
    private TableView<Activite> tablereservation;
    @FXML
    private TableColumn<Activite, String> nom;
    @FXML
    private TableColumn<Activite, String> type;
    @FXML
    private TableColumn<Activite, String> adresse;
    @FXML
    private TableColumn<Activite, String> pays;
    @FXML
    private TableColumn<Activite, String> region;
    @FXML
    private TableColumn<Activite, String> description;
    @FXML
    private TableColumn<Activite, Float> prix;
    @FXML
    private Button reserver;
    @FXML
    private TextField nbrenfant;
    @FXML
    private TextField nbradulte;
    @FXML
    private Label prixx;
    @FXML
    private TableColumn<Activite, Integer> place;

    
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
        ActiviteService service = new ActiviteService();
        ObservableList<Activite> listeHebergements = FXCollections.observableArrayList(service.afficherActivite());
        tablereservation.setItems(listeHebergements);
        
       
        
        
        nom.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Activite, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getNom());
            }
        });
         TableColumn albumArt = new TableColumn("Album Art");
        albumArt.setCellValueFactory(new PropertyValueFactory("album"));
        albumArt.setPrefWidth(200);
        type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Activite, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getType());
            }
        });
        adresse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Activite, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getAdresse());
            }
        });
        pays.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Activite, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPays());
            }
        });
        region.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Activite, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getRegion());
            }
        });
        description.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Activite, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });
        prix.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, Float>, ObservableValue<Float>>() {
            @Override
            public ObservableValue<Float> call(TableColumn.CellDataFeatures<Activite, Float> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPrix());
            }
        });
        place.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Activite, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Activite, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPlacedisponible());
            }
        });
        
        
        
}
int mm=1;

    @FXML
    private void reserver(ActionEvent event) throws SQLException{
        
         if (tablereservation.getSelectionModel().getSelectedItem() != null) {

            
             int idv = tablereservation.getSelectionModel().getSelectedItem().getId();
             int ida = tablereservation.getSelectionModel().getSelectedItem().getId_agence();
             int nbra=Integer.parseInt(nbradulte.getText());
             int nbre=Integer.parseInt(nbrenfant.getText());
             float pr=tablereservation.getSelectionModel().getSelectedItem().getPrix();
             double p=pr*nbra+(pr*0.8*nbre);
             mm= (int) p ;
             prixx.setText(Double.toString(p));
             int nbr=nbre+nbra;
             int pla=tablereservation.getSelectionModel().getSelectedItem().getPlacedisponible();
             if(nbr>pla)
             {Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Seulement "+pla+" places disponible!");
            alert2.show();}else{
             UtilisateurServices su = new UtilisateurServices();
             Utilisateur u = su.retournerUtilisateur();
                 ReservationActivite v=new ReservationActivite(idv,u.getIdu(),ida,mm,nbr);
                                  ReservationActivite v1=new ReservationActivite(idv, u.getIdu(), ida,mm, nbr);

             ServiceReservationActivite sa=new ServiceReservationActivite();
                 System.out.println(v);
                 System.out.println(v1);
             sa.ajoutreservation(v1);
             
             ServiceReservationActivite ee=new ServiceReservationActivite();
             ee.updateplaces(idv,pla,nbr);
             sendMail() ;
        rafrechir();
       
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
			System.out.println(e);
		}

    }
    
    
}