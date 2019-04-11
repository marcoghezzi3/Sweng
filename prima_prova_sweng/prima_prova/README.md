# Corso di Ingegneria del Software a.a. 2017/18

## Laboratorio 5 (Prova in itinere I)

Ogni coppia di studenti procede ad effettuare il **fork** di questo repository.
Inoltre, concede i permessi di scrittura al proprio compagno di team e i **permessi di lettura** ai due docenti (`carlobellettini` e `matteocamilli`).

Una volta effettuato il **clone** del repository, il **driver** effettua `cd` nella directory del progetto ed esegue il comando:

```
bash start.sh email_unimi  username_bitbucket username_bitbucket_compagno
```

- controllare con il nuovo comando `git pair` che gli username siano corretti
- ogni volta che si cambia chi ha la tastiera (normalmente dopo aver fatto passare un nuovo test),
  dare il comando `git pair swap` e controllare con `git pair` che l'ordine delle username sia corretto

## Processo

Una volta effettuato il **clone** del repository, il gruppo esegue il comando `git flow init` all'interno della directory clonata.
Dopodiché, il gruppo implementa secondo la *metodologia TDD*
le specifiche riportate di seguito; in maggior dettaglio, ripete i passi seguenti fino ad aver implementato tutte le funzionalità richieste:

* crea un nuovo *branch* per la funzionalità corrente attraverso l'esecuzione del comando `git flow feature start`,
* implementa un test per le funzionalità richieste **procedendo nell'ordine** in cui sono specificate,
* verifica che **il codice compili correttamente**, ma l'**esecuzione del test fallisca**; solo a questo punto effettua un *commit* (usando `git add` e `git commit`) iniziando il messaggio di commit con la stringa `ROSSO:`,
* aggiunge la minima implementazione necessaria a realizzare la funzionalità, in modo che **il test esegua con successo**; solo a questo punto
  effettua un *commit* (usando `git add` e `git commit`) iniziando il messaggio di commit con la stringa `VERDE:`,
* procede, se necessario, al **refactoring** del codice, accertandosi che le modifiche non comportino il fallimento di alcun test; solo in questo caso fa seguire ad ogni
  passo un *commit* (usando `git add` e `git commit`) iniziando il messaggio di commit con la stringa `REFACTORING:`,
* esegue il *merge* del *branch* per la funzionalità sviluppata all'interno del *branch develop* attraverso il comando `git flow feature finish`,
* **solo in fase di rilascio**, esegue una *release* all'interno del *branch master* attraverso il comando `git flow release start` e successivamente `git flow release finish`,
* effettua un *push* (di tutti i *branch*) su Bitbucket con `git push origin --all --follow-tags`.

Al termine del laboratorio effettua una **ultima release**, un ultimo *push*, e **verifica su Bitbucket** che ci sia la completa traccia di *commit* effettuati.
Si suggerisce di eseguire i test non soltanto con Eclipse, ma anche eseguendo `./gradlew test` da riga di comando.


## Specifica dei requisiti

Le funzionalità di seguito elencate vanno implementate **nell'ordine in cui
sono presentate**. Si suggerisce  di prendere visione di una funzionalità per
volta (procedendo subito al ciclo di implementazione della medesima) in modo
da non farsi influenzare dalle specifiche successive circa le scelte di
progetto.

Obiettivo dell'esercizio è creare lo strumento `WordsPaginator`
che consente di ricavare informazioni utili sulla paginazione di elementi appartenenti ad un insieme di stringhe.
La classe `WordsPaginator` implementa l'interfaccia `Paginator`, la quale dichiara i seguenti metodi:

```
#!java
int pageCount();
int itemCount();
int pageItemCount(final int pageIndex);
String page(final int pageIndex);
String upperCasePage(final int pageIndex);
void remove(final int pageIndex);
```

Il comportamento del software è specificato dai seguenti punti.

**Step 1**

Il costruttore `public WordsPaginator(final String[] elements, final int pageSize)` inizializza un'istanza, dato l'insieme di elementi e il numero di elementi per pagina.
Più in dettaglio:

* se l'insieme di elementi è vuoto, l'invocazione di `pageCount()` e `itemCount()` restituisce `0` indipendentemente dal valore del parametro `pageSize`. Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{}, 2);
paginator.pageCount() restituisce 0
paginator.itemCount() restituisce 0
```

* se l'insieme di elementi non è vuoto, `pageCount()` restituisce il numero totale di pagine, mentre `itemCount()` restituisce il numero totale di elementi. Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet"}, 2);
paginator.pageCount() restituisce 3
paginator.itemCount() restituisce 5
```

