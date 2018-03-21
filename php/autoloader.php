<?php

function classesAutoloader($class) {

    $file = 'classes/' . $class . '.php';
    if (file_exists($file)) {
        include $file;
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
