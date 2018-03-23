<?php
use Monolog\Logger;
use Monolog\Handler\StreamHandler;
use Monolog\Formatter\LineFormatter;
use Elasticsearch\ClientBuilder;

class LoggerFactory {

    static $elasticClient;

    public static function logger($channel) {

        $client = ClientBuilder::create()->setHosts(
            [
                'http://127.0.0.1:9200'
            ])->build();

        $logstashFormatter = new MyLogstashFormatter('billing-php', null, null, '', 1);
        $elkHandler = new MyElasticLogstashHandler($client, ['type' => 'Billing']);
        $elkHandler->setFormatter($logstashFormatter);

        $log = new Logger($channel);
        $logsFileHandler = new StreamHandler('/opt/workspace/priv/wystapienia/loggers/code/php/application.log', Logger::DEBUG);

        $dateFormat = "Y-m-d H:i:s.u";
        //default logging format
        //        "[%datetime%] %channel%.%level_name%: %message% %context% %extra%\n";

        $headers = apache_request_headers();
        $correlationId = (isset($headers['X-CorrelationId'])) ? $headers['X-CorrelationId'] : '';
        $output = "%datetime% $correlationId %level_name% %channel% : %message% %context% %extra%\n";

        $formatter = new LineFormatter($output, $dateFormat);
        $logsFileHandler->setFormatter($formatter);

        $log->pushProcessor(function ($record) {

            $headers = apache_request_headers();

            if (isset($headers['X-CorrelationId'])) {
                $record['context']['correlation_id'] = $headers['X-CorrelationId'];

            }

            return $record;
        });


        $log->pushHandler($logsFileHandler);
        $log->pushHandler($elkHandler);

        return $log;
    }

}
