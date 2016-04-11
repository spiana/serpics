## Progetto Frontend-Mvn

**Serpics** Frontend integrato con maven.

## Tecnologie

Utilizzo del plugin di Maven: Fronten-Maven-Plugin

>This plugin downloads/installs Node and NPM locally for your project, runs NPM install, and then any combination of Bower, Grunt, Gulp, Jspm, Karma, or Webpack. It's supposed to work on Windows, OS X and Linux.

Utilizzo del JavaScript runtime built Node.js con librerie npm/bower.

I tasks automatizzati sono creati utilizzando Gulp. 

## Motivation

Ottimizzare il processo di building del frontend.

## Comandi

*Build di default del sito nella directory **target**: -> Apache

	mvn clean install
	mvn clean install -Denv=build

*LiveReload for development:

    mvn clean install -Denv=serve
    
*Services packaging: i services script vengono compressi e copiati nella directory **target/services/script/app.123465.js (Il versionamento dello script finale può essere eliminato.) 
	
	mvn clean install -Denv=srv
	

## Project References

[angular-styleguide](https://github.com/johnpapa/angular-styleguide)
[javascript-build-automation-gulpjs](https://www.pluralsight.com/courses/javascript-build-automation-gulpjs)
[angular-styleguide](https://github.com/toddmotto/angular-styleguide)

## Tests

I tests seguono le specifiche **Jasmine**. Eseguire il file html nella directory **src/test/test.html**.

  **TODO**
  Creazione di un task automation di Gulp per i tests, implementando l'utilizzo di **karma** come motore per l'esecuzione.
  
  Creazione di un profilo maven per lanciare i tests.
    
    	mvn clean install -Denv=test
    	
## Bins
  
  **TOTEST**
Nella directory **bin** sono presenti degli script per eseguire localmente npm/bower/gulp senza doverli installare globalmente nella propria macchina.
é presente inoltre un _mock-server_ per poter testare il sito senza l'utilizzo di tomcat e il caricamento del progetto **jax-rs**.

## Gulp Tasks:

*Build

	gulp / gulp build

*Server

	gulp serve
	
*Test

	gulp test
	


**Static Code Analysis (cfr. gulp/vet.js)**

*Eslint - Javascript/angular oriented

	gulp eslint
	
*Jshint - General purpose linter

	gulp jshint
	
*Jscs - Code style oriented

	gulp jscs
	
*Css Lint - CSSLint plugin for gulp

	gulp cssLint
	

**TODO**

*Implementare un task per la generazione automatica di _documentazione_ riguardante i services o in generale gli script '*.js' utilizzando **jsDoc** [jsDoc site](http://usejsdoc.org/)





