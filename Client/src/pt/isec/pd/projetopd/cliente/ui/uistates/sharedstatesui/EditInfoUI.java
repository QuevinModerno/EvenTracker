package pt.isec.pd.projetopd.cliente.ui.uistates.sharedstatesui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pt.isec.pd.projetopd.cliente.model.Manager;
import pt.isec.pd.projetopd.cliente.model.data.OPTIONS;
import pt.isec.pd.projetopd.cliente.model.fsm.ClientStates;

public class EditInfoUI extends BorderPane {
    private Manager manager;
    private Button btnSubmit, btnBack;
    private TextField passwordField, name, studentNumber, nif, address;

    private VBox vbox;
    private GridPane grid;
    private HBox hBox;
    private Label messageLabel;

    public EditInfoUI(Manager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    /**
     * Cria os botões e imagens
     */
    private void createViews() {
        btnSubmit = new Button("Submit");
        btnSubmit.setMinWidth(200);
        btnSubmit.setMinHeight(50);


        btnBack = new Button("Back");
        btnBack.setMinWidth(200);
        btnBack.setMinHeight(50);

        Label passwordLabel = new Label("Password: ");
        passwordField = new TextField();
        passwordField.setPromptText("Enter new Password");

        Label nameLabel = new Label("Name:");
        name = new TextField(manager.getName());
        name.setPromptText("Enter new Name");

        Label studentNumberLabel = new Label("StudentNumber:");
        studentNumber = new TextField(String.valueOf(manager.getStudentNumber()));
        studentNumber.setPromptText("Enter new StudentNumber");

        Label nifLabel = new Label("NIF:");
        nif = new TextField(String.valueOf(manager.getNIF()));
        nif.setPromptText("Enter new NIF");


        Label addressLabel = new Label("Address:");
        address = new TextField(manager.getAddress());
        address.setPromptText("Enter new Address");

        messageLabel = new Label();
        messageLabel.getStyleClass().add("info");
        messageLabel.setTextFill(Color.RED);
        messageLabel.setStyle("-fx-font-size: 20px;");

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        int rowIndex = 0;

        rowIndex++;
        grid.add(passwordLabel, 0, rowIndex);
        grid.add(passwordField, 1, rowIndex, 25, 1);

        rowIndex++;
        grid.add(nameLabel, 0, rowIndex);
        grid.add(name, 1, rowIndex, 25, 1);

        rowIndex++;
        grid.add(studentNumberLabel, 0, rowIndex);
        grid.add(studentNumber, 1, rowIndex, 25, 1);

        rowIndex++;
        grid.add(nifLabel, 0, rowIndex);
        grid.add(nif, 1, rowIndex, 20, 1);

        rowIndex++;
        grid.add(addressLabel, 0, rowIndex);
        grid.add(address, 1, rowIndex, 50, 1);

        hBox = new HBox(btnBack, btnSubmit);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);


        vbox = new VBox( grid, hBox, messageLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        vbox.setPadding(new Insets(10));


        double fieldsWidthPercentage = 0.5;
        passwordField.maxWidthProperty().bind(vbox.widthProperty().multiply(fieldsWidthPercentage));
        name.maxWidthProperty().bind(vbox.widthProperty().multiply(fieldsWidthPercentage));
        studentNumber.maxWidthProperty().bind(vbox.widthProperty().multiply(fieldsWidthPercentage));
        nif.maxWidthProperty().bind(vbox.widthProperty().multiply(fieldsWidthPercentage));
        address.maxWidthProperty().bind(vbox.widthProperty().multiply(fieldsWidthPercentage));

        VBox container = new VBox(vbox);
        container.setAlignment(Pos.CENTER);

        this.setCenter(container);
    }


    /**
     * regista os handlers
     */
    private void registerHandlers() {

        manager.addPropertyChangeListener(evt -> { Platform.runLater(this::update);});

        btnSubmit.setOnAction(event -> {
            String string =
                    passwordField.getText() + "\n"
                    + name.getText() + "\n"
                    + studentNumber.getText() + "\n"
                    + nif.getText() + "\n"
                    + address.getText();

            manager.selectOption(OPTIONS.SUBMIT, string);
            update();
        });

        this.setFocusTraversable(true);
        this.setOnKeyPressed((key) -> {
            if (key.getCode() == KeyCode.ENTER) {
                String string =
                        passwordField.getText() + "\n"
                                + name.getText() + "\n"
                                + studentNumber.getText() + "\n"
                                + nif.getText() + "\n"
                                + address.getText();
                manager.selectOption(OPTIONS.SUBMIT,  string+ "\n");
                update();
            }
        });

        btnBack.setOnAction(event -> {
            manager.selectOption(OPTIONS.BACK, null);
            update();
        });
    }

    private void update() {
        if (manager.getState() != ClientStates.EDIT_USER_DATA) {
            passwordField.setText(null);
            name.setText(null);
            studentNumber.setText(null);
            nif.setText(null);
            address.setText(null);

            this.setVisible(false);
            return;
        }
        this.setVisible(true);

        String msg = manager.getLastMessage();
        if (msg != null) {
            messageLabel.setText(msg);
        }

        passwordField.setText("");
        name.setText(manager.getName());
        studentNumber.setText(String.valueOf(manager.getStudentNumber()));
        nif.setText(String.valueOf(manager.getNIF()));
        address.setText(manager.getAddress());

        this.layoutChildren();
    }

}
