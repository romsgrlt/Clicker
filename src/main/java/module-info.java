module com.romsteam.clicker {
    requires javafx.controls;
    requires javafx.fxml;
            
                requires net.synedra.validatorfx;
    requires lombok;

    opens com.romsteam.clicker to javafx.fxml;
    exports com.romsteam.clicker;
}