package ir.ac.kntu;

import ir.ac.kntu.manager.Level;
import ir.ac.kntu.manager.ManagerUser;
import ir.ac.kntu.manager.database.ManagerDataBase;
import ir.ac.kntu.userinterface.PagePrinter;

public class Main {

    public static void main(String[] args) throws Exception {
        ManagerDataBase db = new ManagerDataBase();
        // initializing support users
        ManagerUser managerUser = new ManagerUser();
        managerUser.setUsername("manager");
        managerUser.setPassword("manager");
        managerUser.setName("narges");
        managerUser.setName("B.Ansari");
        managerUser.setLevel(Level.HIGHEST);
        db.addUser(managerUser);
        // initializing User interface
        PagePrinter pagePrinter = new PagePrinter();
        System.out.println("hello and welcome to meowter!");
        pagePrinter.landingPage();
    }
}
