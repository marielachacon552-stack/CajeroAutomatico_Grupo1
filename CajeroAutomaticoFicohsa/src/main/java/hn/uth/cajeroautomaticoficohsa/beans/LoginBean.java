package hn.uth.cajeroautomaticoficohsa.beans;

import hn.uth.cajeroautomaticoficohsa.data.Cliente;
import hn.uth.cajeroautomaticoficohsa.data.CajeroService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String cuenta;
    private String pin;
    private Cliente clienteAutenticado;

    // Método principal llamado por el commandButton en index.xhtml
    public String login() {
        // Cargamos la lista desde el servicio que lee el archivo txt
        List<Cliente> listaClientes = CajeroService.cargarClientes();

        // Iteramos para buscar una coincidencia
        for (Cliente c : listaClientes) {
            if (c.getNumeroCuenta().equals(this.cuenta) && c.getPin().equals(this.pin)) {
                this.clienteAutenticado = c;

                // Guardamos el cliente en la sesión para usarlo en otras pantallas
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                        .put("usuarioLogueado", c);

                // Redirección exitosa al menú
                return "menu?faces-redirect=true";
            }
        }

        // Si no se encontró el cliente, mostramos el mensaje de error definido en index.xhtml
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Acceso Denegado", "Número de cuenta o PIN incorrectos."));

        return null; // Nos quedamos en la misma página
    }

    // Getters y Setters necesarios para JSF
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Cliente getClienteAutenticado() {
        return clienteAutenticado;
    }

    public void setClienteAutenticado(Cliente clienteAutenticado) {
        this.clienteAutenticado = clienteAutenticado;
    }
}