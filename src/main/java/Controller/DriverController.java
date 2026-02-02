package Controller;

import Controller.dao.ConductoresDAO;
import model.Conductor;
import view.DriverView;

public class DriverController {

    DriverView vistaConductor;
    ConductoresDAO daoConductor;
    Conductor conductor;

    public DriverController(DriverView view){
        this.vistaConductor = view;
        daoConductor = new ConductoresDAO();
        conductor = new Conductor();
    }

    public void consultar

}
