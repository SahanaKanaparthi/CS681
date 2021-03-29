package edu.umb.cs681.hw04;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;
public class Hotel {
	private String name, location;
	private double rating;
	private int avg_price;	
public Hotel(String name, String location, double rating, int avg_price) {
		
		this.name = name;
		this.location = location;
		this.rating = rating;
		this.avg_price = avg_price;
		
	}
public String getName() {
	return name;
}


public String getLocation() {
	return location;
}


public double getRating() {
	return rating;
}


public int getAvg_price() {
	return avg_price;
}

public static void main(String args[]) {
	List<Hotel> list = new ArrayList<>();
	list.add(new Hotel("DoubleTree by Hilton Hotel Boston Bayside", "Boston", 4,240));
	list.add(new Hotel("The Godfrey Hotel Boston", "Boston", 4.5, 350));
	list.add(new Hotel("Holiday Inn Express", "Boston", 4.3, 294));
	list.add(new Hotel("Boston park plaza", "Boston", 4.2, 270));
	list.add(new Hotel("The Revolution Hotel", "Boston", 4.1, 170));

Hotel rating = list.stream()
			.max(Comparator.comparing(Hotel::getRating)).get();
	System.out.println("Hotel with highest rating is " +rating.getName() +": "+ rating.getRating());

	Hotel avgPrice = list.stream()
			.min(Comparator.comparing(Hotel::getAvg_price)).get();
	System.out.println("Hotel with Lowest Avg price per night is " +avgPrice.getName() +": " + avgPrice.getAvg_price());

	List<Hotel> sortedByRating = list.stream()
			.sorted(Comparator.comparing(Hotel::getRating))
			.collect(Collectors.toList());
	System.out.println("Hotel overall Rating : ");
	sortedByRating.forEach((Hotel r) -> System.out.println(r.getName() + ": " + r.getRating()));
	
	List<Hotel> sortedByCost = list.stream()
			.sorted(Comparator.comparing(Hotel::getAvg_price))
			.collect(Collectors.toList());
	System.out.println("Hotel Average cost per night : ");
	sortedByCost.forEach((Hotel r) -> System.out.println(r.getName() + ": " + r.getAvg_price()));

	}

}

