package Tienda;

public class Producto {
	private String identificador;
    private String descripcion;
    private int cantidadStock;
    private double precioVenta;
    private double costoUnidad;
    private boolean disponibleParaVenta;
    public double getPrecioVenta() {
        return precioVenta;
    }
    
    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }
    
    public String getIdentificador() {
        return identificador;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}