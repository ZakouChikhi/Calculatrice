module permissions {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens vues to javafx.fxml;

    exports pnt;
}