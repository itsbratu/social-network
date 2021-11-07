package socialnetwork.repository;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.validators.RepoException;
import socialnetwork.domain.validators.ValidationException;

import java.util.List;

/**
 * CRUD operations repository interface
 * @param <MAIL> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in repository
 */

public interface Repository0<MAIL, E extends Entity<MAIL>> {
    /**
     *
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     *
     * @return all relations
     */
    List<Prietenie> findRelations();

    /**
     *
     * @param entity
     *         entity must be not null
     * @return null- if the given entity is saved
     *         otherwise returns the entity (id already exists)
     * @throws ValidationException
     *            if the entity is not valid
     * @throws IllegalArgumentException
     *             if the given entity is null.     *
     * @throws RepoException
     *             if the entity already exists
     */
    E save(E entity);


    /**
     *  removes the entity with the specified id
     * @param entity
     *      mail must be not null
     * @return the removed entity or null if there is no entity with the given mail
     * @throws IllegalArgumentException
     *                   if the given mail is null.
     */
    E delete(E entity);

    /**
     * adds relation between users with mail1 and mail2
     * @param mail1 - String , mail for user1
     *              mail1 must not be null and must match a regex
     * @param mail2 - String , mail for user2
     *              mail2 must not be null and must match a regex
     * @return null if the relation was added successfully
     * @throws RepoException
     *        if the relation already exists
     */
    E add_relation(String mail1 , String mail2);

    /**
     * delets relation between users with mail1 and mail2
     * @param mail1 - String , mail for user1
     *              mail1 must not be null and match a regex
     * @param mail2 - String , mail for user2
     *              mail2 must not be null and match a regex
     * @return null if  the relation was deleted successfully
     * @throws RepoException
     *          if the relation does not exist
     */
    E delete_relation(String mail1 , String mail2);
}

