package filmnow;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {


    private Filme filmeBase;


    @BeforeEach
    void preparaFilmes() {
        this.filmeBase = new Filme("Avatar", "2009", "Disney+");
    }

    @Test
    void testNomeAno() {
        String msg = "Esperando Nome e Ano";
        assertEquals( "Avatar, 2009", this.filmeBase.toString(), msg);
    }
    @Test
    public void testNomeNull() {
        try {
            Filme filmeInvalido = new Filme(null, "2009", "Disny+");
            throw new NullPointerException("filme invalido");
        } catch (NullPointerException npe) {
            assertEquals("filme invalido", npe.getMessage());
        }
    }
    private static void rangeCheck(int length, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException(
                    "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }

    }
    @Test
    void testNullNone(){
        Exception nulo = assertThrows(NullPointerException.class, () -> {
            new Filme(null, "2007", "Netflix");
        });
        String esperando = "filme invalido";
        String recebido = nulo.getMessage();

        assertEquals(esperando, recebido);
    };}
