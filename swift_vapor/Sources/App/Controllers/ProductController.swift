import Vapor

final class ProductController {
    func dataHandler(_ req: Request) throws -> [Product] {
        return sampleData()
    }

    func concatHandler(_ req: Request) throws -> String {
        return randomString(length: 10000)
    }

    func fibonacciHandler(_ req: Request) throws -> Int {
        return fibonacci(n: 30)
    }

    func boot(routes: RoutesBuilder) throws {
        let productRoutes = routes.grouped("product")
        productRoutes.get("data", use: dataHandler)
        productRoutes.get("concat", use: concatHandler)
        productRoutes.get("fibonacci", use: fibonacciHandler)
    }
}
