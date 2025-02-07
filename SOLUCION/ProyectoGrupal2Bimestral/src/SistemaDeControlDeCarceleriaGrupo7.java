
/**
 *El objetivo del proyecto es desarrollar un sistema que gestione la lista de Personas
 * Privadas de la Libertad (PPL), los delitos cometidos por cada una de ellas y establecer
 * los días máximos de visitas permitidos. Además, ante la presencia de agravantes durante
 * el cumplimiento de la sentencia, se deberá aplicar un aumento de la pena y/o castigos
 * internos, lo que puede resultar en una reducción de los días de visita permitidos. Al
 * final, se generarán estadísticas con métricas importantes que permitirán la toma de
 * decisiones por parte de las autoridades penitenciarias.
 * Requerimientos Funcionales:
 *  Registro de Personas Privadas de la Libertad (PPL): Implementar un mecanismo
 * para registrar las PPL, incluyendo información como nombre, delitos cometidos
 * y fecha de ingreso.
 *  Gestión de Delitos: Permitir la asignación de delitos a cada PPL, permitiendo
 * registrar información detallada sobre el delito cometido y su gravedad.
 *  Control de Visitas: Establecer un límite máximo de días de visitas permitidos
 * para cada PPL y controlar el número de visitas recibidas por cada uno de ellos.
 *  Manejo de Agravantes: En caso de agravantes durante el cumplimiento de la
 * sentencia, aplicar un aumento de la pena y/o castigos internos a la PPL en
 * cuestión, lo que puede llevar a una reducción de los días de visita permitidos.
 *  Generación de Estadísticas: Desarrollar un mecanismo que genere estadísticas
 * sobre las PPL, incluyendo datos como número de delitos, días de visita
 * permitidos, aumento de la pena, entre otros. Estas estadísticas serán útiles para
 * las autoridades en la toma de decisiones.
 *
 * @author Ricardo Rosales, Daniela Briceño
 */
import java.util.Scanner;

public class SistemaDeControlDeCarceleriaGrupo7 {

    static int ppl = 0;
    static String[][] pplN = new String[1000][6];
    static int[] diasVisitaPermitidos = new int[1000];
    static Scanner an = new Scanner(System.in);

    public static void main(String[] args) {
        presentacion();
    }

    public static void presentacion() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Gestión de Delitos ---");
            System.out.println("1. Asignar delito a un PPL");
            System.out.println("2. Mostrar información de todos los PPLs");
            System.out.println("3. Registrar visita a un PPL");
            System.out.println("4. Registrar agravante para un PPL");
            System.out.println("5. Estadísticas de PPL");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(an.nextLine());

