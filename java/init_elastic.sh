#docker network create elastic
#docker run --network=elastic --name=elasticsearch docker.elastic.co/elasticsearch/elasticsearch:6.2.3
#docker run --network=elastic -p 5601:5601 docker.elastic.co/kibana/kibana:6.2.3

docker run -d -p 9200:9200 -p 5601:5601 nshou/elasticsearch-kibana

echo Elastic is at http://localhost:9200
echo Kibana is at http://localhost:5601
