package com.ceiba.tiendatecnologica.dominio.servicio.vendedor;

import com.ceiba.tiendatecnologica.dominio.excepcion.GarantiaExtendidaException;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioGarantiaExtendida;
import com.ceiba.tiendatecnologica.dominio.repositorio.RepositorioProducto;

public class ServicioVendedor {

	public static final String EL_PRODUCTO_TIENE_GARANTIA = "El producto ya cuenta con una garantía extendida";
	public static final String EL_PRODUCTO_NO_TIENE_GARANTIA = "Este producto no cuenta con garantía extendida";
	public static final int CANTIDAD_MAXIMA_VOCALES = 3;
	public static final double PRECIO_MINIMO_PARA_GARANTIA = 500000;

	private RepositorioProducto repositorioProducto;
	private RepositorioGarantiaExtendida repositorioGarantia;

	public ServicioVendedor(RepositorioProducto repositorioProducto, RepositorioGarantiaExtendida repositorioGarantia) {
		this.repositorioProducto = repositorioProducto;
		this.repositorioGarantia = repositorioGarantia;
	}

	public void generarGarantia(String codigo, String nombreCliente) {

		if (!tieneGarantia(codigo)){
			if (codigoValidoParaGenerarGarantia(codigo)){
				double precio = calcularPrecioGarantia(repositorioProducto.obtenerPorCodigo(codigo).getPrecio());
				//TODO: implementar codigo para cumplir con requisitos
				//llamar metodo
			}else{
				throw new GarantiaExtendidaException(EL_PRODUCTO_NO_TIENE_GARANTIA);
			}
		}else{
			throw new GarantiaExtendidaException(EL_PRODUCTO_TIENE_GARANTIA);
		}
	}

	public boolean tieneGarantia(String codigo) {
		return repositorioGarantia.
				obtenerProductoConGarantiaPorCodigo(codigo)!= null; }

	private static boolean codigoValidoParaGenerarGarantia(String codigo) {
		return contarVocalesCodigoProducto(codigo) != CANTIDAD_MAXIMA_VOCALES;
	}

	private static boolean caracterEsVocal(char letra) {
		return "aeiou".contains(String.valueOf(letra).toLowerCase());
	}

	private static int contarVocalesCodigoProducto(String codigo) {
		int cantidadVocales = 0;
		for (int i= 0; i < codigo.length(); i++){
			if(caracterEsVocal(codigo.charAt(i))) cantidadVocales ++;
		}
		return cantidadVocales;
	}

	private static double calcularPrecioGarantia(double costoProducto) {
		double precioGarantia = 0;

		if(costoProducto> PRECIO_MINIMO_PARA_GARANTIA) {
			precioGarantia = precioGarantia * 0.2;
			//TODO: aplicar logica de negocio

		}
		return precioGarantia;
	}

}
