package rs.raf;

import rs.raf.core.ApplicationFramework;

public class AppCore {

    public static void main(String[] args) {
        ApplicationFramework applicationFramework = ApplicationFramework.getInstance();

        applicationFramework.initialize();
    }
}