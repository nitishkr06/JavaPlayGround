package java8.test.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.*;

// stream -> filter -> processing
// stream -> configuration(filter/map/flatMap) -> processing
public class FilterExample {

	public static void main(String[] args) {
		List<Integer> al1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,0,10,11));
		
		// option 1
		List result = al1.stream().filter(ob-> ob%2 == 0).collect(Collectors.toList());
		result.stream().forEach(System.out::print);
		
		// option 2
		System.out.println("");
		Predicate<Integer> predicateToCheckEvenNumber = (n)-> n%2 ==0;
		al1.stream().filter(predicateToCheckEvenNumber).forEach(System.out::print);
	}

}
