package co.edu.unicauca.mycompany.projects.presentation;

import co.edu.unicauca.mycompany.projects.access.CompanySqliteRepository;
import co.edu.unicauca.mycompany.projects.access.ICompanyRepository;
import co.edu.unicauca.mycompany.projects.domain.entities.Company;
import co.edu.unicauca.mycompany.projects.domain.services.CompanyService;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author libardo
 */
public class GUIMenu extends javax.swing.JFrame {

    private CompanyService companyService;

    /**
     * Creates new form GUIMenu
     */
    public GUIMenu(CompanyService service) {
        this.companyService = service;
        initComponents();
        alignButtons();
        fillCompanies();
    }

    private void alignButtons() {
        lblOptions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20)); // Ancho máximo y altura fija
        btnHome.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        btnNewCompany.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        btnWhoWeAre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        btnClose.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblSpace1 = new javax.swing.JPanel();
        lblOptions = new javax.swing.JLabel();
        btnHome = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnNewCompany = new javax.swing.JButton();
        lblSpace2 = new javax.swing.JLabel();
        btnWhoWeAre = new javax.swing.JButton();
        lblSpace3 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        pnlCenter = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCompanies = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Unicauca");

        pnlNorth.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("Gestión de Proyectos Académicos del Programa de Ingeniería de Sistemas de UNICAUCA");
        pnlNorth.add(jLabel1);

        getContentPane().add(pnlNorth, java.awt.BorderLayout.PAGE_START);

        lblSpace1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSpace1.setLayout(new javax.swing.BoxLayout(lblSpace1, javax.swing.BoxLayout.Y_AXIS));

        lblOptions.setForeground(new java.awt.Color(102, 102, 255));
        lblOptions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOptions.setText("Opciones");
        lblSpace1.add(lblOptions);

        btnHome.setText("Inicio");
        btnHome.setPreferredSize(new java.awt.Dimension(134, 23));
        lblSpace1.add(btnHome);

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        jLabel3.setText("    ");
        lblSpace1.add(jLabel3);

        btnNewCompany.setText("Registrar Empresa");
        btnNewCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCompanyActionPerformed(evt);
            }
        });
        lblSpace1.add(btnNewCompany);

        lblSpace2.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        lblSpace2.setText("    ");
        lblSpace1.add(lblSpace2);

        btnWhoWeAre.setText("Quienes Somos");
        btnWhoWeAre.setPreferredSize(new java.awt.Dimension(134, 23));
        lblSpace1.add(btnWhoWeAre);

        lblSpace3.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        lblSpace3.setText("    ");
        lblSpace1.add(lblSpace3);

        btnClose.setText("Salir");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        lblSpace1.add(btnClose);

        getContentPane().add(lblSpace1, java.awt.BorderLayout.LINE_START);

        pnlCenter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlCenter.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Empresas registradas en el periodo 2025.1");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlCenter.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        tblCompanies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No", "Nombre", "Nit", "Sector industrial"
            }
        ));
        jScrollPane1.setViewportView(tblCompanies);

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCompanyActionPerformed
        GUINewCompany instance = new GUINewCompany(null, companyService, this);
        instance.setVisible(true);

    }//GEN-LAST:event_btnNewCompanyActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed
    public void fillCompanies() {
        
        // ------------------------------------- CompanyArraysRepository -----------------------------------------------
        
        /*
        DefaultTableModel model = new DefaultTableModel(new String[]{"NIT", "Nombre", "Sector"}, 0);

        model.setRowCount(0); // Limpiar la tabla antes de llenarla
        for (Company company : companyService.getAllCompanies()) {
            model.addRow(new Object[]{company.getNit(), company.getName(), company.getSector()});
        }
        tblCompanies.setModel(model);

        */
        
        // ------------------------------------- ----------------------- -----------------------------------------------
         
        DefaultTableModel model = new DefaultTableModel(new String[]{"NIT", "Nombre", "Sector"}, 0);
        model.setRowCount(0); // Limpiar la tabla antes de llenarla

        // Obtener la lista de compañías desde la base de datos
        ICompanyRepository companyRepository = new CompanySqliteRepository();
        List<Company> companies = companyRepository.listAll();

        // Llenar el modelo con los datos de las compañías
        for (Company company : companies) {
            model.addRow(new Object[]{company.getNit(), company.getName(), company.getSector().toString()});
        }

        // Asignar el modelo actualizado a la tabla
        tblCompanies.setModel(model);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnNewCompany;
    private javax.swing.JButton btnWhoWeAre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblOptions;
    private javax.swing.JPanel lblSpace1;
    private javax.swing.JLabel lblSpace2;
    private javax.swing.JLabel lblSpace3;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JTable tblCompanies;
    // End of variables declaration//GEN-END:variables
}
