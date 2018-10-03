
package controlador;

import dao.PermisosDAO;
import dao.RegistroUsuarioDAO;
import dao.RolesDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Permisos;
import modelo.Roles;
import modelo.Usuario;



@WebServlet(name = "RegistroUsuarioControlador", urlPatterns = {"/RegistroUsuarioControlador"})
public class RegistroUsuarioControlador extends HttpServlet {
    private PermisosDAO daoPermisos ;
    private RolesDAO daoRoles;
    private RegistroUsuarioDAO daoRegistroUsuario;
    private UsuarioDAO daoUsuario;
    
    public RegistroUsuarioControlador(){
        super();
        //-----------------------CREAMOS LOS OBJETOS DE FORMA GLOBAL-------------------------------
        daoPermisos = new PermisosDAO();
        daoRoles = new RolesDAO(); //Creamos el objeto de rolesDAO
        daoRegistroUsuario = new RegistroUsuarioDAO(); //creamos el objeto de "RegistroUsuarioDAO"
        daoUsuario = new UsuarioDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        Usuario usuario = new Usuario(); 
        
        
        
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setUsuario(request.getParameter("usuario"));
        usuario.setPass(request.getParameter("pass"));
        
        String rol = request.getParameter("rolAsignado");
        
        /*
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String usuario = request.getParameter("usuario");
        String contra = request.getParameter("pass");
        */
        
        Permisos permisos = new Permisos(); //Creamos el objeto "permisos" para recibir los valores del formulario
        
        //System.out.println("Valor de crear: "+request.getParameter("crear"));
        
        permisos.setCrear(Boolean.parseBoolean(request.getParameter("crear")));
        permisos.setListar(Boolean.parseBoolean(request.getParameter("listar")));
        permisos.setEliminar(Boolean.parseBoolean(request.getParameter("eliminar")));
        permisos.setEditar(Boolean.parseBoolean(request.getParameter("editar")));
     
        
        //System.out.println("El numero de rol recibido es: "+rol);
        
        Roles ROL = daoRoles.buscarIdRol(rol);//pasamos como parametro el "rol" recibido del formulario
                                         //para buscar su ID porque del formulario solo recibimos su nombre
       
        //Aqui obtengo el nombre del usuario recibido del formuario
        //solo para pasar como argumento al "buscarIdUsuario" para 
        //poder obtener el ID del usuario dependiendo de su nombre de usuario
        //En la validación hay que tratar de evitar de que haya dos nombres
        //de usuarios iguales
        
                                        
                                         
                                         
                                         
        //String idRolObtenido = ROL.toString();
                                        
        
        
        
        if(daoRegistroUsuario.registrarUsuario(ROL, usuario)){

            String nombreUsuario = (usuario.getUsuario());
            Usuario IDusuario = daoUsuario.buscarIdUsuario(nombreUsuario);
            
            if(daoPermisos.agregarPermiso(permisos, IDusuario)){
                response.sendRedirect("login.jsp"); //en caso de que entre por el si se le redirecciona para que inicie sesion
            }
            
        }else{
            response.sendRedirect("registro.jsp");//si no se cumple entonces se mantiene en la misma página
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
