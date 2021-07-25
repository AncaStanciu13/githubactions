package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Product;
import com.example.demo.Category;
import com.example.demo.ExampleData;

@SpringBootApplication
public class DemoApplication {
	static Optional<Product> findProduct(List<Product> products, Predicate<Product> predicate) {
		for (Product product : products) {
			if (predicate.test(product)) {
				return Optional.of(product);
			}
		}

		return Optional.empty();
	}
	
	public static void main(String[] args) {
		List<Product> products = ExampleData.getProducts();

		String name = "Spaghetti";
		findProduct(products, product -> product.getName().equals(name)).map(Product::getPrice).ifPresentOrElse(
				price -> System.out.println("name %s had price %p"+ name+ price),
				() -> System.out.println("Not available %n" + name));
		
		Map<Category, List<Product>> productsByCategory = new HashMap<>();
		
		for(Product product: products) {
			
			productsByCategory.computeIfAbsent(product.getCategory(), c -> new ArrayList<>()).add(product);
		}
		
		Stream<Product> stream = products.stream().map(product -> {
			System.out.println(product);
			return product;
		});
		
		stream.forEach(System.out::println);
		
		String[] array = new String[] {"one", "two", "three"};
		Stream<String> stream2 = Arrays.stream(array);
		Stream<String> stream3 = Stream.of("one");
		Stream<String> stream4 = Stream.ofNullable("four");
		
		SpringApplication.run(DemoApplication.class, args);
	}

}
