
package dao;

import conexion.ConexionBD;
import static conexion.ConexionBD.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Permisos;
import modelo.Usuario;



public class PermisosDAO {
    private Connection connection;
    
    public PermisosDAO(){
        connection = ConexionBD.getConnection();
    }
    
    //"agregarPermiso" va retornar un true o false por eso es "public boolean"
    public boolean agregarPermiso(Permisos permiso, Usuario IDusuario){
        PreparedStatement ps = null;
        
        try{
            String consulta = "insert into permisos (id_usuario, crear, listar, "
                    + "eliminar, editar) values (?, ?, ?, ?, ?)";
            ps = getConnection().prepareStatement(consulta);
            //PreparedStatement ps = connection.prepareStatement();
            
            ps.setInt(1, IDusuario.getId_usuario());
            
            System.out.println("ID USUARIO NUM: "+IDusuario.getId_usuario());
            
            ps.setBoolean(2, permiso.isCrear());
            ps.setBoolean(3, permiso.isListar());
            ps.setBoolean(4, permiso.isEliminar());
            ps.setBoolean(5, permiso.isEditar());
            
            System.out.println("Valor Crear: "+permiso.isCrear());
            System.out.println("Valor Listar: "+permiso.isListar());
            System.out.println("Valor Eliminar: "+permiso.isEliminar());
            System.out.println("Valor Editar: "+permiso.isEditar());
            //ps.setString(6, permiso.getNombreModulo());

            if(ps.executeUpdate()==1);
            return true;
             
            
        } catch(Exception e){
            System.out.println("Error en agregarPermiso de PermisosDAO: "+ e.getMessage());
        }finally{
            try{
                if(getConnection() != null) getConnection().close();
                if(ps != null) ps.close();
            }catch(Exception e){
                System.out.println("Error al cerrar una conexión en AgregarPermiso"
                                    + "de PermisoDAO :"+ e.getMessage());                        
            }
        }
                                     
        return false; // si el "if(ps.executeUpdate() == 1)" es falso entonces retorna FALSE                            
                                   
    }

    
}
    

