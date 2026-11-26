package tn.isamm.ticketManagement.scenario;

import java.util.Scanner;
import java.util.UUID;

import tn.isamm.ticketManagement.models.Ticket;
import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.repositories.RepoInterface.EtudiantRepository;
import tn.isamm.ticketManagement.repositories.RepoInterface.TicketRepository;

public class CreateTicketScenario {

    private final TicketRepository ticketRepository;
    private final EtudiantRepository etudiantRepository;
    private final Scanner scanner;

    public CreateTicketScenario(TicketRepository ticketRepository, EtudiantRepository etudiantRepository) {
        this(ticketRepository, etudiantRepository, new Scanner(System.in));
    }

    public CreateTicketScenario(TicketRepository ticketRepository, EtudiantRepository etudiantRepository,
                                Scanner scanner) {
        if (ticketRepository == null || etudiantRepository == null || scanner == null) {
            throw new IllegalArgumentException("Repositories and scanner are required.");
        }
        this.ticketRepository = ticketRepository;
        this.etudiantRepository = etudiantRepository;
        this.scanner = scanner;
    }

    /** Reads ticket details from the console and stores the new ticket. */
    public void run() {
        System.out.println("=== Create a ticket ===");

        Long etudiantId = readStudentId();
        if (etudiantId == null) return;

        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        if (etudiant == null) {
            System.out.println("No student found with ID: " + etudiantId);
            return;
        }

        String titre = readRequired("Title: ");
        if (titre == null) return;

        String description = readRequired("Description: ");
        if (description == null) return;

        String tag = readRequired("Tag: ");
        if (tag == null) return;

        Ticket ticket = new Ticket(UUID.randomUUID().toString(), "OPEN", etudiant.getDateNaissance(),
                titre, description, tag);
        ticket.setEtudiant(etudiant);
        int insertedRows = ticketRepository.insert(ticket);

        if (insertedRows == 1) {
            System.out.println("Ticket created successfully. ID: " + ticket.getId());
        } else {
            System.out.println("Ticket was not created.");
        }
    }

    private String readRequired(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextLine()) {
                System.out.println("Input ended. Ticket creation cancelled.");
                return null;
            }

            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field is required. Please try again.");
        }
    }

    private Long readStudentId() {
        while (true) {
            String value = readRequired("Student ID: ");
            System.out.println("Hello");
            if (value == null) return null;
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                System.out.println("Student ID must be a whole number.");
            }
        }
    }
}
