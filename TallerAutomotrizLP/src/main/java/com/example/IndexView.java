package com.example;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class IndexView extends VerticalLayout {


    // Metodo constructor
    public IndexView() throws Exception {

        // Construccion principal de la pagina --> creacion de los VerticalLayouts y FormLayouts

        VerticalLayout main = new VerticalLayout();

        FormLayout hero = new FormLayout();
        hero.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1), new FormLayout.ResponsiveStep("600px", 2));

        // Invocamos la funcion constructora del Hero

        construirHero(hero);

        main.add(hero);

        add(main);

    }

    // Funciones creadoras

    // Construir el Hero, agregrara los componentes del encabezado inicial de la pagina
    public static void construirHero(FormLayout hero) throws Exception{
        try {

            VerticalLayout leftSide = new VerticalLayout();

            HorizontalLayout rightSide = new HorizontalLayout();

            // LeftSide --> Marca (titulo y logo)
            H1 nombreEmpresa = new H1("JAVA VIRTUAL MOTOR");

            nombreEmpresa.getStyle()
                    .set("font-family", "Momo Signature', cursive");

            // IA: Crear componente Image
            Image logoEmpresa = new Image("https://static.vecteezy.com/system/resources/thumbnails/047/097/062/small/black-and-white-silhouette-style-sports-car-logo-illustration-vector.jpg", "Logo de la empresa");

            // IA / Opcional: definir tamaño de la imagen (Logo)
            logoEmpresa.setWidth("400px");
            logoEmpresa.setHeight("200PX");

            // Agregamos los componentes al LeftSide

            leftSide.add(nombreEmpresa, logoEmpresa);

            // RightSide --> Menu (Menu Bar --> medios de contacto y Boton para solicitar Agendamiento de cita o reparacion)

            // IA: Menu Bar (Sobre nosotros y contactos)
            MenuBar menuBar = new MenuBar();
            menuBar.setOpenOnHover(true); // se despliega el submenú al pasar el mouse

            // IA / Opción 1: "Nosotros"
            MenuItem opcionNosotros = menuBar.addItem("Nosotros");
            opcionNosotros.addClickListener(e ->
                    Notification.show("Somos una empresa dedicada a brindar el mejor servicio a nuestros clientes por medio del cuidado de sus vehiculos con ayuda de nuestros profesionales en la mecanica alemana", 3000, Notification.Position.MIDDLE)
            );

            // IA / Opción 2: "Contáctanos" con submenú
            MenuItem opcionContactanos = menuBar.addItem("Contáctanos");

            // Submenú con información del teléfono
            opcionContactanos.getSubMenu().addItem("Teléfono: +57 312 345 6789");

            // Agregamoslos componentes al RightSide
            rightSide.add(menuBar, opcionNosotros, opcionContactanos);

            hero.add(leftSide, rightSide);

        } catch (Exception e) {
            throw new Exception("Error en la creacion del Hero " + e);
        }
    }

}
