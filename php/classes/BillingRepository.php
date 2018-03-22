<?php

class BillingRepository {
    private static $inst;

    public static function getInstance() {
        if (!self::$inst) {
            self::$inst = new BillingRepository();
        }
        return self::$inst;
    }

    public function findAll() {
        $billings = array();

        $billingRecords = getDatabase()->all('SELECT * FROM billing');

        if ($billingRecords !== false) {

            foreach ($billingRecords as $user) {
                $billings[] = $this->fromRecord($user);
            }
        }

        return $billings;
    }


    public function findByUserEmail($email) {
        $user = getDatabase()->one('SELECT * FROM user WHERE email=:email', array(':email' => $email));

        if ($user === false) {
            return null;
        }

        return $this->fromRecord($user);
    }

    public function findUserByLoginAndPassword($username, $password) {
        $passHash = PassUtils::hash($password);
        $user = getDatabase()->one('SELECT * FROM user WHERE login=:login and password = :pass', array(':login' => $username, ':pass' => $passHash));

        if ($user === false) {
            return null;
        }
        getLogger()->info(var_export($user, true));

        return $this->fromRecord($user);
    }

    public function save(Billing $billing) {

        $params = ['phone_no' => '123',
            'password' => $billing->getPassword(),
            'full_name' => $billing->fullName,
            'email' => $billing->email,
            'group_id' => $billing->groupId,
            'api_key' => $billing->getApiKey(),
            'roles' => implode(',', $billing->getRoles())
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

    public function findByPhoneNo($phoneNo) {
        $billings = array();

        $billingRecords = getDatabase()->all('SELECT * FROM billing WHERE phone_no = :phoneNo ORDER by date_time DESC', array(':phoneNo' => $phoneNo));

        if ($billingRecords !== false) {

            foreach ($billingRecords as $user) {
                $billings[] = $this->fromRecord($user);
            }
        }

        return $billings;
    }

    private function fromRecord($billing) {

        return new Billing($billing['date_time'], $billing['party'], $billing['length']);
    }


}
