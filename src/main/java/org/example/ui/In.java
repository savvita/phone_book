package org.example.ui;

import java.util.function.Consumer;
import java.util.function.Supplier;

class In {
    static String name(Consumer<String> out, Supplier<String> in) {
        out.accept("Enter name : ");
        return in.get();
    }

    static String phone(Consumer<String> out, Supplier<String> in) {
        out.accept("Phone (press ENTER to exit) : ");
        return in.get();
    }

    static int id(Consumer<String> out, Supplier<String> in) throws Exception {
        out.accept("Enter Id : ");
        String input = in.get();
        return Integer.parseInt(input);
    }
}
