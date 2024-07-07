package com.pooespol.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pooespol.Principales.Editor;
import com.pooespol.Principales.Revisor;
import com.pooespol.Principales.Usuario;
import com.pooespol.Proceso.Revision;
import com.pooespol.Publicacion.Articulo;
import com.pooespol.Publicacion.Autor;
import com.pooespol.Tipos.TipoDeRol;

import java.util.Random;

public class Aplicacion {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Articulo> articulos = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarUsuariosDesdeArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\usuarios.txt"); // Cargar datos de usuarios desde archivo
        cargarArticulos("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Articulos.txt"); // Cargar datos de artículos desde archivo


        System.out.println("\nBienvenido al sistema de gestión de artículos científicos");

        while (true) {
            mostrarMenu();
            int opcion = obtenerOpcion(sc);

            switch (opcion) {
                case 1:
                    someterArticulo(sc);
                    break;
                case 2:
                    iniciarSesion();
                    break;
        
                case 3:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nOpciones disponibles:");
        System.out.println("1. Someter artículo");
        System.out.println("2. Iniciar sesión");
        System.out.println("3. Salir");
        System.out.print("Ingrese la opción deseada: ");
    }

    private static int obtenerOpcion(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
            }
        }
    }

    private static void someterArticulo(Scanner scanner) {
        System.out.println("\nRegistro de datos del autor:");
        System.out.print("Nombre: ");
        String nombreAutor = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellidoAutor = scanner.nextLine();
        System.out.print("Correo electrónico: ");
        String correoAutor = scanner.nextLine();
        System.out.print("Institución: ");
        String institucion = scanner.nextLine();
        System.out.print("Campo de investigación: ");
        String campoInvestigacion = scanner.nextLine();

        // Generar un código único para el autor (simulación)
        Random r=new Random();
        int i= r.nextInt(4);
        int codigoID = usuarios.size()+10000*i; // Ejemplo simple de generación de ID único

        Autor autor = new Autor(codigoID, nombreAutor, apellidoAutor, correoAutor, institucion, campoInvestigacion);
        usuarios.add(autor);

        autor.someterArticulo(scanner, articulos,autor);
        escribirArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Investigadores.txt", "Investigador: "+autor.toString());

        // Asignar revisores al artículo recién sometido
        Articulo articuloReciente = articulos.get(articulos.size() - 1);
        asignarRevisores(articuloReciente);
    }

    private static void asignarRevisores(Articulo articulo) {
        ArrayList<Revisor> disponibles = obtenerRevisoresDisponibles();
        articulos.add(articulo);
    
        if (disponibles.size() < 2) {
            System.out.println("No hay suficientes revisores disponibles para asignar a este artículo.");
            return;
        }
    
        Revisor revisor1 = disponibles.get(0);
        Revisor revisor2 = disponibles.get(1);
    
        System.out.println("Revisores asignados automáticamente:");
        System.out.println("- " + revisor1.getNombre());
        System.out.println("- " + revisor2.getNombre());

        escribirArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Revisores.txt", revisor1.toString());
        escribirArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Revisores.txt", revisor2.toString());

    
        // Asignar artículo a los revisores
        revisor1.setArticuloAsignados(articulo);
        revisor2.setArticuloAsignados(articulo);
    
        // Agregar revisores a la lista de revisores del artículo
        articulo.agregarRevisor(revisor1);
        articulo.agregarRevisor(revisor2);
    
        // Asignar un editor al artículo
        ArrayList<Editor> editoresDisponibles = obtenerEditoresDisponibles();
        if (!editoresDisponibles.isEmpty()) {
            Editor editorAsignado = editoresDisponibles.get(0); // Simplemente asignamos el primer editor disponible
    
            editorAsignado.setArticuloAsignados(articulos);
            articulo.setEditor(editorAsignado);
            System.out.println("Editor asignado automáticamente:");
            System.out.println("- " + editorAsignado.getNombre());
    
            // Agregar editor al artículo
            articulo.setEditor(editorAsignado);
        } else {
            System.out.println("No hay editores disponibles para asignar a este artículo.");
        }
        escribirArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Articulos.txt", "\n"+articulo.toString()+ ", Revisor1 :"+articulo.getRevisores().get(0).getNombre()+" "+articulo.getRevisores().get(0).getApellido()+", Revisor2: "+articulo.getRevisores().get(1).getNombre()+" "+articulo.getRevisores().get(1).getApellido()+","+" Editor :"+articulo.getEditor().getNombre()+" "+articulo.getEditor().getApellido());

        enviarCorreo(revisor1.getCorreo(), "Nuevo artículo asignado para revisión", "Estimado revisor,\n\nSe les ha asignado el artículo \"" 
        + articulo.getTitulo() + "\" para revisión. Por favor, revisen su cuenta para más detalles.\n\nSaludos,\nSistema de Gestión de Artículos Científicos");
        enviarCorreo(revisor2.getCorreo(), "Nuevo artículo asignado para revisión", "Estimado revisor,\n\nSe les ha asignado el artículo \"" 
        + articulo.getTitulo() + "\" para revisión. Por favor, revisen su cuenta para más detalles.\n\nSaludos,\nSistema de Gestión de Artículos Científicos");
       enviarCorreo(articulo.getEditor().getCorreo(), "Nuevo artículo asignado para revisión", "Estimado editor,\n\nSe le ha asignado el artículo \"" 
        + articulo.getTitulo() + "\" para revisión. Por favor, revise su cuenta para más detalles.\n\nSaludos,\nSistema de Gestión de Artículos Científicos");

    }
    
    private static ArrayList<Revisor>  obtenerRevisoresDisponibles() {
        ArrayList<Revisor>  disponibles = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Revisor) {
                Revisor revisor = (Revisor) usuario;
                if (revisor.getArticuloAsignados() == null) {
                    disponibles.add(revisor);
                }
            }
        }
        return disponibles;
    }

    private static ArrayList<Editor> obtenerEditoresDisponibles() {
        ArrayList<Editor> disponibles = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Editor) {
                Editor editor = (Editor) usuario;
                if (editor.getArticulosAsignado().size()<5) {
                    disponibles.add(editor);
                }
            }
        }
        return disponibles;
    }

    private static void iniciarSesion() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        Usuario usuarioEncontrado = null;
        TipoDeRol tipo = null;
        for (Usuario u : usuarios) {
            if (u instanceof Editor) {
                Editor editor = (Editor) u;
                if (editor.IniciarSesion(usuario, contrasena)) {
                    tipo = editor.getTipoDeRol();
                    usuarioEncontrado = editor;
                    break;
                }
            } else if (u instanceof Revisor) {
                Revisor revisor = (Revisor) u;
                if (revisor.IniciarSesion(usuario, contrasena)) {
                    tipo = revisor.getTipoDeRol();
                    usuarioEncontrado = revisor;
                    break;
                }
            }
        }

        if (usuarioEncontrado != null) {
            System.out.println("\nInicio de sesión exitoso como " + tipo + ": " + usuarioEncontrado.getNombre() + " " + usuarioEncontrado.getApellido());
            if (usuarioEncontrado instanceof Editor) {
                Editor editor = (Editor) usuarioEncontrado;
                System.out.println("Ingrese el id del articulo");
                int idArticulo=sc.nextInt();
                sc.nextLine();
                procesarComentariosDecisiones("C:\\Users\\Estra\\proyectopoo\\POO4_1P_ESPINOZA_ESTRADA_VEINTIMILLA\\src\\main\\java\\com\\pooespol\\Informacion.txt\\ComentariosDecisiones.txt", idArticulo);          
                editor.tareaAsignada(idArticulo);
                editor.guardarComentarios(editor, idArticulo);
                verEstadoArticulo(idArticulo);

                //Escribir el archivo Editores.txt
                escribirArchivo("C:\\ProyectoPOO\\POO4_1P_ESPINOZA_ESTRADA_VEINTIMILLA\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Editores.txt", editor.toString());
                
                
            } else if (usuarioEncontrado instanceof Revisor) {
                Revisor revisor = (Revisor) usuarioEncontrado;
                revisor.tareaAsignada();

                if(revisor.getArticuloAsignados()!=null){
                    System.out.println("Agregue comentarios sobre el artículo:");
                    String comentario = sc.nextLine();
                    revisor.agregarComentarios(comentario);
                    revisor.guardarComentarios(revisor);
                }
            }
            System.out.println("\nPresione Enter para volver al menú principal.");
            sc.nextLine();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
    private static void verEstadoArticulo(int idArticulo) {
        

        boolean encontrado = false;
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo() == idArticulo) {
                // Obtener revisores y editor asociados al artículo
                ArrayList<Revisor> revisores = articulo.getRevisores();
                Editor editor = articulo.getEditor();
                Revision r= new Revision(articulo, revisores.get(0),revisores.get(1),editor);
                enviarCorreo(articulo.getAutor().getCorreo(), "La revision ha concluido, aqui su informe con respecto a la misma.", r.imprimirRevision());
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Artículo no encontrado.");
        }

        System.out.println("\nPresione Enter para volver al menú principal.");
        sc.nextLine();
    }

   private static void enviarCorreo(String destinatario, String asunto, String cuerpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
    
        // Autenticación
        String username = "pooproyecto7@gmail.com";
        String password = "vqtz eryx ukur tfqs";

        // Crear la sesión
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });
    
        try {
            // Crear el mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
    
            // Enviar el mensaje
            Transport.send(message);
    
            System.out.println("Correo enviado exitosamente a " + destinatario);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
     public static void escribirArchivo(String nombreArchivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write(contenido);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo " + nombreArchivo);
            e.printStackTrace();
        }
    } 

    private static void cargarUsuariosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 7) { // Ajustar el número según la cantidad de campos esperados
                    String tipoUsuario = datos[0].trim();
                    int codigoID = Integer.parseInt(datos[1].trim());
                    String nombre = datos[2].trim();
                    String apellido = datos[3].trim();
                    String correo = datos[4].trim();
                    String acceso = datos[5].trim();
                    String contrasena = datos[6].trim();
    
                    switch (tipoUsuario) {
                        case "Autor":
                            if (datos.length >= 9) {
                                String institucion = datos[7].trim();
                                String campoInvestigacion = datos[8].trim();
                                Autor autor = new Autor(codigoID, nombre, apellido, correo, institucion, campoInvestigacion);
                                usuarios.add(autor);
                            } else {
                                System.out.println("Error en el formato de línea para Autor: " + linea);
                            }
                            break;
                        case "Revisor":
                            if (datos.length >= 8) {
                                String especialidad = datos[7].trim();
                                Revisor revisor = new Revisor(nombre, apellido, correo, acceso, contrasena, especialidad);
                                usuarios.add(revisor);
                            } else {
                                System.out.println("Error en el formato de línea para Revisor: " + linea);
                            }
                            break;
                        case "Editor":
                            if (datos.length >= 8) {
                                String nombreJournal = datos[7].trim();
                                Editor editor = new Editor(nombre, apellido, correo, acceso, contrasena, nombreJournal);
                                usuarios.add(editor);
                            } else {
                                System.out.println("Error en el formato de línea para Editor: " + linea);
                            }
                            break;
                        default:
                            System.out.println("Tipo de usuario desconocido en el archivo: " + tipoUsuario);
                    }
                } else {
                    System.out.println("Error en el formato de línea del archivo: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
        }
    }

    private static void cargarArticulos(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            br.readLine();
            String linea;
            while ((linea = br.readLine()) != null) {
                // Procesar la línea para extraer la información necesaria
                String[] partes = linea.split(", ");
                String titulo = partes[0].split("=")[1].replace("'", "").trim();
                String nombreCompletoAutor = partes[1].split(":")[1].trim();
                String[] nombreAutorPartes = nombreCompletoAutor.split(" ");
                String nombreAutor = nombreAutorPartes[0].trim();
                String apellidoAutor = nombreAutorPartes[1].trim();
                int codigoArticulo = Integer.parseInt(partes[2].split("=")[1].trim());
                String resumen = partes[3].split("=")[1].replace("'", "").trim();
                String contenido = partes[4].split("=")[1].replace("'", "").trim();
                String palabrasClave = partes[5].split("=")[1].replace("'", "").trim();
                String nombreCompletoRevisor1 = partes[7].split(":")[1].trim();
                String[] nombreRevisor1Partes = nombreCompletoRevisor1.split(" ");
                String nombreRevisor1 = nombreRevisor1Partes[0].trim();
                String apellidoRevisor1 = nombreRevisor1Partes[1].trim();
                String nombreCompletoRevisor2 = partes[8].split(":")[1].trim();
                String[] nombreRevisor2Partes = nombreCompletoRevisor2.split(" ");
                String nombreRevisor2 = nombreRevisor2Partes[0].trim();
                String apellidoRevisor2 = nombreRevisor2Partes[1].trim();
                String nombreCompletoEditor = partes[9].split(":")[1].trim();
                String[] nombreEditorPartes = nombreCompletoEditor.split(" ");
                String nombreEditor = nombreEditorPartes[0].trim();
                String apellidoEditor = nombreEditorPartes[1].trim();
                
                
    
                // Buscar el autor, revisores y editor en la lista de usuarios
                Autor autor = (Autor) obtenerUsuarioPorNombre(nombreAutor, apellidoAutor);
                Revisor revisor1 = (Revisor) obtenerUsuarioPorNombre(nombreRevisor1, apellidoRevisor1);
                Revisor revisor2 = (Revisor) obtenerUsuarioPorNombre(nombreRevisor2, apellidoRevisor2);
                Editor editor = (Editor) obtenerUsuarioPorNombre(nombreEditor, apellidoEditor);
    
                // Crear el artículo y asignar las relaciones
                Articulo articulo = new Articulo(autor, codigoArticulo, titulo, resumen, contenido, palabrasClave);
                articulo.agregarRevisor(revisor1);
                articulo.agregarRevisor(revisor2);
                articulo.setEditor(editor);
    
                articulos.add(articulo);
                revisor1.setArticuloAsignados(articulo);
                revisor2.setArticuloAsignados(articulo);
                ArrayList<Articulo> articulos=editor.getArticulosAsignado();
                articulos.add(articulo);
                editor.setArticuloAsignados(articulos);
                
    
                System.out.println("Artículo cargado: " + titulo);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
        }
    }
    
    private static Usuario obtenerUsuarioPorNombre(String nombre, String apellido) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre) && usuario.getApellido().equalsIgnoreCase(apellido)) {
                return usuario;
            }
        }
        return null;
    }
    private static void procesarComentariosDecisiones(String nombreArchivo, int idArticulo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean procesarSiguiente = false;
            
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith("Articulo:")) {
                    String[] partes = linea.split(", ");
                    String nombreRevisor= partes[0].split(" ")[0].trim();
                    int id = Integer.parseInt(partes[0].split(":")[1].trim());
                    for(Articulo a: articulos){
                        if (id == a.getCodigoArticulo()) {
                           for(Revisor revisor: a.getRevisores()){
                                if(revisor.getNombre().equals(nombreRevisor)){
                                    String decision = partes[1].split(":")[1].trim();
                                    boolean decisionRevisor = Boolean.parseBoolean(decision);
                                    revisor.setdecision(decisionRevisor);
                                    String comentarios = partes[2].split(":")[1].trim();
                                    revisor.agregarComentarios(comentarios);
                                    procesarSiguiente = true; // Marcar que se procesó una línea válida para este artículo
                                }
                            }
                        } else {
                            procesarSiguiente = false; // Resetear si el artículo no es el buscado
                        }
                    }

                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error de formato numérico en el archivo: " + e.getMessage());
        }
    }
    
}
