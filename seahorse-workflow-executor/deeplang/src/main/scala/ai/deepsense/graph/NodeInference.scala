/**
 * Copyright 2015 deepsense.ai (CodiLime, Inc)
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

package ai.deepsense.graph

import ai.deepsense.deeplang.inference.InferContext
import ai.deepsense.graph.DeeplangGraph.DeeplangNode

trait NodeInference {
  def inferKnowledge(
    node: DeeplangNode,
    context: InferContext,
    inputInferenceForNode: NodeInferenceResult): NodeInferenceResult

  def inputInferenceForNode(
    node: DeeplangNode,
    context: InferContext,
    graphKnowledge: GraphKnowledge,
    nodePredecessorsEndpoints: IndexedSeq[Option[Endpoint]]): NodeInferenceResult
}
