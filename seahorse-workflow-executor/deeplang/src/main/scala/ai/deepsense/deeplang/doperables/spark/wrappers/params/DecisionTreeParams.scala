/**
 * Copyright 2016 deepsense.ai (CodiLime, Inc)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ai.deepsense.deeplang.doperables.spark.wrappers.params

import scala.language.reflectiveCalls

import ai.deepsense.deeplang.doperables.spark.wrappers.params.common._
import ai.deepsense.deeplang.params.Params

trait DecisionTreeParams
  extends Params
  with PredictorParams
  with HasCheckpointIntervalParam
  with HasSeedParam
  with HasMaxDepthParam
  with HasMaxBinsParam
  with HasMinInstancePerNodeParam
  with HasMinInfoGainParam
  with HasMaxMemoryInMBParam
  with HasCacheNodeIdsParam
