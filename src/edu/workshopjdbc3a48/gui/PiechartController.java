package edu.workshopjdbc3a48.gui;

import edu.workshopjdbc3a48.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author Louay
 */
public class PiechartController implements Initializable {

    @FXML
    private PieChart pichart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Connexion à la base de données
            Connection cnx = DataSource.getInstance().getCnx();

            // Récupération des données de la table "article"
            String query = "SELECT COUNT(*) FROM article WHERE type_article = ?";
            PreparedStatement stmtProduit = cnx.prepareStatement(query);
            stmtProduit.setString(1, "produit");
            ResultSet rsProduit = stmtProduit.executeQuery();
            rsProduit.next();
            int nbProduit = rsProduit.getInt(1);

            PreparedStatement stmtService = cnx.prepareStatement(query);
            stmtService.setString(1, "service");
            ResultSet rsService = stmtService.executeQuery();
            rsService.next();
            int nbService = rsService.getInt(1);

            // Fermeture des connexions
            rsProduit.close();
            stmtProduit.close();
            rsService.close();
            stmtService.close();

            // Calcul des pourcentages
            int total = nbProduit + nbService;
            double pourcentageProduit = ((double) nbProduit / total) * 100;
            double pourcentageService = ((double) nbService / total) * 100;

            // Créer les données pour le PieChart
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Produits " + String.format("%.2f", pourcentageProduit) + "%", nbProduit),
                    new PieChart.Data("Services " + String.format("%.2f", pourcentageService) + "%", nbService));
            

            // Afficher le PieChart
            pichart.setData(pieChartData);

        } catch (SQLException ex) {
            Logger.getLogger(PiechartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
