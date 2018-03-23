<?php
require_once 'autoloader.php';
require_once 'vendor/autoload.php';

require_once 'epiLoggerAdapter.php';

Epi::init('route','template','session', 'config', 'debug', 'api', 'database');//, 'logger', 'security');

//Epi::setPath('base', './');
Epi::setSetting('exceptions', true);
Epi::setSetting('debug', true);
Epi::setPath('view', './views');
Epi::setPath('config', './config');

getConfig()->load('config.ini');


$cfg = getConfig()->get('database');
EpiDatabase::employ('mysql', $cfg->db, $cfg->host, $cfg->user, $cfg->pass);

getSession();

getApi()->get('/api/users', array('UserController', 'users'), EpiApi::external);
getApi()->get('/api/billing', array('BillingController', 'getBilling'), EpiApi::external);
getApi()->get('/api/maintain/billing/random', array('BillingController', 'generateRandom'), EpiApi::external);

getRoute()->get('/', array('HomeController', 'display'));


getRoute()->run();

?>
