### Instalaci�n con Maven (mvn)

    mvn jetty:run

Si un error es lanzado reportando dependencias no encontradas (artifact javax.transaction:jta:jar:1.0.1B) usar entonces el jar presente en el directorio *dependencias* con nombre jta-1.0.1B.jar

		mvn install:install-file -DgroupId=javax.transaction -DartifactId=jta -Dversion=1.0.1B -Dpackaging=jar -Dfile=jta-1_0_1B.jar

Para a�adir el controlador JDBC de Oracle9i para java usar el archivo descargado en el directorio *dependencias* dentro de este directorio e ingresar el comando siguiente:

		mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc9 -Dversion=9.0.1 -Dpackaging=jar -Dfile=dependencias/ojdbc14.jar

War:

mvn compile war:war 

Y queda en: target/
