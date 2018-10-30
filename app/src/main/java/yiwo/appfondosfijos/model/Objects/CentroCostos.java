package yiwo.appfondosfijos.model.Objects;

public class CentroCostos {
    private String codigo;
    private String nombre;

    public CentroCostos(String codigo, String nombre) {
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
