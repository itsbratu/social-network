package socialnetwork.service;

import socialnetwork.utils.Graph;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.repository.Repository0;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for our app
 */
public class UtilizatorService0 {
    //The service takes a repository as a field
    private Repository0<String, Utilizator> repo;

    /**
     * Constructor with parameters for Service class
     * @param repo - Repository0 object
     */
    public UtilizatorService0(Repository0<String, Utilizator> repo) {
        this.repo = repo;
    }

    /**
     * Adds a user to the repository
     * @param messageTask - 'Utilizator' object
     */
    public void addUtilizator(Utilizator messageTask) {
        Utilizator task = repo.save(messageTask);
    }

    /**
     * Delets a user from the repository
     * @param messageTask - 'Utilizator' object
     */
    public void deleteUtilizator(Utilizator messageTask){
        repo.delete(messageTask);
    }

    /**
     * Adds a relation to the repository
     * @param user1 - String (mail address for user1)
     * @param user2 - String (mail address for user2)
     */
    public void addRelation(String user1 , String user2){
        repo.add_relation(user1 , user2);
    }

    /**
     * Delets a relation from the repository
     * @param user1 - String (mail address for user1)
     * @param user2 - String (mail address for user2)
     */
    public void deleteRelation(String user1 , String user2){
        repo.delete_relation(user1 , user2);
    }

    /**
     * Finds the number of conex components from the network
     * @return connectedComponents - int
     */
    public int conexComponents(){
        Iterable<Utilizator> current_users = repo.findAll();
        Map<String , Integer> mailID = new HashMap<String , Integer>();

        int id_curr = 0;
        for(Utilizator u : current_users){
            mailID.put(u.getMail() , id_curr);
            id_curr++;
        }
        Graph g = new Graph(id_curr);

        List<Prietenie>all_relations = repo.findRelations();
        for(Prietenie p : all_relations){
            int firstVortex = mailID.get(p.user_mail1);
            int secondVortex = mailID.get(p.user_mail2);
            g.addEdge(firstVortex , secondVortex);
        }
        g.DFS();
        return g.ConnecetedComponents();

    }

    /**
     * Gets all 'Utilizator' objects from the repo
     * @return - repo.findAll() - an iterable of 'Utilizator' objects
     */
    public Iterable<Utilizator> getAll(){
        return repo.findAll();
    }

    /**
     * Gets all 'Prietenie' objects from the repo
     * @return repo.findRelations() - a list of 'Prietenie' objects
     */
    public List<Prietenie> getRelations(){return repo.findRelations();}
}
