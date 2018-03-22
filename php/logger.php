<?php

use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use Monolog\Formatter\JsonFormatter;

// create a log channel
$log = new Logger('channel_name');

// create a Json formatter
$formatter = new JsonFormatter();

// create a handler
$stream = new StreamHandler(__DIR__.'/application-json.log', Logger::DEBUG);
$stream->setFormatter($formatter);

// bind
$log->pushHandler($stream);


//$log->info('Adding a new user', array('username' => 'Seldaek'));

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

function getLogger($channelName = 'app') {
    static $logger;

    if (isset($logger[$channelName])) {
        return $logger[$channelName];
    }

    $monoLog = new Logger($channelName);
    $formatter = new JsonFormatter();

// create a handler
    $jsonStream = new StreamHandler(__DIR__.'/application-json.log', Logger::DEBUG);
    $jsonStream->setFormatter($formatter);
    $humanStream = new StreamHandler(__DIR__.'/application.log', Logger::DEBUG);

// bind
    $monoLog->pushHandler($jsonStream);
    $monoLog->pushHandler($humanStream);

    $log = new MonologAdaper($monoLog);

    $logger[$channelName] = $log;

    return $log;
}
