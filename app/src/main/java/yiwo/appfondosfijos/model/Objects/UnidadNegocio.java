package yiwo.appfondosfijos.model.Objects;

public class UnidadNegocio {
    private String codigo;
    private String nombre;

    public UnidadNegocio(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }
}
