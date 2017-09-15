# Copyright (c) 2015, CodiLime Inc.

from pyspark import SparkContext
from pyspark import SparkConf
from pyspark.sql import SQLContext
from pyspark.sql import DataFrame
from py4j.java_gateway import JavaGateway, GatewayClient, java_import
from ready_handler import ReadyHandler
import urllib2

def restart_kernel():
    urllib2.urlopen("http://{}:{}/jupyter/api/kernels/{}/restart".format(
        notebook_server_address, notebook_server_port, kernel_id), "")

ready_handler = ReadyHandler([mq_address, mq_port])
ready_handler.handle_ready(restart_kernel)

# gateway_address and gateway_port are set in the kernel
gateway = JavaGateway(
    GatewayClient(
        address=gateway_address,
        port=gateway_port),
    start_callback_server=False,
    auto_convert=True)

java_spark_context = gateway.entry_point.getSparkContext()
java_spark_conf = gateway.entry_point.getSparkConf()

java_import(gateway.jvm, "org.apache.spark.SparkEnv")
java_import(gateway.jvm, "org.apache.spark.SparkConf")
java_import(gateway.jvm, "org.apache.spark.api.java.*")
java_import(gateway.jvm, "org.apache.spark.api.python.*")
java_import(gateway.jvm, "org.apache.spark.mllib.api.python.*")
java_import(gateway.jvm, "org.apache.spark.sql.*")
java_import(gateway.jvm, "org.apache.spark.sql.hive.*")
java_import(gateway.jvm, "scala.Tuple2")
java_import(gateway.jvm, "scala.collection.immutable.List")

sc = SparkContext(
    conf=SparkConf(_jvm=gateway.jvm, _jconf=java_spark_conf),
    gateway=gateway,
    jsc=java_spark_context)

sqlContext = SQLContext(sc)


def dataframe():
    # workflow_id, node_id and port_number are set in the kernel
    if node_id is None or port_number is None:
        raise Exception("No edge is connected to this Notebook")

    try:
        java_data_frame = gateway.entry_point.retrieveOutputDataFrame(workflow_id, node_id, port_number)
    except Py4JJavaError:
        raise Exception("Input operation is not yet executed")

    return DataFrame(jdf=java_data_frame, sql_ctx=sqlContext)
