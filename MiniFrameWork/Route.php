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
require_once 'Auth.php';
/**
* @asociar una ruta con el controlador correpondiente
*/
class Route
{
    private static $POST = [];
    private static $GET = [];
    private static $login = [];
    private static $Admin = [];
    // TODO : no vendria bien un diccionario

    public static function post($ruta,$filter,$funcion)
    {
        global $site;   
        // asociar la rutas tipo post con la funcion 
        array_push(self::$POST,[$site['root'].$ruta,$funcion]);
        self::addFilter($site['root'].$ruta,$filter);

    }

    public static function get($ruta,$filter,$funcion)
    {
        global $site;
        // asociar la rutas tipo get con la funcion 
        array_push(self::$GET,[$site['root'].$ruta,$funcion]);        
        self::addFilter($site['root'].$ruta,$filter);
    }

    public static function addFilter($ruta,$filter)
    {
        global $site;
        if (in_array('login', $filter)) {
            // el usuario tiene que estar autentificado
            array_push(self::$login,$ruta);
        }
        if (in_array('admin', $filter)) {
            array_push(self::$Admin,$ruta);
        }        
    }    

    private static function dirigirPost()
    {
        // sabemos que la consulta es de tipo post dirigir la ruta 
        // al controlador adecuado
        foreach (self::$POST as $registro) {
            if ($_SERVER['REQUEST_URI'] == $registro[0]) {
                // en la ruta que coincida ejecutar su funcion
                call_user_func($registro[1]);
            }
        }
    }
    
    private static function dirigirGet()
    {
        foreach (self::$GET as $registro) {
            if ($_SERVER['REQUEST_URI'] == $registro[0]) {
                // en la ruta que coincida ejecutar su funcion
                call_user_func($registro[1]);
            }
        }        
    }

    public static function dispatch()
    {       
        $auth = new Auth();

        if (in_array($_SERVER['REQUEST_URI'],self::$login)) {
            $auth->filterLogin();
        }


        if (in_array($_SERVER['REQUEST_URI'],self::$login)) {
            $auth->filterAdmin();
        }


        // de acuerdo a la ruta de la consulta despachar las solicitudes
        if ( $_SERVER['REQUEST_METHOD'] == 'POST') {
            self::dirigirPost();
        }
        else if ($_SERVER['REQUEST_METHOD'] == 'GET') {
            self::dirigirGet();
        }
    }
}
 ?>