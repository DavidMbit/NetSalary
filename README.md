# Net Salary 2026

# Obiettivo

MVP di un simulatore di retribuzione netta sviluppato per stimare il netto annuale e mensile a partire dalla RAL, mostrando le principali componenti del calcolo.

Il progetto è stato sviluppato come technical task.

# Stack
- Java 21

- Spring Boot 3.5

- Maven

- JavaScript

- HTML / CSS

- JUnit per i test

# Assunzioni e limiti della MVP

Il calcolo considera:

- anno fiscale 2026;

- lavoratore dipendente privato;

- contratto a tempo indeterminato;

- residente a Milano;

- nessun familiare a carico;

- nessuna agevolazione particolare;

- 12 mensilità utilizzate per il calcolo del valore medio mensile.

Sono esclusi dalla MVP, tra gli altri:

- trattamento integrativo;

- benefit aziendali;

- buoni pasto;

- premi di risultato;

- situazioni personali o contributive particolari;

- ulteriori casistiche payroll.

Il risultato è quindi una stima semplificata e non sostituisce un calcolo payroll professionale.

# Dati utilizzati

I principali parametri utilizzati nella simulazione sono:

**Parametro	Valore**

- Contributi dipendente	9,19%

- IRPEF fino a 28.000 €	23%

- IRPEF 28.001–50.000 €	33%

- IRPEF oltre 50.000 €	43%

- Addizionale Lombardia	1,23% – 1,73%

- Addizionale comunale Milano	0,80%

- Soglia comunale considerata	23.000 €

Sono inoltre implementate le detrazioni da lavoro dipendente e l'ulteriore detrazione previste dal modello della MVP.

# Fonti utilizzate

** Fonti istituzionali **

[INPS — Aliquote contributive](https://www.inps.it/it/it/inps-comunica/diritti-e-obblighi-in-materia-di-sicurezza-sociale-nell-unione-e/per-le-imprese/aliquote-contributive.html?utm_source=chatgpt.com)

[INPS — Parametri 2026 per lavoratori dipendenti](https://www.inps.it/it/it/inps-comunica/notizie/dettaglio-news-page.news.2026.02.lavoratori-dipendenti-limite-minimo-di-retribuzione-giornaliera-2026.html?utm_source=chatgpt.com)

[Regione Lombardia — Addizionale regionale IRPEF](https://www.regione.lombardia.it/bollo-auto-e-tributi-regionali/red-addizionale-regionale-irpef?utm_source=chatgpt.com)

[Comune di Milano — Addizionale comunale IRPEF](https://www.comune.milano.it/argomenti/tributi/addizionale-comunale-irpef?utm_source=chatgpt.com)

** Fonti informative consultate **

[CAF Informa — Detrazioni lavoro dipendente 2026](https://cafinforma.it/detrazioni-lavoro-dipendente-2026/?utm_source=chatgpt.com)

[Informazione Fiscale — Detrazioni lavoro dipendente](https://www.informazionefiscale.it/detrazioni-lavoro-dipendente-importo-calcolo?utm_source=chatgpt.com)

[Fisco e Tasse — IRPEF 2026](https://www.fiscoetasse.com/new-rassegna-stampa/2990-irpef-2026-le-aliquote-di-questanno.html?utm_source=chatgpt.com)

[Open Dot Com — Busta paga 2026](https://www.opendotcom.it/lavoro/buste-paga/?utm_source=chatgpt.com)
