package com.nznext.geargrove.login.utils;

import com.nznext.geargrove.login.entity.Role;
import com.nznext.geargrove.login.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component that initializes default roles in the database when the application starts.
 *
 * <p>This class implements {@link CommandLineRunner}, which means the {@link #run(String... args)} method will be
 * executed at startup. It checks whether the roles "USER" and "ADMIN" exist in the database, and if not, it creates them.</p>
 *
 * <p>Annotations used:</p>
 * <ul>
 *   <li>{@code @Component} - Marks this class as a Spring component, making it eligible for dependency injection
 *   and ensuring it is executed at startup.</li>
 *   <li>{@code @RequiredArgsConstructor} - Automatically generates a constructor for all required fields (final fields),
 *   enabling dependency injection of the {@link RoleRepository}.</li>
 * </ul>
 */
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    /** The repository for managing roles in the database. */
    private final RoleRepository roleRepository;

    /**
     * Initializes default roles at the start of the application.
     *
     * <p>This method is executed on startup to ensure that the roles "USER" and "ADMIN" exist in the database.
     * If they don't exist, new roles are created and saved into the database.</p>
     *
     * @param args command-line arguments (not used in this implementation).
     * @throws Exception if an error occurs during the initialization process.
     */
    @Override
    public void run(String... args) throws Exception {
        // Check if "USER" role exists, if not, create and save it
        if (!roleRepository.existsByName("USER")) {
            Role userRole = new Role();
            userRole.setName("USER");
            roleRepository.save(userRole);
        }

        // Check if "ADMIN" role exists, if not, create and save it
        if (!roleRepository.existsByName("ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }
    }
}
