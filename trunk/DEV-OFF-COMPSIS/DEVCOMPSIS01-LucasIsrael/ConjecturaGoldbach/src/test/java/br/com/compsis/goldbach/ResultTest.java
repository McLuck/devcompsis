
package br.com.compsis.goldbach;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Teste unitário para os Resultados <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 13/10/2012 - @author LucasIsrael - Primeiva versão da classe. <br>
 *<br>
 *
 */

public class ResultTest {
	@Test
	public void criaUmaInstanciaDeResultComDoisPrimos(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		assertNotNull(result);
		assertNotNull(result.prime1);
		assertNotNull(result.prime2);
		assertTrue(result.prime1.contains(nPrime1));
		assertTrue(result.prime2.contains(nPrime2));
	}

	@Test
	public void verificaSeUmNumeroEstaNosResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		assertTrue(result.contains(nPrime2));
		assertTrue(result.contains(nPrime1));
	}
		
	@Test
	public void montaListaContendoTodosOsResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		final List<Integer> primes = result.getPrimes();
		assertNotNull(primes);
		assertEquals(2, primes.size());
	}
	
	
	@Test
	public void adicionaNovosComposicoesDePrimosNosResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		List<Integer> primes = result.getPrimes();
		assertEquals(2, primes.size());
		result.add(Integer.valueOf(5), Integer.valueOf(7));
		primes = result.getPrimes();
		assertEquals(4, primes.size());		
	}
	
	@Test
	public void adicionaPrimeiroPrimoRepetidoDaListaUmNosResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		List<Integer> primes = result.getPrimes();
		assertEquals(2, primes.size());
		result.add(Integer.valueOf(2), Integer.valueOf(7));
		primes = result.getPrimes();
		assertEquals(2, primes.size());		
	}
	
	@Test
	public void adicionaPrimeiroPrimoRepetidoDaListaDoisNosResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		List<Integer> primes = result.getPrimes();
		assertEquals(2, primes.size());
		result.add(Integer.valueOf(7), Integer.valueOf(2));
		primes = result.getPrimes();
		assertEquals(2, primes.size());		
	}
	
	@Test
	public void adicionaSegundoPrimoRepetidoDaListaUmNosResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		List<Integer> primes = result.getPrimes();
		assertEquals(2, primes.size());
		result.add(Integer.valueOf(5), Integer.valueOf(3));
		primes = result.getPrimes();
		assertEquals(2, primes.size());		
	}
	
	@Test
	public void adicionaSegundoPrimoRepetidoDaListaDoisNosResultados(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		List<Integer> primes = result.getPrimes();
		assertEquals(2, primes.size());
		result.add(Integer.valueOf(3), Integer.valueOf(7));
		primes = result.getPrimes();
		assertEquals(2, primes.size());		
	}
	
	@Test
	public void criaVetorComDuasPosicoesContendoComposicaoDePrimos(){
		final Integer nPrime1 = Integer.valueOf(2);
		final Integer nPrime2 = Integer.valueOf(3);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		Integer[] composition = result.createComposition(nPrime1, nPrime2);
		assertEquals(2, composition.length);
		assertEquals(nPrime1, composition[0]);
		assertEquals(nPrime2, composition[1]);
	}
	
	
	@Test
	public void montaListaContendoResultadosDePrimos(){
		final Integer nPrime1 = Integer.valueOf(5);
		final Integer nPrime2 = Integer.valueOf(5);
		final Integer nPrime3 = Integer.valueOf(3);
		final Integer nPrime4 = Integer.valueOf(7);
		final Result result = Result.valueOf(nPrime1, nPrime2);
		result.add(nPrime3, nPrime4);
		List<Integer[]> results = result.getCompositionsResults();
		assertNotNull(results);
		assertEquals(2, results.size());
		assertEquals(nPrime1, results.get(0)[0]);
		assertEquals(nPrime2, results.get(0)[1]);
		assertEquals(nPrime3, results.get(1)[0]);
		assertEquals(nPrime4, results.get(1)[1]);
	}
	
	
}
