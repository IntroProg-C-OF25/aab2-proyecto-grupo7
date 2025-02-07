import random

def main():
    presentacion()

def presentacion():
    ppl_list = []
    dias_visita_permitidos = {}
    
    while True:
        print("\n--- Menú de Gestión de Delitos ---")
        print("1. Asignar delito a un PPL")
        print("2. Mostrar información de todos los PPLs")
        print("3. Registrar visita a un PPL")
        print("4. Registrar agravante para un PPL")
        print("5. Estadísticas de PPL")
        print("6. Salir")
        opcion = input("Seleccione una opción: ")

        if opcion == '1':
            registro_ppl(ppl_list, dias_visita_permitidos)
        elif opcion == '2':
            presentacion_informacion(ppl_list)
        elif opcion == '3':
            visitas(ppl_list, dias_visita_permitidos)
        elif opcion == '4':
            agravantes_ppl(ppl_list, dias_visita_permitidos)
        elif opcion == '5':
            presentar_estadisticas(ppl_list, dias_visita_permitidos)
        elif opcion == '6':
            print("Saliendo del sistema de PPL...")
            break
        else:
            print("Opción no válida. Intente de nuevo.")

def registro_ppl(ppl_list, dias_visita_permitidos):
    ppl = {}
    ppl['nombre'] = input("Nombre del PPL: ")
    ppl['id'] = input("Identificación del PPL: ")
    ppl['crimen'] = input("Crimen cometido: ")
    ppl['fecha_ingreso'] = input("Fecha de ingreso (dd/mm/aaaa): ")
    ppl['duracion_pena'] = int(input("Duración de la pena (en días): "))
    ppl['info_delito'] = input("Información del delito: ")
    
    respuesta = input("¿El PPL ha cometido alguna infracción? (si/no): ")
    if respuesta.lower() == 'si':
        dias_aislamiento = random.randint(1, 100)
        print(f"Fue asignado al área de aislamiento por {dias_aislamiento} días.")
    else:
        print("Tuvo buena conducta.")
    
    ppl_list.append(ppl)
    dias_visita_permitidos[ppl['id']] = random.randint(1, 5)

def presentacion_informacion(ppl_list):
    if not ppl_list:
        print("No hay información de ningún PPL.")
    else:
        for i, ppl in enumerate(ppl_list, start=1):
            print(f"\nPPL #{i}")
            for key, value in ppl.items():
                print(f"{key.capitalize()}: {value}")

def visitas(ppl_list, dias_visita_permitidos):
    if not ppl_list:
        print("No hay PPL registrados para visitas.")
        return
    
    for i, ppl in enumerate(ppl_list, start=1):
        print(f"PPL #{i} ({ppl['nombre']}) tiene {dias_visita_permitidos[ppl['id']]} visitas disponibles.")
    
    id_ppl = input("Ingrese la identificación del PPL a visitar: ")
    if id_ppl in dias_visita_permitidos:
        n_visitas = int(input("¿Cuántas visitas hará?: "))
        if n_visitas <= dias_visita_permitidos[id_ppl]:
            dias_visita_permitidos[id_ppl] -= n_visitas
            print(f"Al PPL le quedan {dias_visita_permitidos[id_ppl]} visitas.")
        else:
            print("Excedió el número de visitas disponibles.")
    else:
        print("Ese PPL no está registrado.")

def agravantes_ppl(ppl_list, dias_visita_permitidos):
    if not ppl_list:
        print("No hay PPL registrados para añadir agravante.")
        return
    
    id_ppl = input("Ingrese la identificación del PPL para registrar un agravante: ")
    for ppl in ppl_list:
        if ppl['id'] == id_ppl:
            agravante = input("Describa el agravante: ")
            aumento_pena = int(input("¿Cuántos días de aumento de pena?: "))
            reduccion_visitas = int(input("¿Cuántos días de visita desea reducir?: "))
            
            ppl['duracion_pena'] += aumento_pena
            dias_visita_permitidos[id_ppl] = max(0, dias_visita_permitidos[id_ppl] - reduccion_visitas)
            
            print(f"Agravante registrado. Nueva duración de la pena: {ppl['duracion_pena']} días.")
            print(f"Nuevos días de visita permitidos: {dias_visita_permitidos[id_ppl]}")
            return
    print("Ese PPL no está registrado.")

def presentar_estadisticas(ppl_list, dias_visita_permitidos):
    if not ppl_list:
        print("No hay PPL registrados para generar estadísticas.")
        return
    
    total_ppl = len(ppl_list)
    total_pena = sum(ppl['duracion_pena'] for ppl in ppl_list)
    total_visitas = sum(dias_visita_permitidos.values())
    promedio_visitas = total_visitas / total_ppl if total_ppl > 0 else 0
    
    print("\n--- Estadísticas de PPL ---")
    print(f"Número total de PPL registrados: {total_ppl}")
    print(f"Promedio de días de visita permitidos: {promedio_visitas:.2f}")
    print(f"Total de días de aumento de pena aplicados: {total_pena}")

if __name__ == "__main__":
    main()
