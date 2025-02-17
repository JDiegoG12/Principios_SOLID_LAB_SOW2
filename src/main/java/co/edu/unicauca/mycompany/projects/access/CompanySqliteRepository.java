package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del repositorio de empresas utilizando SQLite como base de datos.
 * 
 * Esta clase proporciona métodos para conectar a la base de datos SQLite,
 * crear la tabla de empresas si no existe, insertar empresas, listar todas
 * las empresas y verificar la existencia de una empresa por su NIT.
 * 
 * @author Libardo, Julio
 * @author Ana Sofia Arango Yanza
 * @author Juan Diego Gomez Garces
 */
public class CompanySqliteRepository implements ICompanyRepository {

    private Connection conn;
    
    /**
     * Constructor que inicializa la conexión a la base de datos.
     */
    public CompanySqliteRepository() {
        connect();
    }
    
    /**
     * Establece la conexión con la base de datos SQLite y crea las tablas si no existen.
     */
    private void connect() {
        try {
            String url = "jdbc:sqlite:src/database/myDatabase.db";
            conn = DriverManager.getConnection(url);
            createTables(); // Crear tablas si no existen
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
    
    /**
     * Crea la tabla 'company' en la base de datos si no existe.
     */
    private void createTables() {
        String sql = "CREATE TABLE IF NOT EXISTS company ("
               + "nit VARCHAR(15) PRIMARY KEY,"
               + "name VARCHAR(80) NOT NULL,"
               + "phone VARCHAR(15),"
               + "pageWeb VARCHAR(150),"
               + "sector VARCHAR(15) NOT NULL CHECK (sector IN ('TECHNOLOGY', 'HEALTH', 'EDUCATION', 'SERVICES', 'OTHER')),"
               + "email VARCHAR(80) UNIQUE NOT NULL,"
               + "password VARCHAR(64) NOT NULL"
               + ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear tablas: " + e.getMessage());
        }
    } 

    /**
     * Guarda una nueva empresa en la base de datos.
     * 
     * @param newCompany Objeto Company a insertar en la base de datos.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    @Override
    public boolean save(Company newCompany) {
       String sql = "INSERT INTO company (nit, name, phone, pageWeb, sector, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newCompany.getNit());
            pstmt.setString(2, newCompany.getName());
            pstmt.setString(3, newCompany.getPhone());
            pstmt.setString(4, newCompany.getPageWeb());
            pstmt.setString(5, newCompany.getSector().name());  // Guardamos el enum como string
            pstmt.setString(6, newCompany.getEmail());
            pstmt.setString(7, newCompany.getPassword());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al guardar la empresa: " + e.getMessage());
            return false;
        } 
    }
    
    /**
     * Obtiene una lista con todas las empresas registradas en la base de datos.
     * 
     * @return Lista de objetos Company.
     */
    @Override
    public List<Company> listAll() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM company";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                companies.add(new Company(
                        rs.getString("nit"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("pageWeb"),
                        Sector.valueOf(rs.getString("sector")),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar empresas: " + e.getMessage());
        }
        return companies;
    }
    
    /**
     * Verifica si una empresa con el NIT dado existe en la base de datos.
     * 
     * @param nit Número de Identificación Tributaria (NIT) de la empresa.
     * @return true si la empresa existe, false en caso contrario.
     */
    @Override
    public boolean existsNit(String nit) {
        String sql = "SELECT 1 FROM company WHERE nit = ? LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nit);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // Retorna true si encuentra un resultado, false si no.
        } catch (SQLException e) {
            System.out.println("Error al buscar la empresa: " + e.getMessage());
            return false;
        }
    }

}
