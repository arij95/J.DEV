/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Entites.ReservationVols;
import Entites.Utilisateur;
import Entites.Vols;
import Services.ReservationVolService;
import Services.UtilisateurServices;
import Services.VolService;
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
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * FXML Controller class
 *
 * @author RANI
 */
public class AffichzgeRechercheFXMLController implements Initializable {

     @FXML
    private TableColumn<Vols, Date> dv;
    @FXML
    private TableColumn<Vols, Date> fv;
    @FXML
    private TableColumn<Vols, String> de;
    @FXML
    private TableColumn<Vols, String> ar;
    @FXML
    private TableColumn<Vols, Float> p;
    @FXML
    private TableColumn<Vols, String> des;
        @FXML
    private TableColumn<Vols, String> type;
    private TableView<Vols> tableVolsss;
    @FXML
    private TableView<Vols> tabrecherche;
    @FXML
    private Button btn_find;
    @FXML
    private TextField txt_dd;
    @FXML
    private TextField txt_aa;
    @FXML
    private Button btn_sshow;
    @FXML
    private TableColumn<Vols, Integer> placedisponible;
    @FXML
    private Button reserver;
    @FXML
    private Label prixx;
    @FXML
    private TextField nbradulte;
    @FXML
    private TextField nbrenfant;
    @FXML
    private Button imprimer;
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
         BooleanBinding booleanBinding = 
      (
        nbradulte.textProperty().isEqualTo("")).or(
        nbrenfant.textProperty().isEqualTo(""));

    reserver.disableProperty().bind(booleanBinding);
        
    }
     @FXML
        public void find() throws SQLException {
        VolService service = new VolService();
      String arrive = txt_aa.getText();
        String depart  = txt_dd.getText();
         

        /* add column to the tableview and set its items */
        ObservableList<Vols> listeVols = FXCollections.observableArrayList(service.findVolParIdaff(arrive,depart));
         System.out.println(listeVols);
        tabrecherche.setItems(listeVols);

        de.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille_arrive());
            }
        });
      
        dv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Vols, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_depart());
            }
        });
        fv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Vols, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_arrive());
            }
        });
        ar.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille_depart());
            }
        });
        des.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });

        /*    columnPrix .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, float>, ObservableValue<float>>() {
         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, float> param) {
         return new ReadOnlyObjectWrapper(param.getValue().getPrix());
         }
         });    */
           p .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Float>, ObservableValue<Float>>() {
         @Override
         public ObservableValue<Float> call(TableColumn.CellDataFeatures<Vols, Float> param) {
         return new ReadOnlyObjectWrapper(param.getValue().getPrix());
         }
         });
           type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getType_vol());
            }
        });
           placedisponible.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Vols, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPlacedisp());
            }
        });
              System.out.println(listeVols);
    }
        // TODO

  

    @FXML
    private void sshow(ActionEvent event) throws SQLException {
        VolService service = new VolService();


        /* add column to the tableview and set its items */
        ObservableList<Vols> listeVols = FXCollections.observableArrayList(service.getALLVolss());
        tabrecherche.setItems(listeVols);

          de.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille_arrive());
            }
        });
      
        dv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Vols, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_depart());
            }
        });
        fv.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Date>, ObservableValue<Date>>() {
            @Override
            public ObservableValue<Date> call(TableColumn.CellDataFeatures<Vols, Date> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDate_arrive());
            }
        });
        ar.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getVille_depart());
            }
        });
        des.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getDescription());
            }
        });

        /*    columnPrix .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, float>, ObservableValue<float>>() {
         @Override
         public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, float> param) {
         return new ReadOnlyObjectWrapper(param.getValue().getPrix());
         }
         });    */
           p .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Float>, ObservableValue<Float>>() {
         @Override
         public ObservableValue<Float> call(TableColumn.CellDataFeatures<Vols, Float> param) {
         return new ReadOnlyObjectWrapper(param.getValue().getPrix());
         }
         });
           type.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Vols, String> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getType_vol());
            }
        });
           placedisponible.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vols, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Vols, Integer> param) {
                return new ReadOnlyObjectWrapper(param.getValue().getPlacedisp());
            }
        });
          
    }
int mm=1;
    @FXML
    private void reserver(ActionEvent event) throws SQLException {
        
          if (tabrecherche.getSelectionModel().getSelectedItem() != null) {

            
             int idv = tabrecherche.getSelectionModel().getSelectedItem().getId_vol();
             int ida = tabrecherche.getSelectionModel().getSelectedItem().getId_agence();
              System.out.println(idv);
              System.out.println(ida);
             int nbra=Integer.parseInt(nbradulte.getText());
             int nbre=Integer.parseInt(nbrenfant.getText());
             float pr=tabrecherche.getSelectionModel().getSelectedItem().getPrix();
             double p=pr*nbra+(pr*0.8*nbre);
             mm= (int)p;
             prixx.setText(Double.toString(p));
             int nbr=nbre+nbra;
             int pla=tabrecherche.getSelectionModel().getSelectedItem().getPlacedisp();
             if(nbr>pla)
             {Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("Seulement "+pla+" places disponible!");
            alert2.show();}else{
             UtilisateurServices su = new UtilisateurServices();
             Utilisateur u = su.retournerUtilisateur();
             ReservationVols v=new ReservationVols(idv,u.getIdu(),ida,nbr,mm);
             ReservationVolService sa=new ReservationVolService();
                 try {
                     sa.ajoutreservation(v);
                 } catch (SQLException ex) {
                     Logger.getLogger(AffichzgeRechercheFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             ReservationVolService ee=new ReservationVolService();
             ee.updateplaces(idv,pla,nbr);
             sendMail();
        
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
            PdfWriter.getInstance(pdfReport, new FileOutputStream("ReservationVol.pdf"));
            pdfReport.open();
            pdfReport.add(new Paragraph("les reservations"));
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            pdfReport.add(Chunk.NEWLINE);
            
            
            PdfPTable my_report_table = new PdfPTable(6);
            
            /* PdfPCell tableCellColumn = new PdfPCell(new Phrase("debut vol"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("fin volt"));
            my_report_table.addCell(tableCellColumn);*/
            PdfPCell  tableCellColumn = new PdfPCell(new Phrase("ville depart"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("ville arrive"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("prix"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("description"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("type vol"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("place"));
            my_report_table.addCell(tableCellColumn);
            
            /*
            tableCellColumn = new PdfPCell(new Phrase("Quantite"));
            my_report_table.addCell(tableCellColumn);
            tableCellColumn = new PdfPCell(new Phrase("Date"));
            my_report_table.addCell(tableCellColumn);
            */
            double h= 0;
            tabrecherche.getItems().forEach((Vols e) -> {
                
                PdfPCell  tableCell = new PdfPCell(new Phrase(e.getVille_depart()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getVille_arrive()));
                my_report_table.addCell(tableCell);
                
                
                String Prix = "" + e.getPrix();
                tableCell = new PdfPCell(new Phrase(Prix));
                my_report_table.addCell(tableCell);
                
                
                tableCell = new PdfPCell(new Phrase(e.getDescription()));
                my_report_table.addCell(tableCell);
                
                tableCell = new PdfPCell(new Phrase(e.getType_vol()));
                
                my_report_table.addCell(tableCell);
                
                String quant = " "+e.getPlacedisp();
                
                // double h = h + e.getPrixProduit()*e.getQuantite();
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
    

