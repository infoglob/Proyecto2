/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static conexion.ConexionBD.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Roles;
import modelo.Usuario;

/**
 *
 * @author Diego
 */
public class RegistroUsuarioDAO {
    
    public boolean autenticacionUsuario(String usuario, String password){
        PreparedStatement ps = null; //como funciona un "PreparedStatement": https://bit.ly/2w64Th0
        ResultSet rs = null; //como funciona un "ResultSet": https://bit.ly/2P6nNNC
        
        //Haciendo la consulta a la Base de datos
        try{
            String consulta = "select* from usuarios where usuario = ? and pass = ?"; //se hace la comparación en la
            ps = getConnection().prepareStatement(consulta);                          // base de datos para ver si exite
            ps.setString(1, usuario);                                                  // o no el usuario con esa contraseña
            ps.setString(2, password);
            rs = ps.executeQuery(); //se ejecuta a consulta y se guarda el resultado en el "ResultSet"
            
            if(rs.absolute(1)){ //si existe el registro que se consulto en la base de datos y se guardo en el
                return true;    // "ResultSet" entonces se pregunta si el ResultSet en la fila "1" tiene el registro
            }                   // guardado, de ser así entonces se retorna True y si no se retorna False
                
        } catch(Exception e){
            System.out.println("Error en el primer try de AUTENTICACION de ConsultasDbDAO"+ e.getMessage());
            
        }finally{
            try{
                if(getConnection() != null)//Si la conexion no es nula entonces la cerramos
                    getConnection().close();
                if(ps != null) //Si el "ps" no es nula entonces la cerramos
                    ps.close();
                if(rs != null)//Si el "rs" no es nula entonces la cerramos
                    rs.close();
            }catch(Exception e){
                System.out.println("Error al cerrar una conexion en -ConsultasDbDAO- :"+ e.getMessage());
            }
        }
        return false; //En caso de que no haya registro entonces va retornar "false"
    }
    
    
    
    //---------------------METODO PARA REGISTRAR USUARIOS--------------------------
    public boolean registrarUsuario(Roles rol, Usuario usuario){
        PreparedStatement ps = null; //No se necesita un "ResultSet" porque no se va hacer
                                     //una consulta, mas bien se hara una inserción
        
        try{
            String consulta = "insert into usuarios (id_rol, nombre, apellido, usuario, pass) values (?, ?, ?, ?, ?)";
            ps = getConnection().prepareStatement(consulta);
            
            ps.setInt(1, rol.getId_rol());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido() );
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getPass());
            /*
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, usuario);
            ps.setString(5, password);*/
            
            if(ps.executeUpdate() == 1) 
                return true;
            
        } catch(SQLException e){
            System.out.println("Error en RegistrarUsuario de RegistroUsuarioDAO: "+ e.getMessage());
        }finally{
            try{
                if(getConnection() != null) getConnection().close();
                if(ps != null) ps.close();
            } catch(Exception e){
                System.out.println("Error al cerrar una conexión en RegistrarUsuario"
                                    + "de ConsultasDbDAO :"+ e.getMessage());
                                    
            }
        }
                                     
        return false; // si el "if(ps.executeUpdate() == 1)" es falso entonces retorna FALSE                            
                                   
    }
}
