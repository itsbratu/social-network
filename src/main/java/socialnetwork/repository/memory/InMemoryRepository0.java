package socialnetwork.repository.memory;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.validators.RepoException;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository0<MAIL, E extends Entity<MAIL>> implements Repository0<MAIL,E> {

    private Validator<E> validator;
    Map<MAIL,E> entities;
    List<Prietenie> relations;

    public InMemoryRepository0(Validator<E> validator) {
        this.validator = validator;
        entities=new HashMap<MAIL,E>();
        relations=new ArrayList<Prietenie>();
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public List<Prietenie> findRelations(){return relations;}

    @Override
    public E save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");
        validator.validate(entity);
        if(entities.get(entity.getMail()) != null) {
            throw new RepoException("User cu aceasta adresa de mail deja inregistrat!");
        }
        else entities.put(entity.getMail(),entity);
        return null;
    }

    @Override
    public E delete(E entity) {
        if (entity == null) throw new IllegalArgumentException("entity must be not null");
        relations.removeIf(relation -> (entity.getMail().equals(relation.user_mail1)) || (entity.getMail().equals(relation.user_mail2)));
        entities.remove(entity.getMail());
        return null;
    }

    @Override
    public E add_relation(String mail1 , String mail2){
        for (Prietenie relation : relations) {
            if((mail1.equals(relation.user_mail1) && mail2.equals(relation.user_mail2)) || (mail1.equals(relation.user_mail2) && mail2.equals(relation.user_mail1))){
                throw new RepoException("Relation already existent!");
            }
        }
        relations.add(new Prietenie(mail1 , mail2));
        return null;
    }

    @Override
    public E delete_relation(String mail1 , String mail2){
        boolean found = false;
        for (Prietenie relation : relations) {
            if((mail1.equals(relation.user_mail1) && mail2.equals(relation.user_mail2)) || (mail1.equals(relation.user_mail2) && mail2.equals(relation.user_mail1))){
                relations.remove(relation);
                found = true;
                break;
            }
        }
        if(!found){
            throw new RepoException("Relation does not exist!");
        }
        return null;
    }
}
