package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entity.Employee;

public class Main {
	public static void main(String args[]) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> emp = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] field = line.split(",");
				emp.add(new Employee(field[0], field[1], Double.parseDouble(field[2])));
				line = br.readLine();
			}
			System.out.print("Enter salary: ");
			double minSalary = sc.nextDouble();
			double sum = emp.stream().filter(p -> p.getName().charAt(0) == 'M').map(p -> p.getSalary()).reduce(0.0,
					(x, y) -> x + y);
			
			List<String> emails = emp.stream().filter(p -> p.getSalary() >= minSalary).map(p -> p.getEmail()).sorted()
					.collect(Collectors.toList());

			System.out.println("Email of people whose salary is more than 2000.00:");

			emails.forEach(System.out::println);

			System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
		}
		sc.close();
	}
}
