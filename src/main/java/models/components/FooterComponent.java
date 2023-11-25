package models.components;

import org.openqa.selenium.WebDriver;

@ComponentCSSSelector(value = ".footer")
public class FooterComponent {

    private final WebDriver driver;

    public FooterComponent(WebDriver driver) {
        this.driver = driver;
    }


    //Selectors

    //Methods
    public void doSomething() {

    }
}
