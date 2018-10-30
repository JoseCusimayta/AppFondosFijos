package yiwo.appfondosfijos.model.Objects;

public class Caja {

    private String codigo;
    private String nombre;
    private String tipo;
    private String codigo_desconocido;

    public Caja(String codigo, String nombre, String tipo, String codigo_desconocido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.codigo_desconocido = codigo_desconocido;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo_desconocido() {
        return codigo_desconocido;
    }
}
