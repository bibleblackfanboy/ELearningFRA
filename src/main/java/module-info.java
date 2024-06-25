module com.example.elearningfra {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires waffle.jna;
    requires javafx.web;
    requires javafx.media;
    requires org.slf4j;


    opens com.example.elearningfra to javafx.fxml;
    exports com.example.elearningfra;
}