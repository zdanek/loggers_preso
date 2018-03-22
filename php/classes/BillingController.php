<?php

class BillingController {

    static public function getBilling() {

        if (!isset($_GET['phoneNo'])) {
            throw new EpiErrorResponseException(new ErrorResponse('PhoneNo not set', 400));
        }

        $phoneNo = $_GET['phoneNo'];

        $billings = BillingRepository::getInstance()->findByPhoneNo($phoneNo);

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
