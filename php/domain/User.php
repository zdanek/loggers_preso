<?php

class User implements EpiSecurityPrincipal {

    public $id;
    public $username;
    public $email;
    private $clientId;

    public function __construct($id, $username, $email, $clientId) {
        $this->id = $id;
        $this->username = $username;
        $this->email = $email;
        $this->clientId = $clientId;
    }

    public function getId() {
        return $this->id;
    }

    public function getUsername() {
        return $this->username;
    }

    public function getPasswordExpiryTimestamp() {
        return -1;
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
