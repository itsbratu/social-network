package socialnetwork.repository.database;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.Validator;
import socialnetwork.repository.Repository0;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositoryDB implements Repository0<String , Utilizator> {
    private String url;
    private String username;
    private String password;
    private Validator<Utilizator> validator;

    public RepositoryDB(String url, String username, String password, Validator<Utilizator> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;
    }

    @Override
    public Iterable<Utilizator> findAll() {
        Set<Utilizator> users = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from users2");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String mail = resultSet.getString("mail");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                Utilizator utilizator = new Utilizator(firstName, lastName);
                utilizator.setMail(mail);
                users.add(utilizator);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<Prietenie> findRelations() {
        return null;
    }

    @Override
    public Utilizator save(Utilizator entity) {
        return null;
    }

    @Override
    public Utilizator delete(Utilizator entity) {
        return null;
    }

    @Override
    public Utilizator add_relation(String mail1, String mail2) {
        return null;
    }

    @Override
    public Utilizator delete_relation(String mail1, String mail2) {
        return null;
    }
}
