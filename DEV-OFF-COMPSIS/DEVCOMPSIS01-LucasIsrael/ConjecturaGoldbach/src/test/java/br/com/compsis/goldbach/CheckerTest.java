package br.com.compsis.goldbach;

import org.junit.Test;
import static org.junit.Assert.*;
/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Teste unitário para classe Main <br>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 08/10/2012 - @author LucasIsrael - Primeiva versão da classe. <br>
 *<br>
 */

public class CheckerTest {

	@Test
	public void carregaOsNumerosPrimosNaLista() {
		Checker checker = new Checker();
		assertFalse(checker.primes.isEmpty());
		assertEquals(2, checker.primes.get(0).intValue());
	}
	
	@Test
	public void checaTodosOsNumerosPrimosDaLista(){
		Checker checker = new Checker();
		assertFalse(checker.primes.isEmpty());
		for (Integer prime : checker.primes) {
			boolean isPrime = true;
			for(int i=prime.intValue();i>0;i--){
				if(prime % i == 0 && i != prime && i != 1){
					isPrime = false;
				}
			}
			if(!isPrime){
				System.err.println("Numero nao primo encontrado na lista: "+prime);
			}
			assertTrue(isPrime);
		}
	}
	
	@Test
	public void deveLimparTodosOsItensDaListaDePrimos(){
		Checker checker = new Checker();
		checker.limparPrimos();
		assertTrue(checker.primes.isEmpty());
	}
	
	@Test
	public void verificaParametroParMaiorQueDois(){
		Checker checker = new Checker();
		assertFalse(checker.verifyParameter(2));
		assertTrue(checker.verifyParameter(4));
	}
	
	@Test
	public void verificaParametroImpar(){
		Checker checker = new Checker();
		assertFalse(checker.verifyParameter(3));
	}
	
	@Test
	public void encontraPosicaoDoPrimoNaLista(){
		Checker checker = new Checker();
		final Integer aNumber = Integer.valueOf(10);
		int position = checker.getNearPrimePosition(aNumber);
		/**
		 * TABELA PRIMOS
		 * PRIMOS: 	2 3 5 7 11
		 * POSICAO:	0 1 2 3  4
		 * 
		 * Deve encontrar a metade do numero informado, isto é, o "7" na posicao 3
		 */
		assertNotSame(-1, position);
		assertEquals(3, position);
	}
	
	@Test
	public void encontraPosicaoDoPrimoMenorNaLista(){
		Checker checker = new Checker();
		final Integer aNumber = Integer.valueOf(12);
		int position = checker.getNearPrimePosition(aNumber);
		/**
		 * TABELA PRIMOS
		 * PRIMOS: 	2 3 5 7 11 13
		 * POSICAO:	0 1 2 3  4  5
		 * 
		 * Deve encontrar o numero inferior da metade do numero informado, isto é, o "11" na posicao 5
		 */
		assertNotSame(-1, position);
		assertEquals(4, position);
	}
	
	@Test
	public void encontraOsDoisPrimosQueSomadosCompoemONumeroInformado(){
		Checker checker = new Checker();
		
		//5 + 3
		int informado = 8;
		Result result = checker.searchComposition(informado);
		assertNotNull(result);
		assertTrue(result.contains(Integer.valueOf(5)));
		assertTrue(result.contains(Integer.valueOf(3)));
		System.out.println(result.toString());
	}
	
	@Test
	public void encontraDoisResultadosDeComposicoesDePrimos(){
		Checker checker = new Checker();
		
		//7 + 3 && 5 + 5
		int informado = 10;
		Result result = checker.searchComposition(informado);
		assertNotNull(result);
		assertTrue(result.contains(Integer.valueOf(5)));
		assertTrue(result.contains(Integer.valueOf(3)));
		assertTrue(result.contains(Integer.valueOf(7)));
		assertEquals(2, result.getCompositionsResults().size());
		System.out.println(result.toString());
	}
}
