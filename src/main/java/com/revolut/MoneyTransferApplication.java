package com.revolut;

import com.revolut.resources.DemoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MoneyTransferApplication().run(args);
    }

    @Override
    public String getName() {
        return "moneytransfer";
    }

    @Override
    public void initialize(final Bootstrap<MoneyTransferConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MoneyTransferConfiguration configuration,
                    final Environment environment) {
        final DemoResource resource = new DemoResource(configuration.getMessage(),
                configuration.getFirstParameter(), configuration.getSecondParameter());
        environment.jersey().register(resource);
    }

}
