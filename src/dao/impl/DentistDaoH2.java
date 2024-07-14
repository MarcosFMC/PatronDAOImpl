package dao.impl;
import dao.DB;
import dao.IDao;
import model.Dentist;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class DentistDaoH2 implements IDao<Dentist> {

    private static final Logger logger = Logger.getLogger(DentistDaoH2.class);
    private static final String SQL_INSERT = "INSERT INTO DENTIST(REGISTRATION, NAME, LASTNAME)"
            + "VALUES(?, ?, ?)";
    private static final String SQL_SELECT = "SELECT * FROM DENTIST WHERE ID = ? ";
    private static final String SQL_DELETE = "DELETE FROM DENTIST WHERE ID = ? ";

    private static final String SQL_SELECTALL = "SELECT * FROM DENTIST";
    private static final String SQL_UPDATE = "UPDATE DENTIST SET "
            + "REGISTRATION = ?,"
            + "NAME = ?,"
            + "LASTNAME = ? "
            + "WHERE ID = ?";
    @Override
    public Dentist save(Dentist dentist) {
        logger.info("Iniciando el guardado de un odontologo");
        Connection connection = null;
        try {
            connection = DB.getConnection();

            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            psInsert.setInt(1,dentist.getRegistration());
            psInsert.setString(2, dentist.getName());
            psInsert.setString(3, dentist.getLastName());
            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();

            while (rs.next()){
                dentist.setId(rs.getInt(1));
            }
            logger.info("Se guardo el odontologo con exito!");

        }
        catch (Exception e)
        {
            logger.error("Error:    " + e.getMessage() );
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return dentist;
    }
    @Override
    public Dentist findById(Integer id) {
        logger.info("Iniciando la busqueda de un odontologo por id");
        Connection connection = null;
        Dentist dentist = null;
        try {

            connection = DB.getConnection();

            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT);

            psSelect.setInt(1, id);

            ResultSet rs = psSelect.executeQuery();

            while(rs.next()){
                dentist = new Dentist(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                        );
            }
            logger.info("Consultamos el odontologo con el id: " + dentist.getId());
        }catch (Exception e){
                logger.error("Error: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return dentist;
    }
    @Override
    public void update(Dentist dentist) {
        logger.info("Iniciando la actualizacion de un odontologo");
        Connection connection = null;
        try {

            connection = DB.getConnection();

            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, dentist.getRegistration());
            psUpdate.setString(2, dentist.getName());
            psUpdate.setString(3, dentist.getLastName());
            psUpdate.setInt(4,dentist.getId());

            psUpdate.execute();
            logger.info("Se actualizo con exito el odontologo");

        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }
    @Override
    public void delete(Integer id) {
        logger.info("Iniciando la eliminacion de un odontologo");
        Connection connection = null;
        try {

            connection = DB.getConnection();

            PreparedStatement psUpdate = connection.prepareStatement(SQL_DELETE);
            psUpdate.setInt(1, id);
            psUpdate.execute();

            logger.info("Se elimino el odontologo con el id: "  + id);

        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Dentist> findAll() {

        logger.info("Iniciando la busqueda de todos los odontologos");

        Connection connection = null;
        List<Dentist> dentists = new ArrayList<>();
        Dentist dentist = null;

        try {

            connection = DB.getConnection();

            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECTALL);

            ResultSet rs = psSelectAll.executeQuery();

            while(rs.next()){
                dentist = new Dentist(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4)
                        );
                dentists.add(dentist);
                logger.info("Se obtuvo el odontologo: " + rs.getString(3));
            }
            logger.info("Se obtuvieron con exito todos los odontologos");
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception e){
                logger.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return dentists;
    }
}
