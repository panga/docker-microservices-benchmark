
package com.github.panga.app;

import io.helidon.logging.common.LogConfig;
import io.helidon.config.Config;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        LogConfig.configureRuntime();
        Config config = Config.create();
        Config.global(config);

        WebServer server = WebServer.builder()
                .config(config.get("server"))
                .routing(Main::routing)
                .build()
                .start();

        System.out.println(String.format("Listening on port %d", server.port()));
    }

    static void routing(HttpRouting.Builder routing) {
        routing.register("/", new AppService());
    }
}