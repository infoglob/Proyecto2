<%@page import="dao.RolesDAO"%>
<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
    RolesDAO dao = new RolesDAO();
    request.setAttribute("Roles", dao.listarRoles());
%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Registrar Usuario</title>

        <!-- BOOTSTRAP STYLES-->
        <link href="css/bootstrap.css" rel="stylesheet" />
        <!-- FONTAWESOME STYLES-->
        <link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet" />
           <!--CUSTOM BASIC STYLES-->
        <link href="css/basic.css" rel="stylesheet" />
        <!--CUSTOM MAIN STYLES-->
        <link href="css/custom.css" rel="stylesheet" />
        <!-- GOOGLE FONTS-->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
    </head>
    <body style="background-color: #E2E2E2;">
        
        
        
            
            <div role="form" class="panel-body">
                <h2><center>Registrar Usuario</center></h2>
                 <hr /><!-- Crea una linea de separación -->
                 <br /><!-- Salto de línea -->
                 <!-- ---------------------------------------------------------------- -->
                                     <!-- INICIO FORMULARIO -->
                 <!-- ---------------------------------------------------------------- -->
                <div class="container">
                    <form action="RegistroUsuarioControlador" method="POST">


                        <div class="row"><!--FILA 1-->
                            <div class="form-group col-md-4 col-md-offset-1">
                                <div class="input-group ">
                                    <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                    <input type="text" class="form-control" id="NOMBRE" placeholder="Nombre" name="nombre"
                                          onkeyup="validacion('NOMBRE');" />
                                </div>
                            </div>

                            <div class="form-group col-md-4 col-md-offset-1">
                                <div class="input-group ">
                                    <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                    <input type="text" class="form-control" id="APELLIDO" placeholder="Apellido" name="apellido"
                                           onkeyup="validacion('APELLIDO');" />
                                </div>    
                            </div>
                        </div>

                        <div class="row"><!--FILA 2-->
                            <div class="form-group col-md-4 col-md-offset-1">
                                <div class="input-group"><!-- Con el "input-group" agrupamos el icono con el campo del input -->
                                    <span class="input-group-addon"><i class="fa fa-tag"  ></i></span>
                                    <input type="text" class="form-control" id="USUARIO" placeholder="Usuario" name="usuario" 
                                           onkeyup="validacion('USUARIO');" />
                                </div>    
                            </div>

                            <div class="form-group col-md-4 col-md-offset-1">                      
                                <div class="input-group"> <!-- Con el "input-group" agrupamos el icono con el campo del input -->
                                    <span class="input-group-addon"><i class="fa fa-tag"  ></i></span> <!-- ICONO  --> 
                                    <input type="password" class="form-control" id="PASSWORD" placeholder="Contraseña" name="pass"
                                           onkeyup="validacion('PASSWORD');"/>
                                    <span class="help-block"></span>
                                </div>
                            </div>
                        </div>
                        
                        <div class="row"><!--FILA 3-->
                            <div class="form-group col-md-4 col-md-offset-1">
                                <!-- <label for="inputState">Asignar Roles: </label>-->
                                    <select id="inputState" class="form-control" name="rolAsignado">
                                        <option selected>Seleccionar Rol...</option>
                                        <c:forEach items="${Roles}" var="ROLES">
                                            <option ><c:out value="${ROLES.descripcion}" /></option>
                                        </c:forEach>
                                    </select>
                            </div>
                        </div>
                        
                        
                        
                        <div class="row"><!--FILA 4 CHECKBOX-->
                            <div class="form-group col-md col-md-offset-1">
                                <h3>Seleccionar Permisos:</h3>
                            </div>

                                <div class="form-check col-md-4 col-md-offset-1 ">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1"
                                           name="crear" value="true">
                                    <label class="form-check-label" for="inlineCheckbox1">Crear</label>
                                </div>
                           
                                <div class="form-check col-md-4 col-md-offset-1">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2"
                                           name="listar" value="true">
                                    <label class="form-check-label" for="inlineCheckbox2">Listar</label>
                                </div>
                            
                                <div class="form-check col-md-4 col-md-offset-1 ">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1"
                                           name="eliminar" value="true">
                                    <label class="form-check-label" for="inlineCheckbox1">Eliminar</label>
                                </div>
                                                        
                                <div class="form-check col-md-4 col-md-offset-1">
                                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2"
                                           name="editar" value="true">
                                    <label class="form-check-label" for="inlineCheckbox2">Editar</label>
                                </div>
                            
                        </div><!-- FIN ROW - FILA 4 - CHECKBOX -->
                        <br>
                               
                        <div class="col-md-4 col-md-offset-9" > 
                            <input type="submit" class="btn btn-primary" value="Registrar" />
                        </div>
                            
                    </form>
                  
                </div><!-- /CONTAINER-->
            </div><!-- /PANEL BODY-->
        
        <footer>
            <center><a href="login.jsp" > <b>Iniciar Sesión</b> </a> o volver a la página principal
                    <a href="sistema.jsp"><b>Sistema</b> </a></center>
        </footer>

    

        <script src="js/jquery-1.10.2.js"></script>
        <!-- BOOTSTRAP SCRIPTS -->
        <script src="js/bootstrap.js"></script>
        <!-- METISMENU SCRIPTS -->
        <script src="js/jquery.metisMenu.js"></script>
           <!-- CUSTOM SCRIPTS -->
        <script src="js/custom.js"></script>
        <!-- Anclamos la carpeta Validación y el archivo correspondiente a este formulario -->
        <script src="validaciones/VALclientes.js"></script>
    </body>
    
</html>
