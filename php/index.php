<?php
require_once 'autoloader.php';
require_once 'vendor/autoload.php';

require_once 'logger.php';

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
getApi()->get('/api/users/current', array('UserController', 'loggedUser'), EpiApi::external);

getRoute()->get('/', array('HomeController', 'display'));

//getSecurity()->configEndpoint('/init', false);
//getSecurity()->configEndpoint('/api/accounts/check', false);

getRoute()->run();

?>
