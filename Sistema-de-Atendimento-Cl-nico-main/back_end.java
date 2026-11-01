package com.clinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaApplication {
    public static final String SUPABASE_PUBLISHABLE_KEY = "sb_publishable_Jx4TsKDfo7hD8QCrAAbumg_5d1cAkLX";
    public static final String SUPABASE_SECRET_KEY = "sb_secret_REu2nPMOcgXEh3kaE_4k7g__KrYZ2-y";

    public static void main(String[] args) {
        SpringApplication.run(ClinicaApplication.class, args);
    }
}
