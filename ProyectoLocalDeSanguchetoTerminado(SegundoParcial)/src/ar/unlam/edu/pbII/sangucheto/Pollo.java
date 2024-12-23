package ar.unlam.edu.pbII.sangucheto;

public class Pollo extends Ingrediente {
	
	public Pollo(Double precio) {
		this.descripcion = this.getClass().getSimpleName();
		this.precio=precio;
	}
}
