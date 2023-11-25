package models.components;

public class InternalLoginPage extends LoginPage {
    @Override
    public void inputUserName(String username) {
        System.out.println(username);
    }
}
