public class Romawi extends Number implements Comparable<Romawi> {
    private int value;
    private String roman_text;

    // constructor buat nilai
    public Romawi(int value) {
        if (value < 0) {
            throw new ExceptionInvalidRomeNumber("Symbol -" + value + " bukan bilangan positif!");
        }
        this.value = value;
        this.roman_text = intToRoman(value);
    }

    // constructor buat romawi
    public Romawi(String romawi_string) {
        if (romawi_string == null || romawi_string.trim().isEmpty()) {
            throw new ExceptionInvalidRomeNumber("Symbol tidak valid!");
        }

        String sanitized = romawi_string.trim().toUpperCase();

        this.value = romanToInt(sanitized);
        this.roman_text = sanitized;
    }

    public int romanToInt(String romawi) {
        int total = 0;
        int previousVal = 0;

        for (int i = romawi.length()-1; i >= 0; i--) {
            int currentVal = charToValue(romawi.charAt(i));
            if (currentVal < previousVal) {
                total -= currentVal;
            } else {
                total += currentVal;
            }
            previousVal = currentVal;
        }
        return total;
    }

    public int charToValue(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            default: throw new ExceptionInvalidRomeNumber("Symbol gak valid");
        }
    }

    public String intToRoman(int num) {
        if (num == 0) return "0";

        // 67

        int[] values = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num > 0; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
                // num = 67 - 50 = 17
                // num = 17 - 10 = 7
                // num = 7 - 5 = 2
                // num = 2 - 1 = 1
                // LXVII
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
