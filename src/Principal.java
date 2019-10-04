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
			
			cinta.add(0," "); //coloco un segmento vacio de la cinta antes
			
			for (int i = 1; i <= entrada.length(); i++) {
				
				cinta.add(i,String.valueOf(entrada.charAt(i-1)) );
			}
			cinta.add(" ");  //coloco un segmento vacio de la cinta al final
			imprime_cinta();
	}
	
	public boolean analiza_entrada() {
		 int l = entrada.length(); 
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
	
	public void estado_q0(int p_anterior) {
		p_actual = p_anterior + 1; 
		
		if(cinta.get(p_actual).equals("a")) {
			cinta.remove(p_actual); // elimina la antigual
			cinta.add(p_actual, "A"); // Anade el valor analizado 
	        System.out.println("\n[a:"+cinta.get(p_actual)+":R]->Q1");
			imprime_cinta();
			estado_q1(p_actual);
		} 
		
		while(cinta.get(p_actual).equals("B")) {
			 System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0");
		     p_actual++; 
		}//analiza la cinta en busca de alguna a o b
		
		if(cinta.get(p_actual).equals(" ")) {
			System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q2");
			estado_q2();
		}
	}//Q0
	public void estado_q1(int p_anterior) {
		p_actual = p_anterior + 1; //analizo la posicion seguiente
		
		while(cinta.get(p_actual).equals("a")) {
	        System.out.print("\n[a:"+cinta.get(p_actual)+":R]->Q1");
	        p_actual++;
		}// bucle Q1
		
        if(cinta.get(p_actual).equals("b")) {
        	cinta.remove(p_actual);
        	cinta.add(p_actual,"B");
	        System.out.println("\n[b:"+cinta.get(p_actual)+":L]<-Q3");
			imprime_cinta();
        	estado_q3( p_actual);	        	
        } else if(cinta.get(p_actual).equals("B")){
	        System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q4");
			imprime_cinta();
        	estado_q4(p_actual);	    
        }
        
	}//Q1
	public void estado_q2() {
        System.out.println();
        System.out.println();
		imprime_cinta();
        System.out.println();
        System.out.println("Fin del recorrido!");
        System.exit(0);
        		
	}//Q2
	public void estado_q3(int p_anterior) {
		p_actual = p_anterior - 1; //analizo la posicion posterior
        while(cinta.get(p_actual).equals("a") || cinta.get(p_actual).equals("B")) {
	        System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":L]<-Q3");
	        p_actual--;
        }//bucle q3
        if(cinta.get(p_actual).equals("A")) {
	        System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q0");
	        estado_q0(p_actual);
        }
        /*
         * Este metodo retrocedera hasta encontrar la primera A y apartir de alli ara nuevamente el analicis de par en par
         * 
         * */
		
	}//Q3
	public void estado_q4(int p_anterior) {
		p_actual = p_anterior + 1;
		while(cinta.get(p_actual).equals("B")){
			 System.out.print("\n["+cinta.get(p_actual)+":"+cinta.get(p_actual)+":R]->Q4");
		     p_actual++;
		}
		
		if(cinta.get(p_actual).equals("b")) {
        	cinta.remove(p_actual);
        	cinta.add(p_actual,"B");
	        System.out.println("\n[b:"+cinta.get(p_actual)+":L]<-Q3");
			imprime_cinta();
        	estado_q3( p_actual);
		}
		
		
	}//Q4
	
	public void imprime_cinta() {
		System.out.print("\tCINTA\n");
		System.out.print(" [");
		for (String string : cinta) {
			System.out.print(" - "+string);
		}
		System.out.print(" -]");
	}
	
	public static void main(String args[]) {
		Principal p = new Principal("aaaaaaaaabbbbbbbbb");
		System.out.println("\nEntrada valida para MT: "+p.analiza_entrada());
		p.estado_q0(0);
	}
}//end class
