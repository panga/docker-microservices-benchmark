package main

import (
	"bytes"
	"io"
	"math/rand"
	"net/http"

	"gopkg.in/gin-gonic/gin.v1"
)

func main() {
	router := gin.New()

	router.Use(customHeaders())

	router.GET("/data", func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{
			"data": sampleData(),
		})
	})

	router.GET("/concat", func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{
			"concat": randomString(10000),
		})
	})

	router.GET("/fibonacci", func(context *gin.Context) {
		context.JSON(http.StatusOK, gin.H{
			"fibonacci": fibonacci(30),
		})
	})

	router.Run(":3000")
}

func customHeaders() gin.HandlerFunc {
	return customHeadersWithWriter(gin.DefaultWriter)
}

func customHeadersWithWriter(out io.Writer) gin.HandlerFunc {
	return func(context *gin.Context) {
		context.Header("Connection", "close")
		context.Next()
	}
}

type product struct {
	ID     string  `json:"id"`
	Name   string  `json:"name"`
	Price  float32 `json:"price"`
	Weight int     `json:"weight"`
}

func sampleData() []product {
	data := []product{}

	data = append(data, product{
		ID:     "prod3568",
		Name:   "Egg Whisk",
		Price:  3.99,
		Weight: 150})
	data = append(data, product{
		ID:     "prod7340",
		Name:   "Tea Cosy",
		Price:  5.99,
		Weight: 100})
	data = append(data, product{
		ID:     "prod8643",
		Name:   "Spatula",
		Price:  1,
		Weight: 80})

	return data
}

func randomString(length int) string {
	alphabet := "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
	var result bytes.Buffer

	alphabetLen := len(alphabet)
	for i := 0; i < length; i++ {
		random := rand.Intn(alphabetLen)
		result.WriteByte(alphabet[random])
	}

	return result.String()
}

func fibonacci(n int) int {
	if n < 2 {
		return n
	}
	return fibonacci(n-2) + fibonacci(n-1)
}
