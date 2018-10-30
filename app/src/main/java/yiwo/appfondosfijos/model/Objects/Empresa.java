package yiwo.appfondosfijos.model.Objects;

public class Empresa {

    private String codigo;
    private String nombre;
    private String ruc;


    public Empresa(String codigo, String nombre, String ruc) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.ruc = ruc;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }
}
