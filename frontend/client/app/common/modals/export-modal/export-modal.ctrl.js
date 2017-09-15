/**
 * Copyright 2017, deepsense.ai
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
'use strict';

/* @ngInject */
function ExportModalController($uibModalInstance, $stateParams, WorkflowsApiClient) {

  const vm = this;

  vm.close = close;
  vm.download = download;

  function download() {
    /**
     * Iframe is needed because Firefox on download link click closes all WebSocket connection.
     * By using iframe, we remove the bug of "Reconnecting" message appearing while exporting workflow
     */
    $('body')
      .append(angular.element(`
          <iframe style="display: none" src="${WorkflowsApiClient.getDownloadWorkflowMethodUrl($stateParams.id, vm.includeDatasources)}"></iframe>
        `));
    close();
  }

  function close() {
    $uibModalInstance.dismiss();
  }
}

exports.inject = function(module) {
  module.controller('ExportModalController', ExportModalController);
};