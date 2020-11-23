package xyz.toddlasley.dropwizarddemo;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import xyz.toddlasley.dropwizarddemo.health.DemoHealthCheck;
import xyz.toddlasley.dropwizarddemo.resources.DemoResource;

public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropwizard-demo";
    }

    @Override
    public void initialize(final Bootstrap<DemoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final DemoConfiguration configuration,
                    final Environment environment) {
        final DemoResource resource = new DemoResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final DemoHealthCheck healthCheck =
                new DemoHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("demo", healthCheck);
        environment.jersey().register(resource);
    }

}
