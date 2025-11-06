package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.setErr(new PrintStream(System.err, true, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("WARNING: Could not set UTF-8 encoding");
        }

        Injector injector = Guice.createInjector(new BankInjector());
        App app = injector.getInstance(App.class);
        app.run();
    }
}