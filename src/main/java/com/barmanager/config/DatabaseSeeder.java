package com.barmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@Profile("prod") // Solo se ejecuta en Render
public class DatabaseSeeder implements CommandLineRunner {

    private final DataSource dataSource;

    @Value("${app.db.seed-on-start:true}")
    private boolean seedOnStart;

    public DatabaseSeeder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!seedOnStart) {
            System.out.println(">>> DB Seed deshabilitado");
            return;
        }

        System.out.println(">>> Ejecutando DB Seeder...");

        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(
                conn,
                new ClassPathResource("CompleteData-postgres.sql")
            );
        }

        System.out.println(">>> DB Seed COMPLETADO");
    }
}
