package socialnetwork.repository.file;

import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;

import java.util.List;

public class UtilizatorFile0 extends AbstractFileRepository0<String, Utilizator> {

    public UtilizatorFile0(String fileName, String fileRelations , Validator<Utilizator> validator) {
        super(fileName,  fileRelations , validator);
    }

    @Override
    public Utilizator extractEntity(List<String> attributes) {
        Utilizator user = new Utilizator(attributes.get(1),attributes.get(2));
        user.setMail(attributes.get(0));
        return user;
    }

    @Override
    protected String createEntityAsString(Utilizator entity) {
        return entity.getMail()+","+entity.getFirstName()+","+entity.getLastName();
    }

    @Override
    protected String createRelationAsString(String mail1 , String mail2){
        return mail1 + "," + mail2;
    }
}
