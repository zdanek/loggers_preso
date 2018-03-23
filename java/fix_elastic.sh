curl -H "Content-Type: application/json" -XPUT 'http://localhost:9200/_cluster/settings'  -d '
{
  "transient": {
    "cluster.routing.allocation.disk.watermark.low": "5gb",
    "cluster.routing.allocation.disk.watermark.high": "2gb",
    "cluster.routing.allocation.disk.watermark.flood_stage": "1gb",
    "cluster.info.update.interval": "1m"
  }
}
'



curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_all/_settings -d '{"index.blocks.read_only_allow_delete": null}'
