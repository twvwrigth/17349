package mx.uv.fei.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class SaludarControlador {
    
    String nombre;

    @GetMapping("/Saludos")
    public String saludar() {
        return "Hola mundo" + nombre;
    }
    
    @GetMapping("/Despedidas")
    public String adios() {
        return "Adios bronsio";
    }

    @PostMapping ("/Nombramientos")
    public void nombre(){
        nombre = "Emmanuel";
    }
    //Nombramiento
    @PutMapping ("/Nombramientos")
    public void met1(){
        nombre = "Emmanuel 2";
    }
    //Desnombramiento
    @DeleteMapping ("/Nombramientos")
    public void met2(){
        nombre = null;
    }

    //
    @GetMapping ("/Saludar/{nombre}")
    public Saludador met3(@PathVariable String nombre){
        System.out.println(nombre);
        return new Saludador(nombre);
    }

    @GetMapping ("/Saludar2/{nombreX}")
    public Saludador met4(@PathVariable("nombreX") String nombre){
        System.out.println(nombre);
        saludador = new Saludador(nombre);
        return saludador;
    }

    Saludador saludador;
    @PostMapping ("/crearSaludador")
    public String crearSaludador(@RequestBody Saludador nombre){
        saludador = nombre;
        return "Saludador creado: " + nombre;
    }

    @GetMapping ("/obtenerSaludador")
    public Saludador obtenerSaludador(){
        return saludador;
    }

    // Metodo que mapee raiz (/)
    @RequestMapping ("/")
    public String raiz(){
        String contenido = null;
        contenido= """
                <h1>Menu de opciones</h1>
                <ul>
                    <li><a href='Saludos'>Saludos</a></li>
                    <li><a href='/Despedidas'>Despedidas</a></li>
                    <li> /nombramientos (POST, PUT, DELETE)</li>
                    <li><a href='/Saludar/default'>Saludar {nombre}</a></li>
                    <li><a href='/Saludar2/default2'>Saludar2 {nombre}</a></li>
                    <li> /crearSaludador (Post - Json)</li>
                    <li><a href='/obtenerSaludador'>Obtener Saludador</a></li>
                    <li><a href='/query1'>/query1?nombre=valor&paterno=valor2&materno=valor3</a></li>
                    <li><a href='/query2'>/query2[?nombre=valor]</a></li>
                </ul>

                <script>
                    alert('mensaje.....')
                </script>
                """;
        return contenido;
    }

    @GetMapping ("/query1")
    public String query1(@RequestParam String nombre, @RequestParam String paterno, @RequestParam(defaultValue = "") String materno){
        return nombre + " " + paterno + " " + materno;
    }
    
    @GetMapping ("/query2")
    public String query2(@RequestParam(defaultValue = "default") String nombre){
        return nombre;
    }

}
