package com.github.aushacker.otel.openliberty.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/dbtest")
public class DbTestResource {

    @Inject
    @ConfigProperty(name="DB_TYPE")
    public String dbType;

    @Inject
    @ConfigProperty(name="DB_HOST")
    public String dbHost;

    @Inject
    @ConfigProperty(name="DB_PORT")
    public String dbPort;

    @Inject
    @ConfigProperty(name="DB_NAME")
    public String dbName;

    @Inject
    @ConfigProperty(name="DB_USER")
    public String dbUser;

    @Inject
    @ConfigProperty(name="DB_PASSWORD")
    public String dbPassword;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testConnect() {
        String connectString = "jdbc:" + dbType + "://" + dbHost + ":" + dbPort + "/" + dbName + "?user=" + dbUser + "&password=" + dbPassword;
        try (Connection connection = DriverManager.getConnection(connectString);
             Statement s = connection.createStatement()) {
            s.execute("SELECT NOW()");
        }
        catch (SQLException ex) {
            return "Failed\n";
        }

        return "Connected OK\n";
    }
}
