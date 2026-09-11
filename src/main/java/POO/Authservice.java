package POO;

import java.util.HashMap;
import java.util.Map;

public class Authservice {

    private final Map<String, Usuario> usuarios = new HashMap<>();

    public boolean registarUsuario(Usuario usuario){
        if (usuario == null || usuario.getUsername() == null){
            return false;
        }if (usuarios.containsKey(usuario.getUsername())){
            return false;
        }
        usuarios.put(usuario.getUsername(), usuario);
        return true;
    }
    public boolean iniciarSesion(String username, String password){
        Usuario usuario = usuarios.get(username);
        if (usuario == null){
            return false;
        }return usuario.getPassword().equals(password);
    }

    public boolean eliminarCuenta(String username){
        return false;
    }

    public boolean verificarDisponibilidad(String username){
        return !usuarios.containsKey(username);
    }
}
