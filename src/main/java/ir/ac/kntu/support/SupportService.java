package ir.ac.kntu.support;

public class SupportService {

    public SupportUser login(String username, String password) throws Exception {
        for (SupportUser supportUser : SupportUser.getSupportUsers()) {
            if (supportUser.getUsername().equals(username)) {
                if (supportUser.getPassword().equals(password)) {
                    return supportUser;
                }
                throw new Exception("wrong password");
            }
        }
        throw new Exception("wrong username");
    }
}
