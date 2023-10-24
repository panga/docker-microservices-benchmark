package com.github.panga.app;

public class Product {

	private final String id;
	private final String name;
	private final Float price;
	private final Integer weight;

	public Product(final String id, final String name, final Float price, final Integer weight) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Float getPrice() {
		return price;
	}

	public Integer getWeight() {
		return weight;
	}
}
