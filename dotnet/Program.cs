var builder = WebApplication.CreateBuilder(args);
builder.Logging.SetMinimumLevel(LogLevel.Error);
builder.Logging.AddFilter("Microsoft.Hosting", LogLevel.Information);
var app = builder.Build();
app.MapGet("/data", () => Results.Ok(new DataResponse(SampleData())));
app.MapGet("/concat", () => Results.Ok(new ConcatResponse(RandomString(10000))));
app.MapGet("/fibonacci", () => Results.Ok(new FibonacciResponse(Fibonacci(30))));
app.Run("http://0.0.0.0:3000");

List<Product> SampleData()
{
    return new List<Product>
    {
        new Product("prod3568", "Egg Whisk", 3.99f, 150),
        new Product("prod7340", "Tea Cosy", 5.99f, 100),
        new Product("prod8643", "Spatula", 1, 80)
    };
}

string RandomString(int length)
{
    var alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    var result = new char[length];

    var random = new Random();

    for (int i = 0; i < length; i++)
    {
        result[i] = alphabet[random.Next(alphabet.Length)];
    }

    return new string(result);
}

int Fibonacci(int n)
{
    if (n < 2)
    {
        return n;
    }
    return Fibonacci(n - 2) + Fibonacci(n - 1);
}

record DataResponse(List<Product> Data);
record ConcatResponse(string Concat);
record FibonacciResponse(int Fibonacci);
record Product(string ID, string Name, float Price, int Weight);

