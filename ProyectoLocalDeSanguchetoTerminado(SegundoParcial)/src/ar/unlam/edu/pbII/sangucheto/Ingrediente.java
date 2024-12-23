package ar.unlam.edu.pbII.sangucheto;

public class Ingrediente implements Comparable <Ingrediente>{
	
	protected String descripcion;
	protected Integer cantidad = 0;
	protected Double precio;
	

	
	protected void setCantidad(Integer cantidad) {
		this.cantidad  += cantidad;
		
	}
	
	public void restarCantidad (Integer cantidad) {
		this.cantidad -= cantidad;
	}
	
	public Integer cantidad() {
		return this.cantidad;
	}

	public Double getPrecio() {
		return this.precio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		return true;
	}

	@Override
	public int compareTo(Ingrediente o) {
		
		return this.descripcion.compareTo(o.descripcion);
	}

	@Override
	public String toString() {
		return "Ingrediente: " + descripcion ;
	}

	
	
	
	

	
}
