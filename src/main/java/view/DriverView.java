package view;

import javax.swing.*;

public class DriverView extends JFrame {

    public JButton btnBuscar;
    public JTextField txtNumDriver;
    public JLabel etiquetaDriver;

    public DriverView(){
        //super("Vista Conductor");
        setTitle("Vista Conductor");
        setBounds(600, 600, 400, 400);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setSize(100,50);
        txtNumDriver = new JTextField(10);
        etiquetaDriver = new JLabel("Introduzca el conductor a buscar");

        this.add(etiquetaDriver);
        this.add(btnBuscar);
        this.add(txtNumDriver);

        setVisible(true);

    }

}
