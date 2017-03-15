<?php 
require_once 'config.php';
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

/**
* 
*/
class DB
{
    public $conexion;
    public static function conectar()
    {     
        global $db;
        $conexion  = new mysqli($db['host'],$db['user'],$db['password'],$db['dbname']);

        if (!$conexion) {
            // fallo la conexion a la base de datos
            die('Error al conectar con la base de datos' . $conexion->connect_error);
        }        
        return $conexion;
    }

    public function consultar($sql)
    {
        $result = $this->conexion->query($sql);

        if (!$result) {
            die('Error al ejecutar la consulta ' . $this->conexion->error);
        }        
        return $result;        
    }

    public function fetch($result)
    {
        $response = array();

        while ($rows = $result->fetch_assoc()) {
            array_push($response, $rows);
        }
        return $response;
    }


}


 ?>