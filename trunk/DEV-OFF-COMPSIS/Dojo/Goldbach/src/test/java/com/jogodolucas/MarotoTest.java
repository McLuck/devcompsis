package com.jogodolucas;

import static junit.framework.Assert.*;

import org.junit.Test;

public class MarotoTest {
	
	@Test
	public void ehNumeroParTest(){
		Maroto maroto = new Maroto();
		assertTrue(maroto.ehNumeroPar(10));
	}

	@Test
	public void naoEhNumeroParTest(){
		Maroto maroto = new Maroto();
		assertFalse(maroto.ehNumeroPar(11));
	}
	
	@Test
	public void ehNumeroPrimoTest(){
		Maroto maroto = new Maroto();
		assertTrue(maroto.ehNumeroPrimo(937));
	}

	@Test
	public void naoEhNumeroPrimoTest(){
		Maroto maroto = new Maroto();
		assertFalse(maroto.ehNumeroPrimo(15));
	}

	@Test
	public void conjecturaGoldBach8Test(){
		Maroto maroto = new Maroto();
		assertTrue(maroto.conjecturaGoldBach(8));
		assertEquals(3, maroto.getNumero1());
		assertEquals(5, maroto.getNumero2());
	}
	
	@Test
	public void conjecturaGoldBach10Test(){
		Maroto maroto = new Maroto();
		assertTrue(maroto.conjecturaGoldBach(10));
		assertEquals(3, maroto.getNumero1());
		assertEquals(7, maroto.getNumero2());
	}
	
	@Test
	public void conjecturaGoldBach2Test(){
		Maroto maroto = new Maroto();
		assertFalse(maroto.conjecturaGoldBach(2));
	}
	
	@Test
	public void conjecturaGoldBach2NegativoTest(){
		Maroto maroto = new Maroto();
		assertFalse(maroto.conjecturaGoldBach(-2));
	}
	
}
