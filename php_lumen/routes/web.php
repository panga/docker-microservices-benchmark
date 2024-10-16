<?php

$router->get('/data', function () use ($router) {
    return response()->json([
        'data' => [
            [
                'id' => 'prod3568',
                'name' => 'Egg Whisk',
                'price' => 3.99,
                'weight' => 150
            ],
            [
                'id' => 'prod7340',
                'name' => 'Tea Cosy',
                'price' => 5.99,
                'weight' => 100
            ],
            [
                'id' => 'prod8643',
                'name' => 'Spatula',
                'price' => 1,
                'weight' => 80
            ]
        ]
    ]);
});

$router->get('/concat', function () use ($router) {
    $alphabet = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
    $result = '';
    $length = 10000;

    for ($i = 0; $i < $length; $i++) {
        $result .= $alphabet[rand(0, strlen($alphabet) - 1)];
    }

    return response()->json(['concat' => $result]);
});

$router->get('/fibonacci', function () use ($router) {
    function fibonacci($n) {
        if ($n < 2) {
            return $n;
        }
        return fibonacci($n - 2) + fibonacci($n - 1);
    }

    return response()->json(['fibonacci' => fibonacci(30)]);
});
