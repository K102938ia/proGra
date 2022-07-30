module sample.progect3temp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens sample.progect3temp to javafx.fxml;
    exports sample.progect3temp;
}