/*
 * McLuck Sistemas<br>
 * Produto ConjecturaGoldbach - ConjecturaGoldbach<br>
 *
 * Data de Criação: 13/10/2012<br>
 * <br>
 * Todos os direitos reservados.
 */

package br.com.compsis.goldbach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 * DOCUMENTAÇÃO DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Wrapper para resultado da busca do <code>Checker</code>. <br>
 * Esta classe irá encapsular os números resultantes da consulta (Um <code>ObjectValue</code>)
 * <br>
 * HISTÓRICO DE DESENVOLVIMENTO: <br>
 * 13/10/2012 - @author LucasIsrael - Primeiva versão da classe. <br>
 *<br>
 *<br>
 */

public final class Result {
	List<Integer> prime1;
	List<Integer> prime2;
	
	private Result(){}
	
	public static Result valueOf(final Integer prime1, final Integer prime2){
		Result result = new Result();
		result.prime1 = new ArrayList<Integer>();
		result.prime2 = new ArrayList<Integer>();
		result.prime1.add(prime1);
		result.prime2.add(prime2);
		return result;
	}
	
	/**
	 * Verifica se o numero informado no parametro esta contido no resultado pesquisado
	 * @param prime O numero a ser verificado
	 * @return a Boolean 
	 */
	public boolean contains(final Integer aPrime){
		return (prime1!= null && prime2!=null) && (prime1.contains(aPrime) || prime2.contains(aPrime));
	}
	
	/**
	 * Monta uma lista contendo todos os numeros primos do resultado
	 * @return  uma lista contendo todos os números primos do resultado
	 */
	public List<Integer> getPrimes(){
		List<Integer> result = new ArrayList<Integer>();
		result.addAll(prime1);
		result.addAll(prime2);
		return result;
	}
	
	/**
	 * Adiciona um novo composto de primos que, somados, deve ter o resultado esperado
	 * @param aPrime1 
	 * @param aPrime2
	 */
	protected Result add(final Integer aPrime1, final Integer aPrime2){
		if(prime1==null || prime2 == null){
			prime1 = new ArrayList<Integer>();
			prime2 = new ArrayList<Integer>();
		}
		//Evita que se repitam resultados
		if(!prime1.contains(aPrime1) && !prime2.contains(aPrime1)
				&& !prime1.contains(aPrime2) && !prime2.contains(aPrime2)){
			prime1.add(aPrime1);
			prime2.add(aPrime2);
		}
		return this;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((prime1 == null) ? 0 : prime1.hashCode());
		result = prime * result + ((prime2 == null) ? 0 : prime2.hashCode());
		return result;
	}
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Result)) {
			return false;
		}
		Result other = (Result) obj;
		if (prime1 == null) {
			if (other.prime1 != null) {
				return false;
			}
		} else if (!prime1.equals(other.prime1)) {
			return false;
		}
		if (prime2 == null) {
			if (other.prime2 != null) {
				return false;
			}
		} else if (!prime2.equals(other.prime2)) {
			return false;
		}
		return true;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compositions=");
		for (Integer[] results : getCompositionsResults()) {
			builder.append(Arrays.toString(results));
		}
		return builder.toString();
	}
	
	/**
	 * Cria um vetor com duas posicoes contendo uma composicao de primos informada nos parametros
	 * @param aPrime1 O primeiro primo da composicao
	 * @param aPrime2 O Segundo primo da composicao
	 * @return Um vetor de duas posicoes contendo a composicao de primos
	 */
	Integer[] createComposition(final Integer aPrime1, final Integer aPrime2){
		return new Integer[]{aPrime1, aPrime2};
	}
	
	/**
	 * Cria uma lista onde cada elemento é um vetor de duas posições contendo uma 
	 * composição válida de primos do Resultado
	 * @return Uma lista de vetores de Inteiros de duas posições
	 */
	public List<Integer[]> getCompositionsResults(){
		List<Integer[]> results = new ArrayList<Integer[]>();
		for (int i=0; i< prime1.size(); i++) {
			results.add(createComposition(prime1.get(i), prime2.get(i)));
		}
		return results;
	}
}
