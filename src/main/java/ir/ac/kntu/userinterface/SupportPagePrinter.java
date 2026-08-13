package ir.ac.kntu.userinterface;

import ir.ac.kntu.support.SupportService;
import ir.ac.kntu.support.SupportTickets;
import ir.ac.kntu.support.SupportUser;
import ir.ac.kntu.user.User;
import ir.ac.kntu.user.database.UserDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupportPagePrinter {
    private UserDataBase userDatabase = new UserDataBase();
    private final SupportService supportService = new SupportService();
    private final Scanner scanner = new Scanner(System.in);
    String choice;

    public void supportLogin() {
        System.out.println("""
                welcome to support login, what would you like to do?
                1) login
                2) exit""");
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> loginPage();
                case 2 -> System.exit(0);
                default -> {
                    System.out.println("enter 1 or 2");
                    supportLogin();
                }
            }
        } catch (Exception e) {
            System.out.println("please enter a number!");
            supportLogin();
        }
    }

    public void loginPage() throws Exception {
        System.out.println("enter your username: ");
        String username = scanner.nextLine();
        System.out.println("enter your password: ");
        String password = scanner.nextLine();
        try {
            SupportUser user = supportService.login(username, password);
            dashboard(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            supportLogin();
        }
    }

    public void dashboard(SupportUser user) {
        System.out.println("""
                welcome to dashboard, what would you like to do?
                1) Tickets
                2) logout
                """);
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> Tickets(user);
                case 2 -> supportLogin();
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to dashboard");
            dashboard(user);
        }
    }

    public void Tickets(SupportUser user) {
        List<SupportTickets> tickets_list = new ArrayList<>();
        for (User normal_user: userDatabase.getUserList()) {
            for (SupportTickets ticket : normal_user.getTickets()) {
                if (ticket.getCategory().equals(user.getAccess())) {
                    tickets_list.add(ticket);
                }
            }
        }
        int i = 1;
        for (SupportTickets ticket : tickets_list) {
            System.out.println(i + ") " + ticket.getText());
        }
        System.out.println("Enter the index of the ticket you want to select or enter -1 to go to dashboard");
        try {
            choice = scanner.nextLine();
            if (choice.equals("-1")) dashboard(user);
            SupportTickets target_ticket = tickets_list.get(Integer.parseInt(choice) - 1);
            System.out.println("""
                    1) Answer ticket
                    2) Set status
                    3) Back to tickets""");
            String action = scanner.nextLine();
            switch (action) {
                    case "1" -> {
                        System.out.println("Enter you answer or -1 to go back:");
                        String answer = scanner.nextLine();
                        if (answer.equals("-1")) {
                            Tickets(user);
                        }
                        target_ticket.setAnswer(answer);
                    }
                    case "2" -> {
                        System.out.println("""
                                Enter new status or -1 to go back:
                                1) Not Answered
                                2) In progress
                                3) Answered""");
                        String status = scanner.nextLine();
                        switch (status) {
                            case "-1" -> Tickets(user);
                            case "1" -> target_ticket.setSupportStatus(SupportTickets.SupportStatus.UNRESOLVED);
                            case "2" -> target_ticket.setSupportStatus(SupportTickets.SupportStatus.BEING_RESOLVED);
                            case "3" -> target_ticket.setSupportStatus(SupportTickets.SupportStatus.RESOLVED);
                            default -> {
                                System.out.println("Please enter 1 to 3. Going back to tickets");
                                Tickets(user);
                            }
                        }
                    }
                    case "3" -> Tickets(user);
                    default -> {
                        System.out.println("Please enter 1 to 3. Going back to tickets");
                        Tickets(user);
                    }
                }
                Tickets(user);
        } catch (Exception e) {
            System.out.println("invalid input!");
            Tickets(user);
        }
    }
}
