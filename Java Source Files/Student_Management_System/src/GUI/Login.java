/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import sun.misc.BASE64Decoder;

/**
 *
 * @author pasindu
 */
public class Login extends javax.swing.JFrame {

    int i = 0;
    String decoded;
    String usertype;
    String occupation;
    String image;
    String name;
    static MainFrame mainFrame = new MainFrame();
    static String username;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Reports/logo.png"));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtpass = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/ds logo (2).png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 150, 210));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Student Information System");
        jLabel33.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 400, 30));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel7.setText("User Name");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 70, 20));
        jPanel3.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 160, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel8.setText("Password");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 70, 20));

        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 610, 110, -1));

        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setText("Log In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 110, -1));
        jPanel3.add(txtpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 160, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pics/Backgrond.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 650));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 650));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(1);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            
            ResultSet rs1 = DataBase.executequary1("login", "username", txtusername.getText());
            if(rs1.next()){
            ResultSet rs = DataBase.executequary1("login", "username", txtusername.getText());
            while(rs.next()){
            String dbpass = rs.getString("password");
            usertype=rs.getString("usertype");
            occupation= rs.getString("occupation");
            image = txtusername.getText();
            name = rs.getString("name");
            
            byte decodedRaw[] = null;
            decodedRaw = (new BASE64Decoder()).decodeBuffer(dbpass);
            decoded = new String(decodedRaw, "UTF8");
                System.out.println(decoded);   
            }
            if (txtpass.getText().equals(decoded)) {
                
                    username=txtusername.getText();
                    mainFrame.setVisible(true);
                    mainFrame.setprivillage(usertype, occupation , image, name);
                    dispose();
            } else {
                i++;
                if (i >= 3) {
                    Runtime r = Runtime.getRuntime();
                    r.exec("RUNDLL32 USER32.DLL,LockWorkStation");
                    System.exit(1);
                } else {
                    txtusername.setText("");
                    txtpass.setText("");
                    Object[] options = {"Ok"};
                    JOptionPane.showOptionDialog(new JFrame(), "Wrong Username or Password", "Student Information System", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                }}
            }else {
                i++;
                if (i >= 3) {
                    Runtime r = Runtime.getRuntime();
                    r.exec("RUNDLL32 USER32.DLL,LockWorkStation");
                    System.exit(1);
                } else {
                    txtusername.setText("");
                    txtpass.setText("");
                    Object[] options = {"Ok"};
                    JOptionPane.showOptionDialog(new JFrame(), "Wrong Username or Password", "Student Information System", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                
                    
                    new Login().setVisible(true);
               
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
