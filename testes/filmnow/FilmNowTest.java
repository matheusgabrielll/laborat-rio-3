package filmnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilmNowTest {

    private FilmNow fn;

    @BeforeEach
    void preparaSistema() {
        this.fn = new FilmNow();
    }

    //adicionar filme

    @Test
    void testAdicionaEmPosicaoVazia() {
        assertEquals("FILME ADICIONADO", this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+"),
                "Esperando FILME ADICIONADO");
        assertNotNull(this.fn.getFilmes()[0], "Esperando Avatar na posição 1");
    }

    @Test
    void testAdicionaNaPosicaoLimite100() {
        assertEquals("FILME ADICIONADO", this.fn.cadastraFilme(100, "Avatar", "2009", "Disney+"),
                "Esperando FILME ADICIONADO");

        assertNotNull(this.fn.getFilmes()[99], "Esperando Avatar na posição 100");
    }

    @Test
    void testAdicionaAcimaDoLimite101() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.cadastraFilme(101, "Avatar", "2009", "Disney+"),
                "Esperando POSIÇÃO INVÁLIDA");
    }

    @Test
    void testAdicionaAbaixoDoLimite0() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.cadastraFilme(0, "Avatar", "2009", "Disney+"),
                "Esperando POSIÇÃO INVÁLIDA");
    }
//vazio
    @Test
    void testAdicionaComLocalVazio() {
        assertEquals("FILME INVALIDO", this.fn.cadastraFilme(1, "20 dias em Mariupol", "2023", ""),
                "Esperando FILME INVALIDO");

        assertNull(this.fn.getFilmes()[0], "Esperando posição 1 vazia");
    }

    @Test
    void testAdicionaComAnoVazio() {
        assertEquals("FILME ADICIONADO", this.fn.cadastraFilme(1, "20 dias em Mariupol", "", "Cinema"),
                "Esperando FILME ADICIONADO");

        assertNotNull(this.fn.getFilmes()[0], "Esperando filme na posição 1");
    }

    @Test
    void testAdicionaComNomeVazio() {
        assertEquals("FILME INVALIDO", this.fn.cadastraFilme(1, "", "2023", "Cinema"),
                "Esperando FILME INVALIDO");

        assertNull(this.fn.getFilmes()[0], "Esperando posição 1 vazia");
    }

    //detalhar filme

    @Test
    void testDetalhaFilmeCompleto() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("Avatar, 2009\nDisney+", this.fn.detalhaFilme(1), "Esperando nome, ano e local");
    }

    @Test
    void testDetalhaAbaixoDoLimite0() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.detalhaFilme(0), "Esperando POSIÇÃO INVÁLIDA");
    }

    @Test
    void testDetalhaAcimaDoLimite101() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.detalhaFilme(101), "Esperando POSIÇÃO INVÁLIDA");
    }

    @Test
    void testDetalhaFilmeDaHotList() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
        this.fn.adicionaHot(1, 1);
        assertEquals("🔥 Avatar, 2009\nDisney+", this.fn.detalhaFilme(1), "Esperando 🔥 antes do filme");
    }

    //adicionar local

    @Test
    void testAdicionaLocal() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("LOCAL ADICIONADO", this.fn.adicionaLocal(1, "Prime Video"), "Esperando LOCAL ADICIONADO");
        assertEquals("Avatar, 2009\nDisney+, Prime Video", this.fn.detalhaFilme(1),
                "Esperando os dois locais separados por vírgula");
    }

    @Test
    void testAdicionaLocalAbaixoDoLimite0() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.adicionaLocal(0, "Prime Video"), "Esperando POSIÇÃO INVÁLIDA");
    }

// esse limite eu escolhi como forma mais facil para trabalhar: fiz com o final static
    @Test
    void testAdicionaLocalAcimaDoLimite101() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.adicionaLocal(101, "Prime Video"), "Esperando POSIÇÃO INVÁLIDA");
    }

    @Test
    void testAdicionaLocalVazio() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("LOCAL INVALIDO", this.fn.adicionaLocal(1, ""), "Esperando LOCAL INVALIDO");
        assertEquals("Avatar, 2009\nDisney+", this.fn.detalhaFilme(1), "Esperando continuar só com um local");
    }

    //remover local

    @Test
    void testRemoveLocal() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
        this.fn.adicionaLocal(1, "Prime Video");

        assertEquals("LOCAL REMOVIDO", this.fn.removeLocal(1, "Disney+"), "Esperando LOCAL REMOVIDO");
        assertEquals("Avatar, 2009\nPrime Video", this.fn.detalhaFilme(1), "Esperando só o Prime Video");
    }

    @Test
    void testRemoveLocalEmPosicaoInvalida() {
        assertEquals("POSIÇÃO INVÁLIDA", this.fn.removeLocal(0, "Disney+"), "Esperando POSIÇÃO INVÁLIDA");
    }
}