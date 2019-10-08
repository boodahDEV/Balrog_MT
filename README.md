# Balrog_MT
Balrog es un pequeño desarrollo el busca como fin hacer una MT el en cual tenga como lenguaje valida (a)^n (b)^n


### Funcionamiento
Practicamente tiene como entrada cadenas pares de a y b (ambas minusculas) como ejemplo:
  * ab
  * aabb
  * aaaabbbb
  
El recorrido de los estados consta del analicis de la siguiendo el desarrollo de una MT:

#### Lenguaje:<br>

  <math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
    <mi>L</mi>
    <mo>=</mo>
    <mo fence="false" stretchy="false">{</mo>
    <msup>
      <mn>a</mn>
      <sup><mi>n</mi></sup>
    </msup>
    <msup>
      <mn>b</mn>
      <sup><mi>n</mi></sup>
    </msup>
    <mtext>&#xA0;</mtext>
    <mo>:</mo>
    <mi>n</mi>
    <mo>&gt;</mo>
    <mn>0</mn>
    <mo fence="false" stretchy="false">}</mo>
  </math>
  
  #### Donde el alfabeto seria:<br>
  
  <math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
    <mi mathvariant="normal">&#x03A3;<!-- Σ --></mi>
    <mo>=</mo>
    <mo fence="false" stretchy="false">{</mo>
    <mn>a</mn>
    <mo>,</mo>
    <mn>b</mn>
    <mo fence="false" stretchy="false">}</mo>
  </math>
 
  #### Los símbolos de cinta son: <br>
  <math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
    <mrow class="MJX-TeXAtom-ORD">
      <mi class="MJX-tex-caligraphic" mathvariant="script">T</mi>
    </mrow>
    <mo>=</mo>
    <mo fence="false" stretchy="false">{</mo>
    <mn>a</mn>
    <mo>,</mo>
    <mn>b</mn>
    <mo>,</mo>
    <mi>B (" "/ 32)</mi>
    <mo>,</mo>
    <mi>A</mi>
    <mo>,</mo>
    <mi>B</mi>
    <mo fence="false" stretchy="false">}</mo>
  </math>
  
``` text 
  siendo B el símbolo en blanco, espacio, o simplemente el ascii 32.
```  
  
#### La MT consta de cinco estados: <br>

<math xmlns="http://www.w3.org/1998/Math/MathML" display="block">
  { <msub>
    <mi>q</mi>
    <sub><mn>0</mn></sub>
  </msub>
  <mo>,</mo>
  <msub>
    <mi>q</mi>
    <sub><mn>1</mn></sub>
  </msub>
  <mo>,</mo>
  <msub>
    <mi>q</mi>
    <sub><mn>2</mn></sub>
  </msub>
  <mo>,</mo>
  <msub>
    <mi>q</mi>
    <sub><mn>3</mn></sub>
  </msub>
  <mo>,</mo>
  <msub>
    <mi>q</mi>
    <sub><mn>4</mn></sub>
  </msub> }
</math>

``` text 
  Los estados q0 y q4 son el inicial y el final, respectivamente.
```  

##### Quedando de la siguiente manera: 
<img src="https://github.com/boodahDEV/Balrog_MT/blob/master/bin/Screenshot_20191007_032900.png" > 

<br>
<br>
<br>
<br>

## Adjunto unas capturas de [BalrogMT_beta-1.0](https://github.com/boodahDEV/Balrog_MT/archive/BalrogMT_beta-1.0.zip):
<img src="https://github.com/boodahDEV/Balrog_MT/blob/master/bin/Screenshot_20191007_024617.png" > 
<img src="https://github.com/boodahDEV/Balrog_MT/blob/master/bin/Screenshot_20191007_024643.png" > 


## Adjunto unas capturas de [BalrogMT_beta-1.5 estable](https://github.com/boodahDEV/Balrog_MT/archive/BalrogMT_beta-1.5.zip):
<img src="https://raw.githubusercontent.com/boodahDEV/Balrog_MT/master/bin/Screenshot_20191008_173231.png?token=AIWRLPVQUPLZN2ZLLQLSLBC5TUIGG" > 
<img src="https://raw.githubusercontent.com/boodahDEV/Balrog_MT/master/bin/Screenshot_20191008_173242.png?token=AIWRLPQYPONQ4PEBS2DHRHS5TUIGQ" > 

