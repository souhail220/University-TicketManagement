package tn.isamm.ticketManagement.scenario;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.scripts.InsertTablesData;

/** Console scenarios for student operations. */
public class StudentScenarios {

    private final InsertTablesData insertTablesData;
    private final Scanner scanner;

    public StudentScenarios(InsertTablesData insertTablesData) {
        this(insertTablesData, new Scanner(System.in));
    }

    public StudentScenarios(InsertTablesData insertTablesData, Scanner scanner) {
        if (insertTablesData == null || scanner == null) {
            throw new IllegalArgumentException("Insert service and scanner are required.");
        }
        this.insertTablesData = insertTablesData;
        this.scanner = scanner;
    }

    /** Prompts for a student's details and inserts the new student. */
    public void createEtudiant() {
        System.out.println("=== Create a student ===");

        Long id = readId();
        if (id == null) return;

        String email = readRequired("Email: ");
        if (email == null) return;

        String nom = readRequired("Name: ");
        if (nom == null) return;

        String telephone = readRequired("Phone: ");
        if (telephone == null) return;

        LocalDate dateNaissance = readDateNaissance();
        if (dateNaissance == null) return;

        String adresse = readRequired("Address: ");
        if (adresse == null) return;

        Etudiant etudiant = new Etudiant(id, email, nom, telephone, dateNaissance, adresse, null);
        int insertedRows = insertTablesData.insertEtudiant(etudiant);

        if (insertedRows == 1) {
            System.out.println("Student created successfully. ID: " + etudiant.getId());
        } else {
            System.out.println("Student was not created.");
        }
    }

    /** Alias for running this scenario from a menu or application entry point. */
    public void run() {
        createEtudiant();
    }

    private Long readId() {
        while (true) {
            String value = readRequired("Student ID: ");
            if (value == null) return null;
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                System.out.println("Student ID must be a whole number.");
            }
        }
    }

    private String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("Input ended. Student creation cancelled.");
                return null;
            }

            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field is required. Please try again.");
        }
    }

    private LocalDate readDateNaissance() {
        while (true) {
            String value = readRequired("Birth date (YYYY-MM-DD): ");
            if (value == null) return null;
            try {
                return LocalDate.parse(value);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use the YYYY-MM-DD format.");
            }
        }
    }
}
