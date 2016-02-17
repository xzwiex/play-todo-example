
initialScriptLoaded = false
addScripts = ->
  if !initialScriptLoaded

    console.log 'Load init scripts'
    head = document.getElementsByTagName('head')[0]

    script = document.createElement('script')
    script.type = 'text/javascript'
    script.onload = -> initialScriptLoaded = true
    script.src = '//ulogin.ru/js/ulogin.js';

    head.appendChild(script);


UloginCtrl = ($log) ->
  $log.log 'Ulogin controller call'

UloginPanel = ->
  template : '<div class="ulogin-panel"></div>'
  link : (scope, element, attrs) ->
    console.log 'Ulogin link'
    addScripts()




angular.module('todoApplication').directive 'uloginPanel', UloginPanel
