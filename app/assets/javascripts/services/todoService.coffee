(->

  TodoService = ($http, $q) ->

    fetch = ->
      $http.get('/todo/list').then( (data) -> data.data )

    return {
      fetch : fetch
    }



  angular.module('todoApplication').factory 'todoService', TodoService
)()