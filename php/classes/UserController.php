<?php

class UserController {

    static public function users() {

        $repo = UserRepository::getInstance();

        $users = $repo->findAll();

        return $users;
    }

    static public function loggedUser() {
        $user = getSecurity()->getPrincipal();

        return $user;
    }
}
