package mif.vu.ikeea.Generator;

import java.util.UUID;

public class TokenValueGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
