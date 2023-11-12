package com.github.panga.appspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class AppSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppSpringApplication.class, args);
	}

	@GetMapping("/data")
	public ResponseEntity<?> getData() {
		return new ResponseEntity<>(Map.of("data", sampleData()), HttpStatus.OK);
	}

	@GetMapping("/concat")
	public ResponseEntity<?> getConcat() {
		return new ResponseEntity<>(Map.of("concat", randomString(10000)), HttpStatus.OK);
	}

	@GetMapping("/fibonacci")
	public ResponseEntity<?> getFibonacci() {
		return new ResponseEntity<>(Map.of("fibonacci", fibonacci(30)), HttpStatus.OK);
	}

	private List<Product> sampleData() {
		final List<Product> data = new ArrayList<>(4);

		data.add(new Product("prod3568", "Egg Whisk", 3.99f, 150));
		data.add(new Product("prod7340", "Tea Cosy", 5.99f, 100));
		data.add(new Product("prod8643", "Spatula", 1f, 80));

		return data;
	}

	private String randomString(final int len) {
		final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final StringBuilder result = new StringBuilder(len + 1);

		for (int i = 0; i < len; i++) {
			final int rand = (int) Math.floor(Math.random() * (alphabet.length()));
			result.append(alphabet.charAt(rand));
		}

		return result.toString();
	}

	private long fibonacci(final int n) {
		if (n < 2) {
			return n;
		}
		return fibonacci(n - 2) + fibonacci(n - 1);
	}
}
