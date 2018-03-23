<?php

use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use Monolog\Formatter\JsonFormatter;

class MonologAdaper {

    private $monoLog;

    /**
     * MonologAdaper constructor.
     * @param $monoLog
     */
    public function __construct($monoLog) {
        $this->monoLog = $monoLog;
    }

    public function info($message, $exception = null) {
        if ($exception != null) {
            $this->monoLog->info($message, array('exception' => $exception));
        } else {
            $this->monoLog->info($message);
        }
    }

}

function getLogger($channelName = 'epiFramework') {
    static $logger;

    if (isset($logger[$channelName])) {
        return $logger[$channelName];
    }

    $monoLog = new Logger($channelName);
    $stream = new StreamHandler(__DIR__.'/application.log', Logger::DEBUG);

    $monoLog->pushHandler($stream);

    $log = new MonologAdaper($monoLog);

    $logger[$channelName] = $log;

    return $log;
}
