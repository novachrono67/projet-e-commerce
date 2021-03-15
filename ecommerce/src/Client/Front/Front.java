package Client.Front;

import Client.Modele.Corbeille;
import Client.Service.ServiceMagasin;
import Magasin.Modele.Article;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.Optional;

public class Front extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Application E-Commerce");
        afficherSceneConnexion();
    }

    public void afficherSceneConnexion()
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Bienvenue");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Nom d'utilisateur:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Mot de passe:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Connexion");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String nom = userTextField.getText();
                String motDePasse = pwBox.getText();

                Corbeille corbeille = new Corbeille(nom, motDePasse);

                System.out.println("Nom d'utilisateur = " + nom);
                System.out.println("Mot de passe = " + motDePasse);

                afficherSceneArticles();
            }
        });

        Scene scene = new Scene(grid, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void afficherSceneArticles() throws IllegalStateException
    {
        TableView tableView = new TableView();

        TableColumn<Article, String> column1 = new TableColumn<>("Article");
        column1.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Article, String> column2 = new TableColumn<>("Prix (€)");
        column2.setCellValueFactory(new PropertyValueFactory<>("prix"));

        TableColumn column3 = new TableColumn("Acheter");
        column3.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Article, String>, TableCell<Article, String>> cellFactory
                = //
                new Callback<TableColumn<Article, String>, TableCell<Article, String>>()
                {
                    @Override
                    public TableCell call(final TableColumn<Article, String> param)
                    {
                        final TableCell<Article, String> cell = new TableCell<Article, String>()
                        {

                            final Button btn = new Button("Acheter");

                            @Override
                            public void updateItem(String item, boolean empty)
                            {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event ->
                                    {
                                        Article article = getTableView().getItems().get(getIndex());
                                        System.out.println(article.getNom() + "   " + article.getPrix());
                                        Corbeille corbeille = new Corbeille();
                                        corbeille.ajouterArticle(article);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        column3.setCellFactory(cellFactory);


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        ArrayList<Article> articles = ServiceMagasin.getListeDesArticles();
        for(Article a : articles)
        {
            tableView.getItems().add(a);
        }

        Button boutonCorbeille = new Button("Corbeille");
        HBox hbBtn = new HBox(10);
        hbBtn.getChildren().add(boutonCorbeille);
        boutonCorbeille.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                afficherSceneCorbeille();
            }
        });

        Button boutonDeconnexion = new Button("Deconnexion");
        HBox hbBtn1 = new HBox(10);
        hbBtn1.getChildren().add(boutonDeconnexion);
        boutonDeconnexion.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                Corbeille.viderCorbeille();
                afficherSceneConnexion();
            }
        });

        VBox vbox = new VBox(tableView, boutonCorbeille, boutonDeconnexion);

        Scene scene = new Scene(vbox, 600, 600);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void afficherSceneCorbeille() throws IllegalStateException
    {
        TableView tableView = new TableView();

        TableColumn<Article, String> column1 = new TableColumn<>("Article");
        column1.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Article, String> column2 = new TableColumn<>("Prix (€)");
        column2.setCellValueFactory(new PropertyValueFactory<>("prix"));

        TableColumn column3 = new TableColumn("Retirer");
        column3.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Article, String>, TableCell<Article, String>> cellFactory
                = //
                new Callback<TableColumn<Article, String>, TableCell<Article, String>>()
                {
                    @Override
                    public TableCell call(final TableColumn<Article, String> param)
                    {
                        final TableCell<Article, String> cell = new TableCell<Article, String>()
                        {

                            final Button btn = new Button("Retirer");

                            @Override
                            public void updateItem(String item, boolean empty)
                            {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event ->
                                    {
                                        Article article = getTableView().getItems().get(getIndex());
                                        System.out.println("Retirer de la corbeille: " + article.getNom() + "   " + article.getPrix());
                                        Corbeille.retirerArticle(article);
                                        afficherSceneCorbeille();
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        column3.setCellFactory(cellFactory);

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        Corbeille corbeille = new Corbeille();
        ArrayList<Article> articles = corbeille.getListeDesArticles();

        for(Article a : articles)
        {
            tableView.getItems().add(a);
        }

        Button boutonListeArticles = new Button("Liste des Articles");
        HBox hbBoutonListeArticles = new HBox(10);
        hbBoutonListeArticles.getChildren().add(boutonListeArticles);
        boutonListeArticles.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                afficherSceneArticles();
            }
        });

        Button boutonPayer = new Button("Payer");
        HBox hbBoutonPayer = new HBox(10);
        hbBoutonPayer.getChildren().add(boutonPayer);
        boutonPayer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                afficherAlerteConfirmationPaiement();
            }
        });

        Text total = new Text("Votre total est de " + Corbeille.getTotal() + "€");

        VBox vbox = new VBox(tableView, total, boutonListeArticles, boutonPayer);

        Scene scene = new Scene(vbox, 600, 600);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public void afficherAlerteConfirmationPaiement()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de paiement");
        alert.setHeaderText("Souhaitez vous procéder au paiement?");
        alert.setContentText("Votre total est de " + Corbeille.getTotal() + "€");

        ButtonType boutonOui = new ButtonType("Oui");
        ButtonType boutonNon = new ButtonType("Non");

        alert.getButtonTypes().setAll(boutonOui, boutonNon);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == boutonOui){
            boolean resultatPaiement = ServiceMagasin.payer();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Confirmation de paiement");
            if(resultatPaiement == true)
            {
                alert1.setHeaderText("Paiement validé");
                alert1.setContentText("Merci et à bientot");
            }
            else
            {
                alert1.setHeaderText("Paiement Impossible");
                alert1.setContentText("Veulliez vérifier votre solde ou vos identifiants");
            }
            alert1.showAndWait();
            Corbeille.viderCorbeille();
            afficherSceneConnexion();
        } else if (result.get() == boutonNon) {

        } else {

        }
    }
}
