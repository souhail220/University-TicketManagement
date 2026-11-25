package tn.isamm.ticketManagement.repositories.RepoInterface;

import java.util.Map;
import tn.isamm.ticketManagement.models.Specialite;
import tn.isamm.ticketManagement.models.Moderateur;

public interface ModerateurRepository extends JooqRepository<Moderateur> {

    Map<String, Specialite> fetchSpecialitesById();
}
