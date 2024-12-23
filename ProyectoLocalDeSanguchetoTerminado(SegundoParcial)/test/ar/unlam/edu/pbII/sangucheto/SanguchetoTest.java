package ar.unlam.edu.pbII.sangucheto;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Test;

public class SanguchetoTest {

	/*
	 * // ¿ QUE APRENDISTE HOY ?
	 * 
	 * /* SI QUIERO ITERAR UN MAP DEBO ESCRIBIR ASI
	 * 
	 * Iterator <Map.entry<Key,Value>> iteradorSubclaseMap =
	 * atributoMapAIterar.entrySet().iterator();
	 * while(iteradorSubclaseMap.hastNext()) { //AGARRO LA ENTRADA, QUE SERIA EL PAR
	 * DEL MAPA Map.entry <Key,Value> entry = iteradorSubclaseMap.next(); //PUEDO
	 * AGARRAR LO QUE PRECISE DE LA ENTRADA Key valorKey = entry.getKey(); Value
	 * valorValue = entry.getValue(); }
	 * 
	 * .remove(elemento) -> para colection .get(key) -Devuelve el "value" si existe
	 * para luego hacer .remove(key,value) mapa.put(key,value) | si ya existe la key
	 * le puedo actualizar el "value" colection.add(elemento) | agregar elemento
	 * para colection
	 * 
	 * Tengo un comproblema con la implementacion del "Comparable" no me queda claro
	 * cuando es completamente necesario
	 * 
	 */

	/*
	 * Armar un sangucheto a su gusto, se debe permitir agregar ingredientes y
	 * condimentos por separado (que se descuentan del stock), y poder consultar el
	 * precio del sangucheto a medida que se agregan cosas al mismo. Interesa ver
	 * por separado los ingredientes y condimentos que forman parte del sándwich. En
	 * todo momento se debe poder cancelar, que elimina todo lo agregado al
	 * sangucheto.
	 */

	@Test
	public void armarUnSanguche() {
		LocalSangucheto local = new LocalSangucheto();
		// DEBE TENER ID
		Sanguche sanguche = new Sanguche();
		Sanguche sanguche2 = new Sanguche();
		Ingrediente pollo = new Pollo(50.25);
		Ingrediente mayonesa = new Condimento("Mayonesa", 5.50);
		Ingrediente lechuga = new Verdura("Lechuga", 6.10);
		Ingrediente queso = new Fiambre("Queso", 7.25);
		Ingrediente huevo = new Huevo(6.0);

		local.agregarIngredientes(pollo, 40);
		local.agregarIngredientes(mayonesa, 19);
		local.agregarIngredientes(lechuga, 24);
		local.agregarIngredientes(queso, 0);
		local.agregarIngredientes(huevo, 10);

		/*
		 * NO ME SIRVE PORQUE DEBO CONSULTAR EL PRECIO CADA VEZ QUE AGREGO INGREDIENTES,
		 * ENTONCES SURGE QUE CADA SANGUCHE CREADO TENGA UN ID Y CADA VEZ QUE SE ARMA
		 * UNO, SE IDENTIFIQUE ESE MISMO. List <Ingrediente> ingredientesAAgregar;
		 * 
		 * ingredientesAAgregar.add(huevo); ingredientesAAgregar.add(lechuga);
		 * ingredientesAAgregar.add(mayonesa); ingredientesAAgregar.add(pollo);
		 */

		// DEBE BUSCARLO POR ID
		local.armarSanguche(sanguche, pollo, 2);
		local.armarSanguche(sanguche, lechuga, 2);
		local.armarSanguche(sanguche, mayonesa, 1);
		Double precioActual = local.cuantoVale(sanguche);
		local.armarSanguche(sanguche, huevo, 1);

		Double resultadoEsperado = 118.2;
		Double resultadoObtenido = precioActual;

		Integer cantidadDeLechuga = local.verStock(lechuga);
		Integer resultadoObtenido2 = cantidadDeLechuga;
		Integer resultadoEsperado2 = 22;

		assertEquals(resultadoEsperado, resultadoObtenido);
		assertEquals(resultadoEsperado2, resultadoObtenido2);

		local.armarSanguche(sanguche2, pollo, 1);
		local.armarSanguche(sanguche2, huevo, 2);
		local.armarSanguche(sanguche2, mayonesa, 2);
		local.cancelarSanguche(sanguche2);

		// PROBAR SI EL PEDIDO FUE CANCELADO

		Sanguche sangucheCancelado = null;
		Sanguche resultadoEsperado3 = sangucheCancelado;
		Sanguche resultadoObtenido3 = local.buscarSanguche(sanguche2);
		assertEquals(resultadoEsperado3, resultadoObtenido3);
	}

