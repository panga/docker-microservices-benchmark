<?php

$app = new Laravel\Lumen\Application(
    realpath(__DIR__.'/../')
);

$app->configure('app');

// $app->withFacades();
// $app->withEloquent();

// $app->routeMiddleware([
//     'auth' => App\Http\Middleware\Authenticate::class,
// ]);

// $app->register(App\Providers\AppServiceProvider::class);
// $app->register(App\Providers\AuthServiceProvider::class);
// $app->register(App\Providers\EventServiceProvider::class);

require __DIR__.'/../routes/web.php';

return $app;
