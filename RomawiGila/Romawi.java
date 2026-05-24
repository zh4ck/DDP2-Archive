import java.util.regex.Pattern;

public class Romawi extends Number implements Comparable<Romawi> {
    private int value;
    private String roman_text;

    private static final String roman_regex = "^(C|XC|L?X{0,3})(IX|IV|V?I{0,3})$";
    private static final Pattern PATTERN = Pattern.compile(roman_regex);

    public Romawi(int value) {
        if (value < 0) {
            throw new ExceptionInvalidRomeNumber("Symbol -" + value + " bukan bilangan romawi!");
        }
        this.value = value;
        this.roman_text = intToRoman(value);
    }

    public Romawi(String roman_string) {
        if (roman_string == null || roman_string.trim().isEmpty()) throw new ExceptionInvalidRomeNumber("Symbol " + roman_string + " bukan bilangan romawi!");

        String sanitized = roman_string.trim().toUpperCase();

        // validasi regexxxxx
        if (!PATTERN.matcher(sanitized).matches()) throw new ExceptionInvalidRomeNumber("Symbol " + roman_string + " bukan bilangan romawi!");

        this.value = romanToInt(sanitized);
        this.roman_text = sanitized;
    }

    private int romanToInt(String roman) {
        int total = 0;
        int previousVal = 0;

        for (int i = roman.length()-1; i >= 0; i--) {
            int currentVal = charToValue(roman.charAt(i));
            if (currentVal < previousVal) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
            previousVal = currentVal;
        }
        return total;
    }

    private int charToValue(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            default: throw new ExceptionInvalidRomeNumber("Symbol " + c + " bukan bilangan romawi!");
        }
    }

    private String intToRoman(int num) {
        if (num == 0) return "0";

        int[] values = {100,90,50,40,10,9,5,4,1};
        String[] symbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return roman_text;
    }

    @Override public int intValue() { return value; }
    @Override public long longValue() { return value; }
    @Override public float floatValue() { return (float) value; }
    @Override public double doubleValue() { return (double) value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Romawi)) return false;
        return this.value == ((Romawi) o).value;
    }

    @Override
    public int compareTo(Romawi o) {
        return Integer.compare(this.value, o.value);
    }
}
