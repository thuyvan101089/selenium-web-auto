package test;

import models.components.FindComponent;
import models.components.FooterComponent;

public class AnnotationTest {
    public static void main(String[] args) {
      String footerComponentSel=  new FindComponent().getComponentCssSelector(FooterComponent.class);
        System.out.println(footerComponentSel);
    }
}
