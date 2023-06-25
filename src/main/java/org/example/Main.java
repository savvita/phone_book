package org.example;

import org.example.ui.UI;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        ContactCollection book = new ContactBookFileController();

        Scanner scanner = new Scanner(System.in);
        Consumer<String> out = str -> System.out.println(str);
        Supplier<String> in = () -> scanner.nextLine();

        UI.start(book, out, in);

        book.close();
    }


}