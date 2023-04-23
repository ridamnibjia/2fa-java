import com.google.zxing.WriterException;
import java.io.IOException;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
public class AuthOtp extends javax.swing.JFrame {
    int user_id;
    String secret;
    String email, name;
    int otp;
    public AuthOtp() {
        initComponents();
    }
     public AuthOtp(int user_id) {
        initComponents();
        this.user_id = user_id;
        try{
      Class.forName("com.mysql.cj.jdbc.Driver");  
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
      PreparedStatement ps = con.prepareStatement("select email,secret,name from user where id = ?");
      ps.setInt(1, user_id);
      ResultSet rs = ps.executeQuery();
      if(rs.next())
      {
          secret = rs.getString("secret");
          email = rs.getString("email");
          name = rs.getString("name");
      }
    }
    catch(ClassNotFoundException | SQLException ex)
    {
        JOptionPane.showMessageDialog(this, ex);
    }
        otp = generateOTP();
SendMail sm = new SendMail(email,otp);
sm.sendMail();
insertOtp(user_id,otp);

    }
     public int generateOTP() 
    {  //int randomPin declared to store the otp
        //since we using Math.random() hence we have to type cast it int
        //because Math.random() returns decimal value
        int randomPin   =(int) (Math.random()*9000)+1000;
        return randomPin; //returning value of otp
    }
     public void insertOtp(int id, int otp)
{
    try{
         Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
        PreparedStatement ps = con.prepareStatement("INSERT INTO email_otp (user_id, otp, expiry) VALUES (?, ?, ?)"
                + " ON DUPLICATE KEY UPDATE otp = VALUES(otp), expiry = VALUES(expiry)");
        LocalDateTime now = LocalDateTime.now();
// Add 10 minutes to the current system time
LocalDateTime expirationTime = now.plusMinutes(10);
// Convert the expiration time to a java.util.Date object
        java.util.Date expirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
java.sql.Timestamp sqlDate = new java.sql.Timestamp(expirationDate.getTime());
        ps.setInt(1, id);
        ps.setInt(2,otp);
        ps.setTimestamp(3,sqlDate);
        ps.execute();
        con.close();
    }
    catch(ClassNotFoundException | SQLException ex)
    {
        JOptionPane.showMessageDialog(this, ex);
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Verify");

        jLabel1.setText("Enter TOTP: ");

        jLabel2.setText("Enter Email OTP: ");

        jButton1.setText("Login now");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Verify the Login Credentials");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jLabel2)
                    .addContainerGap(221, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton1))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(jLabel2)
                    .addContainerGap(121, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 String code = t2.getText();
 int o = Integer.parseInt(t1.getText());
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");  
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
      PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as count FROM email_otp "
              + "WHERE user_id = ? AND otp = ? AND NOW() <= expiry");
      ps.setInt(1, user_id);
      ps.setInt(2, o);
      ResultSet rs = ps.executeQuery();
       int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        if (count > 0) {  
     if (code.equals(Utils.getTOTPCode(secret))) {
         JOptionPane.showMessageDialog(this, "Welcome "+name +"! You are now authenticated.");
         return;
     } 
            } 
        else {
            Object[] options1 = { "Try Again", "Resend OTP",
                "Cancel" };
             int n = JOptionPane.showOptionDialog(this,"Entered OTP is invalid, please check again", 
                     "Invalid OTP", JOptionPane.YES_NO_CANCEL_OPTION,
                     JOptionPane.ERROR_MESSAGE,null,
                     options1,options1[0]);
             if(n == 1)
             {
                  otp = generateOTP();
SendMail sm = new SendMail(email,otp);
sm.sendMail();
insertOtp(user_id,otp);
JOptionPane.showMessageDialog(this, "OTP Sent again.");

             }
                
            }
      
    }
    catch(ClassNotFoundException | SQLException ex)
    {
        JOptionPane.showMessageDialog(this, ex);
    }
 
 
 

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuthOtp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthOtp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthOtp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthOtp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthOtp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    // End of variables declaration//GEN-END:variables
}
