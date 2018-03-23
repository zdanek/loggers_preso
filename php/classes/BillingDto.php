<?php


class BillingDto {
    public $time;
    public $connectionParty;
    public $connectionLength;


    /**
     * Billing constructor.
     * @param $time
     * @param $connectionParty
     * @param $connectionLength
     */
    public function __construct($time, $connectionParty, $connectionLength) {
        $this->time = $time;
        $this->connectionParty = $connectionParty;
        $this->connectionLength = $connectionLength;
    }


    public static function fromBilling(Billing $billing) {

        return new BillingDto($billing->getTime(), $billing->getConnectionParty(), $billing->getConnectionLength());
    }

}
