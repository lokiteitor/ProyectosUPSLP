<?php 
require_once 'config.php';

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