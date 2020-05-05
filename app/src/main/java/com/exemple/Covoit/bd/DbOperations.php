<?php


class DbOperations
{
    private $co;

    /**
     * bdOperations constructor.
     * @param $co
     */
    public function __construct()
    {
        require_once dirname(__FILE__) . '/DbConnect.php';

        $db = new DbConnect();

        $this->co = $db->connect();
    }

    function createUser($nom, $prenom, $mail, $mdp, $tel, $conducteur, $passager){
        $password = md5($mdp);
        $stmt = $this->co->prepare("INSERT INTO Utilisateur VALUES (?, ?, ?, ?, ?, ?, ?);");
        $stmt->bind_param("sss",$nom, $prenom, $mail, $password, $tel, $conducteur, $passager);

        if($stmt->execute()){
            return true;
        }else{
            return false;
        }
    }

}