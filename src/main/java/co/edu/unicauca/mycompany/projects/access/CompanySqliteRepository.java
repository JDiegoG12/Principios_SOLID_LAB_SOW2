package co.edu.unicauca.mycompany.projects.access;

import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.entities.Sector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Implementación del repositorio con Sqlite
 *
 * @author Libardo, Julio
 */
public class CompanySqliteRepository implements ICompanyRepository {

    private Connection conn;
    
    public CompanySqliteRepository() {
        connect();
    }
    
    // Conectar a la base de datos
    private void connect() {
        try {
            String url = "jdbc:sqlite:D:/Documentos/LabSoftware2/Practica2/Principios_SOLID_LAB_SOW2/myDatabase.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }

    // Insertar una nueva empresa en la base de datos
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
    
    // Obtener todas las empresas
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
