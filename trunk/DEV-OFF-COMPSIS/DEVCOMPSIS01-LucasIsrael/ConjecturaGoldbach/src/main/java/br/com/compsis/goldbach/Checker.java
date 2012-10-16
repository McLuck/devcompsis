package br.com.compsis.goldbach;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * DOCUMENTA��O DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * <p>A conjectura de Goldbach determina que qualquer n�mero 
 * PAR maior que 2 pode ser representado atrav�s da soma de 
 * 2 n�meros primos. </p>
 * 
 * <p>Iremos construir um programa para ler um n�mero e 
 * determinar quais s�o os n�meros primos que, somados, 
 * formam o n�mero informado na entrada do programa.</p>
 * 
 * <p>
 * O padr�o para numeros primos a serem carregados � de (0 - 1000) zero a mil,
 * mas pode ser alterado no construtor alternativo informando o numero m�ximo. 
 * </p>
 *<br>
 *	<p><b>Limita��es</b>: </p>
 *<ul>
 *	<li>Em nosso universo de testes, iremos considerar n�meros entre 2 e 1966 (inclusive);</li>
 *	<li>Nenhuma biblioteca ou framework poder� ser utilizado. (Com exce��o do <b>Junit</b>, quando <i>Java</i>)</li>
 *	<li>No caso do desenvolvimento em <i>Java</i>, somente poder� ser utilizado classes e m�todos nativos da API (<b><i><u>JDK6</u></i></b>, apenas);</li>
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
 * HIST�RICO DE DESENVOLVIMENTO: <br>
 * 08/10/2012 - @author LucasIsrael - Primeiva vers�o da classe. <br>
 *<br>
 *<br>
 */
public class Checker {
	/**
	 * Lista contendo os n�meros primos declarada sincronizada para evitar ModificationConcurrentException
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
	 * Carrega todos os n�meros primos que ser�o utilizados.
	 * Como o maior n�mero � 1966, ent�o o maior n�mero primo 
	 * necess�rio ser� o 983 (983 + 983 = 1966).
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
	 * Este m�todo ir� esvaziar toda lista de n�meros primos.
	 */
	void limparPrimos() {
		primes.clear();
	}

	/** 
	 * <p> Verifica se o parametro passado � valido</p>
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
	 * @throws InvalidParameterException lan�ada no caso do parametro ser impar ou menor que quatro
	 */
	protected Result searchComposition(final int aNumber) {
		if(!verifyParameter(aNumber)){
			throw getIllegalArgumentException();
		}
		int referencePosition = getNearPrimePosition(aNumber);
		/**
		 * Para encontrar os resultados, deve-se percorrer a lista de um lado para o outro somando
		 * os valores das posi��es e armazenando-as no result, caso a soma forne�a a resposta correta.
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
	 * Busca na lista de numeros primos a posi��o que se encontra o primo 
	 * que seja igual ou inferior ao n�mero par informado no parametro.
	 * @param aNumber Um n�mero par maior que 2
	 * @return a posi��o da lista de n�meros primos
	 * @throws InvalidParameterException lan�ada no caso do parametro ser impar ou menor que quatro
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
