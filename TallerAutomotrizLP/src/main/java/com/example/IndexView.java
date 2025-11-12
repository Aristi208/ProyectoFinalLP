package com.example;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.jsoup.helper.ValidationException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.InputMismatchException;
import java.util.Set;

@Route("")
public class IndexView extends VerticalLayout {


    // Metodo constructor
    public IndexView() throws Exception {

        try{

            // Construccion principal de la pagina --> creacion de los VerticalLayouts y FormLayouts

            VerticalLayout main = new VerticalLayout();

            // IA: este fragmento de codigo tiene la funcion de asignarle una imagen al fondo de pantalla de nuestra pagina
            getStyle()
                    .set("background-image", "url('https://i.ibb.co/k6Qkth1r/wallhaven-2yo6zm.png')")
                    .set("background-size", "cover")        // La imagen cubre toda la pantalla
                    .set("background-position", "center")   // Centra la imagen
                    .set("background-repeat", "no-repeat")  // Evita que se repita
                    .set("min-height", "100vh");            // Ocupa toda la ventana

            FormLayout hero = new FormLayout();
            hero.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2));

            FormLayout formularioInteractivo = new FormLayout();
            formularioInteractivo.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2));

            FormLayout galeria = new FormLayout();
            galeria.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 3));

            // Invocamos la funcion constructora del Hero

            construirHero(hero);

            construirformularioInteractivo(formularioInteractivo);

            construirGaleria(galeria);

            main.add(hero, formularioInteractivo, galeria);

            add(main);

        } catch (Exception e) {
            System.out.println("Error en la construccion principal de la pagina");
        }

    }

    // Funciones creadoras

    // Construir el Hero --> agregrara los componentes del encabezado inicial de la pagina
    public static void construirHero(FormLayout hero) throws Exception{
        try {

            VerticalLayout leftSide = new VerticalLayout();

            HorizontalLayout rightSide = new HorizontalLayout();

            // LeftSide --> Marca (titulo y logo)
            H1 nombreEmpresa = new H1("JAVA - VIRTUAL - MOTOR");
            nombreEmpresa.addClassName("nombreEmpresa-personalizado");

            // Estilo para el nombre de la empresa
            nombreEmpresa.getStyle()
                    .set("font-family", "'Brush Script MT', cursive")
                    .set("font-size", "50px")
                    .set("font-weight", "bold")
                    .set("color", "black");

            // IA: Crear componente Image
            Image logoEmpresa = new Image("https://i.ibb.co/MxbwfkYJ/Imagen-Empresa.png", "Logo de la empresa");

            // Estilopara la imagen del logo
            logoEmpresa.getStyle()
                    .set("border-radius", "15px");

            // IA / Opcional: definir tamaño de la imagen (Logo)
            logoEmpresa.setWidth("450px");
            logoEmpresa.setHeight("100PX");

            // Agregamos los componentes al LeftSide
            leftSide.add(nombreEmpresa, logoEmpresa);

            // RightSide --> Menu (Menu Bar --> medios de contacto y Boton para solicitar Agendamiento de cita o reparacion)

            // IA: Menu Bar (Sobre nosotros y contactos)
            MenuBar menuBar = new MenuBar();
            menuBar.setOpenOnHover(true); // se despliega el submenú al pasar el mouse

            // IA / Opción 1: "Nosotros"
            MenuItem opcionNosotros = menuBar.addItem("Nosotros");

            // Estilo para las casillas
            opcionNosotros.getStyle()
                    .set("border-radius", "15px");

            opcionNosotros.addClickListener(e ->
                    Notification.show("Somos una empresa dedicada a brindar el mejor servicio a nuestros clientes por medio del cuidado de sus vehiculos con ayuda de nuestros profesionales en la mecanica.", 3000, Notification.Position.MIDDLE)
            );

            // Le damos un estilo o diseño
            opcionNosotros.getStyle()
                    .set("color", "blue");

            // IA / Opción 2: "Contáctanos" con submenú
            MenuItem opcionContactanos = menuBar.addItem("Contáctanos");

            // Le damos un estilo o diseño
            opcionContactanos.getStyle()
                    .set("color", "blue");

            // Submenú con información del teléfono
            opcionContactanos.getSubMenu().addItem("Teléfono: +57 312 345 6789");

            // Agregamoslos componentes al RightSide
            rightSide.add(menuBar, opcionNosotros, opcionContactanos);

            // Alineamos el VerticalLayout y el HorizontalLayout
            leftSide.setAlignItems(Alignment.CENTER);

            // IA: Esta línea alinea los componentes hacia la derecha
            rightSide.setJustifyContentMode(JustifyContentMode.END);

            // IA / Opcional: asegura que tome el ancho completo para que el alineamiento funcione correctamente
            rightSide.setWidthFull();

            // Agregamos el leftSide y el rigthSide
            hero.add(leftSide, rightSide);

        } catch (Exception e) {
            throw new Exception("Error en la creacion del Hero " + e);
        }
    }

    // Construir formularioInteractivo --> va a crear el formulario para agendar cita y mostrar imagenes de los diferentes servicios que se prestan

    public static void construirformularioInteractivo(FormLayout formularioInteractivo) throws Exception {
        try {

            VerticalLayout leftSide = new VerticalLayout();

            VerticalLayout rightSide = new VerticalLayout();

            // leftSide --> Formulario para agendar la cita

            // Titulo del formulario

            H2 tituloFormulario = new H2("Agendar cita");
            tituloFormulario.addClassName("tituloFormulario-personalizado");

            // Estilo para el titulo del formulario
            tituloFormulario.getStyle()
                    .set("font-family", "'Brush Script MT', cursive")
                    .set("font-size", "50px")
                    .set("font-weight", "bold")
                    .set("color", "black");

                // IA: Campos del formulario --> nos habilita unos campos para ingresar la información requerida
                TextField nombreCliente = new TextField("Nombre del cliente");

                TextField idCliente = new TextField("ID del cliente");
                idCliente.setHelperText("8 - 10 digitos");

                TextField numeroCliente = new TextField("numero del cliente");
                numeroCliente.setHelperText("10 digitos");

            // IA: ComboBox múltiple para Servicios --> nos despliega un mini menu en donde podemos seleccionar los diferentes tipos de servicio
            CheckboxGroup<String> servicios = new CheckboxGroup<>();
            servicios.setLabel("Servicios");
            servicios.setItems(
                    "Cambio de aceite",
                    "Cambio de frenos",
                    "Mantenimiento de cajas automáticas",
                    "Otros"
            );

            // IA: Campos del formulario --> nos habilita unos campos para ingresar la información requerida
            TextField marcaVehiculo = new TextField("Marca del vehículo");

            // IA: ComboBox múltiple para Tipo de vehículo --> nos despliega un mini menu en donde podemos seleccionar los diferentes tipos de vehiculos a los que se le realizan servicio
            CheckboxGroup<String> tipoVehiculo = new CheckboxGroup<>();
            tipoVehiculo.setLabel("Tipo de vehículo");
            tipoVehiculo.setItems(
                    "Gasolina",
                    "Diésel",
                    "Camioneta",
                    "Campero",
                    "Automóvil",
                    "Camión",
                    "Bus"
            );

            TextField placaVehiculo = new TextField("Placa del vehículo");
            placaVehiculo.setHelperText("ABC123");

            // IA: Selector de fecha --> nos sirve para que el usuario seleccione la fecha en la cual desea el servicio o la cita
            LocalDate now = LocalDate.now(ZoneId.systemDefault());

            DatePicker fechaCita = new DatePicker("Fecha de la cita");

            // Validaciones y control sobre le dia de la cita

            fechaCita.setRequiredIndicatorVisible(true);
            fechaCita.setMin(now);
            fechaCita.setMax(now.plusDays(60));
            fechaCita.setHelperText("Maximo 60 dias adelante de la fecha actual");

            // IA: Botón para guardar --> se encarga de guardar la información y recordarle al usuario si aun le falta agregar alguna información o rellenar algún campo
            Button botonGuardar = new Button("Guardar cita", event -> {

                // Validación simple para cada uno de los campos del formulario para evitar cualquier error
                if (nombreCliente.isEmpty() || idCliente.isEmpty() || servicios.getSelectedItems().isEmpty()
                        || marcaVehiculo.isEmpty() || tipoVehiculo.getSelectedItems().isEmpty()
                        || placaVehiculo.isEmpty() || fechaCita.isEmpty()) {
                    Notification.show("⚠️ Por favor completa todos los campos antes de continuar.", 3000, Notification.Position.MIDDLE);

                }
                 else if (!(nombreCliente.getValue().matches("[a-zA-Z ]+"))) {

                    Notification.show("⚠️ Ingresa valor valido, sin simbolos ni caracteres especiales", 3000, Notification.Position.MIDDLE);

                    nombreCliente.clear();

                } else if (idCliente.getValue().matches("[0-9]+") && !(idCliente.getValue().length() >= 8 && idCliente.getValue().length() <= 10) || idCliente.getValue().matches("[a-zA-Z ]+")){

                    Notification.show("⚠️ Ingresa un ID / cedula valido", 3000, Notification.Position.MIDDLE);

                    idCliente.clear();

                }
                else if ((numeroCliente.getValue().matches("[0-9]+")) && (!(numeroCliente.getValue().length() >= 1 && numeroCliente.getValue().length() <= 10)) || (numeroCliente.getValue().matches("[a-zA-Z ]+"))){

                    Notification.show("⚠️ Ingresa un numero de tel / cel valido", 3000, Notification.Position.MIDDLE);

                    numeroCliente.clear();

                }
                else if (!(marcaVehiculo.getValue().matches("[a-zA-Z ]+"))){

                    Notification.show("⚠️ Ingresa un valor valido en la marca de vehiculo (solo letras)", 3000, Notification.Position.MIDDLE);

                    marcaVehiculo.clear();

                } else if (!(placaVehiculo.getValue().toUpperCase().matches("[A-Z]{3}[0-9]{3}"))){

                    Notification.show("⚠️ Ingresa un placa valida (ABC123) para seguir", 3000, Notification.Position.MIDDLE);

                    placaVehiculo.clear();

                }
                else {

                    String citaInfo = String.format("Nombre: %s, ID: %s, Numero Cliente: %s, Servicios: %s, Marca: %s, Tipo. %s, Placa: %s, Fecha: %s",
                            nombreCliente.getValue(),
                            idCliente.getValue(),
                            numeroCliente.getValue().toString(),
                            servicios.getValue(),
                            marcaVehiculo.getValue(),
                            tipoVehiculo.getValue(),
                            placaVehiculo.getValue(),
                            fechaCita.getValue()
                            );

                    // IA: Notificación de confirmación
                    Notification.show("✅ La cita ha sido asignada correctamente - Info. Cita: " + citaInfo, 8000, Notification.Position.MIDDLE);

                    // IA: Obtener los valores seleccionados
                    Set<String> serviciosSeleccionados = servicios.getSelectedItems();
                    Set<String> tiposSeleccionados = tipoVehiculo.getSelectedItems();

                    // IA: Limpieza opcional de campos --> se encarga de limpiar los campos para que el usuario no los tenga que borrar por sí mismo
                    nombreCliente.clear();
                    idCliente.clear();
                    numeroCliente.clear();
                    servicios.clear();
                    marcaVehiculo.clear();
                    tipoVehiculo.clear();
                    placaVehiculo.clear();
                    fechaCita.clear();

                }

            });

            // Estilos para los multiples campos

            // Estilo para el boton
            botonGuardar.getStyle()
                    .set("background-color", "red")
                    .set("color", "white")
                    .set("border-radius", "10px")
                    .set("padding", "10px 25px");

            // .setWidthFull --> lo utilizamos para que los campos tomen todo el espacio del Layout padre (en este caso un VerticalLayout)
            nombreCliente.setWidthFull();
            idCliente.setWidthFull();
            numeroCliente.setWidthFull();
            servicios.setWidthFull();
            marcaVehiculo.setWidthFull();
            tipoVehiculo.setWidthFull();
            placaVehiculo.setWidthFull();
            fechaCita.setWidthFull();
            botonGuardar.setWidthFull();

            // Agregamos los componentes al leftSide
            leftSide.add(tituloFormulario, nombreCliente, idCliente, idCliente, numeroCliente, servicios, marcaVehiculo, tipoVehiculo, placaVehiculo, fechaCita, botonGuardar);

            // RightSide --> Mostrar servicios (título, imagenes de los servicios, definicion de que es cada uno)

            // Titulo para apartado de servicios
            H2 tituloServicios = new H2("Servicios que ofrecemos");
            tituloServicios.addClassName("tituloServicios-personalizado");

            // Estilo para el título de los servicios
            tituloServicios.getStyle()
                    .set("font-family", "'Brush Script MT', cursive")
                    .set("font-size", "50px")
                    .set("font-weight", "bold")
                    .set("color", "black");

            // IA: Crear un contenedor tipo cuadrícula para almacenar las imagenes
            FlexLayout recuadroServicios = new FlexLayout();
            recuadroServicios.setFlexWrap(FlexLayout.FlexWrap.WRAP);
            recuadroServicios.setJustifyContentMode(JustifyContentMode.CENTER);
            recuadroServicios.getStyle()
                    .set("gap", "20px")
                    .set("padding", "30px");

            // IA: Crear imágenes con mensajes al pasar el cursor --> Se ayuda de la funcion complemantaria "crearImagen"
            Image img1 = crearImagen("https://www.confortauto.com/blog/wp-content/uploads/2021/08/Cambio-de-aceite-y-filtros.jpg", "Cambio de aceite");
            Image img2 = crearImagen("https://fx1neumaticos.com.ar/wp-content/uploads/2019/08/imagen-cambio-disco-freno.jpg", "Cambio de frenos");
            Image img3 = crearImagen("https://tallercajasautomaticas.com/wp-content/uploads/2023/05/Reinstalacion-de-la-Reparacion-de-Cajas-Automaticas-en-Medellin-Taller-de-Transmisiones-Automaticas.jpg", "Mantenimiento de cajas automaticas");
            Image img4 = crearImagen("https://vamotriz.com/wp-content/uploads/2015/05/image_09.jpg", "Otros / Mantenimiento General");

            // IA: Agregar las imágenes al recuadroServicios
            recuadroServicios.add(img1, img2, img3, img4);

            // Agregamos al RightSide
            rightSide.add(tituloServicios, recuadroServicios);

            // Centramos los componentes del rigthSide y leftSide
            rightSide.setAlignItems(Alignment.CENTER);
            leftSide.setAlignItems(Alignment.CENTER);

            // Enviamos lo que hicimos a los parametros
            formularioInteractivo.add(leftSide, rightSide);

        } catch (Exception e) {
            throw new Exception("Error en la creacion del interactivo " + e);
        }
    }

    // Funcion complementaraia al apartado de los servicios (iamgenes)
    public static Image crearImagen(String urlServicio, String mensajeServicio) throws Exception {
        try {

            // Hace que las imagenes sean más agradables a la vista editando su estilo
            Image img = new Image(urlServicio, "imagen");
            img.setWidth("200px");
            img.setHeight("200px");
            img.getStyle()
                    .set("border-radius", "15px")
                    .set("object-fit", "cover")
                    .set("box-shadow", "0 4px 10px rgba(0,0,0,0.3)")
                    .set("transition", "transform 0.3s, box-shadow 0.3s")
                    .set("cursor", "pointer");

            // IA: Mostrar mensaje al pasar el mouse o cursor para que sea más interactivo
            img.getElement().setProperty("title", mensajeServicio);

            img.addClickListener(e ->
                    Notification.show(mensajeServicio, 2000, Notification.Position.BOTTOM_CENTER)
            );

            // IA: Efecto al pasar el cursor
            img.getElement().executeJs(
                    "this.addEventListener('mouseenter', ()=>{ this.style.transform='scale(1.05)'; this.style.boxShadow='0 8px 20px rgba(0,0,0,0.5)'; });"
                            + "this.addEventListener('mouseleave', ()=>{ this.style.transform='scale(1.0)'; this.style.boxShadow='0 4px 10px rgba(0,0,0,0.3)'; });"
            );

            return img;

        } catch (Exception e) {
            throw new Exception("Error en funcion complementaria imagenes servicios " + e);
        }
    }

    // Metodo para construir la galeria --> mostrará imagenes de vehiculos
    public static void construirGaleria(FormLayout galeria) throws Exception {
        try {

            // Agregamos las imagenes que iran en la galeria
            Image imagen1 = new Image("https://i.ibb.co/7N6svdpg/wallhaven-6dl2gw.jpg", "img1");
            Image imagen2 = new Image("https://i.ibb.co/mCgzMLpk/wallhaven-5g3de1.png", "img2");
            Image imagen3 = new Image("https://i.ibb.co/sddwxQkL/wallhaven-4dpo8m.jpg", "img3");

            imagen1.getStyle()
                    .set("border-radius", "15px")
                    .set("object-fit", "cover")
                    .set("box-shadow", "0 4px 10px rgba(0,0,0,0.3)")
                    .set("transition", "transform 0.3s, box-shadow 0.3s")
                    .set("cursor", "pointer");

            imagen2.getStyle()
                    .set("border-radius", "15px")
                    .set("object-fit", "cover")
                    .set("box-shadow", "0 4px 10px rgba(0,0,0,0.3)")
                    .set("transition", "transform 0.3s, box-shadow 0.3s")
                    .set("cursor", "pointer");

            imagen3.getStyle()
                    .set("border-radius", "15px")
                    .set("object-fit", "cover")
                    .set("box-shadow", "0 4px 10px rgba(0,0,0,0.3)")
                    .set("transition", "transform 0.3s, box-shadow 0.3s")
                    .set("cursor", "pointer");

            galeria.add(imagen1, imagen2, imagen3);

        } catch (Exception e) {
            throw new Exception("Error en la creacion de la galeria " + e);
        }
    }

}
