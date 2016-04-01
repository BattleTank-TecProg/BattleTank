![alt tag](http://cifmc.fis.unb.br/ivefras/logounb.png)

# Universidade de Brasília - Campus Gama

 **Disciplina**: Técnicas de programação

 **Professor**: Maurício Serrano

 **Linguagem do Projeto**: Java



# Folha de padronização de código



## Integrantes:

**Nicácio Arruda Bezerra Neto** - 130015857

**João Paulo Busche da Cruz** - 140023348

**Matheus Henrique Sousa Costa** - 140028455

**Ruan N. Herculano Pereira** - 130145173



## Índice:



[1. Objetivo](#1-objetivo)

[2. Comentários](#2-comentários)

[3. Declaração de Variáveis](#3-declaração-de-variáveis)

[4. Declaração de Classes](#4-declaração-de-classes)

[5. Indentação](#5-indentação)
	
[6. Quebra de linha](#6-quebra-de-linha)

[7. Sintaxe](#7-sintaxe)

[8. Declarações](#8-declarações)


# 1. Objetivo:


Esta folha de padronização de código tem por objetivo tornar padrão toda e qualquer forma de escrita de código no projeto, seja em forma de código ou comentário.

# 2. Comentários:


* Os comentários deverão ser postos sempre acima da expressão a que se referem;
* Todos os comentários deverão começar com letra maiúscula e terminar com ponto final;
* Não haverá espaço depois ou antes do operador utilizado para declarar o comentário;
* Os comentários em bloco (mais de uma linha) deverão ser escritos com inicio "```/*```" e fim "```*/```";
*  Apenas os comentários de linha única deverão começar com ‘//’;
* Os comentários sempre deverão estar alinhados com a instrução a que se referem;
Exemplo:


# 3. Declaração de Variáveis:


* As variáveis serão declaradas de preferência no início da classe, ou no início do método, em hipótese alguma as variáveis poderão ser declaradas no meio ou fim do código, salvo em casos em que seja de estrita necessidade ao funcionamento do algoritmo;
* Deverão ser evitadas as mudanças e conversões de tipo de variável;
* As variáveis deverão ser inicializadas após a declaração;
* A declaração de variáveis será realizada em 1 por linha;
* Os nomes de variáveis deverão ser significativos;
* Nomes compostos devem ser escritos com o primeiro nome em minúsculo e a primeira letra de cada palavra subsequente em maiúsculo
* Não deve-se separar o nome das variáveis com ‘-,/,etc’;
* Constantes: As constantes sempre devem ser utilizadas para substituir valores que não mudam.


### Exemplo:

```	java

private int targetAngle = 0;    
private int targetAngleody = 0;
private int previousTargetAngleBody = 0;
protected int bodyAngle = 0;
protected int turrentAngle = 0;
protected int coolDownShell = 33;
public int coolDownShell = 33;
public double distance = 0;
public boolean forward = 0;
```

# 4. Declaração de classes:


* Todas as classes deverão conter documentação ```javaDoc``` e deverão ser escritas seguindo o padrão ```CamelCase``` mais precisamente ```UpperCamelCase```, onde a palavra é iniciada com Maiúsculas e unidas sem espaços.
* As variáveis deverão ser declaradas na seguinte ordem: 1ª - ```public```, 2ª - ```protected``` e 3ª - ```private```.
Todas as classes deverão conter o método construtor.


# 5. Indentação:


* Usar indentação padrão do elipse de uma tabulação;
* Terminar cada arquivo com uma linha em branco;
* Usar espaços ao redor de operadores, depois de vírgulas e ponto e vírgula, e ao redor de chaves;
* Não usar espaços antes de ```(```, ```[``` ou depois de ```]```, ```)```;
* Quando não houver conteúdo entre ```{}```,```()```, preencher com um espaço ```( )```,```{ }```;
* Deixar uma linha em branco acima da declaração do método;
* Cada nova função/método deve ser iniciada junto a extremidade esquerda da tela;
* A indentação dos operadores deve ser separada de seus operandos.

### Exemplos corretos:
```java

(parameter_1 == parameter_2)
(parameter_1 != parameter_2)
```
### Exemplos incorretos:
```java

(parameter_1== parameter_2)
(parameter_1 !=parameter_2)
(parameter_1==parameter_2)
```


## 5.1 Chaves


* As chaves deverão ser abertas logo após a declaração das operações e deverão ser fechadas na linha abaixo do fim das operações;
* Antes de utilizar chaves sempre deve haver um espaço.

# 6. Quebra de linha:


* Deve-se evitar linhas muito longas;
* A nova linha deve começar no mesmo nível da linha da expressão de origem.


# 7. Sintaxe:


* Classe: Primeira letra de cada palavra maiúscula
* Membros de uma classe e variáveis locais: Primeira letra minúscula
* Constantes de classe: Todas as letras em caixa alta (Maiúsculas)
* Palavras compostas não devem ser separadas, devem ser escritas juntas e não deve haver qualquer símbolo entre elas.


# 8. Declarações:


##	8.1 - Métodos:
* Métodos deverão ter o primeiro nome em minúsculo;
* Não deve haver espaço entre os parênteses e os atributos;
* O parêntese de abertura deve estar posicionado após  ao nome do método;
* Caso existe mais de um parâmetro sendo passado, deve ser colocado uma vírgula rente a variável, e acrescentado um espaço logo em seguida. Ao escrever o ultimo parâmetro, não deve existir nenhum espaço entre o parâmetro e o fechamento dos parênteses;
* A passagem de parâmetro deve ser feita começando a ser escrita rente ao parêntese.

###	Exemplo Declaração:
```java
public void damage(int damagePoint) {
  if (damagePoint == -1) {
		active = true;
		enganed = true;
    return;
	}
	HP -= damagePoint;
	engaged = true;
}
```
###	Exemplo passagem de parâmetros:
* #### Corretos:
```java
method(parameter);
method(parameter_1, parameter_2);
```
* #### Incorretos:
```java
method( parameter );
method( parameter);
method(parameter );
method(parameter_1 , parameter_2 );
method( parameter_1, parameter_2);
method(parameter_1 , parameter_2);
```

## 8.2 Classes
* As Classes deverão ser declaradas com a primeira letra maiúscula;
* Todas as Classes deverão ter documentação ```javaDoc```;
* Deverão ter o método construtor.

###	Exemplo:
```java

public class Tinker implements Runnable {

	public Tinker(int i) {
		delay = i;
		t = new Thread(this);
		t.start();
		isTicking = false;
	}
}
```

##	8.3 If/Else
* Não deve haver espaço entre dois ou mais parênteses;
* Não deve haver espaço entre as operações e os parênteses;
* Todo "```if```" deverá ser acompanhado do "```else```" obrigatoriamente;
* Quando não houver nada para se fazer no ‘else’, deverá ser posto um comentário dentro do "```else```" sinalizando que não há nada a se fazer;
* O "```else```" deverá iniciar na mesma linha do fim do "```if```";
* Deve haver um espaço entre o fim do "```if```" e o início do "```else```";
* Mesmo que as instruções só tenham uma linha o uso das chaves é obrigatória.
###	Exemplo:
```java
if ((main.timer + randomNumber1 * 3) % 1000 == 0) {
	if (randomNumber2 > 50) {
		randomNumber = 50;
	} else {
		randomNumber = 51;
	}
}
```

##	8.4 Switch Case
* Deve haver uma linha entre a declaração e o primeiro caso;
* Cada caso do "```switch```" deve estar separado por uma linha;
* Todo switch deverá ter um "```default```";
* Quando não houver nada a declarar no default deve conter um comentário sinalizando que não há nada a se fazer.
###	Exemplo:

```java

switch (WeekDay) {

	case 1:
		System.out.println("Sunday");
		break;

	case 2:
		System.out.println("Monday");
		break;

	case 3:
		System.out.println("Tuesday");
		break;

	case 4:
		System.out.println("Wednesday");
		break;

	default:
		System.out.println("Thats isn't a valid day!");

}
```

##	8.5 While
* Não deve haver espaço entre dois ou mais parênteses;
* Não deve haver espaço entre as operações e os parênteses;
* Mesmo que as instruções só tenham uma linha o uso das chaves é obrigatória.
###	Exemplo:

```java
while (count < 50) {
	System.out.println("Number Repetition: " + count);
	count++;
}
```

## 8.6 Do/While
* Deve haver um espaço entre o "```do```" e a chave de abertura;
* Não deve haver espaço entre as operações e os parênteses;
* O "```while```" deve estar posicionado na mesma linha da chave de encerramento término do "```do```";
* Deve haver um espaço entre a chave do término do "```do```" e o "```while```".

### Exemplo:

```java
do {
	fireActionPerformed();
	try {
		Thread.sleep(delay);
	} catch (InterruptedException interruptedexception) {
		System.out.println("WARNING: Ticker thread interrupted");   
	}
} while (true);
```

## 	8.7 For
* Não deve ter espaço entre o parênteses e a operação;
* Depois de cada ponto e vírgula dentro dos parênteses deve haver um espaço;
* Mesmo que as instruções só tenham uma linha o uso das chaves é obrigatória;
* Entre operadores e números deve haver espaço.
### 	Exemplo:
* #### Correto:

```java
//draw body
for (int i = 0; i < body.lenght; i++) {
	body[i].draw();
}
```

* #### Incorreto:

```java
//update boundary
for(int i = 0; i < 5; i++) {
	boundary[i].update();
}
```

##  8.8 Try/Catch
* Deve haver espaço entre o "```try```" e a chave de abertura;
* Não deve haver espaço entre as operações e os parênteses;
* O "```catch```" deve estar posicionado na mesma linha do teŕmino do "```try```";
* Deve haver um espaço entre a chave do término do "```try```" e o "```catch```".

### Exemplo:

```java
try {
		Thread.sleep(delay);
} catch (InterruptedException interruptedexception) {
		System.out.println("WARNING: Ticker thread interrupted");   
}
```
