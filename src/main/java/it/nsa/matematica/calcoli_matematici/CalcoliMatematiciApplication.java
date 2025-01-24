package it.nsa.matematica.calcoli_matematici;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;

import static it.nsa.matematica.calcoli_matematici.type.Integer.*;

@SpringBootApplication
public class CalcoliMatematiciApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(CalcoliMatematiciApplication.class, args);
//	}

    public static void main(String[] args) {
        BigInteger result;
        result = sumOf(4L, 7L);
        System.out.println("sum of: " + result);
        result = productOf(3L, 7L);
        System.out.println("product of: " + result);
        result = factorialOf(5L);
        System.out.println("factorial of: " + result);
        result = differenceOf(8L, 6L, 3L, 5L, 8L);
        System.out.println("difference of: " + result);
        result = divisionOf(12L, 6L, 2L);
        System.out.println("division of:" + result);
        result = sumOfSquaresOf(3L, 6L, 2L);
        System.out.println("sum of squares of: " + result);
        result = averageOf(3L, 6L, 5L, 2L);
        System.out.println("average of: " + result);
        result = maximumOf(-50L, 6L, -12L, 3L, 0L);
        System.out.println("maximum of: " + result);
        result = minimumOf(-50L, 6L, 12L, 3L, 0L);
        System.out.println("minimum of: " + result);
        result = squareRootOf(64L);
        System.out.println("square root of:" + result);
        result = resultOfFibonacciIterations(25L);
        System.out.println("result of fibonacci iterations: " + result);
    }
}
