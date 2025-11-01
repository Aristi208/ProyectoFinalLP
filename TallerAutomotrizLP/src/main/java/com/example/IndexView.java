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
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.Set;

@Route("")
public class IndexView extends VerticalLayout {


    // Metodo constructor
    public IndexView() throws Exception {

        // Construccion principal de la pagina --> creacion de los VerticalLayouts y FormLayouts

        VerticalLayout main = new VerticalLayout();

        // IA: este fragmento de codigo tiene la funcion de asignarle una imagen al fondo de pantalla de nuestra pagina
        getStyle()
                .set("background-image", "url('https://i.ibb.co/wZc5bJr0/wallhaven-8g5p52.jpg')")
                .set("background-size", "cover")        // La imagen cubre toda la pantalla
                .set("background-position", "center")   // Centra la imagen
                .set("background-repeat", "no-repeat")  // Evita que se repita
                .set("min-height", "100vh");            // Ocupa toda la ventana

        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2));

        FormLayout formularioInteractivo = new FormLayout();
        formularioInteractivo.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2));

        // Invocamos la funcion constructora del Hero

        construirHero(hero);

        construirformularioInteractivo(formularioInteractivo);

        main.add(hero, formularioInteractivo);

        add(main);

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
            TextField numeroCliente = new TextField("numero del cliente");

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

            // IA: Selector de fecha --> nos sirve para que el usuario seleccione la fecha en la cual desea el servicio o la cita
            DatePicker fechaCita = new DatePicker("Fecha de la cita");

            // IA: Botón para guardar --> se encarga de guardar la información y recordarle al usuario si aun le falta agregar alguna información o rellenar algún campo
            Button botonGuardar = new Button("Guardar cita", event -> {

                // Validación simple
                if (nombreCliente.isEmpty() || idCliente.isEmpty() || servicios.getSelectedItems().isEmpty()
                        || marcaVehiculo.isEmpty() || tipoVehiculo.getSelectedItems().isEmpty()
                        || placaVehiculo.isEmpty() || fechaCita.isEmpty()) {
                    Notification.show("⚠️ Por favor completa todos los campos antes de continuar.", 3000, Notification.Position.MIDDLE);

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
                    servicios.clear();
                    marcaVehiculo.clear();
                    tipoVehiculo.clear();
                    placaVehiculo.clear();
                    fechaCita.clear();

                }

            });

            // Estilos para los multiples campos

            nombreCliente.getStyle()
                    .set("color", "white")
                    .set("border-radius", "8px")
                    .set("padding", "8px");

            // Estilo para el boton
            botonGuardar.getStyle()
                    .set("background-color", "red")
                    .set("color", "white")
                    .set("border-radius", "10px")
                    .set("padding", "10px 25px");

            // .setWidthFull --> lo utilizamospara que los campos tomen todo el espacio del Layout padre (en este caso un VerticalLayout)
            nombreCliente.setWidthFull();
            idCliente.setWidthFull();
            servicios.setWidthFull();
            marcaVehiculo.setWidthFull();
            tipoVehiculo.setWidthFull();
            placaVehiculo.setWidthFull();
            fechaCita.setWidthFull();
            botonGuardar.setWidthFull();

            // Agregamos los componentes al leftSide
            leftSide.add(tituloFormulario, nombreCliente, idCliente, idCliente, servicios, marcaVehiculo, tipoVehiculo, placaVehiculo, fechaCita, botonGuardar);

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

}
