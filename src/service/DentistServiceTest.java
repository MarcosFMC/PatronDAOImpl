package service;

import dao.DB;
import dao.impl.DentistDaoH2;
import model.Dentist;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DentistServiceTest {

    DentistService dentistService = new DentistService(new DentistDaoH2());

    @Test
    void save() {
        DB.createTables();
        Dentist dentist = new Dentist(234,"Ricardo","Garcia");

        Dentist testDentist = dentistService.save(dentist);

        Assertions.assertNotNull(testDentist.getId());
    }

    @Test
    void findById() {
        Dentist dentist = dentistService.findById(1);
        Assertions.assertNotNull(dentist);
    }

    @Test
    void update() {
            Dentist dentist = new Dentist(1,321,"Numa","Yesua");

         dentistService.update(dentist);
         Assertions.assertEquals(true, dentist.getName().equals("Numa"));
    }

    @Test
    void delete() {
        Dentist dentistDeleted = dentistService.findById(2);
        Assertions.assertNull(dentistDeleted);
    }

    @Test
    void findAll() {
        List<Dentist> dentistList = dentistService.findAll();

        Assertions.assertTrue(dentistList.size() > 0);
    }
}