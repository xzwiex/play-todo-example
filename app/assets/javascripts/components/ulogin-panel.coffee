class UloginUtls
  generateId : (prefix = 'ulogin_buttons')-> "__#{prefix}_#{Math.random().toString(36).substring(4)}"

  addScripts : ($q) ->

    defer = $q.defer()

    head = document.getElementsByTagName('head')[0]

    script = document.createElement('script')
    script.type = 'text/javascript'
    script.onload = ->  defer.resolve 'Loaded'
    script.src = '//ulogin.ru/js/ulogin.js';

    head.appendChild(script);

    defer.promise




UloginCtrl = ($log, $window, $q, $scope, $http) ->

  utils = new UloginUtls()

  $scope.elementId = utils.generateId()
  $scope.callbackName = utils.generateId 'uloginCallback'


  $window[$scope.callbackName] = (token) ->
    $http.get("/ulogin/#{token}").then (data) ->
      $window.location.reload()
    $log.log token

  @uloginInit = ->
    $window.uLogin.customInit @elementId

  if $window.uLogin
    @uloginInit()
  else
    utils.addScripts($q).then @uloginInit


UloginPanel = ->
  template: '<div ng-attr-id="{{elementId}}" class="ulogin-panel"' +
   'ng-attr-data-ulogin="display=panel;fields=email;verify=1;providers=google,vkontakte,twitter,facebook;redirect_uri=;callback={{callbackName}}"' +
   '></div>'
  controllerAs: 'ctrl'
  controller: UloginCtrl




angular.module('todoApplication').directive 'uloginPanel', UloginPanel