	@Test
	public void listarLosIngredientesDisponibles() {
		LocalSangucheto local = new LocalSangucheto();
		Ingrediente pollo = new Pollo(50.25);
		Ingrediente mayonesa = new Condimento("Mayonesa", 5.50);
		Ingrediente lechuga = new Verdura("Lechuga", 6.10);
		Ingrediente queso = new Fiambre("Queso", 7.25);
		Ingrediente huevo = new Huevo(6.0);

		local.agregarIngredientes(pollo, 14);
		local.agregarIngredientes(mayonesa, 5);
		local.agregarIngredientes(lechuga, 12);
		local.agregarIngredientes(huevo, 24);
		local.agregarIngredientes(queso, 30);

		Set<Ingrediente> ingredientesDisponibles = local.verIngredientesDisponibles();
		for (Ingrediente ingrediente : ingredientesDisponibles) {
			System.out.println("\n" + ingrediente);
		}
	}

	@Test
	public void consultarStockDeTodosLosIngredientesDisponibles() {
		LocalSangucheto local = new LocalSangucheto();
		Ingrediente pollo = new Pollo(50.25);
		Ingrediente mayonesa = new Condimento("Mayonesa", 5.50);
		Ingrediente lechuga = new Verdura("Lechuga", 6.10);
		Ingrediente queso = new Fiambre("Queso", 7.25);
		Ingrediente huevo = new Huevo(6.0);

		local.agregarIngredientes(pollo, 14);
		local.agregarIngredientes(mayonesa, 5);
		local.agregarIngredientes(lechuga, 12);
		local.agregarIngredientes(huevo, 24);
		local.agregarIngredientes(queso, 30);

		Set<Ingrediente> ingredientesDisponibles = local.verIngredientesDisponibles();
		for (Ingrediente ingrediente : ingredientesDisponibles) {
			System.out.println("\n" + ingrediente + " Cantidad: " + local.verStock(ingrediente));
		}

		Integer resultadoEsperado = 5;
		Integer resultadoObtenido = local.verStock(mayonesa);

		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void darDeAltaUnIngredienteConCantidadCero() {
		LocalSangucheto local = new LocalSangucheto();
		Ingrediente pollo = new Pollo(50.25);
		Ingrediente mayonesa = new Condimento("Mayonesa", 5.50);
		local.agregarIngredientes(pollo, 0);

		Integer resultadoEsperado = 0;
		Integer resultadoObtenido = local.verStock(pollo);
		assertEquals(resultadoEsperado, resultadoObtenido);
	}

	@Test
	public void darDeBajaUnIngrediente() throws IngredienteIngresadoInexistenteException {
		LocalSangucheto local = new LocalSangucheto();
		Ingrediente pollo = new Pollo(50.25);
		local.agregarIngredientes(pollo, 4);
		local.darDeBajaIngrediente(pollo);

		Integer resultadoEsperado = null;
		Integer resultadoObtenido = local.verStock(pollo);
		assertEquals(resultadoEsperado,resultadoObtenido);
	}

	@Test(expected = IngredienteIngresadoInexistenteException.class)
	public void arrojarExcepcionAlIntentarDarDeBajaUnIngredienteInexistente()
			throws IngredienteIngresadoInexistenteException {
		LocalSangucheto local = new LocalSangucheto();
		Ingrediente pollo = new Pollo(50.25);
		local.agregarIngredientes(pollo, 4);
		local.darDeBajaIngrediente(pollo);
		local.darDeBajaIngrediente(pollo);
		
		
	}

	@Test 
	public void agregarStockDeUnIngredienteDisponible() throws IngredienteIngresadoInexistenteException {
		LocalSangucheto local = new LocalSangucheto();
		Ingrediente pollo = new Pollo(50.25);
		local.agregarIngredientes(pollo, 4);
		local.sumarStock(pollo,10);
		
		Integer resultadoEsperado = 14;
		Integer resultadoObtenido = local.verStock(pollo);
		assertEquals(resultadoEsperado,resultadoObtenido);
		
	}
}
