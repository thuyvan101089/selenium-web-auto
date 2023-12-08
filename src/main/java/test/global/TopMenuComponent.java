package test.global;

import models.components.Component;
import models.components.ComponentCSSSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

@ComponentCSSSelector(".top-menu")
public class TopMenuComponent extends Component {
    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }


    public List<MainCatItem> mainCatItems() {
        return findComponents(MainCatItem.class);
    }

    @ComponentCSSSelector(".top-menu > li")
    public static class MainCatItem extends Component {

        public MainCatItem(WebDriver driver, WebElement component) {
            super(driver, component);
        }
        public WebElement catItemLinkEle(){
            return component.findElement(By.tagName("a"));
        }
        public List<SubListComponent>subListComps(){
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(SubListComponent.class);
        }
    }

    @ComponentCSSSelector(".sublist li a")
    public static class SubListComponent extends Component {

        public SubListComponent(WebDriver driver, WebElement component) {
            super(driver, component);
        }
    }

}
