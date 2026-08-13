package ir.ac.kntu.userinterface;

import ir.ac.kntu.manager.Level;
import ir.ac.kntu.manager.ManagerService;
import ir.ac.kntu.manager.ManagerUser;
import ir.ac.kntu.manager.database.ManagerDataBase;
import ir.ac.kntu.notification.GeneralNotification;
import ir.ac.kntu.notification.Notification;
import ir.ac.kntu.support.Access;
import ir.ac.kntu.support.SupportUser;
import ir.ac.kntu.support.database.SupportDataBase;
import ir.ac.kntu.user.User;
import ir.ac.kntu.user.database.UserDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerPagePrinter {
    private final UserDataBase userDatabase = new UserDataBase();
    private final SupportDataBase supportDatabase = new SupportDataBase();
    private final  ManagerDataBase managerDataBase = new ManagerDataBase();
    private final ManagerService managerService = new ManagerService();
    private final Scanner scanner = new Scanner(System.in);
    String choice;

    public void managerLogin() {
        System.out.println("""
                welcome to manager login, what would you like to do?
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
                    managerLogin();
                }
            }
        } catch (Exception e) {
            System.out.println("please enter a number!");
            managerLogin();
        }
    }

    public void loginPage() throws Exception {
        System.out.println("enter your username: ");
        String username = scanner.nextLine();
        System.out.println("enter your password: ");
        String password = scanner.nextLine();
        try {
            ManagerUser user = managerService.login(username, password);
            dashboard(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            managerLogin();
        }
    }

    public void dashboard(ManagerUser user) {
        System.out.println("""
                welcome to dashboard, what would you like to do?
                1) notify users
                2) users
                """);
        choice = scanner.nextLine();
        try {
            // try to parse choice, if invalid string is input, go to catch
            switch (Integer.parseInt(choice)) {
                case 1 -> notify(user);
                case 2 -> users(user);
            }
        } catch (Exception ex) {
            System.out.println("invalid input!, going back to dashboard");
            dashboard(user);
        }
    }

    public void notify(ManagerUser user) {
        int i = 1;
        List<GeneralNotification> notification_list = new ArrayList<>();
        for (User normal_user : userDatabase.getUserList()) {
            for (Notification notification : normal_user.getProfile().getNotifications()) {
                if (notification instanceof GeneralNotification) {
                    System.out.println(i + ") " + notification.getNotificationText());
                    if (((GeneralNotification) notification).isActive()) {
                        System.out.println("Active");
                    } else {
                        System.out.println("Inactive");
                    }
                    System.out.println("Read by " + ((GeneralNotification) notification).getReadByUsers().size() + " users");
                    System.out.println("\n----------------------------------------------------------------\n");
                    notification_list.add((GeneralNotification) notification);
                }
            }
        }

        System.out.println("""
                Enter the index of the notification you want to modify
                or enter 'new' to submit new notification
                or enter -1 to go to dashboard""");
        try {
            choice = scanner.nextLine();
            if (choice.equals("-1")) {
                dashboard(user);
            } else if (choice.equals("new")) {
                System.out.println("enter new notification message:");
                String message = scanner.nextLine();
                GeneralNotification newNotification = new GeneralNotification();
                newNotification.setMessage(message);
                System.out.println("Operation successful! going back to dashboard");
                dashboard(user);
            } else {
                int notificationIndex = Integer.parseInt(choice);
                GeneralNotification targetNotification = notification_list.get(notificationIndex);
                System.out.println("""
                        What do you want to do?
                        1) activate
                        2) deactivate
                        3) go back""");
                String action = scanner.nextLine();
                switch (action) {
                    case "1" -> targetNotification.setActive(true);
                    case "2" -> targetNotification.setActive(false);
                    case "3" -> notify(user);
                    default -> {
                        System.out.println("invalid input!, going back");
                        notify(user);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("invalid input!");
            notify(user);
        }
    }
    public void users(ManagerUser user) {
        System.out.println("""
                What do you want to see?
                1) normal users
                2) support users
                3) management users
                4) all users
                5) search by username
                6) search by name
                7) search by lastname
                8) add user
                9) go back
                """);
        choice = scanner.nextLine();
        switch (choice) {
            case "1" -> viewUsers("normal", user);
            case "2" -> viewUsers("support", user);
            case "3" -> viewUsers("manager", user);
            case "4" -> viewUsers("all", user);
            case "5" -> searchBy("username", user);
            case "6" -> searchBy("name", user);
            case "7" -> searchBy("lastname", user);
            case "8" -> addUser(user);
            case "9" -> dashboard(user);
            default -> {
                System.out.println("invalid input!");
                users(user);
            }
        }
    }
    public void viewUsers(String type, ManagerUser user) {
        try {
            switch (type) {
                case "normal" -> {
                    int index = 1;
                    for (User normal_user : userDatabase.getUserList()) {
                        System.out.println(index + ") " + normal_user.getUsername() + " | " + normal_user.getName() + " " + normal_user.getLastname());
                        index++;
                    }
                    System.out.println("Enter index of the user you want to modify:");
                    String userIndex = scanner.nextLine();
                    if ((Integer.parseInt(userIndex) <= index) && (1 <= Integer.parseInt(userIndex))) {
                        modifyUser("normal", Integer.parseInt(userIndex) - 1, user);
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                }
                case "support" -> {
                    int index = 1;
                    for (SupportUser support_user : supportDatabase.getUserList()) {
                        System.out.println(index + ") " + support_user.getUsername() + " | " + support_user.getName() + " " + support_user.getLastname());
                        index++;
                    }
                    System.out.println("Enter index of the user you want to modify:");
                    String userIndex = scanner.nextLine();
                    if ((Integer.parseInt(userIndex) <= index) && (1 <= Integer.parseInt(userIndex))) {
                        modifyUser("support", Integer.parseInt(userIndex) - 1, user);
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                }
                case "manager" -> {
                    int index = 1;
                    for (ManagerUser manager_user : managerDataBase.getUserList()) {
                        System.out.println(index + ") " + manager_user.getUsername() + " | " + manager_user.getName() + " " + manager_user.getLastname());
                        index++;
                    }
                    System.out.println("Enter index of the user you want to modify:");
                    String userIndex = scanner.nextLine();
                    if ((Integer.parseInt(userIndex) <= index) && (1 <= Integer.parseInt(userIndex))) {
                        modifyUser("manager", Integer.parseInt(userIndex) - 1, user);
                    } else {
                        throw new IndexOutOfBoundsException();
                    }
                }
                case "all" -> {
                    int index = 1;
                    for (User normal : userDatabase.getUserList()) {
                        System.out.println(index + ") " + normal.getUsername() + " | " + normal.getName() + " " + normal.getLastname());
                        index++;
                    }
                    for (SupportUser support : supportDatabase.getUserList()) {
                        System.out.println(index + ") " + support.getUsername() + " | " + support.getName() + " " + support.getLastname());
                        index++;
                    }
                    for (ManagerUser manager : managerDataBase.getUserList()) {
                        System.out.println(index + ") " + manager.getUsername() + " | " + manager.getName() + " " + manager.getLastname());
                        index++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("invalid input!");
            users(user);
        }
    }
    public void modifyUser(String type, int index, ManagerUser user) {
        System.out.println("What do you want to change?");
        switch (type) {
            case "normal" -> {
                User target_user = userDatabase.getUserList().get(index);
                System.out.println("""
                        1) name
                        2) lastname
                        3) username
                        4) ban user""");
                String action = scanner.nextLine();
                switch (action) {
                    case "1" -> {
                        System.out.println("enter new name: ");
                        String name = scanner.nextLine();
                        target_user.setName(name);
                    }
                    case "2" -> {
                        System.out.println("enter new lastname: ");
                        String lastname = scanner.nextLine();
                        target_user.setLastname(lastname);
                    }
                    case "3" -> {
                        System.out.println("enter new username: ");
                        String username = scanner.nextLine();
                        target_user.setUsername(username);
                    }
                    case "4" -> userDatabase.getUserList().remove(target_user);
                    default -> {
                        System.out.println("invalid input!");
                        modifyUser(type, index, user);
                    }
                }
                System.out.println("Operation successful! going back to users");
                users(user);
            }
            case "support" -> {
                SupportUser target_user = supportDatabase.getUserList().get(index);
                System.out.println("""
                        1) name
                        2) lastname
                        3) username
                        4) change access
                        5) ban user""");
                String action = scanner.nextLine();
                switch (action) {
                    case "1" -> {
                        System.out.println("enter new name: ");
                        String name = scanner.nextLine();
                        target_user.setName(name);
                    }
                    case "2" -> {
                        System.out.println("enter new lastname: ");
                        String lastname = scanner.nextLine();
                        target_user.setLastname(lastname);
                    }
                    case "3" -> {
                        System.out.println("enter new username: ");
                        String username = scanner.nextLine();
                        target_user.setUsername(username);
                    }
                    case "4" -> {
                        System.out.println("""
                                Enter new access section:
                                1) settings
                                2) users
                                3) reports""");
                        String section = scanner.nextLine();
                        switch (section) {
                            case "1" -> target_user.setAccess(Access.SETTINGS);
                            case "2" -> target_user.setAccess(Access.USER);
                            case "3" -> target_user.setAccess(Access.REPORT);
                            default -> {
                                System.out.println("invalid input!");
                                modifyUser(type, index, user);
                            }
                        }
                    }
                    case "5" -> supportDatabase.getUserList().remove(target_user);
                    default -> {
                        System.out.println("invalid input!");
                        modifyUser(type, index, user);
                    }
                }
                System.out.println("Operation successful! going back to users");
                users(user);
            }
            case "manager" -> {
                ManagerUser target_user = managerDataBase.getUserList().get(index);
                System.out.println("""
                        1) name
                        2) lastname
                        3) username""");
                if (user.getLevel().equals(Level.HIGHEST) || (user.getLevel().equals(Level.MIDDLE) && target_user.getLevel().equals(Level.LOW))) {
                    System.out.println("4) ban user");
                }
                String action = scanner.nextLine();
                switch (action) {
                    case "1" -> {
                        System.out.println("enter new name: ");
                        String name = scanner.nextLine();
                        target_user.setName(name);
                    }
                    case "2" -> {
                        System.out.println("enter new lastname: ");
                        String lastname = scanner.nextLine();
                        target_user.setLastname(lastname);
                    }
                    case "3" -> {
                        System.out.println("enter new username: ");
                        String username = scanner.nextLine();
                        target_user.setUsername(username);
                    }
                    case "4" -> {
                        if (user.getLevel().equals(Level.HIGHEST) || (user.getLevel().equals(Level.MIDDLE) && target_user.getLevel().equals(Level.LOW))) {
                            managerDataBase.getUserList().remove(target_user);
                        }

                    }
                }
                System.out.println("Operation successful! going back to users");
                users(user);
            }
        }
    }

    public void searchBy(String by, ManagerUser user) {
        System.out.println("enter your search term: ");
        String search_term = scanner.nextLine();
        switch (by) {
            case "name" -> {
                int index = 1;
                for (User normal : userDatabase.getUserList()) {
                    if (normal.getName().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + normal.getUsername() + " | " + normal.getName() + " " + normal.getLastname());
                        index++;
                    }
                }
                for (SupportUser support : supportDatabase.getUserList()) {
                    if (support.getName().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + support.getUsername() + " | " + support.getName() + " " + support.getLastname());
                        index++;
                    }
                }
                for (ManagerUser manager : managerDataBase.getUserList()) {
                    if (manager.getUsername().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + manager.getUsername() + " | " + manager.getName() + " " + manager.getLastname());
                        index++;
                    }
                }
            }
            case "lastname" -> {
                int index = 1;
                for (User normal : userDatabase.getUserList()) {
                    if (normal.getLastname().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + normal.getUsername() + " | " + normal.getName() + " " + normal.getLastname());
                        index++;
                    }
                }
                for (SupportUser support : supportDatabase.getUserList()) {
                    if (support.getLastname().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + support.getUsername() + " | " + support.getName() + " " + support.getLastname());
                        index++;
                    }
                }
                for (ManagerUser manager : managerDataBase.getUserList()) {
                    if (manager.getLastname().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + manager.getUsername() + " | " + manager.getName() + " " + manager.getLastname());
                        index++;
                    }
                }
            }
            case "username" -> {
                int index = 1;
                for (User normal : userDatabase.getUserList()) {
                    if (normal.getUsername().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + normal.getUsername() + " | " + normal.getName() + " " + normal.getLastname());
                        index++;
                    }
                }
                for (SupportUser support : supportDatabase.getUserList()) {
                    if (support.getUsername().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + support.getUsername() + " | " + support.getName() + " " + support.getLastname());
                        index++;
                    }
                }
                for (ManagerUser manager : managerDataBase.getUserList()) {
                    if (manager.getUsername().toLowerCase().contains(search_term.toLowerCase())) {
                        System.out.println(index + ") " + manager.getUsername() + " | " + manager.getName() + " " + manager.getLastname());
                        index++;
                    }
                }
            }
        }
        System.out.println("Enter anything to go back to users");
        String anything = scanner.nextLine();
        users(user);
    }

    public void addUser(ManagerUser user) {
        System.out.println("""
                What type of user to add?
                1) support user
                2) manager user
                3) back to dashboard""");
        choice = scanner.nextLine();
        try {
            switch (choice) {
                case "1" -> {
                    System.out.println("enter username: ");
                    String username = scanner.nextLine();
                    System.out.println("enter password: ");
                    String password = scanner.nextLine();
                    System.out.println("enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("enter lastname: ");
                    String lastname = scanner.nextLine();
                    System.out.println("Enter access level (default to user) (user, report, settings): ");
                    String access = scanner.nextLine();
                    Access access_obj = switch (access) {
                        case "user" -> Access.USER;
                        case "report" -> Access.REPORT;
                        case "settings" -> Access.SETTINGS;
                        default -> Access.USER;
                    };
                    SupportUser supportUser = new SupportUser();
                    supportUser.setAccess(access_obj);
                    supportUser.setUsername(username);
                    supportUser.setPassword(password);
                    supportUser.setName(name);
                    supportUser.setLastname(lastname);
                    supportDatabase.addUser(supportUser);
                    System.out.println(username + " added");
                    users(user);
                }
                case "2" -> {
                    System.out.println("enter username: ");
                    String username = scanner.nextLine();
                    System.out.println("enter password: ");
                    String password = scanner.nextLine();
                    System.out.println("enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("enter lastname: ");
                    String lastname = scanner.nextLine();
                    System.out.println("Enter access level (default to 'low') (middle, low): ");
                    String level = scanner.nextLine();
                    Level level_obj = switch (level) {
                        case "middle" -> Level.MIDDLE;
                        case "low" -> Level.LOW;
                        default -> Level.LOW;
                    };
                    ManagerUser managerUser = new ManagerUser();
                    managerUser.setLevel(level_obj);
                    managerUser.setUsername(username);
                    managerUser.setPassword(password);
                    managerUser.setName(name);
                    managerUser.setLastname(lastname);
                    managerDataBase.addUser(managerUser);
                    System.out.println(username + " added");
                    users(user);
                }
                case "3" -> dashboard(user);
                default -> {
                    System.out.println("Invalid input!");
                    users(user);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            users(user);
        }
    }
}
