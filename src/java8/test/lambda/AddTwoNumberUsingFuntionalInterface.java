package java8.test.lambda;

import java.util.function.Function;

public class AddTwoNumberUsingFuntionalInterface {
	public static void main(String arg[]) {
		int a=10;
		int b=20;
		Function<Integer, Integer> function = (x)-> {return x;};
		System.out.println(function.apply(a+b));
	}
}
