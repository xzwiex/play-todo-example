package services

import com.google.inject.Inject
import play.api.http.{DefaultHttpFilters}
import play.filters.cors.CORSFilter

class Filters @Inject() (corsFilter: CORSFilter)
  extends DefaultHttpFilters(corsFilter) {

}

