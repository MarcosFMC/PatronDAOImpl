import dao.DB;
import dao.impl.DentistDaoH2;
import model.Dentist;
import service.DentistService;

public class Main {
    public static void main(String[] args) {

        DentistService dentistService = new DentistService(new DentistDaoH2());

        DB.createTables();

        Dentist dentist1= new Dentist(1234,"Ricardo","Gareca");
        Dentist dentist2 = new Dentist(134,"Rica","Garu");
        Dentist dentist3 = new Dentist(114,"Riqui","Garcia");

        dentistService.save(dentist1);
        dentistService.save(dentist2);
        dentistService.save(dentist3);


        System.out.println(dentistService.findById(2).getName());


        dentist1.setName("Ricky");
        dentistService.update(dentist1);

        dentistService.delete(2);

        dentistService.findAll();
    }
}