* il metodo `public int pageItemCount(int pageIndex)`, dato l'indice associato alla pagina `pageIndex`, restituisce il numero di elementi di quella pagina se l'indice è valido, `-1` altrimenti. Notare che la numerazione delle pagine parte da `1` (non esiste la pagina `0`). Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet"}, 2);
paginator.pageCount() restituisce 3
paginator.itemCount() restituisce 5
paginator.pageItemCount(1) restituisce 2
paginator.pageItemCount(3) restituisce 1
paginator.pageItemCount(0) restituisce -1
paginator.pageItemCount(10) restituisce -1
```

* se l'oggetto `WordsPaginator` viene inizializzato dando in input un valore non valido per il parametro `pageSize` (minore o uguale a `0`), viene sollevata un'eccezione di tipo `IllegalArgumentException` con messaggio `"Il valore assegnato al parametro pageSize non è valido."`.

* il costruttore `public WordsPaginator(final String[] elements)` inizializza un oggetto `WordsPaginator` usando un valore di *default* `pageSize = 4`. Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet"});
paginator.pageCount() restituisce 2
paginator.itemCount() restituisce 5
paginator.pageItemCount(1) restituisce 4
paginator.pageItemCount(2) restituisce 1
paginator.pageItemCount(10) restituisce -1
```

Le funzionalità fino ad ora sviluppate rappresentano la **prima release** del software che deve essere etichettata come `v1.0`.

**Step 2**

* il metodo `public String page(int pageIndex)`, dato l'indice associato alla pagina `pageIndex`, restituisce la rappresentazione testuale di quella pagina se l'indice è valido, `""` (stringa vuota) altrimenti. La rappresentazione testuale è costituita dalla concatenazione delle parole contenute nella pagina separate da `"␣"` (singolo spazio). Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing"}, 3);
paginator.page(2) restituisce "sit amet consectetur"
```

* il metodo `public String toString()` (ereditato da `Object`) restituisce una rappresentazione testuale delle pagine costituita da un insieme di righe in cui ogni riga contiene `<indice_pagina>: <parole>`, dove `<parole>` rappresenta la concatenazione delle parole contenute nella pagina separate da `"␣"` (singolo spazio). Ad esempio, la rappresentazione testuale di

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing"}, 3);
```
è la seguente:
```
1: Lorem ipsum dolor
2: sit amet consectetur
3: adipiscing
```

* il metodo `public String upperCasePage(int pageIndex)`, dato l'indice associato alla pagina `pageIndex`, restituisce la rappresentazione testuale di quella pagina in maiuscolo se l'indice è valido, `""` (stringa vuota) altrimenti. Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing"}, 3);
paginator.upperCasePage(2) restituisce "SIT AMET CONSECTETUR"
```

Le funzionalità fino ad ora sviluppate rappresentano la **seconda release** del software che deve essere etichettata come `v2.0`.

**Step 3**

* il costruttore `public WordsPaginator(final Reader textReader, final int pageSize)` inizializza un'istanza, dato un testo e il numero di elementi per pagina. Il testo viene fornito in input tramite un generico `Reader`. Effettuare il test per questa funzionalità inizializzando l'oggetto `Reader` sia da stringa, sia da file `src/test/resources/input_example.txt`. Ad esempio, dopo l'invocazione di tale costruttore sul file `input_example.txt` con parametro `pageSize = 5` la rappresentazione testuale dell'oggetto sarà:
```
1: Lorem ipsum dolor sit amet
2: consectetur adipiscing elit Aenean hendrerit
3: tincidunt leo nec facilisis Morbi
4: elit arcu vestibulum non ante
5: at, venenatis faucibus odio
```

* il costruttore `public WordsPaginator(final Reader textReader, final String[] stopWords, final int pageSize)` inizializza un'istanza, dato un testo, un insieme di *stop word* e il numero di elementi per pagina. Il testo viene fornito in input tramite un generico `Reader`. Le *stop word* rappresentano un insieme di parole che devono essere eliminate dal testo durante la paginazione. Questo costruttore effettua anche la rimozione della punteggiatura (se presente). Effettuare il test per questa funzionalità inizializzando l'oggetto `Reader` sia da stringa, sia da file `src/test/resources/input_example_2.txt`. Ad esempio, inizializzando l'oggetto `Reader` col seguente testo:

```
Lorem ipsum dolor sit amet, consectetur adipiscing elit.
```
le seguenti *stop word*:
```
#!java
new String[]{"sit", "elit"}
```
e il parametro `pageSize = 5`, la rappresentazione testuale dell'oggetto `WordsPaginator` sarà:
```
1: Lorem ipsum dolor amet consectetur
2: adipiscing
```

* il metodo `public void remove(final int pageIndex)` rimuove la pagina di indice `pageIndex` dall'insieme delle pagine. Ad esempio,

```
#!java
Paginator paginator = new WordsPaginator(new String[]{"Lorem", "ipsum", "dolor", "sit", "amet", "consectetur", "adipiscing"}, 2);
paginator.page(2) restituisce "dolor sit"
paginator.pageCount() restituisce 4
paginator.itemCount() restituisce 7
paginator.remove(2)
paginator.pageCount() restituisce 3
paginator.itemCount() restituisce 5
paginator.page(2) restituisce "amet consectetur"
```

Le funzionalità fino ad ora sviluppate rappresentano la **terza release** del software che deve essere etichettata come `v3.0`.
