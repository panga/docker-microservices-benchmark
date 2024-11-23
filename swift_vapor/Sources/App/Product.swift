import Vapor

final class Product: Content {
    var id: String
    var name: String
    var price: Double
    var weight: Int

    init(id: String, name: String, price: Double, weight: Int) {
        self.id = id
        self.name = name
        self.price = price
        self.weight = weight
    }
}
