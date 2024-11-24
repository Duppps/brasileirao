module com.trabfinal.finallpiii {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires static lombok;

    opens com.trabfinal.finallpiii to javafx.fxml;
    exports com.trabfinal.finallpiii;
}