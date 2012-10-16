package br.com.compsis.goldbach;

import java.awt.TrayIcon.MessageType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/** 
 * DOCUMENTA��O DA CLASSE <br>
 * ---------------------- <br>
 * FINALIDADE: <br>
 * Classe principal do programa <br>
 * <br>
 * HIST�RICO DE DESENVOLVIMENTO: <br>
 * 13/10/2012 - @author LucasIsrael - Primeiva vers�o da classe. <br>
 *<br>
 *<br>
 * LISTA DE CLASSES INTERNAS: <br>
 */

public class Main {
	public static void main(String...args){
		String input = null;
		Checker checker = new Checker();
		do{
			if(input!=null){
				try{
					long inicio = System.currentTimeMillis();
					Result result = checker.searchComposition(Integer.valueOf(input));
					long fim = System.currentTimeMillis();
					System.out.println("!Tempo de consulta: ".concat(String.valueOf((fim-inicio))).concat(" milis"));
					if(result!=null){
						JOptionPane.showMessageDialog(null, String.valueOf(result));											
					} else {
						JOptionPane.showMessageDialog(null, 
								"A configura��o padr�o limita o programa em numeros primos at� 2000. Refa�a esta configura��o para esta entrada de dados!");						
					}
				}catch(IllegalArgumentException iex){
					JOptionPane.showMessageDialog(null, iex.getMessage(),"Erro",MessageType.ERROR.ordinal());
				}
			}
			input = JOptionPane
					.showInputDialog("Digite um n�mero par maior que 2 ou qualquer caracter para sair");
		}while(input!=null && isNumeric(input));
	}
	
	/**
	 * Valida se o texto informado � ou n�o num�rico
	 * @param aText A String a ser validada
	 * @return Um booleano informando se o texto � ou n�o num�rico
	 */
	private static boolean isNumeric(final String aText){
		Pattern p = Pattern.compile( "([0-9]*)" );
		Matcher m = p.matcher(aText);
		return !aText.isEmpty() && m.matches(); 
	}
}
