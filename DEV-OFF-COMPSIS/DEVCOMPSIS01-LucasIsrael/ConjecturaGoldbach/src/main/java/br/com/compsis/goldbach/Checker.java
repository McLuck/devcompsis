package br.com.compsis.goldbach;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * <p>A conjectura de Goldbach determina que qualquer número 
 * PAR maior que 2 pode ser representado através da soma de 
 * 2 números primos. </p>
 * 
 * <p>Iremos construir um programa para ler um número e 
 * determinar quais são os números primos que, somados, 
 * formam o número informado na entrada do programa.</p>
 * 
 * <p>
 * O padrão para numeros primos a serem carregados é de (0 - 1000) zero a mil,
 * mas pode ser alterado no construtor alternativo informando o numero máximo. 
 * </p>
 *<br>
 *	<p><b>Limitações</b>: </p>
 *<ul>
 *	<li>Em nosso universo de testes, iremos considerar números entre 2 e 1966 (inclusive);</li>
 *	<li>Nenhuma biblioteca ou framework poderá ser utilizado. (Com exceção do <b>Junit</b>, quando <i>Java</i>)</li>
 *	<li>No caso do desenvolvimento em <i>Java</i>, somente poderá ser utilizado classes e métodos nativos da API (<b><i><u>JDK6</u></i></b>, apenas);</li>
 *</ul>	
 *</p> 
 *	<br><br>
 * <p>
 * 	<b>FORMA DE USO:</b>
 * </p>
 * <p>
 * <code> 
 * 
 * 		Checker checker = new Checker(); <br>
 * 		Result result = checker.searchComposition(informado);
 * </code>
 * </p>
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 08/10/2012 - @author LucasIsrael - Primeiva versão da classe. <br>
 *<br>
 *<br>
 */
public class Checker {
	/**
	 * Lista contendo os números primos declarada sincronizada para evitar ModificationConcurrentException
	 */
	List<Integer> primes = (List<Integer>) Collections.synchronizedList(new ArrayList<Integer>());
	
	/** 
	 * Instancia um novo Checker
	 */
	public Checker() {
		loadPrimes(new BigInteger("2000"));
	}

	/**
	 * Construtor para carregar numeros primos diferente de 0 a 2000
	 * @param maxPrime Um BigInteger representando o maior numero primo que estara disponivel
	 */
	public Checker(final BigInteger maxPrime){
		loadPrimes(maxPrime);		
	}
	
	/**
	 * Carrega todos os números primos que serão utilizados.
	 * Como o maior número é 1966, então o maior número primo 
	 * necessário será o 983 (983 + 983 = 1966).
	 * @param limiteFinal Um limite de numeros primos a serem carregados para os testes
	 */
	void loadPrimes(final BigInteger limiteFinal){
		long inicio = System.currentTimeMillis();
		BigInteger bigInteger = BigInteger.ONE;
		while(true){
			if(bigInteger.isProbablePrime(7)){
				primes.add(Integer.valueOf(bigInteger.intValue()));
			}
			bigInteger = bigInteger.add(BigInteger.ONE);
			if(bigInteger.intValue()>limiteFinal.intValue()){
				break;				
			}
		}
		long fim = System.currentTimeMillis();
		System.out.println("Tempo gasto carregando numeros primos: "+(fim-inicio)+" milis");
	}
	
	public static void main(String[] args) {
		
	}

	/** 
	 * Este método irá esvaziar toda lista de números primos.
	 */
	void limparPrimos() {
		primes.clear();
	}

	/** 
	 * <p> Verifica se o parametro passado é valido</p>
	 * <p>De acordo com o contrato, deve ser par e maior que 2.</p>
	 * @param i O parametro a verificado
	 */
	boolean verifyParameter(int i) {
		return i%2==0 && i > 2;
	}

	/** 
	 * busca os numeros primos que somados resultam no numero informado
	 * @param aNumber Um numero inteiro que sera consultado
	 * @return Um <code>Result</code> contendo as listas de primos
	 * @throws InvalidParameterException lançada no caso do parametro ser impar ou menor que quatro
	 */
	protected Result searchComposition(final int aNumber) {
		if(!verifyParameter(aNumber)){
			throw getIllegalArgumentException();
		}
		int referencePosition = getNearPrimePosition(aNumber);
		/**
		 * Para encontrar os resultados, deve-se percorrer a lista de um lado para o outro somando
		 * os valores das posições e armazenando-as no result, caso a soma forneça a resposta correta.
		 * EX:
		 * PRIMOS  : 2 3 5 7 11 13 17 19
		 * TESTE 1 : somar 2 com 19
		 * TESTE 2 : somar 3 com 19
		 * TESTE 3 : somar 5 com 19
		 * TESTE 4 : somar 11 com 19
		 * TESTE 5 : somar 13 com 19
		 * TESTE 6 : somar 17 com 19 
		 */
		Result result = null;
		
		int start = 0, end = referencePosition;
	      while (start <= end) {
	         if (primes.get(start).intValue() + primes.get(end).intValue() == aNumber) {
	        	if (result==null){
	        		result = Result.valueOf(primes.get(start), primes.get(end));
	        	}  else {
	        		result.add(primes.get(start), primes.get(end));
	        	}
	        	end--;
	         } else if (primes.get(start).intValue() + primes.get(end).intValue()  < aNumber) start++;
	         else end--;
	      }
		return result;
	}
	
	private RuntimeException getIllegalArgumentException(){
		return new IllegalArgumentException("O numero informado deve ser par e maior que 2!");
	}

	/** 
	 * Busca na lista de numeros primos a posição que se encontra o primo 
	 * que seja igual ou inferior ao número par informado no parametro.
	 * @param aNumber Um número par maior que 2
	 * @return a posição da lista de números primos
	 * @throws InvalidParameterException lançada no caso do parametro ser impar ou menor que quatro
	 */
	int getNearPrimePosition(Integer aNumber) {
		if(!verifyParameter(aNumber.intValue())){
			throw getIllegalArgumentException();
		}
		
		for(int i = 0; i< primes.size(); i++){
			Integer value = primes.get(i);
			if(value.equals(aNumber)){
				return i;
			} else {
				if(value.intValue()>aNumber.intValue()){
					return i-1;
				}
			}
		}
		return -1;
	}
	
}
