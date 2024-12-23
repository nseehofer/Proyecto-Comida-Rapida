package ar.unlam.edu.pbII.sangucheto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LocalSangucheto {

	private Map<Ingrediente, Integer> stockDeIngredientes = new TreeMap<Ingrediente, Integer>();
	private List<Sanguche> pedidosSanguche = new ArrayList<Sanguche>();

	public void agregarIngredientes(Ingrediente ingrediente, Integer cantidad) {
		if (!(tengoElIngrediente(ingrediente))) {
			this.stockDeIngredientes.put(ingrediente, cantidad);
		} else {
			this.stockDeIngredientes.put(buscarIngrediente(ingrediente), cantidad);
		}
		
	}
	
	public Ingrediente buscarIngrediente(Ingrediente ingrediente) {
		Ingrediente ingredienteEncontrado = null;
		Iterator<Map.Entry<Ingrediente, Integer>> iteradorIngrediente = this.stockDeIngredientes.entrySet().iterator();
		while (iteradorIngrediente.hasNext() && !(ingredienteEncontrado != null)) {
			Map.Entry<Ingrediente, Integer> entry = iteradorIngrediente.next();
			ingredienteEncontrado = entry.getKey();
			if (ingredienteEncontrado.equals(ingrediente)) {
				return ingredienteEncontrado;
			}
		}
		return ingredienteEncontrado;
	}

	public Boolean tengoElIngrediente(Ingrediente ingrediente) {
		return this.stockDeIngredientes.containsKey(ingrediente);
	}

	public void armarSanguche(Sanguche sanguche, Ingrediente ingrediente, Integer cantidad) {
		if (buscarSanguche(sanguche) != null) {
			sanguche.agregarIngrediente(ingrediente, cantidad);
			Integer cantidadActual = this.stockDeIngredientes.get(ingrediente);
			if (cantidadActual >= cantidad) {
				this.stockDeIngredientes.put(ingrediente, cantidadActual - cantidad);
			}

		} else {
			nuevoSanguche(sanguche);
			sanguche.agregarIngrediente(ingrediente, cantidad);
			Integer cantidadActual = this.stockDeIngredientes.get(ingrediente);
			if (cantidadActual >= cantidad) {
				this.stockDeIngredientes.put(ingrediente, cantidadActual - cantidad);
			}
		}

	}

	public Sanguche buscarSanguche(Sanguche sanguche) {
		Sanguche sangucheEncontrado = null;
		Iterator<Sanguche> iteradorSanguche = this.pedidosSanguche.iterator();
		while (iteradorSanguche.hasNext() && !(sangucheEncontrado != null)) {
			sangucheEncontrado = iteradorSanguche.next();
			if (sangucheEncontrado.equals(sanguche)) {
				return sangucheEncontrado;
			} else {sangucheEncontrado = null;}
		}
		return sangucheEncontrado;
	}

	public void nuevoSanguche(Sanguche sanguchePedido) {
		this.pedidosSanguche.add(sanguchePedido);
	}

	public Integer verStock(Ingrediente ingrediente) {
		return stockDeIngredientes.get(ingrediente);

	}

	public Double cuantoVale(Sanguche sanguche) {
		Double precioActual = 0.0;
		Iterator<Map.Entry<Ingrediente, Integer>> iteradorIngredientesSanguche = sanguche.getIngredientesDelSanguche()
				.entrySet().iterator();
		while (iteradorIngredientesSanguche.hasNext()) {
			Map.Entry<Ingrediente, Integer> entry = iteradorIngredientesSanguche.next();
			Ingrediente ingrediente = entry.getKey();
			Integer cantidad = entry.getValue();
			precioActual += (ingrediente.getPrecio() * cantidad) ;
		}

		return precioActual;

	}

	public void cancelarSanguche(Sanguche sanguche2) {
		this.pedidosSanguche.remove(sanguche2);
	}

	public Set<Ingrediente> verIngredientesDisponibles() {
		return this.stockDeIngredientes.keySet();
	}

	public void darDeBajaIngrediente(Ingrediente ingrediente) throws IngredienteIngresadoInexistenteException {
		Integer valueDelIngrediente = this.stockDeIngredientes.get(ingrediente);
		
		if(valueDelIngrediente != null) {
			this.stockDeIngredientes.remove(ingrediente, valueDelIngrediente);
		} else {
			throw new IngredienteIngresadoInexistenteException("El ingrediente no existe en el stock");
		}
		
	}

	public void sumarStock(Ingrediente ingrediente, Integer cantidad) throws IngredienteIngresadoInexistenteException {
		Integer valueIngrediente = verStock(ingrediente);
		if (valueIngrediente != null) {
			actualizarCantidadIngrediente(ingrediente, cantidad, valueIngrediente);
		} else {
			
			throw new IngredienteIngresadoInexistenteException("El ingrediente no existe en el stock");			
		}
		
	}

	private void actualizarCantidadIngrediente(Ingrediente ingrediente, Integer cantidad, Integer valueIngrediente) {
		Integer cantidadASumar = valueIngrediente + cantidad;
		this.stockDeIngredientes.put(ingrediente, cantidadASumar);
	}

}
