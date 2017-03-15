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
// TODO : esta parte del microframework esta muy verde aun ya que al motor de
// vistas se le pueden agregar mas capacidades como por ejemplo un metalenguaje

/**
 * @brief Renderiza una vista en el navegador
 * @param $name -- nombre de la vista sin la extencion del archivo
 */
function View($name)
{
    require_once 'php/include/Url.php';
    require_once 'php/include/Views.php';
    // buscar la vista en el directorio correspondiente
    global $site;
    if (file_exists($site['views'] . $name.'.php')) {
        // dibujar
        include $site['views'] . $name .'.php';
    }
}


/**
 * @brief Buscar el layout he imprimirlo
 * @return void
 */
function Layout($layout)
{ 
    require_once 'php/include/Url.php';
    require_once 'php/include/Views.php';
    global $site;
    if (file_exists($site['layouts'].'/'.$layout.'.php' )) {
        include $site['layouts'].'/'.$layout.'.php';
    }    
}


/**
 * @brief Obtiene el nombre de la ruta actual

 * @return ruta a la pagina actual
 */
function getRuta()
{
    return $_SERVER['REQUEST_URI'];
}


 ?>
