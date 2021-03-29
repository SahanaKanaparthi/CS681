package edu.umb.cs681.hw02;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;
public class Car {
	private String model, make;
	private int mileage, year;
	private float price;
	private int dominationCount;
	
	public Car(String make, String model, int mileage, int year, float price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}
	
	public String getModel() {
		return model;
	}

	public String getMake() {
		return make;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public float getPrice() {
		return price;
	}

	public int getDominationCount() {
		return this.dominationCount;
	}
	
public void setDominationCount(List<Car> cars) {
		
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--; 	
	}
public static void main(String args[]) {
	List<Car> cars = new ArrayList<>();
	cars.add(new Car("Maserati", "Ghibli", 17, 2018, 60000));
	cars.add(new Car("BMW", "M5", 16, 2020, 50000));
	cars.add(new Car("Lamborghini", "Aventador", 14, 2021, 70000));
	cars.add(new Car("Porsche", "Taycan", 15, 2019, 80000));

	cars.forEach((Car car) -> car.setDominationCount(cars));

	List<Car> sortByPrice = cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Price");
	
	sortByPrice.forEach((Car car) -> System.out.println(car.getMake() + "= " + car.getPrice()));
	System.out.println("<-------------------------------------->");
	
	List<Car> sortByMileage = cars.stream().sorted(Comparator.comparing(Car::getMileage)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Mileage");
	
	sortByMileage.forEach((Car car) -> System.out.println(car.getMake() + "= " + car.getMileage()));
	System.out.println("<-------------------------------------->");


	List<Car> sortByYear = cars.stream().sorted(Comparator.comparing(Car::getYear)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Year");
	
	sortByYear.forEach((Car car) -> System.out.println(car.getMake() + "= " + car.getYear()));
	System.out.println("<-------------------------------------->");

	
//	cars.forEach((Car car) -> car.setDominationCount(cars));

	List<Car> sortByDominationCount = cars.stream().sorted(Comparator.comparing(Car::getDominationCount)).collect(Collectors.toList());
	System.out.println("List of cars sorted by Domination count");
	
	sortByDominationCount.forEach((Car car) -> System.out.println(car.getMake() + "=" + car.getDominationCount()));
}
}
