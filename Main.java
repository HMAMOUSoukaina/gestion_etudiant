package application;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Main extends Application {
	//déclarations
    private Stage stage;
    private Scene scene2;
    private Scene scene1;
    private Scene scene3;
    
    private Label nom;
    private Label prenom;
    private Label nm_apogee;
    private Label téléphone;
    private Label email;
    private Label date;
    private Label fillière;
    private Label liste;

    private TextField txt1;
    private TextField txt2;
    private TextField txt3;
    private TextField txt4;
    private TextField txt5;
    private TextField txt6;
    private TextField txt7;

    private Button ajouter;
    private Button modifier;
    private Button supprimer;
    private ButtonBar btn;
    private Button revenir;
    private BorderPane borderPane;
    private GridPane grid3;
    private GridPane grid;
    public ObservableList<etudiant> data = FXCollections.observableArrayList();
    private TableView<etudiant> tableView;
    private TableColumn<etudiant, String> nomColumn;
    private TableColumn<etudiant, String> prenomColumn;
    private TableColumn<etudiant, Integer> nm_apogeeColumn;
    private TableColumn<etudiant, String> téléphoneColumn;
    private TableColumn<etudiant, String> emailColumn;
    private TableColumn<etudiant, String> date_naissanceColumn;
    private TableColumn<etudiant, String> fillièreColumn;
    private VBox vbox;
    
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {
        try {
        	
            nom = new Label("Nom");
            prenom = new Label("Prénom");
            nm_apogee = new Label("Numéro apogée");
            téléphone = new Label("Téléphone");
            date = new Label("Date de naissance");
            email = new Label("Email");
            fillière = new Label("Fillière");
            liste = new Label("Liste des étudiants");
           nom.getStyleClass().add("label1"); 
            prenom.getStyleClass().add("label1"); 
           nm_apogee.getStyleClass().add("label1"); 
           téléphone.getStyleClass().add("label1"); 
            date.getStyleClass().add("label1"); 
            email.getStyleClass().add("label1"); 
           fillière.getStyleClass().add("label1"); 
           liste.getStyleClass().add("bold-label"); 

            txt1 = new TextField();
            txt2 = new TextField();
            txt3 = new TextField();
            txt4 = new TextField();
            txt5 = new TextField();
            txt6 = new TextField();
            txt7 = new TextField();

            ajouter = new Button("Ajouter");
            modifier = new Button("Modifier");
            supprimer = new Button("Supprimer");
            revenir=new Button("précédent");
            ajouter.getStyleClass().add("Button"); 
            supprimer.getStyleClass().add("Button"); 
            modifier.getStyleClass().add("Button"); 
           revenir.getStyleClass().add("revenir"); 
            btn = new ButtonBar();
            btn.getButtons().addAll(ajouter, modifier, supprimer);
            
            vbox=new VBox(15);
            grid = new GridPane();
            grid.add(nom, 0, 1);
            grid.add(prenom, 0, 2);
            grid.add(nm_apogee, 0, 3);
            grid.add(téléphone, 0, 4);
            grid.add(email, 0, 5);
            grid.add(date, 0, 6);
            grid.add(fillière, 0, 7);
            

            grid.add(txt1, 1, 1);
            grid.add(txt2, 1, 2);
            grid.add(txt3, 1, 3);
            grid.add(txt4, 1, 4);
            grid.add(txt5, 1, 5);
            grid.add(txt6, 1, 6);
            grid.add(txt7, 1, 7);
            grid.add(btn, 0, 10, 2, 1);
            grid.add(revenir, 1, 12);

            grid.setHgap(30);
            grid.setVgap(17);
            grid.setPadding(new Insets(15, 10, 10, 10));

            // TableView
            tableView = new TableView<>();

            // Les colonnes du tableView
            nomColumn = new TableColumn<>("Nom");
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

            prenomColumn = new TableColumn<>("Prénom");
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            nm_apogeeColumn = new TableColumn<>("Numéro apogée");
            nm_apogeeColumn.setCellValueFactory(new PropertyValueFactory<>("nm_apogee"));

            téléphoneColumn = new TableColumn<>("Téléphone");
            téléphoneColumn.setCellValueFactory(new PropertyValueFactory<>("téléphone"));

            emailColumn = new TableColumn<>("Email");
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

            date_naissanceColumn = new TableColumn<>("Date de naissance");
            date_naissanceColumn.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));

            fillièreColumn = new TableColumn<>("Filière");
            fillièreColumn.setCellValueFactory(new PropertyValueFactory<>("fillière"));

            // Ajouter les colonnes au TableView
            tableView.getColumns().addAll(nomColumn, prenomColumn, nm_apogeeColumn, téléphoneColumn, emailColumn,
                    date_naissanceColumn, fillièreColumn);

            // Redimensionner les colonnes
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            tableView.getStyleClass().add("tableView"); //appliquer du style pour tableView
            tableView.setPrefHeight(430);
            tableView.setItems(data);
           
            vbox.getChildren().addAll(liste,tableView);
            vbox.setAlignment(Pos.CENTER);
            
            borderPane = new BorderPane();
            borderPane.setPadding(new Insets(10));
            
            borderPane.setCenter(grid);
            borderPane.setRight(vbox);
            borderPane.setMargin(grid, new Insets(17,0,0,3));
            BorderPane b1=new BorderPane();
            revenir.setOnAction(e->{primaryStage.setScene(scene1); });
            
            stage = primaryStage;
            scene2 = new Scene(borderPane, 900, 500);
            scene1=new Scene(b1,700,400);
            scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
           
            scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene1);
            primaryStage.show();
            showEtudiants();
            //la scene 1
            VBox vbox2=new VBox(30);
            Label titre=new Label("Gestion des étudiants");
            titre.getStyleClass().add("scene2"); 
            Button btn1=new Button("Espace Etudiant");
            Button btn2=new Button("Liste des étudiants");
            vbox2.getChildren().addAll(btn1,btn2);
            vbox2.setAlignment(Pos.CENTER);
            btn1.setPrefWidth(170);
            btn1.setPrefHeight(30);
            btn2.setPrefWidth(170);
            btn2.setPrefHeight(30);
            btn1.getStyleClass().add("Button"); 
           btn2.getStyleClass().add("Button"); 
            
            b1.setTop(titre);
            b1.setCenter(vbox2);
           b1.getStyleClass().add("b1"); 
           b1.setMargin(titre, new Insets(20,30,20,190));
            btn1.setOnAction(e->{
            primaryStage.setScene(scene2);
            });
            //scene3
             grid3=new GridPane();
             scene3=new Scene(grid3,600,400);
     
            // clic sur le formulaire fait remplir le formulaire
           tableView.setOnMouseReleased(event -> {
                if (event.getClickCount() == 1) { // Vérifie si c'est un double-clic
                    etudiant selectedEtudiant = tableView.getSelectionModel().getSelectedItem();
                    if (selectedEtudiant != null) {
                        // Afficher les détails de l'étudiant dans le formulaire
                        txt1.setText(selectedEtudiant.getNom());
                        txt2.setText(selectedEtudiant.getPrenom());
                        txt3.setText(Integer.toString(selectedEtudiant.getNm_apogee()));
                        txt4.setText(selectedEtudiant.getTéléphone());
                        txt5.setText(selectedEtudiant.getEmail());
                        txt6.setText(selectedEtudiant.getDate_naissance());
                        txt7.setText(selectedEtudiant.getFillière());
                    }
                }
            });


            // Action des boutons
            ajouter.setOnAction(e -> ajouterEtudiant());
            supprimer.setOnAction(e->supprimerEtudiant());
          modifier.setOnAction(e -> modifierEtudiant());
          //pdf
    

        } catch (Exception e) {
            e.printStackTrace();
        }
        

        
        //pour voir le profile d'un étudiant il faut juste faire un simple clic sur l'étudiant dans table view
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Vérifier si c'est un simple clic
                etudiant selectedEtudiant = tableView.getSelectionModel().getSelectedItem();
                if (selectedEtudiant != null) {
                	 Stage stage3 = new Stage();
                     stage3.setScene(scene3);
                     scene3.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                	stage3.show();
                	stage3.setX(450);
                	stage3.setY(150);
                	
                	String nom = selectedEtudiant.getNom();
                    String prenom = selectedEtudiant.getPrenom();
                    String apogee = Integer.toString(selectedEtudiant.getNm_apogee());
                    String tel = selectedEtudiant.getTéléphone();
                    String email = selectedEtudiant.getEmail();
                    String datee = selectedEtudiant.getDate_naissance();
                    String filliere = selectedEtudiant.getFillière();
                    
                    Label voir_etudiant=new Label("Informations sur l'étudiant");
                    voir_etudiant.getStyleClass().add("bold-label"); 
                    Label Nom=new Label();
                    Label Prenom=new Label();
                    Label Nm_apogee=new Label();
                    Label Telephone=new Label();
                    Label Email=new Label();
                    Label Date_naissance=new Label();
                    Label Filliere=new Label();
                    Button telecharger=new Button("Télécharger PDF");
                    Nom.getStyleClass().add("label2"); 
                    Prenom.getStyleClass().add("label2"); 
                    Nm_apogee.getStyleClass().add("label2"); 
                    Telephone.getStyleClass().add("label2"); 
                    Email.getStyleClass().add("label2"); 
                    Date_naissance.getStyleClass().add("label2"); 
                   Filliere.getStyleClass().add("label2"); 
                   telecharger.getStyleClass().add("Button"); 
                    
                    grid3.add(voir_etudiant, 2, 1,2,2);
                    grid3.add(Nom, 2, 4);
                    grid3.add(Prenom, 2, 6);
                    grid3.add(Nm_apogee, 2, 8);
                    grid3.add(Telephone, 2, 10);
                    grid3.add(Email, 2, 12);
                    grid3.add(Date_naissance, 2, 14);
                    grid3.add(Filliere, 2, 16);
                    grid3.add(telecharger, 3, 18);
                    grid3.setVgap(12);
                    grid3.setPadding(new Insets(10,20,20,50));
                    
                   

                    // Remplir les éléments de la scène avec les valeurs
                    Nom.setText("Nom:   " + nom);
                    Prenom.setText("Prénom:   " + prenom);
                    Nm_apogee.setText("Nm apogée:   " + apogee);
                    Telephone.setText("Téléphone:   " + tel);
                    Email.setText("Email:   " + email);
                    Date_naissance.setText("Date de naissance: " + datee);
                    Filliere.setText("Fillière:   " + filliere);
                    //telecharger pdf
                    telecharger.setOnAction(e -> {
                        generatePDF(selectedEtudiant);
                    });
                    
                }
                
            }
            
        });
        //contraintes pour les textfields
       txt1.addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String input = txt1.getText() + event.getCharacter();
            if (!input.isEmpty() && !Character.isLetter(input.charAt(0))) {
                event.consume();
                showAlert("Erreur", "Le nom doit commencer par une lettre");
            }
        });
       
       txt2.addEventFilter(KeyEvent.KEY_TYPED, event -> {
           String input = txt2.getText() + event.getCharacter();
           if (!input.isEmpty() && !Character.isLetter(input.charAt(0))) {
               event.consume();
               showAlert("Erreur", "Le prénom doit commencer par une lettre");
           }
       });
       txt5.addEventFilter(KeyEvent.KEY_TYPED, event -> {
           String input = txt5.getText() + event.getCharacter();
           if (!input.isEmpty() && !Character.isLetter(input.charAt(0))) {
               event.consume();
               showAlert("Erreur", "l'email doit commencer par une lettre");
           }
       });
       txt7.addEventFilter(KeyEvent.KEY_TYPED, event -> {
           String input = txt7.getText() + event.getCharacter();
           if (!input.isEmpty() && !Character.isLetter(input.charAt(0))) {
               event.consume();
               showAlert("Erreur", "Ce champ doit commencer par une lettre");
           }
       });
      txt4.addEventFilter(KeyEvent.KEY_TYPED, event -> {
           String input = txt4.getText() + event.getCharacter();
           if (!input.matches("\\d{0,10}")) {
               event.consume();
               showAlert1("Erreur", "Le numéro de téléphone doit être composé de 10 chiffres");
           }
       });
      txt3.addEventFilter(KeyEvent.KEY_TYPED, event -> {
          String input = txt3.getText() + event.getCharacter();
          if (!input.matches("\\d*")) {
              event.consume();
              showAlert2("Erreur", "Ce champ doit  être un numéro ");
          }
      });  
  }

  
    //showAlerts()
    private void showAlert1(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showAlert2(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private static void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
  //observable list  
    private ObservableList<etudiant> getEtudiantList() {
        ObservableList<etudiant> etudiantList = FXCollections.observableArrayList();
        Connection cnn = connexion.getConnection();
        String query = "SELECT * FROM etudiant";
        try (Statement st = cnn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                etudiant etudiant = new etudiant(rs.getString("nom"), rs.getString("prenom"), rs.getInt("nm_apogee"),
                        rs.getString("téléphone"), rs.getString("email"), rs.getString("date_naissance"),
                        rs.getString("fillière"));
                etudiantList.add(etudiant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return etudiantList;
    }

 //showEtudiant() 
   public void showEtudiants() {
        ObservableList<etudiant> list = getEtudiantList();
        tableView.setItems(list);
    }
//ajouterEtudiant()
    public void ajouterEtudiant() {
        String nomValue = txt1.getText();
        String prenomValue = txt2.getText();
        int nm_apogeeValue = Integer.parseInt(txt3.getText());
        String téléphoneValue = txt4.getText();
        String emailValue = txt5.getText();
        String dateNaissanceValue = txt6.getText();
        String fillièreValue = txt7.getText();
        connexion connexion = new connexion();
        Connection cnn = connexion.getConnection();
        String query = "INSERT INTO etudiant (nom, prenom, nm_apogee, téléphone, email, date_naissance, fillière) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st;
        try {
            st = cnn.prepareStatement(query);
            st.setString(1, nomValue);
            st.setString(2, prenomValue);
            st.setInt(3, nm_apogeeValue);
            st.setString(4, téléphoneValue);
            st.setString(5, emailValue);
            st.setString(6, dateNaissanceValue);
            st.setString(7, fillièreValue);
            st.executeUpdate();
          //alert de tel
            if (txt4.getLength() != 10) {
                showAlert1("Erreur", "Le numéro de téléphone doit être composé de 10 chiffres");
                return; // Arrêter l'exécution de la méthode si la validation échoue
            }

            // Actualiser la liste des étudiants
            showEtudiants();

            // Réinitialiser les champs de saisie
            txt1.clear();
            txt2.clear();
            txt3.clear();
            txt4.clear();
            txt5.clear();
            txt6.clear();
            txt7.clear();
          //alert pour confirmer que l'ajout est bien passé
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText(null);
            alert.setContentText("Étudiant ajouté avec succès");
            alert.getDialogPane().setStyle("-fx-background-color:LIGHTgold;");
            alert.showAndWait();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 //supprimerEtudiant()
    public void supprimerEtudiant() {
        etudiant selectedEtudiant = tableView.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            int nmApogee = selectedEtudiant.getNm_apogee();
            Connection cnn = connexion.getConnection();
            String query = "DELETE FROM etudiant WHERE nm_apogee = ?";
            try {
                PreparedStatement st = cnn.prepareStatement(query);
                st.setInt(1, nmApogee);
                st.executeUpdate();

                // Actualiser la liste des étudiants
                showEtudiants();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        txt1.clear();
        txt2.clear();
        txt3.clear();
        txt4.clear();
        txt5.clear();
        txt6.clear();
        txt7.clear();
    
  //alert pour confirmer que la suppression est bien passée
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Suppression efféctuée");
    alert.setHeaderText(null);
    alert.setContentText("Étudiant supprimé avec succès");
    alert.getDialogPane().setStyle("-fx-background-color:LIGHTgold;");
    alert.showAndWait();
    
    }
   

    private void generatePDF(etudiant selectedEtudiant) {
        Document document = new Document();
        
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer le fichier PDF");
            fileChooser.setInitialFileName("etudiant.pdf");
            File file = fileChooser.showSaveDialog(new Stage());
            
            if (file != null) {
                PdfWriter.getInstance(document, new FileOutputStream(file));
                
                document.open();
                
                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
                Font infoFont = FontFactory.getFont(FontFactory.HELVETICA, 13);
                
                // Titre centré avec un espace avant les autres champs
                Paragraph title = new Paragraph("Fiche d'inscription", titleFont);
                title.setAlignment(Paragraph.ALIGN_CENTER);
                title.setSpacingAfter(40); // Espace après le titre
                document.add(title);
              
                
                document.add(new Paragraph("Nom:    " + selectedEtudiant.getNom(), infoFont));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Prénom:   " + selectedEtudiant.getPrenom(), infoFont));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Numéro apogée:   " + selectedEtudiant.getNm_apogee(), infoFont));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Téléphone:   " + selectedEtudiant.getTéléphone(), infoFont));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Email:   " + selectedEtudiant.getEmail(), infoFont));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Date de naissance:   " + selectedEtudiant.getDate_naissance(), infoFont));
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Filière:   " + selectedEtudiant.getFillière(), infoFont));
                
                document.close();
                
                showAlert("Succès", "Le PDF a été généré avec succès.");
            } else {
                showAlert("Annulé", "La génération du PDF a été annulée.");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur s'est produite lors de la génération du PDF.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Une erreur inattendue s'est produite.");
        }
    }



  //modifierEtudiant()
    public void modifierEtudiant() {
        // Récupérer les valeurs des champs de saisie
        String nomValue = txt1.getText();
        String prenomValue = txt2.getText();
        int nm_apogeeValue = Integer.parseInt(txt3.getText());
        String téléphoneValue = txt4.getText();
        String emailValue = txt5.getText();
        String dateNaissanceValue = txt6.getText();
        String fillièreValue = txt7.getText();

        etudiant selectedEtudiant = tableView.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            // update dans tableView
            selectedEtudiant.setNom(nomValue);
            selectedEtudiant.setPrenom(prenomValue);
            selectedEtudiant.setNm_apogee(nm_apogeeValue);
            selectedEtudiant.setTéléphone(téléphoneValue);
            selectedEtudiant.setEmail(emailValue);
            selectedEtudiant.setDate_naissance(dateNaissanceValue);
            selectedEtudiant.setFillière(fillièreValue);
            if (txt4.getLength() != 10) {
                showAlert1("Erreur", "Le numéro de téléphone doit être composé de 10 chiffres");
                return; // Arrêter l'exécution de la méthode si la validation échoue
            }

            // update  dans la base de données
            Connection cnn = connexion.getConnection();
            String query = "UPDATE etudiant SET nom=?, prenom=?, nm_apogee=?, téléphone=?, email=?, date_naissance=?, fillière=? WHERE nm_apogee=?";
            try {
                PreparedStatement st = cnn.prepareStatement(query);
                st.setString(1, nomValue);
                st.setString(2, prenomValue);
                st.setInt(3, nm_apogeeValue);
                st.setString(4, téléphoneValue);
                st.setString(5, emailValue);
                st.setString(6, dateNaissanceValue);
                st.setString(7, fillièreValue);
                st.setInt(8, nm_apogeeValue); 
                st.executeUpdate();

                // Actualiser la liste des étudiants
                showEtudiants();

                // vider les textField
                txt1.clear();
                txt2.clear();
                txt3.clear();
                txt4.clear();
                txt5.clear();
                txt6.clear();
                txt7.clear();
                //alert pour confirmer que la modification est bien passée
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Modification réussie");
                alert.setHeaderText(null);
                alert.setContentText("Étudiant modifié avec succès");
                alert.getDialogPane().setStyle("-fx-background-color:LIGHTgold;");
                alert.showAndWait();
               
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}