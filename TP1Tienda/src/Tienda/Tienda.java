package Tienda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tienda {
    private String nombre;
    private int maxProductosEnStock;
    private double saldoEnCaja;
    private Map<String, List<Producto>> productosEnStock;

    public Tienda(String nombre, int maxProductosEnStock, double saldoEnCaja) {
        this.nombre = nombre;
        this.maxProductosEnStock = maxProductosEnStock;
        this.saldoEnCaja = saldoEnCaja;
        this.productosEnStock = new HashMap<>();
    }

    public double calcularIngresosConDescuento() {
        double totalIngresos = 0;

        for (List<Producto> productos : productosEnStock.values()) {
            for (Producto producto : productos) {
                if (producto instanceof AplicableDescuento) {
                    AplicableDescuento productoConDescuento = (AplicableDescuento) producto;
                    totalIngresos += productoConDescuento.getPrecioVentaConDescuento() * producto.getCantidadStock();
                } else {
                    totalIngresos += producto.getPrecioVenta() * producto.getCantidadStock();
                }
            }
        }

        return totalIngresos;
    }
    
    public void agregarProducto(Producto producto, String tipo) {
        List<Producto> productosTipo = productosEnStock.getOrDefault(tipo, new ArrayList<>());
        
        if (productosTipo.size() < maxProductosEnStock) {
            productosTipo.add(producto);
            productosEnStock.put(tipo, productosTipo);
            saldoEnCaja -= producto.getPrecioVenta() * producto.getCantidadStock();
        } else {
            System.out.println("No se pueden agregar más productos. Stock máximo alcanzado.");
        }
    }

    public void realizarVenta(List<Producto> productosVenta) {
        double totalVenta = 0;

        for (Producto producto : productosVenta) {
            List<Producto> productosTipo = productosEnStock.get(producto.getClass().getSimpleName());
            
            if (productosTipo != null && productosTipo.contains(producto)) {
                int cantidadVendida = producto.getCantidadStock();
                if (cantidadVendida > producto.getCantidadStock()) {
                    cantidadVendida = producto.getCantidadStock();
                    System.out.println("Hay productos con stock disponible menor al solicitado.");
                }
                
                double precioVenta = producto.getPrecioVentaConDescuento();
                totalVenta += precioVenta * cantidadVendida;

                producto.setCantidadStock(producto.getCantidadStock() - cantidadVendida);
            } else {
                System.out.println("El producto " + producto.getIdentificador() + " no está disponible.");
            }
        }

        saldoEnCaja += totalVenta;
        imprimirDetalleVenta(productosVenta, totalVenta);
    }

    private void imprimirDetalleVenta(List<Producto> productosVenta, double totalVenta) {
        for (Producto producto : productosVenta) {
            System.out.println(producto.getIdentificador() + " " + producto.getDescripcion() + " " +
                    producto.getCantidadStock() + " x " + producto.getPrecioVentaConDescuento());
        }
        System.out.println("TOTAL VENTA: " + totalVenta);
    }
    
    
    public static void main(String[] args) {
    }
}
