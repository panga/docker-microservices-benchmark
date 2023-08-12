package main

import (
	"math/rand"

	"github.com/gofiber/fiber/v2"

	_ "go.uber.org/automaxprocs"
)

func main() {
	app := fiber.New()

	app.Get("/data", func(c *fiber.Ctx) error {
		return c.JSON(&DataResponse{Data: sampleData()})
	})

	app.Get("/concat", func(c *fiber.Ctx) error {
		return c.JSON(&ConcatResponse{Concat: randomString(10000)})
	})

	app.Get("/fibonacci", func(c *fiber.Ctx) error {
		return c.JSON(&FibonacciResponse{Fibonacci: fibonacci(30)})
	})

	app.Listen(":3000")
}

type DataResponse struct {
	Data     []Product  `json:"data"`
}

type ConcatResponse struct {
	Concat     string  `json:"concat"`
}

type FibonacciResponse struct {
	Fibonacci     int  `json:"fibonacci"`
}

type Product struct {
	ID     string  `json:"id"`
	Name   string  `json:"name"`
	Price  float32 `json:"price"`
	Weight int     `json:"weight"`
}

func sampleData() []Product {
	data := []Product{}

	data = append(data, Product{
		ID:     "prod3568",
		Name:   "Egg Whisk",
		Price:  3.99,
		Weight: 150})
	data = append(data, Product{
		ID:     "prod7340",
		Name:   "Tea Cosy",
		Price:  5.99,
		Weight: 100})
	data = append(data, Product{
		ID:     "prod8643",
		Name:   "Spatula",
		Price:  1,
		Weight: 80})

	return data
}

func randomString(length int) string {
	alphabet := "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
	result := make([]byte, length)

	alphabetLen := len(alphabet)
	for i := range result {
        result[i] = alphabet[rand.Intn(alphabetLen)]
    }

    return string(result)
}

func fibonacci(n int) int {
	if n < 2 {
		return n
	}
	return fibonacci(n-2) + fibonacci(n-1)
}
