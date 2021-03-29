package edu.umb.cs681.hw03;
import java.util.ArrayList;
import java.util.List;
public class Car {
	private String model, make;
		private int mileage, year;
		private int price;
		private int dominationCount;
		
		public Car(String make, String model, int mileage, int year, int price) {
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

		public int getPrice() {
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
		cars.add(new Car("BMW", "M5", 16, 2020, 50000));
		cars.add(new Car("Lamborghini", "Aventador", 14, 2021, 70000));
		cars.add(new Car("Maserati", "Ghibli", 17, 2018, 60000));
		cars.add(new Car("Porsche", "Taycan", 15, 2019, 80000));
		System.out.println("Cars price List");	
		cars.forEach( (Car car)->System.out.println(car.getMake()+"="+car.getPrice()));

		Integer min_price = cars.stream().map( (Car car)-> car.getPrice()).reduce(0,(result, PriceOfcar)->{
			
							if(result==0)
								return PriceOfcar;
							else if(PriceOfcar < result) 
								return PriceOfcar;
							else 
								return result;});
		
		System.out.println("Minimum price in this list is : "+min_price);
		

		Integer max_price = cars.stream().map( (Car car)-> car.getPrice()).reduce(0,(result, PriceOfcar)->{
			
							if(result==0)
								return PriceOfcar;
							else if(PriceOfcar < result) 
								return result;
							else 
								return PriceOfcar;});
		
		System.out.println("Maximum price in this list is : "+max_price);
		

		
		Integer car_count = cars.stream().map( (Car car)-> car.getMileage() ).reduce(0, (result, MileageOfcar)->{
			
			return ++result;
		});

	    System.out.println("Count of the car in this list is : "+car_count);

		}
	

}
