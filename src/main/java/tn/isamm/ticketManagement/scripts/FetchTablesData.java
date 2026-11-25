package tn.isamm.ticketManagement.scripts;

import java.util.List;

import tn.isamm.ticketManagement.models.Etudiant;
import tn.isamm.ticketManagement.models.Moderateur;
import tn.isamm.ticketManagement.models.Specialite;
import tn.isamm.ticketManagement.models.Ticket;
import tn.isamm.ticketManagement.repositories.RepoInterface.EtudiantRepository;
import tn.isamm.ticketManagement.repositories.RepoInterface.ModerateurRepository;
import tn.isamm.ticketManagement.repositories.RepoInterface.SpecialiteRepository;
import tn.isamm.ticketManagement.repositories.RepoInterface.TicketRepository;

public final class FetchTablesData {
    private final TicketRepository ticketRepository;
    private final EtudiantRepository etudiantRepository;
    private final ModerateurRepository moderateurRepository;
    private final SpecialiteRepository specialiteRepository;

    public FetchTablesData(TicketRepository ticketRepository,
                           EtudiantRepository etudiantRepository,
                           ModerateurRepository moderateurRepository,
                           SpecialiteRepository specialiteRepository) {
        this.ticketRepository = ticketRepository;
        this.etudiantRepository = etudiantRepository;
        this.moderateurRepository = moderateurRepository;
        this.specialiteRepository = specialiteRepository;
    }

    public void fetchTables() {
        fetchTickets().forEach(ticket -> System.out.println(
                ticket.getId() + " | " + ticket.getTitre() + " | " + ticket.getStatut()));

        fetchStudents().forEach(student -> System.out.println(
                student.getId() + " | " + student.getNom() + " | " + student.getEmail()));

        fetchModerators().forEach(moderator -> System.out.println(
                moderator.getId() + " | " + moderator.getNom() + " | "
                        + (moderator.getSpecialite() == null ? "sans spécialité"
                        : moderator.getSpecialite().getNom())));

        fetchSpecialites().forEach(specialite -> System.out.println(
                specialite.getId() + " | " + specialite.getNom()));
    }

    public List<Ticket> fetchTickets() {
        return ticketRepository.findAll();
    }

    public List<Etudiant> fetchStudents() {
        return etudiantRepository.findAll();
    }

    public List<Moderateur> fetchModerators() {
        return moderateurRepository.findAll();
    }

    public List<Specialite> fetchSpecialites() {
        return specialiteRepository.findAll();
    }
}
