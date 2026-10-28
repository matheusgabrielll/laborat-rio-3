package filmnow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilmeTest {

    private Filme filmeBase;

    @BeforeEach
    void preparaFilmes() {
        this.filmeBase = new Filme("Avatar", "2009", "Disney+");
    }

    //testando o tostring

    @Test
    void testNomeAno() {
        String msg = "Esperando Nome e Ano";

        assertEquals("Avatar, 2009", this.filmeBase.toString(), msg);
    }

    @Test
    void testToStringSemAno() {
        Filme semAno = new Filme("20 dias em Mariupol", "", "Cinema");

        assertEquals("20 dias em Mariupol", semAno.toString(), "Esperando só o nome");
    }

    //testando parametros nulos

    @Test
    void testNomeNull() {
        try {
            new Filme(null, "2009", "Disney+");
            fail("Esperando NullPointerException para nome nulo");

        } catch (NullPointerException e) {
            assertEquals("filme invalido", e.getMessage());
        }
    }

    @Test
    void testAnoNull() {
        try {
            new Filme("Avatar", null, "Disney+");
            fail("Esperando NullPointerException para ano nulo");

        } catch (NullPointerException e) {
            assertEquals("filme invalido", e.getMessage());
        }
    }

    @Test
    void testLocalNull() {
        try {
            new Filme("Avatar", "2009", null);
            fail("Esperando NullPointerException para local nulo");

        } catch (NullPointerException e) {
            assertEquals("filme invalido", e.getMessage());
        }
    }

    //testando o isBlank, trim().isEmpty()

    @Test
    void testNomeSoEspacos() {
        try {
            new Filme("   ", "2009", "Disney+");
            fail("Esperando IllegalArgumentException para nome só com espaços");

        } catch (IllegalArgumentException e) {
            assertEquals("filme invalido", e.getMessage());
        }
    }

    @Test
    void testLocalVazio() {
        try {
            new Filme("Avatar", "2009", "");
            fail("Esperando IllegalArgumentException para local vazio");

        } catch (IllegalArgumentException e) {
            assertEquals("filme invalido", e.getMessage());
        }
    }

    @Test
    void testLocalSoEspacos() {
        try {
            new Filme("Avatar", "2009", "   ");
            fail("Esperando IllegalArgumentException para local só com espaços");

        } catch (IllegalArgumentException e) {
            assertEquals("filme invalido", e.getMessage());
        }
    }

    //testando equals()

    @Test
    void testEqualsMesmoNomeEAnoComLocalDiferente() {
        Filme outro = new Filme("Avatar", "2009", "Popcornflix");

        assertEquals(this.filmeBase, outro, "Esperando filmes iguais (mesmo nome e ano)");
    }

    @Test
    void testNaoEqualsAnoDiferente() {
        Filme outro = new Filme("Avatar", "2022", "Disney+");

        assertNotEquals(this.filmeBase, outro, "Esperando filmes diferentes (ano diferente)");
    }


    //locais

    @Test
    void testLocalInicial() {
        assertEquals(List.of("Disney+"), this.filmeBase.getLocais(), "Esperando só o local do construtor");
    }

    @Test
    void testAdicionaLocal() {
        assertTrue(this.filmeBase.adicionaLocal("Prime Video"), "Esperando que o local fosse adicionado");
        assertEquals(List.of("Disney+", "Prime Video"), this.filmeBase.getLocais(), "Esperando os dois locais");
    }

    @Test
    void testAdicionaLocalNull() {
        try {
            this.filmeBase.adicionaLocal(null);
            fail("Esperando NullPointerException para local nulo");
        } catch (NullPointerException e) {
            assertEquals("local invalido", e.getMessage());
        }
    }

    @Test
    void testAdicionaLocalVazio() {
        try {
            this.filmeBase.adicionaLocal("");
            fail("Esperando IllegalArgumentException para local vazio");
        } catch (IllegalArgumentException e) {
            assertEquals("local invalido", e.getMessage());
        }
    }

    @Test
    void testRemoveLocal() {
        this.filmeBase.adicionaLocal("Prime Video");

        assertTrue(this.filmeBase.removeLocal("Disney+"), "Esperando que o local fosse removido");
        assertEquals(List.of("Prime Video"), this.filmeBase.getLocais(), "Esperando só o Prime Video");
    }


    @Test
    void testRemoveLocalInexistente() {
        this.filmeBase.adicionaLocal("Prime Video");

        assertFalse(this.filmeBase.removeLocal("Netflix"), "Esperando falso para local que o filme não tem");
        assertEquals(2, this.filmeBase.getLocais().size(), "Esperando continuar com 2 locais");
    }

}