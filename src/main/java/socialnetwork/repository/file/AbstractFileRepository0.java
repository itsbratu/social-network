package socialnetwork.repository.file;

import socialnetwork.domain.Entity;
import socialnetwork.domain.Prietenie;
import socialnetwork.domain.validators.RepoException;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.memory.InMemoryRepository0;

import java.io.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class for file repository
 * @param <MAIL> generic field for mail address
 * @param <E> generic field for entity
 */

public abstract class AbstractFileRepository0<MAIL, E extends Entity<MAIL>> extends InMemoryRepository0<MAIL,E> {
    String fileName;
    String fileRelations;

    /**
     * Constructor with parameters for AbstractFileRepository0
     * @param fileName - String (file where we store our users)
     * @param fileRelations - String (file where we store our relations)
     * @param validator - Validator (validator for 'Utilizator' entity)
     */
    public AbstractFileRepository0(String fileName, String fileRelations , Validator<E> validator) {
        super(validator);
        this.fileName=fileName;
        this.fileRelations = fileRelations;
        loadData();
        loadRelations();
    }

    /**
     * Function in which we load our users from file to memory
     */
    private void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while((linie=br.readLine())!=null){
                try{
                    List<String> attr=Arrays.asList(linie.split(","));
                    E e=extractEntity(attr);
                    super.save(e);
                }catch(Exception e){
                    System.out.println("Invalid data in file!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function in which we load our relations from file to memory
     */
    private void loadRelations() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileRelations))) {
            String linie;
            while((linie=br.readLine())!=null){
                List<String> attr=Arrays.asList(linie.split(","));
                super.add_relation(attr.get(0) , attr.get(1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that returns a user from a list of attributes
     * @param attributes - List of String
     * @return user - E entity
     */
    public abstract E extractEntity(List<String> attributes);

    /**
     * Function that returns a String of attributes for an entity
     * @param entity - E entity
     * @return attributes - String
     */
    protected abstract String createEntityAsString(E entity);

    /**
     * Function that returns a String of attrbiutes for a relation
     * @param mail1 - String
     * @param mail2 - String
     * @return attributes - String
     */
    protected abstract String createRelationAsString(String mail1 , String mail2);

    @Override
    public E save(E entity){
        E e=super.save(entity);
        if (e==null)
        {
            writeToFile(entity);
        }
        return e;
    }

    @Override
    public E delete(E entity){
        E deleted_entity = super.delete(entity);
        Iterable<E> entities = super.findAll();
        try{
            new FileWriter(fileName , false).close();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(E curr_entity : entities){
            writeToFile(curr_entity);
        }

        List<Prietenie> relations = super.findRelations();
        try{
            new FileWriter(fileRelations , false).close();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(Prietenie curr_prietenie : relations){
            writeToFileRelation(curr_prietenie.user_mail1 , curr_prietenie.user_mail2);
        }

        return deleted_entity;
    }

    @Override
    public E add_relation(String mail1 , String mail2){
        if(super.add_relation(mail1 , mail2) == null){
            try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileRelations,true))) {
                bW.write(createRelationAsString(mail1 , mail2));
                bW.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public E delete_relation(String mail1 , String mail2){
        E deleted_relation = super.delete_relation(mail1 , mail2);
        List<Prietenie> relations = super.findRelations();
        try{
            new FileWriter(fileRelations , false).close();
        }catch(IOException e){
            e.printStackTrace();
        }
        for(Prietenie curr_prietenie : relations){
            writeToFileRelation(curr_prietenie.user_mail1 , curr_prietenie.user_mail2);
        }
        return deleted_relation;
    }

    /**
     * Function in which we write a new entity to the file where we store our users
     * @param entity - E object
     */
    protected void writeToFile(E entity){
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName,true))) {
            bW.write(createEntityAsString(entity));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Function in which we write a new relation to the file where we store our relations
     * @param mail1 - String
     * @param mail2 - String
     */
    protected void writeToFileRelation(String mail1 , String mail2){
        try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileRelations,true))) {
            bW.write(createRelationAsString(mail1 , mail2));
            bW.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

