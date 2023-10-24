import io.vertx.core.*;
import io.vertx.core.json.*;
import io.vertx.ext.web.*;
import java.util.*;

public class App extends AbstractVerticle {

	@Override
	public void start() {
		final Router router = Router.router(vertx);

		router.get("/data").handler(ctx ->
				ctx.json(new JsonObject().put("data", sampleData())));

		router.get("/concat").handler(ctx ->
				ctx.json(new JsonObject().put("concat", randomString(10000))));

		router.get("/fibonacci").blockingHandler(ctx ->
				ctx.json(new JsonObject().put("fibonacci", fibonacci(30))));

		vertx.createHttpServer().requestHandler(router).listen(3000, r -> {
			System.out.println(String.format("Listening on port %d", r.result().actualPort()));
		});
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
