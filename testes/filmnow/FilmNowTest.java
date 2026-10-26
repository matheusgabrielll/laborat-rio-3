package filmnow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilmNowTest {

    private FilmNow fn;

    @BeforeEach
    void preparaSistema() {
        this.fn = new FilmNow();
    }

    // ---------------- ADICIONAR FILME ----------------

    @Test
    void testAdicionaEmPosicaoVazia() {
        assertEquals("FILME ADICIONADO", this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+"),
                "Esperando FILME ADICIONADO");
        assertNotNull(this.fn.getFilmes()[0], "Esperando Avatar na posição 1");
    }

    @Test
    void testAdicionaEmPosicaoExistente() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("FILME ADICIONADO", this.fn.cadastraFilme(1, "20 dias em Mariupol", "2023", "Cinema"),
                "Esperando FILME ADICIONADO");
        assertEquals(new Filme("20 dias em Mariupol", "2023", "Cinema"), this.fn.getFilmes()[0],
                "Esperando FILME2 na posição 1");
    }

    @Test
    void testAdicionaFilmeJaAdicionadoEmOutraPosicao() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("FILME JA ADICIONADO", this.fn.cadastraFilme(3, "Avatar", "2009", "Disney+"),
                "Esperando FILME JA ADICIONADO");
        assertNull(this.fn.getFilmes()[2], "Esperando posição 3 vazia");
    }

    @Test
    void testAdicionaMesmoNomeEAnoComLocalDiferente() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("FILME JA ADICIONADO", this.fn.cadastraFilme(3, "Avatar", "2009", "Popcornflix"),
                "Esperando FILME JA ADICIONADO");
        assertNull(this.fn.getFilmes()[2], "Esperando posição 3 vazia");
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

    // ---------------- DETALHAR FILME ----------------

    @Test
    void testDetalhaFilmeCompleto() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");

        assertEquals("Avatar, 2009\nDisney+", this.fn.detalhaFilme(1), "Esperando nome, ano e local");
    }

    // professora, aqui tava dando erro, entao so cliquei e deixei do jeito que o proprio intellij pediu
    @Test
    void testDetalhaFilmeSemAno() {
        this.fn.cadastraFilme(1, "20 dias em Mariupol", "", "Cinema");

        assertEquals("20 dias em Mariupol, \nCinema", this.fn.detalhaFilme(1), "Esperando nome e local, sem ano");
    }

    @Test
    void testDetalhaPosicaoSemFilme() {
        assertThrows(IllegalArgumentException.class, () -> this.fn.detalhaFilme(100),
                "Esperando exceção para posição sem filme");
    }
// tava com dificuldade em como fazer essa parte, entao fiz com consulta da ia
    @Test
    void testDetalhaAbaixoDoLimite0() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> this.fn.detalhaFilme(0), "Esperando exceção para posição 0");
        assertEquals("POSIÇÃO INVÁLIDA!", e.getMessage(), "Esperando mensagem de posição inválida");
    }
    // tava com dificuldade em como fazer essa parte, entao fiz com consulta da ia
    @Test
    void testDetalhaAcimaDoLimite101() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> this.fn.detalhaFilme(101), "Esperando exceção para posição 101");
        assertEquals("POSIÇÃO INVÁLIDA!", e.getMessage(), "Esperando mensagem de posição inválida");
    }

    @Test
    void testDetalhaFilmeDaHotList() {
        this.fn.cadastraFilme(1, "Avatar", "2009", "Disney+");
        this.fn.adicionaHot(1, 1);
        assertEquals("🔥 Avatar, 2009\nDisney+", this.fn.detalhaFilme(1), "Esperando 🔥 antes do filme");
    }
}