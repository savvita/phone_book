package org.example.ui;

import java.util.function.Consumer;

class Menu {
    static void showMainMenu(Consumer<String> out) {
        out.accept("Menu : ");
        out.accept("1 - Show all");
        out.accept("2 - Add new contact");
        out.accept("3 - Edit contact");
        out.accept("4 - Remove contact");
        out.accept("5 - Find contact");
        out.accept("0 - Exit");
    }

    static void showEditMenu(Consumer<String> out) {
        out.accept("Menu : ");
        out.accept("1 - Edit name");
        out.accept("2 - Clear phones");
        out.accept("3 - Edit phones");
        out.accept("0 - Exit");
    }

    static void showEditPhonesMenu(Consumer<String> out) {
        out.accept("Menu : ");
        out.accept("1 - Add new phone");
        out.accept("2 - Edit phone");
        out.accept("3 - Remove phone");
        out.accept("0 - Exit");
    }
}
