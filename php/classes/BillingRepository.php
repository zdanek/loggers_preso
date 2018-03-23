<?php

class BillingRepository {
    private static $inst;
    private $logger;

    public static function getInstance() {
        if (!self::$inst) {
            self::$inst = new BillingRepository();
        }
        return self::$inst;
    }

    private function __construct() {
        $this->logger = LoggerFactory::logger('BillingRepository');
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

    public function save(Billing $b) {

        if ($b->getId() !== null) {
            throw new Exception("Billing update is forbidden");
        }

        $db = getDatabase();

        $params = [':phoneNo' => $b->getPhoneNo(),
            ':party' => $b->getConnectionParty(),
            ':length' => $b->getConnectionLength(),
            ':dateTime' => $b->getTime()];

        $this->logger->info('Inserting billing for user ', ['phoneNo' => $b->getPhoneNo()]);

        $id = $db->execute('INSERT INTO billing (phone_no, party, length, date_time)'
        .' VALUES (:phoneNo, :party, :length, :dateTime)', $params);

        $this->logger->debug('Inserted row id ', ['id' => $id]);
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

        return new Billing($billing['id'], $billing['phone_no'], $billing['date_time'], $billing['party'], $billing['length']);
    }


}
