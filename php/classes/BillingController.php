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

        $log->debug("Billing", ['billing' => $billings]);

        return $billings;
    }

    static public function generateRandom() {

        if (!isset($_GET['phoneNo'])) {
            throw new EpiErrorResponseException(new ErrorResponse('PhoneNo not set', 400));
        }

        $phoneNo = $_GET['phoneNo'];


        $repo = BillingRepository::getInstance();

        $repo->save();
    }
}
