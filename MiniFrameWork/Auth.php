<?php 
require_once 'config.php';
require_once 'Url.php';
require_once 'DB.php';

// TODO : Esta clase aun no es flexible

/**
* Validar las credenciales de usuario 
*/
class Auth extends DB
{
    public $conexion;
    public $table = "USUARIO";
    private $userfield = "USERNAME";
    private $passfield = "PASSWORD";
    private $idfield = "ID_USUARIO";
    /**
     * Esta clase crea y verifica que el usuario se ha autentificado
     */

    function __construct()
    { 
        $this->conexion = self::conectar();
        if (session_status() != PHP_SESSION_ACTIVE) {
            session_start();
            $_SESSION['isauth'] = 'false';
        }
    }

    /**
     * Verificar en la tabla de usuario las credenciales que proporciona
     * @param array - Arreglo con las credenciales que el usuario proporciono
     * 
     */
    public function setAuth($userData)
    {
        global $site;
        // obtener los datos del usuario
        // los ? se remplazan por los datos a escapar
        // asi evitamos problemas como sql injection
        $sql = 'SELECT * FROM '. $this->table . ' WHERE '.$this->userfield.'=?';

        $stmt = $this->conexion->prepare($sql);
        $stmt->bind_param('s',$userData['username']);
        $stmt->execute();

        // la consulta fallo
        if ($stmt->errno) {
            die('Error al consultar a la base de datos ' . $stmt->error);          
        }
        else{
            // la consulta no fallo
            $result = $stmt->get_result();
            if (!$result) {
                die('Error durante la consulta');
            }

            if ($result->num_rows == 0) {
                // no existe el usuario retornar a la pagina de inicio
                header("Location: ".$site['url'].getUrl('/login'));
            }
            // verificar la contraseña 
            $query = $result->fetch_array(MYSQLI_ASSOC);


            if ($query[$this->passfield] == $userData['password']) {
                // crear una session
                if (session_status() != PHP_SESSION_ACTIVE) {
                    session_start();    
                }                
                
                $_SESSION['uid'] = $query[$this->idfield];
                $_SESSION['username'] = $userData['username'];
                $_SESSION['isauth']  = 'true';

            }
            else{
                // la contraseña no coincide       
                header("Location: ".$site['url'].getUrl('/login'));

            }
            $result->free();
        }        
        $stmt->close();
    }
    /**
     * Verifica que el usuario esta autentificado       
     */
    public function isAuth()
    {   
        global $site;   
        $login = false;
        // verificar que la session este autentificada
        if (session_status() == PHP_SESSION_ACTIVE) {
            if (isset($_SESSION['uid']) && ($_SESSION['isauth'] == 'true')) {
                $login = true;
            }   
        }

        return $login;
    }

    public function filterAdmin(){
        // TODO : definir el metodo para identificar administradores
        global $site;
        if (session_status() != PHP_SESSION_ACTIVE || ($_SESSION['isauth'] == 'false')) {
            header("Location: ".$site['url'].getUrl('/login'));
        }
        return;
    }

    public function filterLogin()
    {
        global $site;
        if (session_status() != PHP_SESSION_ACTIVE || ($_SESSION['isauth'] == 'false')) {
            header("Location: ".$site['url'].getUrl('/login'));
        }
        return;
    }

    public function logout()
    {
        global $site;
        if (session_status() == PHP_SESSION_ACTIVE) {
            $_SESSION = array();

            $params = session_get_cookie_params();
            setcookie(session_name(), '', time() - 42000,
                $params["path"], $params["domain"],
                $params["secure"], $params["httponly"]
            );
            session_destroy();
            header("Location: ".$site['url'].getUrl('/'));   
        }
    }

    function __destruct()
    {
        $this->conexion->close();

    }
}



 ?>