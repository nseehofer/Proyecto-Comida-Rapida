package ar.unlam.edu.pbII.sangucheto;


import java.util.Map;
import java.util.TreeMap;

public class Sanguche implements Comparable<Sanguche> {
	
	private static int contadorID = 0;
	private Integer id;
	private Map<Ingrediente, Integer> ingredientesDelSanguche = new TreeMap<Ingrediente, Integer>();

	public Sanguche() {
		this.id = ++contadorID;
	}

	public void agregarIngrediente(Ingrediente ingrediente, Integer cantidad) {
		//CHEQUEAR SI ME MODIFICA LA CANTIDAD DE ESE INGREDIENTE EN LA COLECCION DE "stockIngredientes" DE LOCALSANGUCHETO
		this.ingredientesDelSanguche.put(ingrediente, cantidad);
		
	}

	public Map<Ingrediente,Integer> getIngredientesDelSanguche() {
		return this.ingredientesDelSanguche;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Sanguche other = (Sanguche) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

	@Override
	public int compareTo(Sanguche o) {
		return this.id.compareTo(o.id);
	}


	
	
}
