<?php


class Billing {
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


}
