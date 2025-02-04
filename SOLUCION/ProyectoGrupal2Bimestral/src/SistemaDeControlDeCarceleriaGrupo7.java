
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

    public static void main(String[] args) {
        sistemaControl();
    }

    public static void sistemaControl() {
        int opcion, ppl = 0, nVisita, tVisita = 0, pplAgravante, aumentoPena, reduccionVisitas, duracionPena, visita;
        String agravante;
        int aleatorio = (int) (Math.random() * 100) + 1;
        int aleatorio2 = (int) (Math.random() * 5) + 1;

        int[] diasVisitaPermitidos = new int[1000];
        String[][] pplN = new String[1000][6];
        String respuesta;
        Scanner an = new Scanner(System.in);
        do {

            // Mostrar el menú de opciones
            System.out.println("--- Menú de Gestión de Delitos ---");
            System.out.println("1. Asignar delito a un PPL");
            System.out.println("2. Mostrar información de todos los PPLs");
            System.out.println("3. Registrar visita a un PPL");
            System.out.println("4. Registrar agravante para un PPL");
            System.out.println("5. Estadisticas de PPL");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(an.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del PPL: ");
                    pplN[ppl][0] = an.nextLine();
                    System.out.print("Identificación del PPL: ");
                    pplN[ppl][1] = an.nextLine();
                    System.out.print("Crimen cometido del PPL: ");
                    pplN[ppl][2] = an.nextLine();
                    System.out.print("Fecha de ingreso (dd/mm/aaaa): ");
                    pplN[ppl][3] = an.nextLine();
                    System.out.print("Duracion de la pena: ");
                    pplN[ppl][4] = an.nextLine();
                    System.out.print("Informacion del delito: ");
                    pplN[ppl][5] = an.nextLine();
                    System.out.print("¿El ppl ha cometido alguna infraccion? ");
                    respuesta = an.nextLine();
                    if (respuesta.equalsIgnoreCase("si")) {
                        System.out.println("Fue asignado al area de aislamiento por " + aleatorio + " dias");
                    } else {
                        System.out.println("Tuvo buena conducta");
                    }
                    ppl++;
                    break;
                case 2:
                    if (ppl == 0) {
                        System.out.println("No se guardo informacion de ningun PPL");
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
                    break;
                case 3:
                    if (ppl == 0) {
                        System.out.println("No tenemos PPL para visitas.");
                    } else {

                        for (int i = 0; i < ppl; i++) {
                            System.out.println("El PPL " + (i + 1) + " tiene un total de " + (aleatorio2) + " visitas.");
                        }

                        System.out.println("¿A qué PPL visitará? (Ingrese el número del PPL):");
                        visita = Integer.parseInt(an.nextLine());

                        if (visita >= 1 && visita <= ppl) {
                            System.out.println("¿Cuántas veces lo visitará?");
                            nVisita = Integer.parseInt(an.nextLine());

                            if (nVisita <= aleatorio2) {
                                tVisita = aleatorio2 - nVisita;
                                System.out.println("Al PPL le queda un total de " + tVisita + " visitas.");
                            } else {
                                System.out.println("Excedió el número de visitas disponibles.");
                            }
                        } else {
                            System.out.println("Ese PPL no está registrado.");
                        }
                    }
                    break;
                case 4:
                    if (ppl == 0) {
                        System.out.println("No hay PPL registrados para añadir agravante.");
                    } else {
                        System.out.println("¿A qué PPL desea registrar un agravante? (Ingrese el número del PPL)");
                        pplAgravante = Integer.parseInt(an.nextLine()) - 1; // Convertir a índice base 0

                        if (pplAgravante >= 0 && pplAgravante < ppl) { // Verificar si el PPL es válido
                            System.out.println("Describa el agravante:");
                            agravante = an.nextLine();
                            System.out.println("¿Cuántos días de aumento de pena desea aplicar?");
                            aumentoPena = Integer.parseInt(an.nextLine());
                            System.out.println("¿Cuántos días de visita desea reducir?");
                            reduccionVisitas = Integer.parseInt(an.nextLine());

                            // Aplicar aumento de la pena
                            duracionPena = Integer.parseInt(pplN[pplAgravante][4]);
                            duracionPena += aumentoPena;
                            pplN[pplAgravante][4] = String.valueOf(duracionPena);

                            // Reducir días de visita permitidos
                            diasVisitaPermitidos[pplAgravante] -= reduccionVisitas;
                            if (diasVisitaPermitidos[pplAgravante] < 0) {
                                diasVisitaPermitidos[pplAgravante] = 0;
                            }

                            System.out.println("Agravante registrado. Nueva duración de la pena: " + duracionPena + " dias más.");
                            System.out.println("Nuevos días de visita permitidos: " + diasVisitaPermitidos[pplAgravante]);
                        } else {
                            System.out.println("Ese PPL no está registrado.");
                        }
                    }
                    break;

                case 5:
                    if (ppl == 0) {
                        System.out.println("No hay PPL registrados para generar estadísticas.");
                    } else {
                        int totalDelitos = ppl;
                        int totalDiasVisitaPermitidos = 0;
                        int totalAumentoPena = 0;
                        int totalReduccionVisitas = 0;

                        for (int i = 0; i < ppl; i++) {
                            // Sumar días de visita permitidos
                            totalDiasVisitaPermitidos += diasVisitaPermitidos[i];

                            // Sumar aumento de pena (si está registrado)
                            String duracionPenaStr = pplN[i][4].trim(); // Eliminar espacios
                            duracionPena = Integer.parseInt(duracionPenaStr);
                            totalAumentoPena += duracionPena; // Sumar la duración de la pena

                            // Sumar reducción de visitas (si está registrada)
                            totalReduccionVisitas += (aleatorio2 - diasVisitaPermitidos[i]);
                        }

                        // Calcular el promedio de días de visita permitidos
                        double promedioDiasVisita = (double) totalDiasVisitaPermitidos / ppl;

                        // Mostrar estadísticas
                        System.out.println("\n--- Estadísticas de PPL ---");
                        System.out.println("Número total de delitos: " + totalDelitos);
                        System.out.println("Promedio de días de visita permitidos: " + promedioDiasVisita);
                        System.out.println("Total de días de aumento de pena aplicados: " + totalAumentoPena);
                        System.out.println("Total de días de visita reducidos: " + totalReduccionVisitas);
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del sistema de PPL");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

        } while (opcion != 6);
    }
}
/*
run:
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 1
Nombre del PPL: Ricardo Rosales
Identificación del PPL: 123
Crimen cometido del PPL: robo
Fecha de ingreso (dd/mm/aaaa): 11 02 2023
Duracion de la pena: 5
Informacion del delito: robo a mano armada
¿El ppl ha cometido alguna infraccion? si
Fue asignado al area de aislamiento por 19 dias
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 1
Nombre del PPL: Jorge Andres 
Identificación del PPL: 213
Crimen cometido del PPL: Falsificacion
Fecha de ingreso (dd/mm/aaaa): 05 03 2023
Duracion de la pena: 1
Informacion del delito: Falsificacion
¿El ppl ha cometido alguna infraccion? si
Fue asignado al area de aislamiento por 19 dias
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 1
Nombre del PPL: Andres Vallejo
Identificación del PPL: 321
Crimen cometido del PPL: Acoso Sexual
Fecha de ingreso (dd/mm/aaaa): 25 06 2025
Duracion de la pena: 10
Informacion del delito: Acoso a menores 
¿El ppl ha cometido alguna infraccion? no
Tuvo buena conducta
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 2

PPL #1
Nombre: Ricardo Rosales
Identificación: 123
Crimen cometido: robo
Fecha de ingreso: 11 02 2023
Duración de la pena: 5
Información del delito: robo a mano armada

PPL #2
Nombre: Jorge Andres 
Identificación: 213
Crimen cometido: Falsificacion
Fecha de ingreso: 05 03 2023
Duración de la pena: 1
Información del delito: Falsificacion

PPL #3
Nombre: Andres Vallejo
Identificación: 321
Crimen cometido: Acoso Sexual
Fecha de ingreso: 25 06 2025
Duración de la pena: 10
Información del delito: Acoso a menores 
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 3
El PPL 1 tiene un total de 5 visitas.
El PPL 2 tiene un total de 5 visitas.
El PPL 3 tiene un total de 5 visitas.
¿A qué PPL visitará? (Ingrese el número del PPL):
11
Ese PPL no está registrado.
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 4
¿A qué PPL desea registrar un agravante? (Ingrese el número del PPL)
3
Describa el agravante:
pelea
¿Cuántos días de aumento de pena desea aplicar?
5
¿Cuántos días de visita desea reducir?
3
Agravante registrado. Nueva duración de la pena: 15 años más.
Nuevos días de visita permitidos: 0
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 5

--- Estadísticas de PPL ---
Número total de delitos: 3
Promedio de días de visita permitidos: 0.0
Total de días de aumento de pena aplicados: 21
Total de días de visita reducidos: 15
--- Menú de Gestión de Delitos ---
1. Asignar delito a un PPL
2. Mostrar información de todos los PPLs
3. Registrar visita a un PPL
4. Registrar agravante para un PPL
5. Estadisticas de PPL
6. Salir
Seleccione una opción: 6
Saliendo del sistema de PPL
*/