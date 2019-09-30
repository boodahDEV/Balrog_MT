import java.util.ArrayList;
/*
 * Creado por boodahDEV 2019
 * Solo valido para cadenas (a)^n (b)^n
 * 
 * */

public class Principal {
	public	String				entrada;
    public	ArrayList<String> 	cinta 	= 	new ArrayList<String>();
    public 	int 				p_actual;
    public 	int					tope_array; 
    public 	char 				letra_nueva;
    

	public Principal(String entrada) {
			this.entrada=entrada;
			for (int i = 0; i < entrada.length(); i++) {
				cinta.add(i, String.valueOf(entrada.charAt(i)));
			}
	}
	
	public boolean analiza_entrada() {
		 int l = cinta.size(); 
	        if (l%2 == 1){ 
	            return false; 
	        } // :v solo las entradas pares tienen a ser valida 
	        
	        int i = 0; 
	        int j = l-1; 
	        
	        //ahora verifico esta mrd a ver si tiene solo letras ab por lo menos
	        while (i<j) { 
	            if(entrada.charAt(i) != 'a' || entrada.charAt(j) != 'b'){ 
	                return false; 
	            } 	
	          i++; 
	          j--; 
	        } 
		return true;
	}// fiuncion para manejar los errores
	
	public void estado_q0() {
		int cabeza = 0, cola = cinta.size()-1;
		p_actual = cabeza;
		
		if(cinta.get(cabeza).equals("a")) {
			cinta.remove(p_actual); // elimina la antigual
			cinta.add(cabeza, "A"); // Anade el valor analizado 
	        System.out.println("\n[a:"+cinta.get(p_actual)+":R]");
			for (String string : cinta) {
				System.out.print("-"+string+"-");
			}
			estado_q1(p_actual);
		}
	}//Q0
	public void estado_q1(int p_anterior) {
		p_actual = p_anterior + 1; //analizo la posicion seguiente
		
		while(cinta.get(p_actual).equals("a")) {
	        System.out.print("\n[a:"+cinta.get(p_actual)+":R]");
	        p_actual++;
		}// bucle Q1
		
        if(!cinta.get(p_actual).equals("a")) {
        	cinta.remove(p_actual);
        	cinta.add(p_actual,"B");
	        System.out.println("\n[b:"+cinta.get(p_actual)+":L]");
			for (String string : cinta) {
				System.out.print("-"+string+"-");
			}
        	estado_q3( p_actual);	        	
        }
        
	}//Q1
	public void estado_q2(char letraanterior, int posicionanterior) {
		
	}//Q2
	public void estado_q3(int p_anterior) {
        
		
	}//Q3
	public void estado_q5(char letra, int posicion) {
		
	}//Q4
	
	public void imprime_cinta() {
		System.out.print("\tCINTA\n");
		System.out.print(" [");
		for (String string : cinta) {
			System.out.print("-"+string+"-");
		}
		System.out.print("]");
	}
	
	public static void main(String args[]) {
		Principal p = new Principal("aaaabb");
		System.out.println("\nEntrada valida para MT: "+p.analiza_entrada());
		p.estado_q0();
	}
}//end class
