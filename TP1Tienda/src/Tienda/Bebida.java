package Tienda;

public class Bebida extends Producto implements AplicableDescuento {
	private boolean esAlcoholica;
    private double graduacionAlcoholica;
    private boolean esImportada;
    private double porcentajeDescuento;
    @Override
    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
    @Override
    public double getPrecioVentaConDescuento() {
        double precioConDescuento = getPrecioVenta() * (1 - porcentajeDescuento);
        return precioConDescuento;
    }
}
