# Emettitrice di biglietti per il trasporto urbano 
#### Gruppo-J21 

Il sistema realizzato simula il funzionamento di un emettitrice di biglietti per il trasporto urbano che realizza funzioni di vendita e convalida per il viaggiatore e di controllo della validità per il controllore. Il sistema prevede sia un interfaccia testuale che un'applicazione web.
Di seguito sono riportate le istruzioni per il suo corretto utilizzo.

### 1. Preparazione dell'ambiente
Per preparare l'IDE all'esecuzione del programma è necessario scaricare e installare Git. A questo punto sarà possibile fare una fork e una clone della repository per ottenerne una copia locale, tramite il tasto fork in alto a destra della schermata iniziale della repository ed il pulsante verde _"Code"_ che permette di copiare rapidamente il link della repository su GitHub; quindi, lanciare da terminale il comando:

```
git clone [link appena copiato]

```
Importare il progetto nell'IDE prescelto; se è stato scelto Eclipse, selezionare l'opzione _"Import..."_ dal menù a tendina _"File"_ in alto a sinistra e selezionare la directory radice del progetto: è ora possibile eseguire il programma.

### 2. Esecuzione del progetto
> 2.1 Esecuzione tramite interfaccia testuale

Per utilizzare il sistema tramite l'interfaccia testuale che permette l'interazione con il viaggiatore e il controllore direttamente da console, è sufficiente eseguire la classe **TicketTester** _(src/main/java/electronicticketingsystem/test/TicketTester.java)_ e selezionare le operazioni desiderate.

> 2.2 Esecuzione tramite applicazione web

Per utilizzare il sistema tramite applicazione web è necessario aprire il proprio prompt dei comandi e spostarsi con il comando _cd_ nella directory radice del progetto. A questo punto va eseguito il build dell'applicazione (che prepara alla sua esecuzione scaricando automaticamente tutto il necessario):

```
gradlew build

```
Procedere poi al lancio del comando run:

```
gradlew run

```
(Questi comandi sono relativi a dispositivi Windows, per dispositivi Linux e MacOS il comando di run è ./gradlew run).

A questo punto, è possibile interagire con l'applicazione digitando nel proprio browser il link [http://localhost:8080/](https://localhost:8080/). L'applicazione è stata realizzata principalmente per il browser _Mozilla Firefox_, ma è utilizzabile con tutti i principali browser disponibili.





