package uce.edu.web.api.matricula.aplication.representation;

public class LinkDto {
//va a tener dos cosas
//1. el nombre de la URL 
//2.rel = para ponerle el nombre a la url 
    public String href;
    public String rel;

    //despues crear un constructor que reciba estos parametros
    public LinkDto (String href, String rel) {
        this.href = href;
        this.rel = rel;
    }
}

