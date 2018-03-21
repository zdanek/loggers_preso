<?php

class User implements EpiSecurityPrincipal {

    private $id;
    public $login;
    private $expiryTs;
    private $password;
    public $email;
    private $clientId;

    public function __construct($id, $login, $expiryTs, $password, $email, $clientId) {
        $this->id = $id;
        $this->login = $login;
        $this->expiryTs = $expiryTs;
        $this->password = $password;
        $this->email = $email;
        $this->clientId = $clientId;
    }

    public function getId() {
        return $this->id;
    }

    public function getLogin() {
        return $this->login;
    }

    public function getPasswordExpiryTimestamp() {
        return $this->expiryTs;
    }

    public function setExpiryTs($expiryTs)
    {
        $this->expiryTs = $expiryTs;
    }

    public function setPassword($password) {
        $this->password = $password;
    }

    public function getPassword() {
        return $this->password;
    }

    public function getEmail() {
        return $this->email;
    }

    /**
     * @return mixed
     */
    public function getClientId() {
        return $this->clientId;
    }

    /**
     * Returns array of roles.
     *
     * Each role is a String.
     *
     * @return array Roles of type String
     */
    public function getRoles() {
        return array();
    }
}
