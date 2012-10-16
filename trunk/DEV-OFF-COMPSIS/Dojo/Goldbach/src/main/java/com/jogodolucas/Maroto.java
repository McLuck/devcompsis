package com.jogodolucas;

import java.util.ArrayList;
import java.util.List;

public class Maroto {
	
	private int numero1;
	private int numero2;

	public boolean ehNumeroPar(int par) {
		if(par % 2 == 0){return true;}
		
		
		return false;
	}

	public boolean ehNumeroPrimo(int numero) {
		
		
		if(isPossivelPrimo(numero)){		
			int limite =  (int) Math.sqrt(numero);
			for(int i = 3; i <= limite;i++){
				if(!ehNumeroPar(i)){
					if(numero % i == 0){
						return false;
					}
				}
			}
			
			return true;
			
		}
		
		return false;
	}
	
	

	public boolean conjecturaGoldBach(int numero) {
		
		if (ehNumeroPar(numero)) {
			List<Integer> numerosPrimos = new ArrayList<Integer>();
			for (int i = 1; i < numero; i++) {
				if (ehNumeroPrimo(i)) {
					numerosPrimos.add(i);					
				}
			}
			
			
			int size = numerosPrimos.size()-1;
			int inicial = 0;
					
			while(inicial < size){
										
				int soma = numerosPrimos.get(inicial) + numerosPrimos.get(size);
				
				
				if(soma == numero){
					setNumero1(numerosPrimos.get(inicial));
					setNumero2(numerosPrimos.get(size));					
					return true;					
				} 
				
				if(soma > numero){
					size--; 
				} else {
					inicial ++;
				}	
			}
		}
		
		return false;
	}

	private boolean isPossivelPrimo(int numero) {
		if(numero > 2 && !ehNumeroPar(numero)){
			return true;
		}
		return false;
	}

	public int getNumero1() {
		return numero1;
	}

	public void setNumero1(int numero1) {
		this.numero1 = numero1;
	}

	public int getNumero2() {
		return numero2;
	}

	public void setNumero2(int numero2) {
		this.numero2 = numero2;
	}
	
	

}
