<?php

function classesAutoloader($class) {

    $file = 'classes/' . $class . '.php';
    if (file_exists($file)) {
        include $file;
        return;
    }

    if ($class === 'Monolog\\ElasticLogstashHandler') {
        include 'classes/Monolog/ElasticLogstashHandler.php';
        return;
    }
}

function templatesAutoloader($class) {

    $file = 'domain/' . $class . '.php';
    if (file_exists($file)) {
        include $file;
        return;
    }
}

spl_autoload_register('classesAutoloader');
spl_autoload_register('templatesAutoloader');

?>
