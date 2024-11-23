use warp::Filter;
use rand::Rng;
use serde::{Serialize, Deserialize};

#[derive(Serialize, Deserialize)]
struct Product {
    id: String,
    name: String,
    price: f64,
    stock: u32,
}

#[tokio::main]
async fn main() {
    let data_route = warp::path("data")
        .map(|| {
            let data = vec![
                Product { id: "prod3568".to_string(), name: "Egg Whisk".to_string(), price: 3.99, stock: 150 },
                Product { id: "prod7340".to_string(), name: "Tea Cosy".to_string(), price: 5.99, stock: 100 },
                Product { id: "prod8643".to_string(), name: "Spatula".to_string(), price: 1.0, stock: 80 },
            ];
            warp::reply::json(&data)
        });

    let concat_route = warp::path("concat")
        .map(|| {
            let charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            let random_string: String = (0..10000)
                .map(|_| {
                    let idx = rand::thread_rng().gen_range(0..charset.len());
                    charset.chars().nth(idx).unwrap()
                })
                .collect();
            warp::reply::json(&serde_json::json!({"random_string": random_string}))
        });

    let fibonacci_route = warp::path("fibonacci")
        .map(|| {
            let result = fibonacci(30);
            warp::reply::json(&serde_json::json!({"fibonacci": result}))
        });

    let routes = data_route.or(concat_route).or(fibonacci_route);

    warp::serve(routes).run(([0, 0, 0, 0], 3000)).await;
}

fn fibonacci(n: u32) -> u32 {
    match n {
        0 => 0,
        1 => 1,
        _ => fibonacci(n - 1) + fibonacci(n - 2),
    }
}
