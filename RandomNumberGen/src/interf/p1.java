
package interf;
import java.awt.Color;
import java.awt.Desktop;
import static java.lang.Math.pow;
import java.net.URI;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.UIManager;

public class p1 extends javax.swing.JFrame {
    
    Random rand = new Random();
    boolean pas = false;
    int cateAsem = 0,lastMn = 0,lastMx = 100000;
    long n = 0;
    String continuare="";
    
    private boolean prim(long cp){
        long i;
        for(i = 2;i<=cp/2;i++)
            if(cp % i == 0)return false;
        return true;      
    }
    private void generatorLiniar(int min,int max,int nmr){
        if(max-min > 0){           
            int rez,i; 
            if(lastMx == max && lastMn == min)cateAsem += nmr;
            else {
                if(cateAsem != 0)rezultat.append("\n\n");
                cateAsem = nmr; 
                lastMx = max; 
                lastMn = min; 
            }
            if(zecimal.isSelected()){
                for(i = 1;i<=nmr;i++){
                    rez = rand.nextInt(max-min+1)+min;          // ------ generare numere liniar congruential
                    rezultat.append(Integer.toString(rez) + " ");
                }
            }else 
                 for(i = 1;i<=nmr;i++){
                    rez = rand.nextInt(max-min+1)+min;          // ------ generare numere liniar congruential(binar)
                    rezultat.append(Integer.toBinaryString(rez)+ " ");
                }
            
            mesaj.setText("Au fost generate ( "+cateAsem+" ) numere, apartinand intervalului ["+min+","+max+"]");
            mesaj.setForeground(Color.green);
       }
       else { 
            mesaj.setText("!!! Minimul este mai mare decat maximul sau valorile sunt egale.");
            mesaj.setForeground(Color.red);
            }
    }
    
    
    private void generatorBlumShub(int min,long x0, int nmr){// --- min = k(in interfata) | x0 | nmr = m
       long p,q,i;
       String rzt = "";
       
       if(min <= 1 || x0 <= 1 || nmr <= 0){
            mesaj.setText("!!! Unul dintre numerele k, x0 sau m este mai mic sau egal cu 1. Alege alte valori.");
            mesaj.setForeground(Color.red);
            return;
       }
       if(min < 3 || min > 15 || nmr < 1 || nmr > 31){
            mesaj.setText("!!! Numarul k trebuie sa fie cuprins intre [3, 15]. m trebuie sa fie intre [1, 31]. Alege alte valori.");
            mesaj.setForeground(Color.red);
            return;
       }
       if(!pas && n == 0){
            long x = (long)pow(2,min), y = (long)pow(2,min-1), cp; // x si y = marginile intre care trebuie sa se afle p si q
            
            p = y + ThreadLocalRandom.current().nextLong(y);  // --- generez 2 numere aleator( p si q ) 
            if(p%4 != 3 && p+3 < x) p += 3 - p%4;           // ------ cu valori pe EXACT min(sau k) biti
            else if(p%4 != 3 && p-3 > y) p -= 3 - p % 4;    // ------ prime si cu proprietatea ca p si q % 4 == 3    
            cp = p;
            while(cp <= x && !prim(cp)) cp+=4;              
            if(cp >= x){
                cp = p;
                while(cp >= y && !prim(cp)) cp-=4;
            }
            
            q = y + ThreadLocalRandom.current().nextLong(y);   
            if(q%4 != 3 && q+3 < x) q += 3 - q%4;
            else if(q%4 != 3 && q-3 > y) q -= 3 - q % 4;
            cp = q;
            while(cp <= x && !prim(cp)) cp+=4;
            if(cp >= x){
                cp = q;
                while(cp >= y && !prim(cp)) cp-=4;
            }
            n = p * q;               // -------------------- pentru a forma un intreg Blum, prin inmultirea lor
            continuare = " p="+p+", q="+q;
       }
       if(x0 > n){  
                pas = true;
                mesaj.setText("Am generat intregul Blum n="+n+" iar valoarea x0 este mai mare decat n. "
                        + "Alege alta valoare x0 < n.");
                mesaj.setForeground(Color.red);
                return;
        }
       
        x0 = (x0*x0) % n; // ------ renunt la primul reziduu patratic mod n
        for(i = 0;i<nmr;i++){           // ----------- de nmr ( m ) ori  
            x0 = (x0*x0) % n;                
            rzt += (char)(x0 % 2 +'0'); // ----------- generez bitii rezultatului 
        }
        cateAsem++;
        if(binar.isSelected()) rezultat.append(rzt + " ");       // --- afisez in functie de selectie(binar/zec)
        else rezultat.append(Integer.toString(Integer.parseInt(rzt,2))+ " ");
        
        mesaj.setText("Numar("+cateAsem+"): n="+n+","+continuare+" =>"+Integer.parseInt(rzt,2));
        mesaj.setForeground(Color.green);
        lastMn = 0; lastMx = 0; pas = false; n = 0;
    }
    public p1() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        N = new javax.swing.JPanel();
        ms1 = new javax.swing.JLabel();
        mn = new javax.swing.JTextField();
        ms2 = new javax.swing.JLabel();
        mx = new javax.swing.JTextField();
        ms3 = new javax.swing.JLabel();
        num = new javax.swing.JTextField();
        glc = new javax.swing.JRadioButton();
        gbs = new javax.swing.JRadioButton();
        Center = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        rezultat = new javax.swing.JTextArea();
        S = new javax.swing.JPanel();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(145, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        site = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        Generare = new javax.swing.JButton();
        mesaj = new javax.swing.JTextPane();
        E = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 40), new java.awt.Dimension(32767, 40));
        curatare = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        fisier = new javax.swing.JButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        zecimal = new javax.swing.JRadioButton();
        binar = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Generator numere");
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        N.setPreferredSize(new java.awt.Dimension(200, 80));

        ms1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ms1.setText("Minim:");
        N.add(ms1);

        mn.setMinimumSize(new java.awt.Dimension(60, 20));
        mn.setPreferredSize(new java.awt.Dimension(70, 20));
        N.add(mn);

        ms2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ms2.setText("Maxim:");
        N.add(ms2);

        mx.setMinimumSize(new java.awt.Dimension(40, 20));
        mx.setPreferredSize(new java.awt.Dimension(70, 20));
        N.add(mx);

        ms3.setText("Generari:");
        N.add(ms3);

        num.setPreferredSize(new java.awt.Dimension(70, 20));
        N.add(num);

        glc.setSelected(true);
        glc.setText("Generator liniar congruential");
        glc.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        glc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glcActionPerformed(evt);
            }
        });
        N.add(glc);

        gbs.setText("Generator Blum - Shub");
        gbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gbsActionPerformed(evt);
            }
        });
        N.add(gbs);

        getContentPane().add(N, java.awt.BorderLayout.NORTH);

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(300, 200));

        rezultat.setEditable(false);
        rezultat.setColumns(20);
        rezultat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rezultat.setLineWrap(true);
        rezultat.setRows(20);
        rezultat.setWrapStyleWord(true);
        jScrollPane1.setViewportView(rezultat);

        Center.add(jScrollPane1);
        jScrollPane1.getAccessibleContext().setAccessibleName("");
        jScrollPane1.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(Center, java.awt.BorderLayout.CENTER);

        S.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        S.setLayout(new javax.swing.BoxLayout(S, javax.swing.BoxLayout.Y_AXIS));
        S.add(filler4);

        site.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        site.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                siteMouseClicked(evt);
            }
        });
        S.add(site);
        S.add(filler5);

        Generare.setText("Generare numere");
        Generare.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Generare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerareActionPerformed(evt);
            }
        });
        S.add(Generare);
        Generare.getAccessibleContext().setAccessibleDescription("");

        mesaj.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        S.add(mesaj);

        getContentPane().add(S, java.awt.BorderLayout.SOUTH);

        E.setPreferredSize(new java.awt.Dimension(130, 76));
        E.setLayout(new javax.swing.BoxLayout(E, javax.swing.BoxLayout.Y_AXIS));
        E.add(filler2);

        curatare.setText("Curatare rezultat");
        curatare.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        curatare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                curatareActionPerformed(evt);
            }
        });
        E.add(curatare);
        E.add(filler1);

        fisier.setText("Salvare in fisier");
        fisier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fisier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fisierActionPerformed(evt);
            }
        });
        E.add(fisier);
        E.add(filler3);

        zecimal.setSelected(true);
        zecimal.setText("Zecimal");
        zecimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zecimalActionPerformed(evt);
            }
        });
        E.add(zecimal);

        binar.setText("Binar");
        binar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        binar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binarActionPerformed(evt);
            }
        });
        E.add(binar);

        getContentPane().add(E, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GenerareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerareActionPerformed
        int min , max = 0, nmr;
        long x0 = 0;
        String strMx = mx.getText(),strMn = mn.getText(),strNum = num.getText();

        if(strMn.equals("")) min = 0;
        else if(strMn.matches("-?[0-9]+") && strMn.length()<10) min = Integer.parseInt(strMn);
        else {mesaj.setText("!!! Minimul(sau k pt GBS) introdus este invalid!"); mesaj.setForeground(Color.red); return;}
        
        if(strMx.equals("")) max = 100000;
        else if(strMx.matches("-?[0-9]+") && strMx.length()<16 && gbs.isSelected()) x0 = Long.parseLong(strMx);
        else if(strMx.matches("-?[0-9]+") && strMx.length()<10) max = Integer.parseInt(strMx);
        else {mesaj.setText("!!! Maximul(sau x0 pt GBS) introdus este invalid!"); mesaj.setForeground(Color.red); return;}

        if(strNum.equals("")) nmr = 10;
        else if(strNum.matches("[0-9]+") && strNum.length()<10) nmr = Integer.parseInt(strNum);
        else {mesaj.setText("!!! Nr. de generari(sau m pt GBS) introdus este invalid!"); mesaj.setForeground(Color.red); return;}
        
        if(glc.isSelected())
            generatorLiniar(min,max,nmr);
         else
            generatorBlumShub(min,x0,nmr);
    }//GEN-LAST:event_GenerareActionPerformed

    private void curatareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_curatareActionPerformed
        rezultat.setText("");
        mesaj.setText("Rezultatul a fost curatat.");
        mesaj.setForeground(Color.black);
        cateAsem = 0;
    }//GEN-LAST:event_curatareActionPerformed

    private void fisierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fisierActionPerformed
        if(rezultat.getText().equals("")){
            mesaj.setText("Rezultatul este gol. Nu se poate scrie in fisier.");
            mesaj.setForeground(Color.red);
            return;
        }
        p2 aleg = new p2(rezultat.getText());
        aleg.setVisible(true);
    }//GEN-LAST:event_fisierActionPerformed

    private void glcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glcActionPerformed
        if(!glc.isSelected()){glc.setSelected(true);return;}
        gbs.setSelected(false);
        ms1.setText("Minim:");
        ms2.setText("Maxim:");
        ms3.setText("Generari:");
        mn.setText("");
        mx.setText("");
        num.setText("");
        site.setText("");
        lastMx = 100000; lastMn = 0;
        cateAsem = 0;
    }//GEN-LAST:event_glcActionPerformed

    private void gbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gbsActionPerformed
        if(!gbs.isSelected()){gbs.setSelected(true);return;}
        glc.setSelected(false);
        ms1.setText("k (nr. biti al intregului Blum):");
        ms2.setText("x0 (orice nr; un seed):");
        ms3.setText("m (nr. maxim de biti al rezultatului):");
        mn.setText("");
        mx.setText("");
        num.setText("");
        site.setText("<html><a href=\"\">Informatii Blum-Shub</a></html>");
        lastMx = 0; lastMn = 0; cateAsem = 0;
    }//GEN-LAST:event_gbsActionPerformed

    private void siteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_siteMouseClicked
        try{ Desktop.getDesktop().browse(new URI("http://www.galaxyng.com/adrian_atanasiu/cursuri/cript/cr2_3.pdf#page=5"));}
        catch(Exception a){ }
    }//GEN-LAST:event_siteMouseClicked

    private void zecimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zecimalActionPerformed
        if(!zecimal.isSelected())zecimal.setSelected(true);
        binar.setSelected(false);
    }//GEN-LAST:event_zecimalActionPerformed

    private void binarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binarActionPerformed
        if(!binar.isSelected())binar.setSelected(true);
        zecimal.setSelected(false);
    }//GEN-LAST:event_binarActionPerformed
 
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
           /* for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }*/
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(p2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(p2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(p2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(p2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new p1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Center;
    private javax.swing.JPanel E;
    private javax.swing.JButton Generare;
    private javax.swing.JPanel N;
    private javax.swing.JPanel S;
    private javax.swing.JRadioButton binar;
    private javax.swing.JButton curatare;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.JButton fisier;
    private javax.swing.JRadioButton gbs;
    private javax.swing.JRadioButton glc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane mesaj;
    private javax.swing.JTextField mn;
    private javax.swing.JLabel ms1;
    private javax.swing.JLabel ms2;
    private javax.swing.JLabel ms3;
    private javax.swing.JTextField mx;
    private javax.swing.JTextField num;
    private javax.swing.JTextArea rezultat;
    private javax.swing.JLabel site;
    private javax.swing.JRadioButton zecimal;
    // End of variables declaration//GEN-END:variables

}
