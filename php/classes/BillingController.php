<?php

class BillingController {

    static public function getBilling() {

        if (!isset($_GET['phoneNo'])) {
            throw new EpiErrorResponseException(new ErrorResponse('PhoneNo not set', 400));
        }

        $phoneNo = $_GET['phoneNo'];

        $log = LoggerFactory::logger('BillingController');

        $log->info("Fetching billing", ['phoneNo' => $phoneNo]);

        $billings = BillingRepository::getInstance()->findByPhoneNo($phoneNo);

        $dtos = array();
        foreach ($billings as $b) {
            $dtos[] = BillingDto::fromBilling($b);
        }

        $log->debug("Billing", ['BillingDto' => $dtos]);
        return $dtos;
    }

    static public function generateRandom() {

        if (!isset($_GET['phoneNo'])) {
            throw new EpiErrorResponseException(new ErrorResponse('PhoneNo not set', 400));
        }
        $log = LoggerFactory::logger('BillingController');

        $phoneNo = $_GET['phoneNo'];
        $log->info("Generating billing", ['phoneNo' => $phoneNo]);

        $repo = BillingRepository::getInstance();

        $billing = new Billing(null, $phoneNo, time() - rand(500, 60 * 60 * 24), self::randomParty(), rand(10, 500));

        $log->debug('Generated Billing', ['billing' => $billing]);

        $repo->save($billing);

        return "ok";
    }

    private static function randomParty() {
        return '48' . rand(600, 680) . rand(100000, 999999);
    }
}
