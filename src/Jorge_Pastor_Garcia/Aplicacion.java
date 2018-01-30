package Jorge_Pastor_Garcia;

import java.util.*;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class Aplicacion {
	
	//Atributos	
	private String Matricula, Modelo, Color, Titular;			//Variables que le pasamos a la clase coche para inicializar sus atributos
	private float coste_minuto;					//Coste que cuesta estacionar un coche por minuto. Será modificado por la función Cambiar_Coste_Minuto() 
	private Vector <Coche> Listado_Coche  = new  Vector<Coche>(1, 1);	//Vector dinámico en el cual guardaremos los coches registrados
	private static int numero_plazas = 10;	//Plazas de mi parking
	private float dinero_acumulado;			//Guarda el dinero acumulado de la devolución de los coches. Se carga desde un fichero y se resetea desde la aplicación
	private float dinero_pagar;				//Variable calcuar el precio del estacionamiento.
	//Metodos
	
	public void IntroducirVehiculo(){
		
		Coche coche= new Coche();					//Se crea un objeto coche para poder acceder a sus métodos y atributos desde la aplicación.								
		Calendar date = Calendar.getInstance();		//Calendarproporciona un método de clase y getInstancepara obtener un objeto útil de este tipo.
	    int cont = 0;						//Flag para indicar si el coche a registrar ya está registrado
	    
		try {
			
			if(Listado_Coche.size()<numero_plazas) {
				
				do {
					
						Matricula = JOptionPane.showInputDialog("Introduzca la matrícula de su vehículo.").toUpperCase();	//Pido matrícula 
						coche.setMatricula(Matricula);	//Inicializo la matricula del objeto coche
						
						//Hago obligatorio introducir el campo matricula
						if(Matricula.length() == 0) {	
							
							JOptionPane.showMessageDialog(null, "Campo obligatorio.");

						}
						
						//Compruebo que la matricula introducida no esté registrada
						for(int i=0; i<Listado_Coche.size(); i++){
							
							if(Matricula.equals(Listado_Coche.elementAt(i).getMatricula())==true) {
								
								cont++;
								JOptionPane.showMessageDialog(null, "La matricula ya estaba registrada previamente.");
							}else {
								
								cont = 0;
							}
						}				
					
				}while(Matricula.length()==0 || cont!=0);		//Si todo está correcto continuo con el resto de campos del objeto coche
						
				Modelo = JOptionPane.showInputDialog("Introduzca el modelo de su vehículo.").toUpperCase();		//Pido modelo
				coche.setModelo(Modelo);		//Inicializo modelo en el objeto
						
				Color = JOptionPane.showInputDialog("Introduzca el color del vehículo.").toUpperCase();		//Pido color
				coche.setColor(Color);			//Inicializo color en el objeto
						
				Titular = JOptionPane.showInputDialog("Introduzca el titular del vehículo.").toUpperCase();	//Pido titular	
				coche.setTitular(Titular);		//Inicializo titular en el objeto

				//Registo la fecha de entrada automaticamente e inicializo el atributo fecha del objeto coche
				SimpleDateFormat Fecha_Actual = new SimpleDateFormat("dd:MM:yyyy");
				String Fecha = String.valueOf(Fecha_Actual.format(date.getTime()));		 
			    coche.setFecha(Fecha);
			    
				//Registo la hora de entrada automaticamente e inicializo el atributo hora del objeto coche			     
			    SimpleDateFormat Hora_Actual = new SimpleDateFormat("HH.mm");
			    String Hora = String.valueOf(Hora_Actual.format(date.getTime()));
			    coche.setHora(Hora);	
		     		
			    Listado_Coche.addElement(coche);	//Guardo el objeto coche en el vector dinámico de coches		    
			    
		
			}else JOptionPane.showMessageDialog(null, "Ya no queda espacio en el parking.\n\nDisculpe las molestias.");
			
		}catch(java.lang.NullPointerException exception) {}		//Interrupcion para que si se sale del método JOptionPane no salga del programa
		//Actualizar_Fichero_Parking();					
	}
	
	public void Mostrar_Vehiculo_Matricula() throws java.text.ParseException{		//Busco vehículos por matrícula
		
		String Matricula_Buscada, Matricula_Recogida = null;		//Variables para validar si la matricula buscada sta registrada
		char respuesta;
		String Matricula;
		int cont=0;
		
	
		try {	//Interrupcion para que si se sale del método JOptionPane no salga del programa
		
		//Compruebo que haya coches en el parking
		if(Listado_Coche.size()==0) {
			
			JOptionPane.showMessageDialog(null, "No hay vehículos disponibles que mostrar.");
			
		}else {
		
			Matricula_Buscada = JOptionPane.showInputDialog("Introduzca la matricula que desea buscar").toUpperCase();	//Matrícula a buscar
			
			
		//Busco la matrícula introducida en el vector donde guardo los coches	
		for(int i=0; i<Listado_Coche.size(); i++) {
			
			Matricula_Recogida = Listado_Coche.elementAt(i).getMatricula();
			
			//Compruebo matricula a matricula del vector
			if(Matricula_Buscada.equals(Matricula_Recogida)==true) {
				
				cont=i;	//Guardo la posicion donde se encuentra el coche buscado 					
			}
			
		}
		
		Matricula_Recogida = Listado_Coche.elementAt(cont).getMatricula();	//Recupero la matricula encontrada 
		
		if(Matricula_Buscada.equals(Matricula_Recogida)==false){
			
			JOptionPane.showMessageDialog(null, "La matricula que esta buscando no corresponde a ningun vehículo.");
		}
		
		//Si la he recuperado muestro los datos y solicito la posibilidad de retirar el coche
		//Si no vuelvo al menu principal
		if(Matricula_Buscada.equals(Matricula_Recogida) == true) {
				
			//Muestro los datos solicitados
			JOptionPane.showMessageDialog(null, "Los datos del coche seleccionado se mostrarán por consola.");
			
			System.out.println("***LOS DATOS DE LA BUSQUEDA SON***\n");
			System.out.println("Matricula: " + Listado_Coche.elementAt(cont).getMatricula()); 
			System.out.println("Modelo: " + Listado_Coche.elementAt(cont).getModelo());
			System.out.println("Color: " + Listado_Coche.elementAt(cont).getColor());
			System.out.println("Titular: " + Listado_Coche.elementAt(cont).getTitular());
			System.out.println("Fecha de entrada: " + Listado_Coche.elementAt(cont).getFecha());
			System.out.println("Hora de entrada: " + Listado_Coche.elementAt(cont).getHora()+"\n");
			
			//Solicito retirar el vehículo
			do {
				
				respuesta = JOptionPane.showInputDialog("Por favor, pulse S si desea retirar el vehículo.\nPulse N si desea permanecer estacionado.").charAt(0);
				
				if(respuesta == 'S' || respuesta == 's') {
					
					Calcular_Precio(cont);		//Si lo retiro calculo el precio del estacionamiento
					Listado_Coche.remove(cont);	//Borro el coche del parking
					JOptionPane.showMessageDialog(null, "Vehículo retirado con exito.");
					Actualizar_Fichero_Parking();
					
				}else if(respuesta == 'N' || respuesta == 'n') {	//Vuelvo al menú principal sin realizar ninguna otra acción
					
					JOptionPane.showMessageDialog(null, "Usted ha decidido no retirar el vehículo.");
					
				}
				
			}while(respuesta != 'S' && respuesta != 'N' && respuesta != 's' && respuesta != 'n');		
		}
		}
		}catch(java.lang.NullPointerException exception) {}	
		
		//Actualizar_Fichero_Parking();		
	}
	
	public void RetirarVehiculo() throws java.text.ParseException{		//Muestra todos los coches de la aplicación y da la opcion de retirarlos por 
	
		int numero_coche;	//Guarda el numero de coche a retirar	
		int cont;
				
		try {		
			if(Listado_Coche.size()>0) {
				
				MostrarLista();		//Muestra la lista de todos los coches registrados 
				
				try {			
					do {
						
						
						numero_coche = (Integer.parseInt(JOptionPane.showInputDialog("Seleccione el numero del coche que desea retirar."))-1);
						cont = numero_coche;
						
						Calcular_Precio(cont);	//Calcula el precio de estacionamiento 
						Listado_Coche.remove(numero_coche);	//Borra el coche que se ha retirado
						JOptionPane.showMessageDialog(null, "Vehículo retirado con exito.");										
						
					}while(numero_coche>=Listado_Coche.size()+1); //Controlo que el numero de coche seleccionado no sea mayor que el numero de coches del parking
					
					Actualizar_Fichero_Parking();
					
				}catch(java.lang.NumberFormatException exception) {}
				
			}else JOptionPane.showMessageDialog(null, "No hay vehículos disponibles para mostrar.");
			
		}catch(java.lang.ArrayIndexOutOfBoundsException exception){ 
			
			JOptionPane.showMessageDialog(null, "Ese numero de coche no se corresponde con ninguno de nuestro registro.");
		}	
		//Actualizar_Fichero_Parking();		
	}
	
	public void MostrarLista() {
		
		if(Listado_Coche.size()!=0){
			
		JOptionPane.showMessageDialog(null, "Los datos de todos los vehículos se mostraran en la consola");
		System.out.println("***REGISTRO DE VEHICULOS ALMACENADOS***");
		
		//Recorro el vector con todos los coches e imprimo sus cractersticas
		for(int i=0; i<Listado_Coche.size(); i++) 
		{
			System.out.println("\nCOCHE NUMERO " +(i+1));
			System.out.println("\n\tMatricula: " + Listado_Coche.elementAt(i).getMatricula()); 
			System.out.println("\tModelo: " + Listado_Coche.elementAt(i).getModelo());
			System.out.println("\tColor: " + Listado_Coche.elementAt(i).getColor());
			System.out.println("\tTitular: " + Listado_Coche.elementAt(i).getTitular());
			System.out.println("\tFecha de entrada: " + Listado_Coche.elementAt(i).getFecha());
			System.out.println("\tHora de entrada: " + Listado_Coche.elementAt(i).getHora()+ "\n");
		}
		}else JOptionPane.showMessageDialog(null, "No hay ningun vehículo para mostrar.");
		
	}
	
	public float Cambiar_Coste_Minuto() {
		
		try {
		try {
			
			//Asigno un coste por minuto que posteriormente escribiré en un fichero para que se inicialice siempre con el valor de seleccionado anteriormente hasta que se desee cambiar
			coste_minuto = Float.parseFloat(JOptionPane.showInputDialog("El coste por minuto es de "+coste_minuto+" Euros.\nEstablezca un nuevo coste por minuto si lo desea."));
			JOptionPane.showMessageDialog(null, "El nuevo coste por minuto es de "+coste_minuto+" Euros.");
			
		}catch(java.lang.NumberFormatException exception) {}	
		}catch(java.lang.NullPointerException exception) {}	

		return coste_minuto;
	}
	
	public void Calcular_Precio(int cont) throws java.text.ParseException{

		Date Fecha_Entrada = null;	//Creo un tipo date para recuperar la fecha de entrada
		Date Fecha_Salida = null;	//Cuando entro en esta función creo un tipo date registrando la hora de salida
		
		Calendar date = Calendar.getInstance();
		
		String dia_entrada1 = Listado_Coche.elementAt(cont).getFecha();	//Recupero la fecha de entrada en forma de string
        String hora_entrada1 = Listado_Coche.elementAt(cont).getHora();	//Recupero la hora de entrada en formato string
        String entrada = dia_entrada1 +" "+hora_entrada1;	//Junto fecha y hora en un solo string
		
        
        SimpleDateFormat fecha_entrada = new SimpleDateFormat("dd:MM:yyyy HH.mm");	//Defino el formato de la fecha de entrada
        Fecha_Entrada = fecha_entrada.parse(entrada);	//Convierto el string fecha de entrada a tipo date
        Fecha_Salida = date.getTime();					//Registro la hora de salida
	
		long tiempo_ms = Fecha_Salida.getTime() - Fecha_Entrada.getTime();	//La fecha de salida menos la de entrada es el tiemo que ha permanecido en el parking
        long minutos = tiempo_ms/(1000*60);			//Paso los milisegundos que ha estado en elparking a minutos
	
		dinero_pagar = coste_minuto * minutos;	//Calculo el dinero que ha costado el estacionamiento
		
		JOptionPane.showMessageDialog(null , "Usted ha estado estacionado "+minutos+" minutos.\n\nEl dinero que debe desembolsar es de "+dinero_pagar+" Euros.");
		
	}
		
	public void Actualizar_Fichero_Parking(){
		
		String ruta = "Parking.txt";	//Ruta del fichero de texto
		File fichero = new File(ruta);
						
		try {
			
		Coche coche= new Coche();
		FileWriter fichero1 = new FileWriter("Parking.txt", false);
		BufferedWriter bw = new BufferedWriter(fichero1);	
						
		if(fichero.exists()) {
			
			for(int i=0; i<Listado_Coche.size(); i++) {
				
			if(Listado_Coche.elementAt(i) != null) {
				
				//Escribo el vector de clase coche en el fichero
				bw.write(Listado_Coche.elementAt(i).getMatricula()+"; "+Listado_Coche.elementAt(i).getModelo()+"; "+Listado_Coche.elementAt(i).getColor()+"; "+Listado_Coche.elementAt(i).getTitular()+"; "+Listado_Coche.elementAt(i).getFecha()+"; "+Listado_Coche.elementAt(i).getHora()+"\r");
				bw.write("\n");
			}
			
			}
			
			bw.close();
		}
		
	
		}catch(IOException exception) {}		
	}
	
	public void Inicializar_Lista() {
		
		
		String Matricula= null, Modelo=null, Color=null, Titular=null, Fecha=null, Hora=null;	
		
		try {

            File f = new File("Parking.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";
            char punto_coma = ';';
            int cont1=0, cont2=0, cont3=0,cont4=0, cont5=0;
    		int contador=0;

    		//Lee el fichero linea a linea
            while ((readLine = b.readLine()) != null) {            	
               
            	Coche coche = new Coche();
                //Recorro una linea y caracter a caracter del fichero y cada vez que encuentra un punto y coma guarda enun contador
            	//Otro contador guarda la posicion del string en la que esta el punto y coma
                for(int i=0; i<readLine.length(); i++) {                	
                	
                	if(readLine.charAt(i) == punto_coma && contador == 0) {
                		
                		cont1 = i;
                		contador++;  
                		
                	}else if(readLine.charAt(i) == punto_coma && contador == 1) {
                    		
                    		cont2 = i;
                    		contador++; 

                    		
                	}else if(readLine.charAt(i) == punto_coma && contador == 2) {
                		
                		cont3 = i;
                		contador++;
                		
                	}else if(readLine.charAt(i) == punto_coma && contador == 3) {
                		
                		cont4 = i;
                		contador++; 
                		
                	}else if(readLine.charAt(i) == punto_coma && contador == 4) {
                		
                		cont5 = i;
                		contador++;                 		
                	}  
                	               	
                	Matricula=null; Modelo=null; Color = null; Titular = null; Fecha = null; Hora=null;
                }
                
                
                //Matricula=null; Modelo=null; Color = null; Titular = null; Fecha = null; Hora=null;
            	//Teniendo en cuenta las posiciones de los puntos y coma, inicializo el vector de coches con los datos del fichero
                Matricula = readLine.substring(0,cont1);
                coche.setMatricula(Matricula);

        		Modelo = readLine.substring(cont1+2, cont2);
        		coche.setModelo(Modelo);


        		Color= readLine.substring(cont2+2, cont3);
        		coche.setColor(Color);


        		Titular = readLine.substring(cont3+2, cont4);
        		coche.setTitular(Titular);


        		Fecha = readLine.substring(cont4+2, cont5);
        		coche.setFecha(Fecha);


        		Hora = readLine.substring(cont5+2, readLine.length());
        		coche.setHora(Hora);

                cont1=0; cont2=0; cont3=0; cont4=0; cont5=0; contador=0;
        		Listado_Coche.addElement(coche);	//Guardo el fichero en la clase vector 
        		coche = null;
        		      		                
            
        } 
		}catch (IOException e) {
            e.printStackTrace();
        }	
		    
	}

	public void Escribir_Dinero_Acumulado() {
		
		String ruta = "Dinero_Acumulado.txt";
		File fichero = new File(ruta);
				
		try {
		
			//Cada vez que devuelvo un coche se actualiza el dinero acumulado de todos los coches devuelts del parking
			 BufferedWriter bw = null;
		        if(fichero.exists()) {
		            bw = new BufferedWriter(new FileWriter(fichero));
		            bw.write("La cantidad de dinero del parking es: " + dinero_acumulado);
		        }
		
		bw.close();
	
		}catch(IOException exception) {}  
		
	}
	
	public void Inicializar_Dinero_Acumulado() {

		
		try {

            File f = new File("Dinero_Acumulado.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";
          
            //Lee una linea del fichero Dinero_Acumulado.txt y recupera la cantidad de dinero acumulado
            while ((readLine = b.readLine()) != null) {
            	
            	String dinero = readLine.substring(38, readLine.length());
            	dinero_acumulado = Float.parseFloat(dinero);		//Paso el string a float e inicializo el dinero acumulado del parking            	
                  	
            }          	
                
            }catch (IOException e) {
                e.printStackTrace();
            }            
	}

	public void Calcular_Dinero_Acumulado() {
		
		dinero_acumulado+=dinero_pagar;	//Sumo a dinero acumulado el coste del coche devuelto
		dinero_pagar = 0;
		Escribir_Dinero_Acumulado();	//Actualizo el dinero acumulado
	}
	
	public void Mostrar_Dinero_Acumulado() {
		
		//Con el dinero acumulado inicializado doy la opcion de imprimirlo
		char respuesta;
		JOptionPane.showMessageDialog(null, "El dinero acumulado se mostrará por consola.");
		System.out.println("La cantidad de dinero acumulado en el parking es: " + dinero_acumulado + "€");
				
		do {
			
			//Tambien se puede resetear el dinero acumulado en el parking 
			respuesta = JOptionPane.showInputDialog("Por favor, pulse S si desea resetear el acumulado del parking.\nPulse N si desea seguir con la cuenta actual.").charAt(0);
			
			if(respuesta == 'S' || respuesta == 's') {
				
				dinero_acumulado = 0;
				Escribir_Dinero_Acumulado();
				JOptionPane.showMessageDialog(null, "Cuenta reseteada con exito.");
				System.out.println("La cantidad de dinero acumulado en el parking es: " + dinero_acumulado + "€\n");
				
			}else if(respuesta == 'N' || respuesta == 'n') {
				
				JOptionPane.showMessageDialog(null, "Usted ha decidido no resetear la cuenta.");
			}
			
		}while(false);			
	}

	public void Escribir_Coste_Minuto() {
		
		//Cada vez que cambio el coste por minuto lo escribo en un fichero para poder recuperarlo luego
		String ruta = "Coste_Minuto.txt";
		File fichero = new File(ruta);
				
		try {
		
			 BufferedWriter bw = null;
		        if(fichero.exists()) {
		            bw = new BufferedWriter(new FileWriter(fichero));
		            String dinero = String.valueOf (coste_minuto);
		            bw.write(dinero);
		        }
		
		bw.close();
	
		}catch(IOException exception) {}  
		
	}
	
	public void Inicializar_Coste_Minuto() {

		
		try {
			//Cargo el coste por minuto del fichero para poder inicializarlo desde el principio al valor que desee sin cambiarlo en el codigo
            File f = new File("Coste_Minuto.txt");

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";
            
            while ((readLine = b.readLine()) != null) {
            	
            	String dinero = readLine.substring(0, readLine.length());
            	coste_minuto = Float.parseFloat(dinero);       
            	
            }          	
                
            }catch (IOException e) {
                e.printStackTrace();
            }            
	}
}