            switch (opcion) {
                case 1:
                    registroPPL();
                    break;
                case 2:
                    presentacionInformacion();
                    break;
                case 3:
                    visitas();
                    break;
                case 4:
                    agravantesPPL();
                    break;
                case 5:
                    presentarEstadisticas();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema de PPL...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    public static void registroPPL() {
        int aleatorio = (int) (Math.random() * 100) + 1;

        System.out.print("Nombre del PPL: ");
        pplN[ppl][0] = an.nextLine();
        System.out.print("Identificación del PPL: ");
        pplN[ppl][1] = an.nextLine();
        System.out.print("Crimen cometido del PPL: ");
        pplN[ppl][2] = an.nextLine();
        System.out.print("Fecha de ingreso (dd/mm/aaaa): ");
        pplN[ppl][3] = an.nextLine();
        System.out.print("Duración de la pena (en días): ");
        pplN[ppl][4] = an.nextLine();
        System.out.print("Información del delito: ");
        pplN[ppl][5] = an.nextLine();

        System.out.print("¿El PPL ha cometido alguna infracción? (si/no): ");
        String respuesta = an.nextLine();

        if (respuesta.equalsIgnoreCase("si")) {
            System.out.println("Fue asignado al área de aislamiento por " + aleatorio + " días.");
        } else {
            System.out.println("Tuvo buena conducta.");
        }

        ppl++;
    }

    public static void presentacionInformacion() {
        if (ppl == 0) {
            System.out.println("No se guardó información de ningún PPL.");
        } else {
            for (int i = 0; i < ppl; i++) {
                System.out.println("\nPPL #" + (i + 1));
                System.out.println("Nombre: " + pplN[i][0]);
                System.out.println("Identificación: " + pplN[i][1]);
                System.out.println("Crimen cometido: " + pplN[i][2]);
                System.out.println("Fecha de ingreso: " + pplN[i][3]);
                System.out.println("Duración de la pena: " + pplN[i][4]);
                System.out.println("Información del delito: " + pplN[i][5]);
            }
        }
    }

    public static void visitas() {
        int aleatorio2 = (int) (Math.random() * 5) + 1;

        if (ppl == 0) {
            System.out.println("No hay PPL registrados para visitas.");
            return;
        }

        for (int i = 0; i < ppl; i++) {
            System.out.println("El PPL " + (i + 1) + " tiene un total de " + aleatorio2 + " visitas.");
        }

        System.out.print("¿A qué PPL visitará? (Ingrese el número del PPL): ");
        int visita = Integer.parseInt(an.nextLine());

        if (visita >= 1 && visita <= ppl) {
            System.out.print("¿Cuántas veces lo visitará? ");
            int nVisita = Integer.parseInt(an.nextLine());

            if (nVisita <= aleatorio2) {
                System.out.println("Al PPL le queda un total de " + (aleatorio2 - nVisita) + " visitas.");
            } else {
                System.out.println("Excedió el número de visitas disponibles.");
            }
        } else {
            System.out.println("Ese PPL no está registrado.");
        }
    }

    public static void agravantesPPL() {
        if (ppl == 0) {
            System.out.println("No hay PPL registrados para añadir agravante.");
            return;
        }

        System.out.print("¿A qué PPL desea registrar un agravante? (Ingrese el número del PPL): ");
        int pplAgravante = Integer.parseInt(an.nextLine()) - 1; // Convertir a índice base 0

        if (pplAgravante >= 0 && pplAgravante < ppl) { // Verificar si el PPL es válido
            System.out.print("Describa el agravante: ");
            String agravante = an.nextLine();
            System.out.print("¿Cuántos días de aumento de pena desea aplicar?: ");
            int aumentoPena = Integer.parseInt(an.nextLine());
            System.out.print("¿Cuántos días de visita desea reducir?: ");
            int reduccionVisitas = Integer.parseInt(an.nextLine());

            // Aplicar aumento de la pena
            int duracionPena = Integer.parseInt(pplN[pplAgravante][4]);
            duracionPena += aumentoPena;
            pplN[pplAgravante][4] = String.valueOf(duracionPena);

            // Reducir días de visita permitidos
            diasVisitaPermitidos[pplAgravante] -= reduccionVisitas;
            if (diasVisitaPermitidos[pplAgravante] < 0) {
                diasVisitaPermitidos[pplAgravante] = 0;
            }

            System.out.println("Agravante registrado. Nueva duración de la pena: " + duracionPena + " días más.");
            System.out.println("Nuevos días de visita permitidos: " + diasVisitaPermitidos[pplAgravante]);
        } else {
            System.out.println("Ese PPL no está registrado.");
        }
    }

    public static void presentarEstadisticas() {
        if (ppl == 0) {
            System.out.println("No hay PPL registrados para generar estadísticas.");
            return;
        }

        int totalDiasVisitaPermitidos = 0;
        int totalAumentoPena = 0;

        for (int i = 0; i < ppl; i++) {
            totalDiasVisitaPermitidos += diasVisitaPermitidos[i];

            // Sumar aumento de pena
            int duracionPena = Integer.parseInt(pplN[i][4].trim());
            totalAumentoPena += duracionPena;
        }

        double promedioDiasVisita = (double) totalDiasVisitaPermitidos / ppl;

        System.out.println("\n--- Estadísticas de PPL ---");
        System.out.println("Número total de PPL registrados: " + ppl);
        System.out.println("Promedio de días de visita permitidos: " + promedioDiasVisita);
        System.out.println("Total de días de aumento de pena aplicados: " + totalAumentoPena);
    }
}
