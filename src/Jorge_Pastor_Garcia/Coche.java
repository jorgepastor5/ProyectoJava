package Jorge_Pastor_Garcia;

import javax.swing.JOptionPane;

import java.text.SimpleDateFormat;
import java.util.*;

public class Coche {
	
	//Atributos
	private String Matricula; 
	private String Modelo; 
	private String Color;
	private String Titular;
	private String Fecha;
	private String Hora;

	//Gets y Sets para inicializar vehículos
	
	public void setMatricula(String Matricula) {
		
		this.Matricula = Matricula;	
	}
	
	public String getMatricula() {
		
		return Matricula;	
	}
	
	public void setModelo(String Modelo) {
			
		this.Modelo = Modelo;			
	}
	
	public String getModelo() {
		
		return Modelo;
	}
		
	public void setColor(String Color ) {

		this.Color = Color;	
	}
	
	public String getColor() {
		
		return Color;
	}
	
	public void setTitular(String Titular) {

		this.Titular = Titular;
	}
	
	public String getTitular() {
		
		return Titular;
	}
	
	public void setFecha(String Fecha) {
		
		this.Fecha = Fecha;
	}
	
	public String getFecha() {
		
		return Fecha;
	}

	public void setHora(String Hora) {
		
		this.Hora = Hora;
	}
	
	public String getHora() {
		
		return Hora;
	}
	


}
