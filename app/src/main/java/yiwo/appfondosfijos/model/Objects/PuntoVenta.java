package yiwo.appfondosfijos.model.Objects;

public class PuntoVenta {

    private String codigo;
    private String nombre;
    private String almacen;
    private String direccion;

    public PuntoVenta(String codigo, String nombre, String almacen, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.almacen = almacen;
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAlmacen() {
        return almacen;
    }

    public String getDireccion() {
        return direccion;
    }
}