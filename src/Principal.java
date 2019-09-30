import java.util.Stack;
/*
 * Creado por boodahDEV 2019
 * Solo valido para cadenas (a)^n (b)^n
 * 
 * */


public class Principal {
	public	String		entrada;
	
	public Principal(String entrada) {
		this.entrada=entrada;
	}
	
	public boolean analiza_entrada() {
		Stack<String> cinta = new Stack<String>();
		 int l = entrada.length(); 
	        if (l%2 == 1){ 
	            return false; 
	        } // :v solo las entradas pares tienen a ser valida 
	        
	        int i = 0; 
	        int j = l-1; 
	        while (i<j) { 
	            if(entrada.charAt(i) != 'a' || entrada.charAt(j) != 'b'){ 
	                return false; 
	            } 
	            i++; 
	            j--; 
	        } 
		return true;
	}
	
	public static void main(String args[]) {
		System.out.println(new Principal("ccdd").analiza_entrada());
	}
}//end class
