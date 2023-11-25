package test;

import models.components.ComponentExploring;
import models.components.InternalLoginPage;

public class GenericTypeTest {
    public static void main(String[] args) {
        ComponentExploring componentExploring = new ComponentExploring();
        componentExploring.login(InternalLoginPage.class, "Internal user");
        componentExploring.login(InternalLoginPage.class, "External user");

    }
}
