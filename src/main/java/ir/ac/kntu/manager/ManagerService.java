package ir.ac.kntu.manager;

public class ManagerService {

    public ManagerUser login(String username, String password) throws Exception {

        for (ManagerUser managerUser : ManagerUser.getManagerUsers()) {
            if (managerUser.getUsername().equals(username)) {
                if (managerUser.getPassword().equals(password)) {
                    return managerUser;
                }
                throw new Exception("wrong password");
            }
        }
        throw new Exception("wrong username");
    }
}
