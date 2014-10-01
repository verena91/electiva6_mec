Electiva 6 - MEC
================

Esta es una aplicación sencilla que permite convertir la lista de funcionarios administrativos del MEC que está publicada en formato PDF a un CSV con los mismos datos.

Instalación
------------
1. Para poder realizar la instalación de este proyecto es necesario tener previamente instalado un servidor de aplicaciones JBoss AS7 y una base de datos PostgreSQL.
    
2. Crear la base de datos con el nombre **electiva**, propietario **postgres**.

3. Agregar el contenido del archivo datasource.txt en el standalone.xml del servidor JBoss.
    
4. Clonar este repositorio:

 `$ git clone https://github.com/verena91/electiva6_mec`
    
5. Ejecutar el script de inserción de los datos en la base de datos si se quiere utilizar los servicios web localmente.

6. Deployar el proyecto en el servidor JBoss.

7. Acceder desde el navegador, en la dirección donde se despliega el JBoss seguido del nombre del proyecto.

    **Ejemplo**

    https://localhost:8080/electiva6

**Para más detalles ir a:**

[Blog Open Data FP-UNA!](www.opendata.pol.una.py/nombre-del-blog)

