package com.pooespol.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
        cargarUsuariosDesdeArchivo("C:\\Users\\Estra\\ProtitpoPoo\\demo\\src\\main\\java\\com\\espol\\usuarios.txt"); // Cargar datos de usuarios desde archivo


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
                    verEstadoArticulo();
                    break;
                case 4:
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
        System.out.println("3. Ver Estado de Articulo");
        System.out.println("4. Salir");
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

        autor.someterArticulo(scanner, articulos);


        // Asignar revisores al artículo recién sometido
        Articulo articuloReciente = articulos.get(articulos.size() - 1);
        asignarRevisores(articuloReciente);
    }

    private static void asignarRevisores(Articulo articulo) {
        ArrayList<Revisor> disponibles = obtenerRevisoresDisponibles();
    
        if (disponibles.size() < 2) {
            System.out.println("No hay suficientes revisores disponibles para asignar a este artículo.");
            return;
        }
    
        Revisor revisor1 = disponibles.get(0);
        Revisor revisor2 = disponibles.get(1);
    
        System.out.println("Revisores asignados automáticamente:");
        System.out.println("- " + revisor1.getNombre());
        System.out.println("- " + revisor2.getNombre());
    
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
            editorAsignado.setArticuloAsignados(articulo);
            articulo.setEditor(editorAsignado);
            System.out.println("Editor asignado automáticamente:");
            System.out.println("- " + editorAsignado.getNombre());
    
            // Agregar editor al artículo
            articulo.setEditor(editorAsignado);
        } else {
            System.out.println("No hay editores disponibles para asignar a este artículo.");
        }
    
        // Simulación de envío de correo a los revisores y al editor (debería implementarse)
        System.out.println("Se ha enviado un correo a los revisores y al editor asignados.");
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
                if (editor.getArticulosAsignado() == null) {
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
                editor.tareaAsignada();
            } else if (usuarioEncontrado instanceof Revisor) {
                Revisor revisor = (Revisor) usuarioEncontrado;
                revisor.tareaAsignada();

                if(revisor.getArticuloAsignados()!=null){
                    System.out.println("Agregue comentarios sobre el artículo:");
                    String comentario = sc.nextLine();
                    revisor.agregarComentarios(comentario);
                }
            }
            System.out.println("\nPresione Enter para volver al menú principal.");
            sc.nextLine();
        } else {
            System.out.println("Usuario o contraseña incorrectos.");
        }
    }
    private static void verEstadoArticulo() {
        System.out.print("Ingrese el ID del artículo: ");
        int idArticulo = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer de entrada

        boolean encontrado = false;
        for (Articulo articulo : articulos) {
            if (articulo.getCodigoArticulo() == idArticulo) {
                // Obtener revisores y editor asociados al artículo
                ArrayList<Revisor> revisores = articulo.getRevisores();
                Editor editor = articulo.getEditor();
                Revision r= new Revision(articulo, revisores.get(0),revisores.get(1),editor);
                r.imprimirRevision();
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
    

    
}
