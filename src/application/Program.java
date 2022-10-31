package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		// Unidade padrão de medida USA
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		// Endereço do arquivo
		System.out.print("Enter full file path: ");
		String path = sc.next();

		// Salário base para filtrar
		System.out.print("Enter salary: ");
		Double salary = sc.nextDouble();
		
		// Leitor BufferedReader com FileReader para ler o arquivo csv
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Employee> list = new ArrayList<>();
			
			// ler cada linha até a linha estar vazia
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
				}
			
			double avg = salary;
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > avg)
					.map(e -> e.getEmail())
					.collect(Collectors.toList());
			
			System.out.printf("Email of people whose salary is more than %.2f: %n", avg);
			emails.forEach(System.out::println);
			

			double sum = list.stream()
					.filter(x -> x.getName().charAt(0) == 'M')
					.map(x -> x.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.printf("Sum of salary from people whose name starts with 'M': %.2f%n", sum);
			
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		sc.close();
	}

}
