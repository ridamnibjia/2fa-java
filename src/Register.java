
import com.google.zxing.WriterException;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Register extends javax.swing.JFrame {
int user_id;
int generatedOtp;
String secretKey;
String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Register() {
        initComponents();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGeneratedOtp() {
        return generatedOtp;
    }

    public void setGeneratedOtp(int generatedOtp) {
        this.generatedOtp = generatedOtp;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    

    // Java code to explain how to generate OTP
public int generateOTP() 
    {  //int randomPin declared to store the otp
        //since we using Math.random() hence we have to type cast it int
        //because Math.random() returns decimal value
        int randomPin   =(int) (Math.random()*9000)+1000;
        return randomPin; //returning value of otp
    }
public void checkOtp() throws IOException, WriterException{
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");  
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
      PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as count FROM email_otp "
              + "WHERE user_id = ? AND otp = ? AND NOW() <= expiry");
      // checking otp now
      ps.setInt(1, user_id);
      ps.setInt(2, Integer.parseInt(t4.getText()));
      ResultSet rs = ps.executeQuery();
       int count = 0;
        if (rs.next()) {
            count = rs.getInt("count");
        }
        if (count > 0) {
            //if otp is correct then we go inside generate the qr code for the google auth
                b2.setEnabled(false);
                b3.setEnabled(true);
                
       secretKey = Utils.generateSecretKey();
        try {
            Auth.main(secretKey,t3.getText());
            
           b3.doClick();
		
        } catch (IOException | WriterException ex) {
            JOptionPane.showMessageDialog(this,ex);
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
                 //regenerating otp on email
                  generatedOtp = generateOTP();
SendMail sm = new SendMail(t3.getText(),generatedOtp);
sm.sendMail();
user_id = getUserId(t3.getText());
insertOtp(user_id,generatedOtp);
JOptionPane.showMessageDialog(this, "OTP Sent again.");
b3.setEnabled(false);
b2.setEnabled(true);

             }
                
            }
      
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
        b2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        b1 = new javax.swing.JButton();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        t4 = new javax.swing.JTextField();
        l1 = new javax.swing.JLabel();
        b3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        t5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Shree\\Documents\\NetBeansProjects\\MFApp\\img\\logo.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("Register");

        b2.setText("Submit OTP");
        b2.setEnabled(false);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Enter Name: ");

        jLabel4.setText("Enter Password: ");

        b1.setText("Request OTP");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        t2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Enter Email: ");

        jLabel6.setText("Enter OTP: ");

        b3.setText("Show QR");
        b3.setEnabled(false);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        jLabel7.setText("Enter TOTP: ");

        b4.setText("Register");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setText("Login");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(516, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(b1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(b2)
                                .addGap(104, 104, 104)
                                .addComponent(b3)))
                        .addGap(134, 134, 134))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(b4)
                        .addGap(95, 95, 95)
                        .addComponent(b5)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel2)))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(43, 43, 43)
                .addComponent(b1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b2)
                            .addComponent(b3))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b4)
                            .addComponent(b5))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed

    try {
        checkOtp();
    } catch (IOException | WriterException ex) {
        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_b2ActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
String name = t1.getText();
String email = t3.getText();
String pass = new String(t2.getPassword());
insertData(name,email,pass);

        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void t2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
new QRCode().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_b3ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
new Login().setVisible(true);
this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_b5ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
updateUser();
        // TODO add your handling code here:
    }//GEN-LAST:event_b4ActionPerformed
public void insertData(String name,String email, String pass)
{
    //inserting user
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","")) {
            PreparedStatement ps = con.prepareStatement("insert into user(name,email,password) values(?,?,md5(?))");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, pass);
            ps.execute();
            con.close();
            //generating otp
            generatedOtp = generateOTP();
            //sending email
SendMail sm = new SendMail(email,generatedOtp);
sm.sendMail();
user_id = getUserId(email);
//inserting otp into the db
insertOtp(user_id,generatedOtp);

        }
    }
    catch(ClassNotFoundException | SQLException ex)
    {
        JOptionPane.showMessageDialog(this, ex);
    }
}
public int getUserId(String email)
{
    // fetching the userID
    int idd = 0;
     try{
        Class.forName("com.mysql.cj.jdbc.Driver");  
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
     PreparedStatement ps = con.prepareStatement("select id from user where email = ?");
     ps.setString(1, email);
     ResultSet rs = ps.executeQuery();
     
     if(rs.next())
     {
       idd = rs.getInt("id");
     }
     }
     catch(ClassNotFoundException | SQLException ex)
     {
         JOptionPane.showMessageDialog(this, ex);
     }
    return idd;
}
public void updateUser()
{
    //updating the User row when it is authenticated and registered
   code = t5.getText();
		if (code.equals(Utils.getTOTPCode(secretKey))) {
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");  
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth","root","");
      PreparedStatement ps = con.prepareStatement("UPDATE user set email_verify = ?, secret = ? where id = ?");
      ps.setInt(1, 1);
      ps.setString(2, secretKey);
      ps.setInt(3, user_id);
      ps.execute();
      JOptionPane.showMessageDialog(this, "User Registered!");
      new Login().setVisible(true);
      this.dispose();
    }
    catch(ClassNotFoundException | SQLException ex)
    {
        JOptionPane.showMessageDialog(this, ex);
    }
		} else {
			System.out.println("Invalid 2FA Code");
		}
}
public void insertOtp(int id, int otp)
{
    //updating otp in db
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
        b1.setEnabled(false);
b2.setEnabled(true);
    }
    catch(ClassNotFoundException | SQLException ex)
    {
        JOptionPane.showMessageDialog(this, ex);
    }
}
    
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
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Register().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel l1;
    private javax.swing.JTextField t1;
    private javax.swing.JPasswordField t2;
    private javax.swing.JTextField t3;
    private javax.swing.JTextField t4;
    private javax.swing.JTextField t5;
    // End of variables declaration//GEN-END:variables
}
