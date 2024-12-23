package ar.unlam.edu.pbII.sangucheto;

public class Huevo extends Ingrediente {
	public Huevo (Double precio) {
		this.precio=precio;
		this.descripcion = this.getClass().getSimpleName();
	}
}
