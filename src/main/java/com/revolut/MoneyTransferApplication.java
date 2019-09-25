package com.revolut;

import com.revolut.core.Person;
import com.revolut.db.PersonDAO;
import com.revolut.resources.DemoResource;
import com.revolut.resources.PersonResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MoneyTransferApplication extends Application<MoneyTransferConfiguration> {

    private final HibernateBundle<MoneyTransferConfiguration> hibernate = new HibernateBundle<MoneyTransferConfiguration>(Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(MoneyTransferConfiguration configuration) {
            return configuration.getDatabaseAppDataSourceFactory();
        }
    };

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

        final PersonDAO personDAO = new PersonDAO(hibernate.getSessionFactory());
        final PersonResource personResource = new PersonResource(personDAO);
        final DemoResource resource = new DemoResource(configuration.getMessage(),
                configuration.getFirstParameter(), configuration.getSecondParameter());

        environment.jersey().register(resource);
        environment.jersey().register(personResource);
    }

}
