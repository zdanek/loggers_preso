
rm -rf ../elk_storage/nodes


docker run --name=elk_loggers -d -p 9200:9200 -p 5601:5601 -v /opt/workspace/priv/wystapienia/loggers/code/elk_storage:/home/elasticsearch/elasticsearch/data  nshou/elasticsearch-kibana

echo Elastic is at http://localhost:9200
echo Kibana is at http://localhost:5601
