<?php

class UserRepository {
    private static $inst;

    public static function getInstance() {
        if (!self::$inst) {
            self::$inst = new UserRepository();
        }
        return self::$inst;
    }

    public function findAll() {
        $users = array();

        $userRecords = getDatabase()->all('SELECT * FROM users');

        if ($userRecords !== false) {

            foreach ($userRecords as $user) {
                $users[] = $this->fromRecord($user);
            }
        }

        return $users;
    }


    public function findByUserId($userId) {

        $user = getDatabase()->one('SELECT * FROM users WHERE id=:userId', array(':userId' => $userId));

        if ($user === false) {
            return null;
        }

        return $this->fromRecord($user);
    }

    public function findByUserEmail($email) {
        $user = getDatabase()->one('SELECT * FROM users WHERE email=:email', array(':email' => $email));

        if ($user === false) {
            return null;
        }

        return $this->fromRecord($user);
    }

    public function findUserByLoginAndPassword($username, $password) {
        $passHash = PassUtils::hash($password);
        $user = getDatabase()->one('SELECT * FROM users WHERE login=:login and password = :pass', array(':login' => $username, ':pass' => $passHash));

        if ($user === false) {
            return null;
        }
        getLogger()->info(var_export($user, true));

        return $this->fromRecord($user);
    }

    public function save(User $user) {
        $email = $user->email;
        $db = getDatabase();
        $userRecord = $db->one('SELECT id FROM users WHERE email=:email', array(':email' => $email));

        $params = ['login' => $user->login,
            'password' => $user->getPassword(),
            'full_name' => $user->fullName,
            'email' => $user->email,
            'group_id' => $user->groupId,
            'api_key' => $user->getApiKey(),
            'roles' => implode(',', $user->getRoles())
        ];

        if ($userRecord !== false) {
            throw new Exception("update not impl");
            getLogger()->info('Updatign user' . $email);
            $rowCount = $db->execute('UPDATE responders SET responder_text = :respText, responder_start_ts = :respStart, '
            .' responder_stop_ts = :respStop, user_substitute_id = :subsUserId, active = :active '
            .' WHERE user_id = :userId', $params);

            getLogger()->info('Updated row count ' . $rowCount);
        } else {
            getLogger()->info('INSERTING user ' . $email);
            $id = $db->execute('INSERT INTO users (login, password, full_name, email, group_id, api_key, roles)'
            . ' VALUES (:login, :password, :full_name, :email, :group_id, :api_key, :roles)', $params);

            getLogger()->info('Inserted row id ' . $id);
        }
    }

    private function decodeAK($api_key) {

        //TODO co z tym?
        if (true) {
            return $api_key;
        }

        $full = sizeof($api_key);
        $half = $full >> 1;
        $newApiKey = substr($api_key, $half) . substr($api_key. 0, $half);

        getLogger()->info("AP size " . sizeof($api_key));
        getLogger()->info("half " . $half);
        getLogger()->info("new AP " . $newApiKey);

        return $newApiKey;

    }

    private function fromRecord($user) {
        $roles = explode(',', $user['roles']);
        return new User($user['id'], $user['login'], $user['password'], $this->decodeAK($user['api_key']), $user['email'], $user['full_name'], $user['group_id'], $roles);
    }

}
