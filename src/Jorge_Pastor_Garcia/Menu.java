package Jorge_Pastor_Garcia;

import java.text.ParseException;

import javax.swing.JOptionPane;

public class Menu {
	
	//Creo una clase menú para un mejor orden en el programa
	public void menu() throws ParseException{
		
		Aplicacion app = new Aplicacion();
		
		app.Inicializar_Lista();			//Cargo los datos del fichero Parking.txt con todos los vehículos en el parking
		app.Inicializar_Dinero_Acumulado();	//Cargo los datos del fichero Dinero_Acumulado.txt con el dinero recaudado 
		app.Inicializar_Coste_Minuto();		//Cargo el fichero Coste_Minuto.txt para inicializar el coste por minuto del parking
		
	try {
	try {		
				
		char opcion = '0';
		
			
			JOptionPane.showMessageDialog(null, "Bienvenido al sistema de parking automático\n\nA continuación elija una de las siguientes opciones.\n\nGracias.");
	
			
		do {
				
			opcion= JOptionPane.showInputDialog("1) Introducir vehículo.\n2) Mostrar por matrícula.\n3) Retirar vehículo.\n4) Mostrar lista de vehículos.\n5) Cambiar coste por minuto.\n6) Ver dinero acumulado.\n7) Salir de la aplicación.").charAt(0);
	
			switch(opcion) {
			
			case '1':
				
				app.IntroducirVehiculo();
				app.Actualizar_Fichero_Parking();
				
				break;
				
			case '2':
				
				app.Mostrar_Vehiculo_Matricula();
				app.Actualizar_Fichero_Parking();		//Actualizo el fichero Parking.txt con el coche nuevo registrado
				app.Calcular_Dinero_Acumulado();
				
				break;
				
			case '3':
				
				app.RetirarVehiculo();
				app.Actualizar_Fichero_Parking();		//Actualizo el fichero Parking.txt con el coche nuevo registrado
				app.Calcular_Dinero_Acumulado();
				
				break;
					
			case '4':				
				
				app.MostrarLista();
				app.Actualizar_Fichero_Parking();
				
				break;
				
			case '5':
				
				app.Cambiar_Coste_Minuto();
				app.Escribir_Coste_Minuto();
							
				break;
				
			case '6':
				
				app.Mostrar_Dinero_Acumulado();
				
				break;
				
			case '7':
				
				JOptionPane.showMessageDialog(null,"Usted ha salido de la aplicación.\nGracias por usar nuestros servicios.\n\nEsperamos verle pornto de nuevo.");
				app.Actualizar_Fichero_Parking();
				System.exit(0);
				
				break;
				
			default: JOptionPane.showMessageDialog(null, "Opcion incorrecta.\n\nIntentelo de nuevo.");
			}
		
		}while( opcion !='7');
		
	}catch(java.lang.NullPointerException exception) {}
	}catch(java.lang.StringIndexOutOfBoundsException exception) {}
	}
}
