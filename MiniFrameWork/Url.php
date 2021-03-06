<?php 
/*Copyright (C) 2017 David Delgado Hernandez < lokiteitor513@gmail.com >
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.*/
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