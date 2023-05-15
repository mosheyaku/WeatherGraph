module com.example.weathergraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.weathergraph to javafx.fxml;
    exports com.example.weathergraph;
}