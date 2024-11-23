// swift-tools-version: 6.0
import PackageDescription

let package = Package(
    name: "App",
    dependencies: [
        .package(url: "https://github.com/vapor/vapor.git", from: "4.106.4")
    ],
    targets: [
        .target(
            name: "App",
            dependencies: [
                .product(name: "Vapor", package: "vapor")
            ],
            path: "Sources"
        )
    ]
)
