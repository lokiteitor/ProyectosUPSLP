<?php 
require_once 'config.php';
/**
 * Esta parte del framework se encarga de resolver las rutas a los recurso
 * esto con el fin de resolver el problema de links rotos al trabajar sobre un
 * subdirectorio de root
 */


function url($path)
{
    // devuelve la url a una pagina del sitio web
    global $site;
    echo $site['root']. $path;
}

function getUrl($path)
{
    // devuelve la url a una pagina del sitio web
    global $site;
    return $site['root']. $path;
}

function asset($path)
{
    // devuelve la ruta al recurso
    // entiendase por asset, todas las imagenes ,css y js

    //tomar el directorio en el que se encuentra y concatenarlo al pedido
    global $site;
    echo $site['root'] . $path;
}

 ?>