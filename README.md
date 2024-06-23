<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyecto Literatura</title>
</head>
<body>
    <h1>Proyecto Literatura</h1>
    <p>Este proyecto utiliza la API Gutendex para buscar libros y autores de Project Gutenberg y almacenarlos en una base de datos. Proporciona funcionalidades básicas como búsqueda por título, listado de libros registrados, listado de autores registrados, búsqueda de autores vivos en un año específico y búsqueda de libros por idioma.</p>
  <h4 align="center">
:white_check_mark: Proyecto completado :white_check_mark:
</h4>
<p></p>
    <h2>Tecnologías Utilizadas</h2>
    <ul>
        <li>Java</li>
        <li>Spring Boot</li>
        <li>Hibernate/JPA</li>
        <li>Gutendex API</li>
    </ul>
<p></p>
    <h2>Funcionalidades</h2>
    <ol>
        <li><strong>Buscar libro por título</strong>: Permite buscar libros en la API Gutendex por título. Si encuentra un libro que no está registrado en la base de datos local, lo guarda automáticamente.</li>
        <li><strong>Listar libros registrados</strong>: Muestra todos los libros almacenados en la base de datos local.</li>
        <li><strong>Listar Autores registrados</strong>: Muestra todos los autores almacenados en la base de datos local junto con los libros asociados a cada autor.</li>
        <li><strong>Listar autores vivos a partir de un determinado año</strong>: Permite buscar autores que estuvieron vivos en un año específico, utilizando datos almacenados en la base de datos local.</li>
        <li><strong>Listar libros por idioma</strong>: Permite buscar libros por idioma (inglés, español, francés, portugués) utilizando datos almacenados en la base de datos local.</li>
    </ol>
<p></p>
    <h2>Configuración del Proyecto</h2>
    <ol>
        <li><strong>Clonar el repositorio:</strong> <code>git clone https://github.com/Thimo23/alura-challanges-literatura.git</code></li>
        <li><strong>Configurar la base de datos:</strong> Asegúrate de tener una base de datos configurada y accesible. Configura las credenciales de la base de datos en <code>src/main/resources/application.properties</code>.</li>
        <li><strong>Ejecutar el proyecto:</strong> Abre el proyecto en tu IDE y ejecuta la clase <code>LiteraturaApplication</code>. O ejecuta desde la línea de comandos: <code>mvn spring-boot:run</code>.</li>
    </ol>
<p></p>
    <h2>Uso</h2>
    <ul>
        <li>Al ejecutar la aplicación, se mostrará un menú con las opciones disponibles.</li>
        <li>Selecciona la opción deseada ingresando el número correspondiente y sigue las instrucciones en pantalla.</li>
      <li>Para comenzar a consultar la base de datos primero es necesario cargar libros en la misma con la primera opción, que solicitara los libros a la API.</li>
    </ul>
<p></p>
    
</body>
</html>
