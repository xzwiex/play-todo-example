(->

  TodoService = ($http) ->

    fetch = -> $http.get '/todo/list'

    return {
      fetch : fetch
    }



  angular.module('todoApplication').factory 'todoService', TodoService
)()