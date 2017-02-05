package util;

import test.AbstractTestCase;

public class Assertions extends AbstractTestCase{
	
	public static void assertTrue(java.lang.String message, boolean condition){
		try{
			org.junit.Assert.assertTrue(message, condition);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertTrue(boolean condition){
		try{
			org.junit.Assert.assertTrue(condition);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	
	public static void assertNotNull(Object object){
		try{
			org.junit.Assert.assertNotNull(object);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertNotNull(java.lang.String message, Object object){
		try{
			org.junit.Assert.assertNotNull(message, object);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertNotEquals(java.lang.String message, Object object, Object object2){
		try{
			org.junit.Assert.assertNotEquals(message, object, object2);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertNull(Object object){
		try{
			org.junit.Assert.assertNull(object);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertNull(String message, Object object){
		try{
			org.junit.Assert.assertNull(message, object);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertFalse(boolean condition){
		try{
			org.junit.Assert.assertFalse(condition);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	public static void assertFalse(java.lang.String message, boolean condition){
		try{
			org.junit.Assert.assertFalse(message, condition);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void assertEquals(String message, boolean expected, boolean actual){
		try{
			org.junit.Assert.assertEquals(message, expected,actual);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
	}
	
	public static void assertEquals(boolean expected, boolean actual){
		try{
			org.junit.Assert.assertEquals(expected,actual);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
	}
	
	public static void assertEquals(String expected, String actual){
		try{
			org.junit.Assert.assertEquals(expected,actual);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
	}
	
	public static void assertEquals(int expected, int actual){
		try{
			org.junit.Assert.assertEquals(expected,actual);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
	}
	
	public static void assertEquals(String message, int expected, int actual){
		try{
			org.junit.Assert.assertEquals(message, expected,actual);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
	}
	
	public static void assertEquals(String message, String expected, String actual){
		try{
			org.junit.Assert.assertEquals(message, expected,actual);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
	}
	public static void fail(java.lang.String message){
		try{
			org.junit.Assert.fail(message);
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	
	public static void fail(){
		try{
			org.junit.Assert.fail();
		}catch(AssertionError e){
			Util.takeScreenshot();
			throw e;
		}
		
	}
	

}
