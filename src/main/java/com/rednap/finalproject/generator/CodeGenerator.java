package com.rednap.finalproject.generator;

import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

    public String generate() {
        final StringBuilder buffer = new StringBuilder();

        for(int d = 0; d < 8; d++) {
            buffer.append(generateSymbol());
        }

        return buffer.toString();
    }

    private char generateSymbol() {
        final int isDigit = generateBetween(0, 100);

        if(isDigit >= 50) {
            return (char)('0' + generateBetween(0, 9));
        }

        return generateLetter();
    }

    private char generateLetter() {
        final int checkIfCapital = generateBetween(0, 100) > 50 ? 1 : 0;
        final int offset = Math.abs('A' - 'a') * checkIfCapital;
        int charCode = generateBetween('A' + offset, 'Z' + offset);
        return (char)charCode;
    }

    private int generateBetween(int min, int max) {
        return min + (int)(Math.random() * (max - min));
    }

}
