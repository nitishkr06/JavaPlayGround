package java8.test.lambda.operationOnEmployee;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 1. How many male and female employees are there in the organization?
 * 2. Print the name of all departments in the organization?
 * 3. What is the average age of male and female employees?
 * 4. Get the details of highest paid employee in the organization?
 * 5. Get the names of all employees who have joined after 2015?
 * 6. Count the number of employees in each department?
 * 7. calculate second highest salary
 * 8. Increase the wage of all employees by 10% using streams and lambdas
 * 9. Create a new list containing names of all employees in uppercase using streams
 */

public class OperaionOnEmployeeUsingStream {
	static List<Employee> list;
	
	public static void main(String[] args) {

        createAndAddsampleData();
        
        // 1. How many male and female employees are there in the organization?
        System.out.println("Male Emp count: "+getMaleEmpCount());		//	6
        System.out.println("Female Emp count: "+getFemaleEmpCount());	//	11
        getGenderCountUsingGroupBy();
        
        // 2. Print the name of all departments in the organization?
        printDepartmentName();
        
        // 3. What is the average age of male and female employees?
        printAvgAgeOfGender();
        
        // 4. Get the details of highest paid employee in the organization?
        printHighestPaidEmp();
        
        // 5. Get the names of all employees who have joined after 2015?
        printNameOfEmpWhoJoinedAfter2015();
        
        // 6. Count the number of employees in each department?
        printNumberOfEmployeeByDepartment();
        
        // 7. calculate second highest salary
        printSecondHighestSalary();
        
        // 8. Increase the salary of all employees by 10% using streams and lambdas
        printInreaseSalaryBy10Per();
        
        // 9. Create a new list containing names of all employees in uppercase using streams
        createNewListWithNameInUpperCase();
		
	}
	
	private static void getGenderCountUsingGroupBy() {
		System.out.println("___________________________________________");
		Map<String, Long> GenderCount = list.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		GenderCount.forEach((k,v) -> System.out.println(k +":"+v));
	}
	
	private static long getMaleEmpCount() {
		return list.stream().filter(obj -> "Female".equals(obj.getGender())).count();
		
	}
	
	private static long getFemaleEmpCount() {
		return list.stream()
				.filter(obj -> obj.getGender() != null && obj.getGender().equals("Male"))
				.count();
	}
	
	private static void printDepartmentName() {
		System.out.println("___________________________________________");
		List<String> depart = list.stream().map(obj->obj.getDepartment()).distinct().toList();
		depart.stream().forEach(System.out::println);
	}
	
	private static void printAvgAgeOfGender() {
		System.out.println("___________________________________________");
		Map avgAge = list.stream()
		.collect(Collectors.groupingBy(Employee::getGender, TreeMap::new ,
				Collectors.averagingInt(Employee::getAge)));
		avgAge.forEach((k,v)-> System.out.println(k+":"+v));
	
	}
	
	private static void printHighestPaidEmp() {
		System.out.println("___________________________________________");
		Optional<Employee> maxSalary = list.stream().max(Comparator.comparing(Employee::getSalary));
		System.out.println("Max salary emp: "+maxSalary.get());
	}
	
	private static void printNameOfEmpWhoJoinedAfter2015() {
		System.out.println("___________________________________________");
		List listOfEmpName = list.stream().filter(obj-> obj.getYearOfJoining() > 2015)
		.map(Employee::getName).collect(Collectors.toList());
		System.out.println(listOfEmpName);
		
	}
	
	private static void printNumberOfEmployeeByDepartment() {
		System.out.println("___________________________________________");
		Map empCountByDept = list.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		empCountByDept.forEach((k, v) -> System.out.println(k +":"+ v));
	}
	
	private static void printSecondHighestSalary() {
		System.out.println("___________________________________________");
		List<Employee> empSortBySalary = list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
				.collect(Collectors.toList());
		double HighestSalary = empSortBySalary.get(0).getSalary();
		Employee e = empSortBySalary.stream()
				.filter(obj->obj.getSalary()<HighestSalary).findFirst().get();
		System.out.println(e);
	}
	
	private static void printInreaseSalaryBy10Per() {
		System.out.println("___________________________________________");
		list.stream().forEach(obj -> obj.setSalary(obj.getSalary() * 1.1));
		list.forEach(System.out::println);
	}
	
	private static void createNewListWithNameInUpperCase() {
		System.out.println("___________________________________________");
		list.stream().forEach(obj-> obj.setName(obj.getName().toUpperCase()));
		list.forEach(System.out::println);
	}
	
	private static void createAndAddsampleData() {
		list = new ArrayList<Employee>();
		list.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		list.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		list.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		list.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		list.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		list.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		list.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		list.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		list.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		list.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		list.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		list.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		list.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		list.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		list.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		list.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		list.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

	}

}
