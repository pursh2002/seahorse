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

package ai.deepsense.deeplang.inference.exceptions

import ai.deepsense.deeplang.exceptions.DeepLangException
import ai.deepsense.deeplang.params.selections.MultipleColumnSelection

case class SelectedIncorrectColumnsNumber(
    multipleColumnSelection: MultipleColumnSelection,
    selectedColumnsNames: Seq[String],
    modelsCount: Int)
  extends DeepLangException(
    s"The selection '$multipleColumnSelection' selects " +
      s"${selectedColumnsNames.size} column(s): ${selectedColumnsNames.mkString(", ")}. " +
      s"Expected to select $modelsCount column(s).")
