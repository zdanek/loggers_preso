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
        $elkHandler = new ElasticLogstashHandler($client, ['type' => 'billing-logs']);
        $elkHandler->setFormatter($logstashFormatter);



//        $log = new Monolog\Logger('invoicing');
//        $log->pushHandler($handler);
//        $log->warn('new sale', ['user_id' => 42, 'product_id' => 7537]);


        $log = new Logger($channel);

        $json = new StreamHandler('/opt/workspace/priv/wystapienia/loggers/code/php/application.log', Logger::DEBUG);


        $log->pushHandler($json);
        $log->pushHandler($elkHandler);


        return $log;
    }

}
