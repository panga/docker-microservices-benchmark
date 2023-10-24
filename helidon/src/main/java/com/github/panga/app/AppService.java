package com.github.panga.app;

import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppService implements HttpService {

    AppService() {
    }

    @Override
    public void routing(HttpRules rules) {
        rules.get("/data", (req, res) ->
                res.send(Map.of("data", sampleData())));

        rules.get("/concat", (req, res) ->
                res.send(Map.of("concat", randomString(10000))));

        rules.get("/fibonacci", (req, res) ->
                res.send(Map.of("fibonacci", fibonacci(30))));
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

