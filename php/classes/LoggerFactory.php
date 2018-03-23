<?php
    use Monolog\Logger;
    use Monolog\Handler\StreamHandler;
    use Monolog\Formatter\JsonFormatter;

    use Elasticsearch\ClientBuilder;
    use Monolog\Formatter\LogstashFormatter;

    use Monolog\ElasticLogstashHandler;

class LoggerFactory {

    static $elasticClient;

    public static function logger($channel) {


        $client = ClientBuilder::create()->setHosts(
            [
                'http://127.0.0.1:9200'
            ])->build();

        $logstashFormatter = new LogstashFormatter('billing-php', null, null, '', 1);
        $elkHandler = new ElasticLogstashHandler($client, ['type' => 'billing']);
        $elkHandler->setFormatter($logstashFormatter);

        $log = new Logger($channel);
        $logsFileHandler = new StreamHandler('/opt/workspace/priv/wystapienia/loggers/code/php/application.log', Logger::DEBUG);
//        $logsFileHandler->setFormatter()

        $log->pushHandler($logsFileHandler);
        $log->pushHandler($elkHandler);


        return $log;
    }

}
