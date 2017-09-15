/**
 * Copyright (c) 2015, CodiLime Inc.
 */

package io.deepsense.reportlib.model

sealed abstract class Distribution(
  val name: String,
  val subtype: String,
  val description: String,
  val missingValues: Long)

sealed abstract class UnivariateDistribution(
    name: String,
    subtype: String,
    description: String,
    missingValues: Long,
    buckets: Seq[String],
    counts: Seq[Long])
  extends Distribution(name, subtype, description, missingValues)

case class CategoricalDistribution(
    override val name: String,
    override val description: String,
    override  val missingValues: Long,
    buckets: Seq[String],
    counts: Seq[Long],
    override val subtype: String = CategoricalDistribution.subtype,
    blockType: String = DistributionJsonProtocol.typeName)
  extends UnivariateDistribution(
    name,
    CategoricalDistribution.subtype,
    description,
    missingValues,
    buckets,
    counts) {
  require(subtype == CategoricalDistribution.subtype)
  require(blockType == DistributionJsonProtocol.typeName)
  require(buckets.size == counts.size, "buckets size does not match count size. " +
    s"Buckets size is: ${buckets.size}, counts size is: ${counts.size}")
}

object CategoricalDistribution {
  val subtype = "categorical"
}

case class ContinuousDistribution(
    override val name: String,
    override val description: String,
    override val missingValues: Long,
    buckets: Seq[String],
    counts: Seq[Long],
    statistics: Statistics,
    override val subtype: String = ContinuousDistribution.subtype,
    blockType: String = DistributionJsonProtocol.typeName)
  extends UnivariateDistribution(
    name,
    ContinuousDistribution.subtype,
    description,
    missingValues,
    buckets.map(_.toString),
    counts) with ReportJsonProtocol {
  require(subtype == ContinuousDistribution.subtype)
  require(blockType == DistributionJsonProtocol.typeName)
  require(buckets.size == counts.size, "buckets size does not match count size. " +
    s"Buckets size is: ${buckets.size}, counts size is: ${counts.size}")
}

object ContinuousDistribution {
  val subtype = "continuous"
}

case class Statistics(
  median: String,
  max: String,
  min: String,
  mean: String,
  firstQuartile: String,
  thirdQuartile: String,
  outliers: Seq[String])
