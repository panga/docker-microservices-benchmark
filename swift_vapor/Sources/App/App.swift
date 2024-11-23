import Vapor

func routes(_ app: Application) throws {
    app.get("data") { req -> [Product] in
        return sampleData()
    }

    app.get("concat") { req -> String in
        return randomString(length: 10000)
    }

    app.get("fibonacci") { req -> Int in
        return fibonacci(n: 30)
    }
}

func sampleData() -> [Product] {
    return [
        Product(id: "prod3568", name: "Egg Whisk", price: 3.99, weight: 150),
        Product(id: "prod7340", name: "Tea Cosy", price: 5.99, weight: 100),
        Product(id: "prod8643", name: "Spatula", price: 1, weight: 80)
    ]
}

func randomString(length: Int) -> String {
    let alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    return String((0..<length).map { _ in alphabet.randomElement()! })
}

func fibonacci(n: Int) -> Int {
    if n < 2 {
        return n
    }
    return fibonacci(n: n - 2) + fibonacci(n: n - 1)
}

public func configure(_ app: Application) throws {
    try routes(app)
}

@main
struct App {
    static func main() throws {
        var env = try Environment.detect()
        try LoggingSystem.bootstrap(from: &env)
        let app = Application(env)
        defer { app.shutdown() }
        try configure(app)
        try app.run()
    }
}
