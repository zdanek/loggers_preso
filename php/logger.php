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


$log->info('Adding a new user', array('username' => 'Seldaek'));


function getLogger($channelName) {
    static $logger;
    if($logger)
        return $logger;

    $logger = new EpiLogger();
    return $logger;
}
