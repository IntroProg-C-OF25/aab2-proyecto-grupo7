import random

def sistema_control():
    opcion = 0
    ppl = 0
    pplN = []
    dias_visita_permitidos = [0] * 1000
    aleatorio = random.randint(1, 100)
    aleatorio2 = random.randint(1, 5)

    while opcion != 6:
        # Mostrar el menú de opciones
        print("--- Menú de Gestión de Delitos ---")
        print("1. Asignar delito a un PPL")
        print("2. Mostrar información de todos los PPLs")
        print("3. Registrar visita a un PPL")
        print("4. Registrar agravante para un PPL")
        print("5. Estadísticas de PPL")
        print("6. Salir")
        opcion = int(input("Seleccione una opción: "))

        if opcion == 1:
            ppl_info = []
            ppl_info.append(input("Nombre del PPL: "))
            ppl_info.append(input("Identificación del PPL: "))
            ppl_info.append(input("Crimen cometido del PPL: "))
            ppl_info.append(input("Fecha de ingreso (dd/mm/aaaa): "))
            ppl_info.append(input("Duración de la pena: "))
            ppl_info.append(input("Información del delito: "))
            respuesta = input("¿El PPL ha cometido alguna infracción? (si/no): ")
            if respuesta.lower() == "si":
                print(f"Fue asignado al área de aislamiento por {aleatorio} días")
            else:
                print("Tuvo buena conducta")
            pplN.append(ppl_info)
            ppl += 1

        elif opcion == 2:
            if ppl == 0:
                print("No se guardó información de ningún PPL")
            else:
                for i in range(ppl):
                    print(f"\nPPL #{i + 1}")
                    print(f"Nombre: {pplN[i][0]}")
                    print(f"Identificación: {pplN[i][1]}")
                    print(f"Crimen cometido: {pplN[i][2]}")
                    print(f"Fecha de ingreso: {pplN[i][3]}")
                    print(f"Duración de la pena: {pplN[i][4]}")
                    print(f"Información del delito: {pplN[i][5]}")

        elif opcion == 3:
            if ppl == 0:
                print("No tenemos PPL para visitas.")
            else:
                for i in range(ppl):
                    print(f"El PPL {i + 1} tiene un total de {aleatorio2} visitas.")

                visita = int(input("¿A qué PPL visitará? (Ingrese el número del PPL): "))

                if 1 <= visita <= ppl:
                    nVisita = int(input("¿Cuántas veces lo visitará?: "))

                    if nVisita <= aleatorio2:
                        tVisita = aleatorio2 - nVisita
                        print(f"Al PPL le queda un total de {tVisita} visitas.")
                    else:
                        print("Excedió el número de visitas disponibles.")
                else:
                    print("Ese PPL no está registrado.")

        elif opcion == 4:
            if ppl == 0:
                print("No hay PPL registrados para añadir agravante.")
            else:
                pplAgravante = int(input("¿A qué PPL desea registrar un agravante? (Ingrese el número del PPL): ")) - 1

                if 0 <= pplAgravante < ppl:
                    agravante = input("Describa el agravante: ")
                    aumentoPena = int(input("¿Cuántos días de aumento de pena desea aplicar?: "))
                    reduccionVisitas = int(input("¿Cuántos días de visita desea reducir?: "))

                    # Aplicar aumento de la pena
                    duracionPena = int(pplN[pplAgravante][4])
                    duracionPena += aumentoPena
                    pplN[pplAgravante][4] = str(duracionPena)

                    # Reducir días de visita permitidos
                    dias_visita_permitidos[pplAgravante] -= reduccionVisitas
                    if dias_visita_permitidos[pplAgravante] < 0:
                        dias_visita_permitidos[pplAgravante] = 0

                    print(f"Agravante registrado. Nueva duración de la pena: {duracionPena} días más.")
                    print(f"Nuevos días de visita permitidos: {dias_visita_permitidos[pplAgravante]}")
                else:
                    print("Ese PPL no está registrado.")

        elif opcion == 5:
            if ppl == 0:
                print("No hay PPL registrados para generar estadísticas.")
            else:
                totalDelitos = ppl
                totalDiasVisitaPermitidos = 0
                totalAumentoPena = 0
                totalReduccionVisitas = 0

                for i in range(ppl):
                    # Sumar días de visita permitidos
                    totalDiasVisitaPermitidos += dias_visita_permitidos[i]

                    # Sumar aumento de pena (si está registrado)
                    duracionPena = int(pplN[i][4])
                    totalAumentoPena += duracionPena

                    # Sumar reducción de visitas (si está registrada)
                    totalReduccionVisitas += (aleatorio2 - dias_visita_permitidos[i])

                # Calcular el promedio de días de visita permitidos
                promedioDiasVisita = totalDiasVisitaPermitidos / ppl

                # Mostrar estadísticas
                print("\n--- Estadísticas de PPL ---")
                print(f"Número total de delitos: {totalDelitos}")
                print(f"Promedio de días de visita permitidos: {promedioDiasVisita}")
                print(f"Total de días de aumento de pena aplicados: {totalAumentoPena}")
                print(f"Total de días de visita reducidos: {totalReduccionVisitas}")

        elif opcion == 6:
            print("Saliendo del sistema de PPL")
        else:
            print("Opción no válida. Intente de nuevo.")

if __name__ == "__main__":
    sistema_control()