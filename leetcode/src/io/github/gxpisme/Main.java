package io.github.gxpisme;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> strings = Arrays.asList("a", "b", "c");

        for (int i = 0; i < strings.size(); i++) {
            for (int j = i + 1; j < strings.size(); j++) {
                for (int k = j + 1; k < strings.size(); k++) {
                    System.out.println(strings.get(i) + strings.get(j) + strings.get(k));
                }
            }
        }
    }
}
