package socialnetwork;

import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.UtilizatorValidator;
import socialnetwork.repository.Repository0;
import socialnetwork.repository.database.RepositoryDB;
import socialnetwork.repository.file.UtilizatorFile0;
import socialnetwork.service.UtilizatorService0;
import socialnetwork.ui.UI;

public class Main {
    public static void main(String[] args) {
//        String fileName="data/users.csv";
//        String fileRelations="data/relations.csv";
//        Repository0<String,Utilizator> userFileRepository = new UtilizatorFile0(fileName, fileRelations , new UtilizatorValidator());
//        UtilizatorService0 service = new UtilizatorService0(userFileRepository);
//        UI ui = new UI(service);
//        ui.run();
        Repository0<String , Utilizator> repoDB = new RepositoryDB("jdbc:postgresql://localhost:40000/academic" , "postgres" , "postgres" , new UtilizatorValidator());
        UtilizatorService0 service = new UtilizatorService0(repoDB);
        UI ui = new UI(service);
        ui.run();
    }
}


