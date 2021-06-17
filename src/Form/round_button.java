/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import javax.swing.UIManager;


/**
 *
 * @author USER
 */
public class round_button {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }catch(Exception ee){
            System.out.println(ee);
        }
        
        new Form_CRUD().setVisible(true);
    }    
}
