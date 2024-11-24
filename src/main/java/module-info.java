module com.trabfinal.finallpiii {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.trabfinal.finallpiii to javafx.fxml;
    exports com.trabfinal.finallpiii;
}