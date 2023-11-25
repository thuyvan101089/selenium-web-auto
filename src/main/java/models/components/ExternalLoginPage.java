package models.components;

public class ExternalLoginPage extends LoginPage{
    @Override
    public void inputUserName(String username) {
        System.out.println(username);
    }
}
