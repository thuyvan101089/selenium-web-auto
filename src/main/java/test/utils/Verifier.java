package test.utils;

public class Verifier {
    public static void assertEqual(String actual, String expect) {
        if (!actual.equals(expect)) {
            throw new RuntimeException("[Assertion Error]:Expecting " + actual + " to be equal " + expect);

        }
    }


}
