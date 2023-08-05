import io.vertx.core.*;
import io.vertx.core.http.*;
import io.vertx.core.json.*;
import io.vertx.ext.web.*;
import io.vertx.ext.web.handler.*;
import java.util.*;

public class App extends AbstractVerticle {

	@Override
	public void start() {
		final Router router = Router.router(vertx);

		router.route().handler(BodyHandler.create());

		router.get("/data").handler(r -> {
			customHeaders(r.response());
			r.response().end(new JsonObject().put("data", sampleData()).encode());
		});

		router.get("/concat").handler(r -> {
			customHeaders(r.response());
				r.response().end(new JsonObject().put("concat", randomString(10000)).encode());
		});

		router.get("/fibonacci").handler(r -> {
			fibonacciTask(30, res -> {
				customHeaders(r.response());
				r.response().end(new JsonObject().put("fibonacci", res.result()).encode());
			});
		});

		vertx.createHttpServer().requestHandler(router).listen(3000, r -> {
			System.out.println(String.format("Listening on port %d", r.result().actualPort()));
		});
	}

	private void customHeaders(final HttpServerResponse response) {
		response.putHeader("Content-Type", "application/json; charset=utf-8")
			.putHeader("Date", new Date().toString());
	}

	private List<Product> sampleData() {
		final List<Product> data = new ArrayList<>();

		data.add(new Product("prod3568", "Egg Whisk", 3.99f, 150));
		data.add(new Product("prod7340", "Tea Cosy", 5.99f, 100));
		data.add(new Product("prod8643", "Spatula", 1f, 80));

		return data;
	}

	private String randomString(final int len) {
		final String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		final StringBuilder result = new StringBuilder();

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

	private void fibonacciTask(final int n, final Handler<AsyncResult<Long>> handler) {
		vertx.executeBlocking(future -> {
			future.complete(fibonacci(n));
		}, false, handler);
	}
}
