package socialnetwork.ui;

import socialnetwork.domain.Prietenie;
import socialnetwork.domain.Utilizator;
import socialnetwork.domain.validators.RepoException;
import socialnetwork.domain.validators.UIException;
import socialnetwork.domain.validators.ValidationException;
import socialnetwork.repository.Repository0;
import socialnetwork.service.UtilizatorService0;

import java.util.List;
import java.util.Scanner;

/**
 * UI class for our app
 */
public class UI {

    //ui takes a service as a class field
    private final UtilizatorService0 service;

    /**
     * Constructor with parameters for UI class
     * @param service - 'UtilizatorService0' objects
     */
    public UI(UtilizatorService0 service) {
        this.service = service;
    }

    /**
     * Prints out on the screen the menu app
     */
    public void printMenu() {
        System.out.println("\nMENU:");
        System.out.println("1.ADD user!");
        System.out.println("2.DELETE user!");
        System.out.println("3.ADD friendship!");
        System.out.println("4.DELETE friendship!");
        System.out.println("5.Number of distinct communities!");
        System.out.println("6.The most lengthy community!");
        System.out.println("7.PrintUsers!");
        System.out.println("8.PrintRelations!");
        System.out.println("9.EXIT!\n");
    }

    /**
     * Runs the user interface
     */
    public void run(){
        try {
            boolean stop = false;
            String mail, firstName, lastName;
            String mail_user1, mail_user2;
            boolean found_user1 = false;
            boolean found_user2 = false;
            while (!stop) {
                printMenu();
                Scanner sc = new Scanner(System.in);
                System.out.println("Alegeti o optiune : ");
                String user_input = sc.nextLine();
                switch (user_input) {
                    case ("1"):
                        System.out.println("Type user mail : ");
                        mail = sc.nextLine();
                        System.out.println("Type user first name : ");
                        firstName = sc.nextLine();
                        System.out.println("Type user last name : ");
                        lastName = sc.nextLine();
                        String string_date = mail + " " + firstName + " " + lastName;
                        Utilizator user = new Utilizator(firstName, lastName);
                        user.setMail(mail);
                        try {
                            service.addUtilizator(user);
                            System.out.println("Utilizator adaugat cu succes!");
                        } catch (ValidationException | RepoException e) {
                            System.out.println("\n" + e);
                        }
                        break;
                    case ("2"):
                        System.out.println("Deleted user email : ");
                        mail = sc.nextLine();
                        boolean found = false;
                        try {
                            Iterable<Utilizator> all_delete = service.getAll();
                            for (Utilizator u_delete : all_delete) {
                                if (mail.equals(u_delete.getMail())) {
                                    found = true;
                                    Utilizator delete_user = new Utilizator(u_delete.getFirstName(), u_delete.getLastName());
                                    delete_user.setMail(mail);
                                    service.deleteUtilizator(delete_user);
                                    System.out.println("User was succesfully deleted!");
                                    break;
                                }
                            }
                            if (!found) {
                                throw new UIException("User with the given email does not exist!");
                            }
                        } catch (RepoException e) {
                            System.out.println(e);
                        }
                        break;
                    case ("3"):
                        System.out.println("Mail for the first user : ");
                        mail_user1 = sc.nextLine();
                        System.out.println("Mail for the second user : ");
                        mail_user2 = sc.nextLine();
                        Iterable<Utilizator> all_friendship_add = service.getAll();
                        String fs_add_errs = "";
                        try {
                            for (Utilizator fs_add : all_friendship_add) {
                                if (fs_add.getMail().equals(mail_user1)) {
                                    found_user1 = true;
                                }
                                if (fs_add.getMail().equals(mail_user2)) {
                                    found_user2 = true;
                                }
                            }
                            if (!found_user1 || !found_user2) {
                                if (!found_user1) {
                                    fs_add_errs += "First user with the given mail does not exist!\n";
                                }
                                if (!found_user2) {
                                    fs_add_errs += "Second user with the given mail does not exist!";
                                }
                                throw new UIException(fs_add_errs);
                            } else {
                                service.addRelation(mail_user1, mail_user2);
                                System.out.println("Relatie adaugata cu succes!");
                                break;
                            }
                        } catch (UIException | RepoException e) {
                            System.out.println(e);
                            break;
                        }
                    case ("4"):
                        System.out.println("Mail for the first user : ");
                        mail_user1 = sc.nextLine();
                        System.out.println("Mail for the second user : ");
                        mail_user2 = sc.nextLine();
                        found_user1 = false;
                        found_user2 = false;
                        Iterable<Utilizator> all_friendship_del = service.getAll();
                        String fs_delete_errs = "";
                        try {
                            for (Utilizator fs_add : all_friendship_del) {
                                if (fs_add.getMail().equals(mail_user1)) {
                                    found_user1 = true;
                                }
                                if (fs_add.getMail().equals(mail_user2)) {
                                    found_user2 = true;
                                }
                            }
                            if (!found_user1 || !found_user2) {
                                if (!found_user1) {
                                    fs_delete_errs += "First user with the given mail does not exist!\n";
                                }
                                if (!found_user2) {
                                    fs_delete_errs += "Second user with the given mail does not exist!";
                                }
                                throw new UIException(fs_delete_errs);
                            } else {
                                service.deleteRelation(mail_user1, mail_user2);
                                System.out.println("Relatie stearsa cu succes!");
                                break;
                            }
                        } catch (UIException | RepoException e) {
                            System.out.println(e);
                            break;
                        }
                    case ("5"):
                        System.out.println("Number of conex components : " + service.conexComponents());
                        break;
                    case ("6"):
                        System.out.println(user_input);
                        break;
                    case ("7"):
                        Iterable<Utilizator> all_print = service.getAll();
                        for (Utilizator u : all_print) {
                            System.out.println(u.getFirstName() + " " + u.getLastName() + "(" + u.getMail() + ")");
                        }
                        break;
                    case ("8"):
                        List<Prietenie> all_relations = service.getRelations();
                        for (Prietenie p : all_relations) {
                            System.out.println(p.user_mail1 + " " + p.user_mail2);
                        }
                        break;
                    case ("9"):
                        stop = true;
                        System.out.println("Thanks for using our app!");
                        break;
                    default:
                        System.out.println("Warning , user input invalid!");
                }
            }
        }catch(RepoException e){
            System.out.println(e);
        }
    }
}
