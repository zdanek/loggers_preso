<?php

class Billing {

    private $id;
    private $phoneNo;
    private $time;
    private $connectionParty;
    private $connectionLength;

    /**
     * Billing constructor.
     * @param $id
     * @param $phoneNo
     * @param $time
     * @param $connectionParty
     * @param $connectionLength
     */
    public function __construct($id, $phoneNo, $time, $connectionParty, $connectionLength) {
        $this->id = $id;
        $this->phoneNo = $phoneNo;
        $this->time = $time;
        $this->connectionParty = $connectionParty;
        $this->connectionLength = $connectionLength;
    }

    /**
     * @return mixed
     */
    public function getId() {
        return $this->id;
    }

    /**
     * @return mixed
     */
    public function getPhoneNo() {
        return $this->phoneNo;
    }

    /**
     * @return mixed
     */
    public function getTime() {
        return $this->time;
    }

    /**
     * @return mixed
     */
    public function getConnectionParty() {
        return $this->connectionParty;
    }

    /**
     * @return mixed
     */
    public function getConnectionLength() {
        return $this->connectionLength;
    }
}
