package tests.pages;

import org.openqa.selenium.WebDriver;

public class SignIn extends Page {
    private static final String EMAIL_SELECTOR_ID = "email";
    private static final String PASSWORD_SELECTO_ID = "pass";
    private static final String LOGIN_BUTTON_ID = "loginbutton";

    public SignIn(WebDriver driver) {
        super(driver);
        this.path = "/";
    }

    public void setEmail(String email) {
        this.setFieldById(EMAIL_SELECTOR_ID, email);
    }

    public void setPassword(String password) {
        this.setFieldById(PASSWORD_SELECTO_ID, password);
    }

    public void clickLoginButton(){
        this.submitFormById(LOGIN_BUTTON_ID);
    }

    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}
