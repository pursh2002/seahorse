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

package ai.deepsense.commons.utils

import org.slf4j.{Logger, LoggerFactory}

object LoggerForCallerClass {

  def apply(): Logger = {
    // We use the third stack element; second is this method, first is .getStackTrace()
    val myCaller = Thread.currentThread().getStackTrace()(2)
    assert(myCaller.getMethodName() == "<init>", "Must be called in constructor")
    LoggerFactory.getLogger(myCaller.getClassName)
  }

}
