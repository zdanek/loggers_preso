SLEEP_TIME=1

function bill {
	# skip 600000006 in billing!
	PHONE=`shuf -i 600000000-600000005 -n 1`
	echo Billing $PHONE

	curl http://loggers.zero/api/maintain/billing/random?phoneNo=$PHONE

	echo ""
}


function fetchUser {
	PHONE=`shuf -i 600000000-600000007 -n 1`
	echo Fetching user $PHONE

	curl http://localhost:8080/users/$PHONE

	echo ""
}

function fetchBilling {
	PHONE=`shuf -i 600000000-600000007 -n 1`
	echo Fetching user with billing $PHONE

	curl --write-out '%{http_code}' --silent --output /dev/null http://localhost:8080/users/$PHONE/billing

	echo ""
}

while [ 1 ]
do

	fetchBilling
	bill
	fetchUser
   sleep $SLEEP_TIME
done



