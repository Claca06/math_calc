package it.nsa.matematica.calcoli_matematici.type;

import lombok.ToString;

import java.math.BigInteger;

import static it.nsa.matematica.calcoli_matematici.type.Integer.StringValue.*;
import static it.nsa.matematica.calcoli_matematici.type.Integer.Utility.*;

public class Integer {

    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger ZERO = BigInteger.ZERO;

    public static BigInteger sumOf(Long... values) {
        checkLength(values);
        checkValues(values);
        BigInteger sum = ZERO;
        for (Long value : values) {
            sum = sum.add(BigInteger.valueOf(value));
        }
        return sum;
    }

    public static BigInteger differenceOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger result = BigInteger.valueOf(values[0]);

        for (int i = 1; i < values.length; i++) {
            BigInteger current = BigInteger.valueOf(values[i]);
            result = result.subtract(current);
        }

        return result;
    }

    public static BigInteger productOf(Long... values) {
        checkLength(values);
        BigInteger product = ONE;

        for (Long value : values) {
            product = product.multiply(BigInteger.valueOf(value));
        }
        return product;
    }

    public static BigInteger divisionOf(Long... values) {
        checkLength(values);
        checkValues(values);

        BigInteger result = BigInteger.valueOf(values[0]);

        for (int i = 1; i < values.length; i++) {
            Long value = values[i];
            checkZeroValue(value);
            BigInteger currentValue = BigInteger.valueOf(value);

            if (!result.mod(currentValue).equals(ZERO)) {
                throw new IllegalArgumentException("La divisione di " + result + " per " + currentValue + " non è un numero intero.");
            }

            result = result.divide(currentValue);
        }

        return result;
    }

    public static BigInteger sumOfSquaresOf(Long... values) {
        checkLength(values);
        checkValues(values);
        int numValues = values.length;
        Long[] quads = new Long[numValues];
        for (int i = 0; i < numValues; i++) {
            quads[i] = values[i] * values[i];
        }
        return sumOf(quads);
    }

    public static BigInteger averageOf(Long... values) {
        checkLength(values);
        checkValues(values);
        BigInteger sum = sumOf(values);
        BigInteger count = BigInteger.valueOf(values.length);
        return sum.divide(count);
    }

    public static BigInteger maximumOf(Long... values) {
        checkLength(values);
        checkValues(values);
        BigInteger max = BigInteger.valueOf(values[0]);

        for (Long value : values) {
            max = max.max(BigInteger.valueOf(value));
        }
        return max;
    }

    public static BigInteger minimumOf(Long... values) {
        checkLength(values);
        checkValues(values);
        BigInteger min = BigInteger.valueOf(values[0]);

        for (Long value : values) {
            min = min.min(BigInteger.valueOf(value));
        }
        return min;
    }

    public static BigInteger squareRootOf(Long value) {
        checkZeroValue(value);
        checkNegative(value);

        BigInteger n = BigInteger.valueOf(value);
        BigInteger a = BigInteger.ONE;
        BigInteger b = n.add(BigInteger.ONE).shiftRight(1);

        while (b.compareTo(a) >= 0) {
            BigInteger mid = a.add(b).shiftRight(1);
            BigInteger midSquared = mid.multiply(mid);

            if (midSquared.compareTo(n) > 0) {
                b = mid.subtract(BigInteger.ONE);
            } else if (midSquared.compareTo(n) < 0) {
                a = mid.add(BigInteger.ONE);
            } else {
                return mid;
            }
        }

        throw new IllegalArgumentException("Il numero non è un quadrato perfetto.");
    }

    public static BigInteger factorialOf(Long value) {
        checkZeroValue(value);

        BigInteger factorial = ONE;
        boolean negativo = value < 0;
        long nAssoluto = Math.abs(value);

        for (int i = 2; i <= nAssoluto; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }

        if (negativo) {
            if (nAssoluto % 2 == 0) {
                factorial = factorial.negate();
            }
        }

        return factorial;
    }

    public static BigInteger resultOfFibonacciIterations(Long value) {
        checkNegative(value);

        BigInteger a = ZERO;
        BigInteger b = ONE;

        for (int i = 2; i <= value; i++) {
            BigInteger temp = a;
            a = b;
            b = temp.add(b);
        }

        return a;
    }


    static class Utility{

        public static void checkEmpty(Long[] values) {
            if (values.length == 0) {
                throw new IllegalArgumentException(ARRAY_EMPTY.value());
            }
        }

        public static void checkLength(Long[] values) {
            checkEmpty(values);
            if (values.length < 2) {
                throw new IllegalArgumentException(AT_LEAST_TWO_VALUES_REQUIRED.value());
            }
        }

        public static void checkValues(Long[] values) {
            checkEmpty(values);
            for (Long value : values) {
                if (value == null) {
                    throw new IllegalArgumentException(NULL_NOT_VALID.value());
                }
                try {
                    Long.parseLong(value.toString());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(INVALID_INPUT.value());
                }
            }
        }

        public static void checkZeroValue(Long value) {
            if (value == 0) {
                throw new IllegalArgumentException(ZERO_NOT_VALID.value());
            }
        }

        public static void checkNegative(Long value) {
            if (value < 0) {
                throw new IllegalArgumentException(NEGATIVE_NOT_VALID.value());
            }
        }

    }
    @ToString
    enum StringValue {
        NULL_NOT_VALID("Valore null non valido"),
        NEGATIVE_NOT_VALID("Valore negativo non valido"),
        INVALID_INPUT("Valore non valido per l'operazione corrente"),
        AT_LEAST_TWO_VALUES_REQUIRED("Almeno due valori richiesti per l'operazione"),
        ARRAY_EMPTY("Valori forniti vuoti"),
        ZERO_NOT_VALID("Valore Zero non valido per l'operzione corrente");

        private final String value;

        StringValue(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }
    }
}


