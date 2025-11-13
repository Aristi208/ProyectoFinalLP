package com.example;

import Controller.Procesos;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

            IntegerField idCliente = new IntegerField("ID del cliente");
            idCliente.setHelperText("8 - 10 digitos");

            IntegerField numeroCliente = new IntegerField("numero del cliente");
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

            // Mapa de repuestos/insumos disponibles por servicio
            Map<String, List<String>> opcionesRepuestos = new LinkedHashMap<>();
            opcionesRepuestos.put("Cambio de aceite", Arrays.asList("Filtro de aceite", "Aceite 5W30", "Junta de cárter"));
            opcionesRepuestos.put("Cambio de frenos", Arrays.asList("Disco de freno", "Pastillas", "Sensor de desgaste"));
            opcionesRepuestos.put("Mantenimiento de cajas automáticas", Arrays.asList("Filtro de caja", "Aceite de transmisión", "Kit de juntas"));
            opcionesRepuestos.put("Otros", Arrays.asList("Correa de distribución", "Batería", "Bombilla"));

            // Selector de repuestos/insumos que se actualiza según los servicios seleccionados
            MultiSelectComboBox<String> repuestosCombo = new MultiSelectComboBox<>("Repuestos / insumos");
            repuestosCombo.setWidth("300px");
            repuestosCombo.setPlaceholder("Selecciona repuestos vinculados a los servicios");

            // Cuando cambian los servicios seleccionados, actualizar las opciones de repuestos
            servicios.addValueChangeListener(ev -> {
                Set<String> seleccionados = ev.getValue();
                List<String> items = new ArrayList<>();
                if (seleccionados != null) {
                    for (String serv : seleccionados) {
                        List<String> parts = opcionesRepuestos.getOrDefault(serv, Collections.emptyList());
                        for (String p : parts) {
                            items.add(serv + " - " + p);
                        }
                    }
                }
                // Actualizar las opciones del combo
                repuestosCombo.setItems(items);
                // Si se quitan servicios, eliminar selecciones inválidas
                Set<String> actuales = new LinkedHashSet<>(repuestosCombo.getSelectedItems());
                actuales.retainAll(new LinkedHashSet<>(items));
                repuestosCombo.clear();
                repuestosCombo.select(actuales);
            });

            // IA: Campos del formulario --> nos habilita unos campos para ingresar la información requerida
            TextField marcaVehiculo = new TextField("Marca del vehículo");

            // IA: ComboBox múltiple para Tipo de vehículo --> nos despliega un mini menu en donde podemos seleccionar los diferentes tipos de vehiculos a los que se le realizan servicio
            RadioButtonGroup<String> tipoVehiculo = new RadioButtonGroup<>();
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

            // IA: Botón para guardar --> se creará sin listener ahora, listener se agregará después de declarar todos los campos que usa
            Button botonGuardar = new Button("Guardar cita");

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

            // Opciones para agregar otro servicio a la orden y boton para hacerlo

            // Titulo para el apartado de los servicios extra
            H2 tituloServiciosExtra = new H2("Servicios extra");
            tituloServiciosExtra.addClassName("tituloTabla-personalizado");

            // Estilo para el título de los servicios
            tituloServiciosExtra.getStyle()
                    .set("font-family", "'Brush Script MT', cursive")
                    .set("font-size", "50px")
                    .set("font-weight", "bold")
                    .set("color", "black");

            // Creamos la lista de vehículos y el Grid antes de usarlo en el listener de guardarservicios
            List<Vehicle> vehiculosEnTaller = new ArrayList<>();
            Grid<Vehicle> grid = new Grid<>();

             // IA: --- ComboBox de servicios extra ---
             MultiSelectComboBox<String> serviciosExtra = new MultiSelectComboBox<>("Selecciona servicios extra");
             serviciosExtra.setItems(
                     "Lavado general",
                     "Aspirado interior",
                     "Pulida y encerado",
                     "Diagnóstico electrónico"
             );
             serviciosExtra.setWidth("300px");

             // IA: --- Botón para guardar ---
             Button guardarservicios = new Button("Guardar servicio extra");

             // IA: --- Acción del botón ---
            guardarservicios.addClickListener(e -> {
                // Obtener el vehículo seleccionado en el grid
                Set<Vehicle> vehiculosSeleccionados = grid.getSelectedItems();

                if (vehiculosSeleccionados == null || vehiculosSeleccionados.isEmpty()) {
                    Notification.show("⚠️ Debes seleccionar un vehículo para agregar servicios extra", 3000, Notification.Position.MIDDLE);
                    return;
                }

                Set<String> extras = serviciosExtra.getSelectedItems();
                if (extras == null || extras.isEmpty()) {
                    Notification.show("⚠️ No seleccionaste ningún servicio extra", 3000, Notification.Position.MIDDLE);
                    return;
                }

                // Aplicar los servicios extra seleccionados al vehículo seleccionado
                for (Vehicle vehiculo : vehiculosSeleccionados) {
                    vehiculo.addServiciosExtra(extras);
                }

                // Refrescar la tabla para mostrar los cambios
                grid.getDataProvider().refreshAll();

                Notification.show("✅ Servicios extra aplicados al vehículo seleccionado", 3000, Notification.Position.MIDDLE);
            });

            // Estilo para el boton

            guardarservicios.getStyle()
                    .set("background-color", "red")
                    .set("color", "white")
                    .set("border-radius", "10px")
                    .set("padding", "10px 25px");

            // .setWidthFull --> lo utilizamos para que los campos tomen todo el espacio del Layout padre (en este caso un VerticalLayout)
            serviciosExtra.setWidthFull();
            repuestosCombo.setWidthFull();

            // Agregamos los componentes al leftSide
            leftSide.add(tituloFormulario, nombreCliente, idCliente, idCliente, numeroCliente, servicios, repuestosCombo, marcaVehiculo, tipoVehiculo, placaVehiculo, fechaCita, botonGuardar, tituloServiciosExtra, serviciosExtra, guardarservicios);

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

            // Tendra la tabla que muestra los vehiculos que estan en el taller

            // Título para la tabla de vehículos en taller
            H2 tituloVehiculos = new H2("Vehículos en taller");
            tituloVehiculos.addClassName("tituloTabla-personalizado");

            // Estilo para el título de vehículos
            tituloVehiculos.getStyle()
                    .set("font-family", "'Brush Script MT', cursive")
                    .set("font-size", "50px")
                    .set("font-weight", "bold")
                    .set("color", "black");

             // --- Configurar la tabla (Grid) ---
             grid.addColumn(Vehicle::getPlaca).setHeader("Placa del vehículo");
             grid.addColumn(Vehicle::getMarca).setHeader("Marca");
             grid.addColumn(Vehicle::getTipoVehiculo).setHeader("Tipo de vehículo");
             // Mostrar los servicios principales como lista
             grid.addComponentColumn(v -> {
                 Div container = new Div();
                 container.getStyle().set("display", "flex").set("flex-direction", "column").set("gap", "2px");
                 for (String s : v.getServiciosList()) {
                     container.add(new Span(s));
                 }
                 return container;
             }).setHeader("Servicios");
             // Mostrar los servicios extra como lista
             grid.addComponentColumn(v -> {
                 Div container = new Div();
                 container.getStyle().set("display", "flex").set("flex-direction", "column").set("gap", "2px");
                 for (String s : v.getServiciosExtra()) {
                     container.add(new Span(s));
                 }
                 return container;
             }).setHeader("Servicios Extra");

             // Mostrar repuestos / insumos como lista (formato: "Servicio - Repuesto")
             grid.addComponentColumn(v -> {
                 Div container = new Div();
                 container.getStyle().set("display", "flex").set("flex-direction", "column").set("gap", "2px");
                 for (String s : v.getRepuestos()) {
                     container.add(new Span(s));
                 }
                 return container;
             }).setHeader("Repuestos / insumos");

            grid.setWidth("600px");
            grid.getStyle()
                    .set("border", "2px solid #1E3A8A")
                    .set("border-radius", "10px")
                    .set("background-color", "#F8FAFC");

            grid.setWidthFull();

            // Permitir selección de UN SOLO vehículo para facturar
            grid.setSelectionMode(Grid.SelectionMode.SINGLE);

            // Botón Facturar para eliminar vehículos seleccionados
            Button facturarButton = new Button("Facturar");
            facturarButton.getStyle()
                    .set("background-color", "green")
                    .set("color", "white")
                    .set("border-radius", "10px")
                    .set("padding", "10px 25px")
                    .set("margin-top", "15px");

            facturarButton.setWidthFull();

            facturarButton.addClickListener(e -> {
                Set<Vehicle> vehiculosSeleccionados = grid.getSelectedItems();

                if (vehiculosSeleccionados == null || vehiculosSeleccionados.isEmpty()) {
                    Notification.show("⚠️ Debes seleccionar un vehículo para facturar", 3000, Notification.Position.MIDDLE);
                    return;
                }

                if (vehiculosSeleccionados.size() > 1) {
                    Notification.show("⚠️ Solo puedes facturar un vehículo a la vez", 3000, Notification.Position.MIDDLE);
                    return;
                }

                Vehicle seleccionado = vehiculosSeleccionados.iterator().next();

                // Llamar a Procesos.generarFactura con los datos del vehículo seleccionado
                try {
                    List<String> serviciosList = seleccionado.getServiciosList();
                    Collection<String> serviciosExtraColl = seleccionado.getServiciosExtra();
                    Collection<String> repuestosColl = seleccionado.getRepuestos();

                    String ruta = Procesos.generarFactura(
                            seleccionado.getNombreCliente(),
                            seleccionado.getCedulaCliente(),
                            seleccionado.getTelefono(),
                            seleccionado.getMarca(),
                            seleccionado.getPlaca(),
                            seleccionado.getTipoVehiculo(),
                            serviciosList,
                            serviciosExtraColl,
                            repuestosColl
                    );

                    if (ruta != null && ruta.startsWith("ERROR:")) {
                        Notification.show("❌ Error al generar factura: " + ruta, 8000, Notification.Position.MIDDLE);
                    } else {
                        // Eliminar el vehículo seleccionado de la lista solo si la factura se generó correctamente
                        vehiculosEnTaller.remove(seleccionado);
                        grid.setItems(vehiculosEnTaller);
                        Notification.show("✅ Factura generada: " + ruta, 8000, Notification.Position.MIDDLE);
                    }
                } catch (Exception ex) {
                    Notification.show("❌ Error al generar la factura: " + ex.getMessage(), 8000, Notification.Position.MIDDLE);
                }
            });

            // --- Listener único del botón guardar ---
            botonGuardar.addClickListener(event -> {
                // Validación simple para cada uno de los campos del formulario para evitar cualquier error
                if (nombreCliente.isEmpty() || idCliente.isEmpty() || numeroCliente.isEmpty()
                        || servicios.getSelectedItems().isEmpty()
                        || marcaVehiculo.isEmpty() || tipoVehiculo.isEmpty()
                        || placaVehiculo.isEmpty() || fechaCita.isEmpty()) {
                    Notification.show("⚠️ Por favor completa todos los campos antes de continuar.", 3000, Notification.Position.MIDDLE);
                    return;
                }

                if (!(nombreCliente.getValue().matches("[a-zA-Z ]+"))) {
                    Notification.show("⚠️ Ingresa valor valido, sin simbolos ni caracteres especiales", 3000, Notification.Position.MIDDLE);
                    nombreCliente.clear();
                    return;
                }

                String idStr = String.valueOf(idCliente.getValue());
                if (idStr.length() < 8 || idStr.length() > 10){
                    Notification.show("⚠️ Ingresa un ID / cedula valido", 3000, Notification.Position.MIDDLE);
                    idCliente.clear();
                    return;
                }

                String numStr = String.valueOf(numeroCliente.getValue());
                if (numStr.length() < 8 || numStr.length() > 11){
                    Notification.show("⚠️ Ingresa un numero de tel / cel valido", 3000, Notification.Position.MIDDLE);
                    numeroCliente.clear();
                    return;
                }

                if (!(marcaVehiculo.getValue().matches("[a-zA-Z ]+"))){
                    Notification.show("⚠️ Ingresa un valor valido en la marca de vehiculo (solo letras)", 3000, Notification.Position.MIDDLE);
                    marcaVehiculo.clear();
                    return;
                }

                if (!(placaVehiculo.getValue().toUpperCase().matches("[A-Z]{3}[0-9]{3}"))){
                    Notification.show("⚠️ Ingresa un placa valida (ABC123) para seguir", 3000, Notification.Position.MIDDLE);
                    placaVehiculo.clear();
                    return;
                }

                // Construir las cadenas de servicios
                String serviciosSeleccionadosStr = String.join(", ", servicios.getSelectedItems());
                // Los servicios extra al crear la cita deben quedar vacíos hasta que se apliquen vía "Guardar servicio extra"
                Set<String> serviciosExtraSet = new LinkedHashSet<>();

                // Repuestos seleccionados para esta cita (formato: "Servicio - Repuesto")
                Set<String> repuestosSet = new LinkedHashSet<>(repuestosCombo.getSelectedItems());

                // Crear objeto Vehicle y añadirlo a la lista (guardar datos del cliente también)
                Vehicle nuevo = new Vehicle(
                        nombreCliente.getValue(),
                        String.valueOf(idCliente.getValue()),
                        String.valueOf(numeroCliente.getValue()),
                        placaVehiculo.getValue().toUpperCase(),
                        marcaVehiculo.getValue(),
                        tipoVehiculo.getValue(),
                        serviciosSeleccionadosStr,
                        serviciosExtraSet,
                        repuestosSet
                );
                vehiculosEnTaller.add(nuevo);
                grid.setItems(vehiculosEnTaller);

                // IA: Notificación de confirmación
                String citaInfo = String.format("Nombre: %s, ID: %s, Numero Cliente: %s, Servicios: %s, Marca: %s, Tipo: %s, Placa: %s, Fecha: %s",
                        nombreCliente.getValue(),
                        idCliente.getValue(),
                        numeroCliente.getValue().toString(),
                        serviciosSeleccionadosStr,
                        marcaVehiculo.getValue(),
                        tipoVehiculo.getValue(),
                        placaVehiculo.getValue(),
                        fechaCita.getValue()
                );

                Notification.show("✅ La cita ha sido asignada correctamente - Info. Cita: " + citaInfo, 8000, Notification.Position.MIDDLE);

                // IA: Limpieza opcional de campos
                nombreCliente.clear();
                idCliente.clear();
                numeroCliente.clear();
                servicios.clear();
                marcaVehiculo.clear();
                tipoVehiculo.clear();
                placaVehiculo.clear();
                fechaCita.clear();
                serviciosExtra.clear();
                repuestosCombo.clear();
             });

            // Agregamos al RightSide
            rightSide.add(tituloServicios, recuadroServicios, tituloVehiculos, grid, facturarButton);

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

    // POJO para representar un vehículo en la tabla
     private static class Vehicle {
         // Datos del cliente
         private final String nombreCliente;
         private final String cedulaCliente;
         private final String telefonoCliente;

         // Datos del vehículo
         private final String placa;
         private final String marca;
         private final String tipoVehiculo;
         private final String servicios; // servicios principales como cadena

         // ahora guardamos los servicios extra en un Set mutable para evitar duplicados
         private final Set<String> serviciosExtra;
         // Repuestos / insumos vinculados a este vehículo (formato: "Servicio - Repuesto")
         private final Set<String> repuestos;

        // Constructor completo
        public Vehicle(String nombreCliente, String cedulaCliente, String telefonoCliente,
                       String placa, String marca, String tipoVehiculo, String servicios,
                      Set<String> serviciosExtra, Set<String> repuestos) {
            this.nombreCliente = nombreCliente;
            this.cedulaCliente = cedulaCliente;
            this.telefonoCliente = telefonoCliente;
            this.placa = placa;
            this.marca = marca;
            this.tipoVehiculo = tipoVehiculo;
            this.servicios = servicios;
            this.serviciosExtra = serviciosExtra != null ? new LinkedHashSet<>(serviciosExtra) : new LinkedHashSet<>();
            this.repuestos = repuestos != null ? new LinkedHashSet<>(repuestos) : new LinkedHashSet<>();
        }

        public String getNombreCliente() { return nombreCliente; }
        public String getCedulaCliente() { return cedulaCliente; }
        public String getTelefono() { return telefonoCliente; }

        public String getPlaca() { return placa; }
        public String getMarca() { return marca; }
        public String getTipoVehiculo() { return tipoVehiculo; }
        public String getServicios() { return servicios; }

        public Set<String> getServiciosExtra() { return serviciosExtra; }
        public Set<String> getRepuestos() { return repuestos; }

        // Devuelve la lista de servicios principales (separados por coma) como lista
        public List<String> getServiciosList() {
            if (servicios == null || servicios.trim().isEmpty()) return Collections.emptyList();
            String[] parts = servicios.split(",\\s*");
            return Arrays.asList(parts);
        }

        // Añade nuevos servicios extra, evitando duplicados
        public void addServiciosExtra(Collection<String> extras) {
            if (extras == null || extras.isEmpty()) return;
            this.serviciosExtra.addAll(extras);
        }

        // Añade repuestos/insumos (formato: "Servicio - Repuesto")
        public void addRepuestos(Collection<String> items) {
            if (items == null || items.isEmpty()) return;
            this.repuestos.addAll(items);
        }
    }
}
